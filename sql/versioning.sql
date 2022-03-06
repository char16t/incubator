create table t7(
  id bigserial primary key,
  prev_id bigint default null,
  entity_id bigserial not null,
  title varchar(128),
  created_at timestamp default now(),
  updated_at timestamp default null,
  deleted boolean default false
);

-- insert
insert into t7(title) values('AAA');
insert into t7(title) values('BBB');
insert into t7(title) values('CCC');

-- update
insert into t7(prev_id, entity_id, title)
select id, 2, 'BBB2' from t7 where entity_id=2 order by created_at desc limit 1;

-- update
insert into t7(prev_id, entity_id, title)
select id, 2, 'BBB3' from t7 where entity_id=2 order by created_at desc limit 1;

-- delete
update t7
set
deleted=true
from(select * from t7 where entity_id=2 and updated_at is null and deleted=false order by created_at desc limit 1) as last_row
where t7.id = last_row.id
;

-- select
select * from (select * from t7 where entity_id=2 and updated_at is null order by created_at desc limit 1) as found
where found.deleted=false;

