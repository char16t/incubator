-- see: https://www.postgresql.org/docs/14/sql-syntax-lexical.html

-- valid
SELECT * FROM MY_TABLE;
UPDATE MY_TABLE SET A = 5;
INSERT INTO MY_TABLE VALUES (3, 'hi there');

-- Key words and unquoted identifiers are case insensitive. Therefore:
UPDATE MY_TABLE SET A = 5;
-- can equivalently be written as:
uPDaTE my_TabLE SeT a = 5;
-- A convention often used is to write key words in upper case and names in lower case, e.g.:
UPDATE my_table SET a = 5;
UPDATE "my_table" SET "a" = 5;

-- This is a standard SQL comment
/* multiline comment
 * with nesting: /* nested block comment */
 */
 
