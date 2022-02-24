-- see: https://www.postgresql.org/docs/14/ddl-basics.html

CREATE TABLE my_first_table (
    first_column text,
    second_column integer
);

CREATE TABLE products (
    product_no integer,
    name text,
    price numeric
);

DROP TABLE my_first_table;
DROP TABLE IF EXISTS products;
