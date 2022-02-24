-- see: https://www.postgresql.org/docs/14/ddl-default.html

CREATE TABLE products (
    product_no integer,
    name text,
    price numeric DEFAULT 9.99
);

CREATE TABLE products (
    product_no integer DEFAULT nextval('products_product_no_seq'),
    /* ... */
);

CREATE TABLE products (
    product_no SERIAL,
    /* ... */
);
