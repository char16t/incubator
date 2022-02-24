-- see: https://www.postgresql.org/docs/14/dml-update.html

UPDATE products SET price = 10 WHERE price = 5;

UPDATE products SET price = price * 1.10;

UPDATE mytable SET a = 5, b = 3, c = 1 WHERE a > 0;
