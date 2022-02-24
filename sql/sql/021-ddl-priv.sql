-- see: https://www.postgresql.org/docs/14/ddl-priv.html

-- There are different kinds of privileges: SELECT, INSERT, UPDATE, DELETE, 
-- TRUNCATE, REFERENCES, TRIGGER, CREATE, CONNECT, TEMPORARY, EXECUTE, and 
-- USAGE. The privileges applicable to a particular object vary depending 
-- on the object's type (table, function, etc).

ALTER TABLE table_name OWNER TO new_owner;

GRANT UPDATE ON accounts TO joe;

REVOKE ALL ON accounts FROM PUBLIC;

GRANT SELECT ON mytable TO PUBLIC;
GRANT SELECT, UPDATE, INSERT ON mytable TO admin;
GRANT SELECT (col1), UPDATE (col1) ON mytable TO miriam_rw;

\dp mytable
/*
                                  Access privileges
 Schema |  Name   | Type  |   Access privileges   |   Column privileges   | Policies
--------+---------+-------+-----------------------+-----------------------+----------
 public | mytable | table | miriam=arwdDxt/miriam+| col1:                +|
        |         |       | =r/miriam            +|   miriam_rw=rw/miriam |
        |         |       | admin=arw/miriam      |                       |
(1 row)
*/
