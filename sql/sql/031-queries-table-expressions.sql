-- see: https://www.postgresql.org/docs/14/queries-table-expressions.html

-- The FROM Clause:
--
--     FROM table_reference [, table_reference [, ...]]
--

-- Joined Tables:
-- 
--    T1 join_type T2 [ join_condition ]
--

--
--    T1 CROSS JOIN T2
--
-- For every possible combination of rows from T1 and T2
-- (i.e., a Cartesian product), the joined table will contain 
-- a row consisting of all columns in T1 followed by all columns
-- in T2. If the tables have N and M rows respectively, the 
-- joined table will have N * M rows

--
--    T1 { [INNER] | { LEFT | RIGHT | FULL } [OUTER] } JOIN T2 ON boolean_expression
--    T1 { [INNER] | { LEFT | RIGHT | FULL } [OUTER] } JOIN T2 USING ( join column list )
--    T1 NATURAL { [INNER] | { LEFT | RIGHT | FULL } [OUTER] } JOIN T2

-- t1
/*
 num | name
-----+------
   1 | a
   2 | b
   3 | c
*/

-- t2
/*
 num | value
-----+-------
   1 | xxx
   3 | yyy
   5 | zzz
*/

SELECT * FROM t1 CROSS JOIN t2;
/*
 num | name | num | value
-----+------+-----+-------
   1 | a    |   1 | xxx
   1 | a    |   3 | yyy
   1 | a    |   5 | zzz
   2 | b    |   1 | xxx
   2 | b    |   3 | yyy
   2 | b    |   5 | zzz
   3 | c    |   1 | xxx
   3 | c    |   3 | yyy
   3 | c    |   5 | zzz
(9 rows)
*/

SELECT * FROM t1 INNER JOIN t2 ON t1.num = t2.num;
/*
 num | name | num | value
-----+------+-----+-------
   1 | a    |   1 | xxx
   3 | c    |   3 | yyy
(2 rows)
*/

SELECT * FROM t1 INNER JOIN t2 USING (num);
/*
 num | name | value
-----+------+-------
   1 | a    | xxx
   3 | c    | yyy
(2 rows)
*/

SELECT * FROM t1 NATURAL INNER JOIN t2;
/*
 num | name | value
-----+------+-------
   1 | a    | xxx
   3 | c    | yyy
(2 rows)
*/

SELECT * FROM t1 LEFT JOIN t2 ON t1.num = t2.num;
/*
 num | name | num | value
-----+------+-----+-------
   1 | a    |   1 | xxx
   2 | b    |     |
   3 | c    |   3 | yyy
(3 rows)
*/

SELECT * FROM t1 LEFT JOIN t2 USING (num);
/*
 num | name | value
-----+------+-------
   1 | a    | xxx
   2 | b    |
   3 | c    | yyy
(3 rows)
*/

SELECT * FROM t1 RIGHT JOIN t2 ON t1.num = t2.num;
/*
 num | name | num | value
-----+------+-----+-------
   1 | a    |   1 | xxx
   3 | c    |   3 | yyy
     |      |   5 | zzz
(3 rows)
*/

SELECT * FROM t1 FULL JOIN t2 ON t1.num = t2.num;
/*
 num | name | num | value
-----+------+-----+-------
   1 | a    |   1 | xxx
   2 | b    |     |
   3 | c    |   3 | yyy
     |      |   5 | zzz
(4 rows)
*/

SELECT * FROM t1 LEFT JOIN t2 ON t1.num = t2.num AND t2.value = 'xxx';
/*
 num | name | num | value
-----+------+-----+-------
   1 | a    |   1 | xxx
   2 | b    |     |
   3 | c    |     |
(3 rows)
*/

SELECT * FROM t1 LEFT JOIN t2 ON t1.num = t2.num WHERE t2.value = 'xxx';
/*
 num | name | num | value
-----+------+-----+-------
   1 | a    |   1 | xxx
(1 row)
*/

SELECT * FROM some_very_long_table_name s JOIN another_fairly_long_name a ON s.id = a.num;

SELECT * FROM my_table AS m WHERE my_table.a > 5;    -- wrong

SELECT * FROM people AS mother JOIN people AS child ON mother.id = child.mother_id;

-- Subqueries

FROM (SELECT * FROM table1) AS alias_name;

FROM (VALUES ('anne', 'smith'), ('bob', 'jones'), ('joe', 'blow'))
     AS names(first, last);
     
-- Table Functions
CREATE TABLE foo (fooid int, foosubid int, fooname text);

CREATE FUNCTION getfoo(int) RETURNS SETOF foo AS $$
    SELECT * FROM foo WHERE fooid = $1;
$$ LANGUAGE SQL;

SELECT * FROM getfoo(1) AS t1;

SELECT * FROM foo
    WHERE foosubid IN (
                        SELECT foosubid
                        FROM getfoo(foo.fooid) z
                        WHERE z.fooid = foo.fooid
                      );

CREATE VIEW vw_getfoo AS SELECT * FROM getfoo(1);

SELECT * FROM vw_getfoo;

SELECT *
    FROM dblink('dbname=mydb', 'SELECT proname, prosrc FROM pg_proc')
      AS t1(proname name, prosrc text)
    WHERE proname LIKE 'bytea%';
    
