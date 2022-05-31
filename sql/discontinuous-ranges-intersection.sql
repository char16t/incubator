-- Oracle 18c

-- Task
-- Find discontinuous ranges intersection
-- a = [[0, 2], [5, 10], [13, 23], [24, 25]]
-- b = [[1, 5], [8, 12], [15, 18], [20, 24]]


with ta as (
select 0 as "start", 2 as "end" from dual union all
select 5 as "start", 10 as "end" from dual union all
select 13 as "start", 23 as "end" from dual union all
select 24 as "start", 25 as "end" from dual
),
tb as (
select 1 as "start", 5 as "end" from dual union all
select 8 as "start", 12 as "end" from dual union all
select 15 as "start", 18 as "end" from dual union all
select 20 as "start", 24 as "end" from dual
) 
select 
  greatest(ta."start", tb."start") as "start",
  least(ta."end", tb."end") as "end"
from ta, tb
where
  greatest(ta."start", tb."start") <= least(ta."end", tb."end")
;
