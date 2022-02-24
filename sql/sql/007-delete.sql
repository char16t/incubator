-- see: https://www.postgresql.org/docs/14/tutorial-delete.html

DELETE FROM weather WHERE city = 'Hayward';

SELECT * FROM weather;
/*
     city      | temp_lo | temp_hi | prcp |    date
---------------+---------+---------+------+------------
 San Francisco |      46 |      50 | 0.25 | 1994-11-27
 San Francisco |      41 |      55 |    0 | 1994-11-29
(2 rows)
*/

-- Delete all rows
-- DELETE FROM tablename;
