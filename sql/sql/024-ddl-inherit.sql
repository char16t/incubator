-- see: https://www.postgresql.org/docs/14/ddl-inherit.html

CREATE TABLE cities (
    name            text,
    population      float,
    elevation       int     -- in feet
);

CREATE TABLE capitals (
    state           char(2)
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
*/

SELECT name, elevation
    FROM ONLY cities
    WHERE elevation > 500;
/*
   name    | elevation
-----------+-----------
 Las Vegas |      2174
 Mariposa  |      1953
*/

SELECT name, elevation
    FROM cities*
    WHERE elevation > 500;

SELECT c.tableoid, c.name, c.elevation
FROM cities c
WHERE c.elevation > 500;
/*
 tableoid |   name    | elevation
----------+-----------+-----------
   139793 | Las Vegas |      2174
   139793 | Mariposa  |      1953
   139798 | Madison   |       845
*/

SELECT p.relname, c.name, c.elevation
FROM cities c, pg_class p
WHERE c.elevation > 500 AND c.tableoid = p.oid;
/*
 relname  |   name    | elevation
----------+-----------+-----------
 cities   | Las Vegas |      2174
 cities   | Mariposa  |      1953
 capitals | Madison   |       845
*/

SELECT c.tableoid::regclass, c.name, c.elevation
FROM cities c
WHERE c.elevation > 500;

/*
Inheritance does not automatically propagate data 
from INSERT or COPY commands to other tables in the
inheritance hierarchy. In our example, the following
INSERT statement will fail
*/
INSERT INTO cities (name, population, elevation, state)
VALUES ('Albany', NULL, NULL, 'NY');
