-- see: https://www.postgresql.org/docs/14/ddl-generated-columns.html

-- A generated column is a special column that is always computed 
-- from other columns. Thus, it is for columns what a view is for tables.
-- There are two kinds of generated columns: stored and virtual. A stored 
-- generated column is computed when it is written (inserted or updated) and
-- occupies storage as if it were a normal column. A virtual generated column 
-- occupies no storage and is computed when it is read. Thus, a virtual 
-- generated column is similar to a view and a stored generated column is 
-- similar to a materialized view (except that it is always updated 
-- automatically). PostgreSQL currently implements only stored generated columns.

CREATE TABLE people (
    /* ..., */
    height_cm numeric,
    height_in numeric GENERATED ALWAYS AS (height_cm / 2.54) STORED
);
