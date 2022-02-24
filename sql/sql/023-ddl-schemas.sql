-- see: https://www.postgresql.org/docs/14/ddl-schemas.html

CREATE SCHEMA myschema;

-- Create a schema owned by someone else
CREATE SCHEMA schema_name AUTHORIZATION user_name;

CREATE TABLE myschema.mytable (
 /* ... */
);

-- Drop a schema if it's empty (all objects in it have been dropped)
DROP SCHEMA myschema;

-- Drop a schema including all contained objects
DROP SCHEMA myschema CASCADE;
