-- see: https://www.postgresql.org/docs/14/dml-returning.html

CREATE TABLE users (firstname text, lastname text, id serial primary key);
INSERT INTO users (firstname, lastname) VALUES ('Joe', 'Cool') RETURNING id;

UPDATE products SET price = price * 1.10
  WHERE price <= 99.99
  RETURNING name, price AS new_price;
  
DELETE FROM products
  WHERE obsoletion_date = 'today'
  RETURNING *;
