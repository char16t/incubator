-- see: https://www.postgresql.org/docs/14/ddl-alter.html

-- Adding a Column
ALTER TABLE products ADD COLUMN description text;
ALTER TABLE products ADD COLUMN description text CHECK (description <> '');

-- Removing a Column
ALTER TABLE products DROP COLUMN description;
ALTER TABLE products DROP COLUMN description CASCADE;

-- Adding a Constraint
ALTER TABLE products ADD CHECK (name <> '');
ALTER TABLE products ADD CONSTRAINT some_name UNIQUE (product_no);
ALTER TABLE products ADD FOREIGN KEY (product_group_id) REFERENCES product_groups;
ALTER TABLE products ALTER COLUMN product_no SET NOT NULL;

-- Removing a Constraint
ALTER TABLE products DROP CONSTRAINT some_name;
ALTER TABLE products ALTER COLUMN product_no DROP NOT NULL;

-- Changing a Column's Default Value
ALTER TABLE products ALTER COLUMN price SET DEFAULT 7.77;
ALTER TABLE products ALTER COLUMN price DROP DEFAULT;

-- Changing a Column's Data Type
ALTER TABLE products ALTER COLUMN price TYPE numeric(10,2);

-- Renaming a Column
ALTER TABLE products RENAME COLUMN product_no TO product_number;

-- Renaming a Table
ALTER TABLE products RENAME TO items;
