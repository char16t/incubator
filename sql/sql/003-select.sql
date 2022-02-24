SELECT * FROM weather;

SELECT city, temp_lo, temp_hi, prcp, date FROM weather;

SELECT city, (temp_hi+temp_lo)/2 AS temp_avg, date FROM weather;

SELECT * FROM weather
    WHERE city = 'San Francisco' AND prcp > 0.0;

SELECT * FROM weather
    ORDER BY city;

SELECT * FROM weather
    ORDER BY city, temp_lo;

SELECT DISTINCT city
    FROM weather;