SELECT *
FROM ROWS FROM
    (
        json_to_recordset('[{"a":40,"b":"foo"},{"a":"100","b":"bar"}]')
            AS (a INTEGER, b TEXT),
        generate_series(1, 3)
    ) AS x (p, q, s)
ORDER BY p;
/*
  p  |  q  | s
-----+-----+---
  40 | foo | 1
 100 | bar | 2
     |     | 3
*/

-- LATERAL Subqueries
SELECT * FROM foo, LATERAL (SELECT * FROM bar WHERE bar.id = foo.bar_id) ss;

SELECT * FROM foo, bar WHERE bar.id = foo.bar_id;

SELECT p1.id, p2.id, v1, v2
FROM polygons p1, polygons p2,
     LATERAL vertices(p1.poly) v1,
     LATERAL vertices(p2.poly) v2
WHERE (v1 <-> v2) < 10 AND p1.id != p2.id;

SELECT p1.id, p2.id, v1, v2
FROM polygons p1 CROSS JOIN LATERAL vertices(p1.poly) v1,
     polygons p2 CROSS JOIN LATERAL vertices(p2.poly) v2
WHERE (v1 <-> v2) < 10 AND p1.id != p2.id;

SELECT m.name
FROM manufacturers m LEFT JOIN LATERAL get_product_names(m.id) pname ON true
WHERE pname IS NULL;

-- The WHERE Clause
SELECT ... FROM fdt WHERE c1 > 5;
SELECT ... FROM fdt WHERE c1 IN (1, 2, 3);
SELECT ... FROM fdt WHERE c1 IN (SELECT c1 FROM t2);
SELECT ... FROM fdt WHERE c1 IN (SELECT c3 FROM t2 WHERE c2 = fdt.c1 + 10);
SELECT ... FROM fdt WHERE c1 BETWEEN (SELECT c3 FROM t2 WHERE c2 = fdt.c1 + 10) AND 100;
SELECT ... FROM fdt WHERE EXISTS (SELECT c1 FROM t2 WHERE c2 > fdt.c1);

-- The GROUP BY and HAVING Clauses
SELECT * FROM test1;
/*
 x | y
---+---
 a | 3
 c | 2
 b | 5
 a | 1
(4 rows)
*/

SELECT x FROM test1 GROUP BY x;
/*
 x
---
 a
 b
 c
(3 rows)
*/

SELECT x, sum(y) FROM test1 GROUP BY x;
/*
 x | sum
---+-----
 a |   4
 b |   5
 c |   2
(3 rows)
*/

SELECT product_id, p.name, (sum(s.units) * p.price) AS sales
    FROM products p LEFT JOIN sales s USING (product_id)
    GROUP BY product_id, p.name, p.price;

SELECT x, sum(y) FROM test1 GROUP BY x HAVING sum(y) > 3;
/*
 x | sum
---+-----
 a |   4
 b |   5
(2 rows)
*/

SELECT x, sum(y) FROM test1 GROUP BY x HAVING x < 'c';
/*
 x | sum
---+-----
 a |   4
 b |   5
(2 rows)
*/

SELECT product_id, p.name, (sum(s.units) * (p.price - p.cost)) AS profit
    FROM products p LEFT JOIN sales s USING (product_id)
    WHERE s.date > CURRENT_DATE - INTERVAL '4 weeks'
    GROUP BY product_id, p.name, p.price, p.cost
    HAVING sum(p.price * s.units) > 5000;
    
-- GROUPING SETS, CUBE, and ROLLUP
SELECT * FROM items_sold;
/*
 brand | size | sales
-------+------+-------
 Foo   | L    |  10
 Foo   | M    |  20
 Bar   | M    |  15
 Bar   | L    |  5
(4 rows)
*/

SELECT brand, size, sum(sales) FROM items_sold GROUP BY GROUPING SETS ((brand), (size), ());
/*
 brand | size | sum
-------+------+-----
 Foo   |      |  30
 Bar   |      |  20
       | L    |  15
       | M    |  35
       |      |  50
(5 rows)
*/

CUBE ( a, b, c );
-- is equivalent to
GROUPING SETS (
    ( a, b, c ),
    ( a, b    ),
    ( a,    c ),
    ( a       ),
    (    b, c ),
    (    b    ),
    (       c ),
    (         )
);


CUBE ( (a, b), (c, d) );
-- is equivalent to
GROUPING SETS (
    ( a, b, c, d ),
    ( a, b       ),
    (       c, d ),
    (            )
);


ROLLUP ( a, (b, c), d );
-- is equivalent to
GROUPING SETS (
    ( a, b, c, d ),
    ( a, b, c    ),
    ( a          ),
    (            )
);


GROUP BY a, CUBE (b, c), GROUPING SETS ((d), (e));
-- is equivalent to
GROUP BY GROUPING SETS (
    (a, b, c, d), (a, b, c, e),
    (a, b, d),    (a, b, e),
    (a, c, d),    (a, c, e),
    (a, d),       (a, e)
);


GROUP BY ROLLUP (a, b), ROLLUP (a, c);
-- is equivalent to
GROUP BY GROUPING SETS (
    (a, b, c),
    (a, b),
    (a, b),
    (a, c),
    (a),
    (a),
    (a, c),
    (a),
    ()
);


GROUP BY DISTINCT ROLLUP (a, b), ROLLUP (a, c);
GROUP BY GROUPING SETS (
    (a, b, c),
    (a, b),
    (a, c),
    (a),
    ()
);
