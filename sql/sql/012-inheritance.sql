-- see: https://www.postgresql.org/docs/14/tutorial-inheritance.html

CREATE TABLE cities (
  name       text,
  population real,
  elevation  int     -- (in ft)
);

CREATE TABLE capitals (
  state      char(2) UNIQUE NOT NULL
) INHERITS (cities);

SELECT name, elevation
  FROM cities
  WHERE elevation > 500;
/*
   name    | elevation
-----------+-----------
 Las Vegas |      2174
 Mariposa  |      1953
 Madison   |       845
(3 rows)
*/

SELECT name, elevation
    FROM ONLY cities
    WHERE elevation > 500;
/*
   name    | elevation
-----------+-----------
 Las Vegas |      2174
 Mariposa  |      1953
(2 rows)
*/
