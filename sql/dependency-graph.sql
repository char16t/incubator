create table nodes (
	id    bigserial not null primary key,
	name  text      not null,
	state smallint  not null default 0 -- 0 - up-to-date, 1 - out-to-date
);

create table edges (
	a bigint not null references nodes(id),
	b bigint not null references nodes(id),
	primary key (a, b)
);

create or replace function f_nodes()
returns trigger as $$
	begin
		update nodes
		set state = 1
		where nodes.id in (
			with recursive graph_cte (a, b) as (
				select a, b
				from edges
				where b = old.id
				union all 
				select nxt.a, nxt.b
				from edges nxt
					join graph_cte prv on nxt.b = prv.a
			)
			select distinct g.a
			from graph_cte g
		);
		return new;
	end;
$$ language plpgsql;

create or replace trigger t_nodes after update
on nodes
for each row
when (old.state != 0 or new.state != 1)
execute function f_nodes();


insert into nodes(name) values 
	('A'), -- 1
	('B'), -- 2
	('C'), -- 3
	('D'), -- 4
	('E'), -- 5
	('F')  -- 6
;
insert into edges(a, b) values
	(1, 2), -- (A, B)
	(1, 3), -- (A, C)
	(2, 4), -- (B, D)
	(3, 4), -- (C, D)
	(4, 5), -- (D, E)
	(4, 6)  -- (D, F)
;


-- Вершины графа
select * from nodes;

-- Меняем "состояние" вершины и помечаем все
-- транзитивно зависящие от неё как "устаревшие"
update nodes set name = 'E (updated)' where id = 5;
