create table processes (
  id bigserial not null,
  parent_id bigint,
  state varchar(128) not null,
  model jsonb not null default '{}'::jsonb,
  primary key (id)
);
comment on table processes is 'Processes';
comment on column processes.id is 'Process ID';
comment on column processes.parent_id is 'ID of the parent process';
comment on column processes.state is 'One of the possible process states (http://faculty.salina.k-state.edu/tim/ossg/Process/p_states.html)';
comment on column processes.state is 'Process model described as json';

CREATE FUNCTION p_start(payload jsonb, parent_pid bigint default null)
RETURNS BIGINT AS $$
DECLARE
  ret_id BIGINT;
BEGIN
  INSERT INTO processes(parent_id, state, model) 
  VALUES (parent_pid, 'READY', payload) 
  RETURNING id INTO ret_id;
  RETURN ret_id;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION p_schedule(pid bigint)
RETURNS TABLE (id bigint) AS $$
BEGIN
  RETURN QUERY
  UPDATE processes 
  SET state = 'RUNNING' 
  FROM (
    with recursive cte as (
       select p.id, p.model, 1 AS lvl
       from   processes a
       join   processes p ON p.parent_id = a.id
       where  a.id = pid

       union all
       select p.id, p.model, c.lvl + 1
       from   cte    c
       join   processes p ON  p.parent_id = c.id
   )
   select cte.id
   from   cte
   order  by lvl
  ) as descendants
  WHERE processes.id = descendants.id
  RETURNING processes.id;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION p_request(pid bigint)
RETURNS TABLE (id bigint) AS $$
BEGIN
  RETURN QUERY
  UPDATE processes 
  SET state = 'BLOCKED' 
  FROM (
    with recursive cte as (
       select p.id, p.model, 1 AS lvl
       from   processes a
       join   processes p ON p.parent_id = a.id
       where  a.id = pid

       union all
       select p.id, p.model, c.lvl + 1
       from   cte    c
       join   processes p ON  p.parent_id = c.id
   )
   select cte.id
   from   cte
   order  by lvl
  ) as descendants
  WHERE processes.id = descendants.id
  RETURNING processes.id;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION p_allocate(pid bigint)
RETURNS TABLE (id bigint) AS $$
BEGIN
  RETURN QUERY
  UPDATE processes 
  SET state = 'READY' 
  FROM (
    with recursive cte as (
       select p.id, p.model, 1 AS lvl
       from   processes a
       join   processes p ON p.parent_id = a.id
       where  a.id = pid

       union all
       select p.id, p.model, c.lvl + 1
       from   cte    c
       join   processes p ON  p.parent_id = c.id
   )
   select cte.id
   from   cte
   order  by lvl
  ) as descendants
  WHERE processes.id = descendants.id
  RETURNING processes.id;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION p_done(pid bigint)
RETURNS TABLE (id bigint) AS $$
BEGIN
  RETURN QUERY
  UPDATE processes 
  SET state = 'FINISHED' 
  FROM (
    with recursive cte as (
       select p.id, p.model, 1 AS lvl
       from   processes a
       join   processes p ON p.parent_id = a.id
       where  a.id = pid

       union all
       select p.id, p.model, c.lvl + 1
       from   cte    c
       join   processes p ON  p.parent_id = c.id
   )
   select cte.id
   from   cte
   order  by lvl
  ) as descendants
  WHERE processes.id = descendants.id
  RETURNING processes.id;
END;
$$ LANGUAGE plpgsql;


select p_start('{ "a": "b" }'::jsonb) as pid;
select p_start('{ "a": "c" }'::jsonb) as pid;
select p_start('{ "a": "d" }'::jsonb) as pid;
select p_start('{ "a": "e" }'::jsonb, 2) as pid;
select p_start('{ "a": "f" }'::jsonb, 2) as pid;
select p_start('{ "a": "g" }'::jsonb, 2) as pid;
select p_start('{ "a": "e" }'::jsonb, 4) as pid;
select p_start('{ "a": "f" }'::jsonb, 5) as pid;
select p_start('{ "a": "g" }'::jsonb, 6) as pid;
select p_start('{ "a": "h" }'::jsonb, 4) as pid;
select p_start('{ "a": "i" }'::jsonb, 5) as pid;
select p_start('{ "a": "j" }'::jsonb, 6) as pid;
select p_start('{ "b": "h" }'::jsonb, 7) as pid;
select p_start('{ "b": "i" }'::jsonb, 8) as pid;
select p_start('{ "b": "j" }'::jsonb, 9) as pid;


select p_schedule(2) as pid;
select p_schedule(3) as pid;
select p_request(3) as pid;
select p_allocate(3) as pid;
select p_schedule(3) as pid;
select p_done(3) as pid;

select * from processes;
