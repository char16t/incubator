-- see: https://www.postgresql.org/docs/14/tutorial-join.html

SELECT * FROM weather JOIN cities ON city = name;

SELECT weather.city, weather.temp_lo, weather.temp_hi,
       weather.prcp, weather.date, cities.location
    FROM weather JOIN cities ON weather.city = cities.name;
    
SELECT *
    FROM weather, cities
    WHERE city = name;
    
SELECT *
    FROM weather LEFT OUTER JOIN cities ON weather.city = cities.name;
/*
     city      | temp_lo | temp_hi | prcp |    date    |     name      | location
---------------+---------+---------+------+------------+---------------+-----------
 Hayward       |      37 |      54 |      | 1994-11-29 |               |
 San Francisco |      46 |      50 | 0.25 | 1994-11-27 | San Francisco | (-194,53)
 San Francisco |      43 |      57 |    0 | 1994-11-29 | San Francisco | (-194,53)
(3 rows)
*/



