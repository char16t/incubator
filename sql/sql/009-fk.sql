-- see: https://www.postgresql.org/docs/14/tutorial-fk.html

DROP TABLE cities;
DROP TABLE weather;

CREATE TABLE cities (
        name     varchar(80) primary key,
        location point
);

CREATE TABLE weather (
        city      varchar(80) references cities(name),
        temp_lo   int,
        temp_hi   int,
        prcp      real,
        date      date
);

INSERT INTO weather VALUES ('Berkeley', 45, 53, 0.0, '1994-11-28');
/*
ERROR:  insert or update on table "weather" violates foreign key constraint "weather_city_fkey"
DETAIL:  Key (city)=(Berkeley) is not present in table "cities".
*/
