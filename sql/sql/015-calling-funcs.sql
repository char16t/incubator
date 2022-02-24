-- see: https://www.postgresql.org/docs/14/sql-syntax-calling-funcs.html

CREATE FUNCTION concat_lower_or_upper(a text, b text, uppercase boolean DEFAULT false)
RETURNS text
AS
$$
 SELECT CASE
        WHEN $3 THEN UPPER($1 || ' ' || $2)
        ELSE LOWER($1 || ' ' || $2)
        END;
$$
LANGUAGE SQL IMMUTABLE STRICT;

SELECT concat_lower_or_upper('Hello', 'World', true);
/*
 concat_lower_or_upper 
-----------------------
 HELLO WORLD
(1 row)

*/

SELECT concat_lower_or_upper('Hello', 'World');
/*
 concat_lower_or_upper 
-----------------------
 hello world
(1 row)
*/

SELECT concat_lower_or_upper(a => 'Hello', b => 'World');
/*
 concat_lower_or_upper 
-----------------------
 hello world
(1 row)
*/

SELECT concat_lower_or_upper(a => 'Hello', b => 'World', uppercase => true);
/*
 concat_lower_or_upper 
-----------------------
 HELLO WORLD
(1 row)
*/

SELECT concat_lower_or_upper(a => 'Hello', uppercase => true, b => 'World');
/*
 concat_lower_or_upper 
-----------------------
 HELLO WORLD
(1 row)
*/

-- An older syntax based on ":=" is supported for backward compatibility:
SELECT concat_lower_or_upper(a := 'Hello', uppercase := true, b := 'World');
/*
 concat_lower_or_upper 
-----------------------
 HELLO WORLD
(1 row)
*/

-- The mixed notation combines positional and named notation. However, 
-- as already mentioned, named arguments cannot precede positional arguments.
SELECT concat_lower_or_upper('Hello', 'World', uppercase => true);
/*
 concat_lower_or_upper 
-----------------------
 HELLO WORLD
(1 row)
*/
