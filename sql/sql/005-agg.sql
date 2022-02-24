-- see: https://www.postgresql.org/docs/14/tutorial-agg.html

SELECT max(temp_lo) FROM weather;
/*
 max
-----
  46
(1 row)
*/

SELECT city FROM weather
    WHERE temp_lo = (SELECT max(temp_lo) FROM weather);
/*
     city
---------------
 San Francisco
(1 row)
*/

SELECT city, max(temp_lo)
    FROM weather
    GROUP BY city;
/*
     city      | max
---------------+-----
 Hayward       |  37
 San Francisco |  46
(2 rows)
*/

SELECT city, max(temp_lo)
    FROM weather
    GROUP BY city
    HAVING max(temp_lo) < 40;
/*
  city   | max
---------+-----
 Hayward |  37
(1 row)
*/

SELECT city, max(temp_lo)
    FROM weather
    WHERE city LIKE 'S%'
    GROUP BY city
    HAVING max(temp_lo) < 40;
    
