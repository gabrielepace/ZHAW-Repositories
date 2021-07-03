-- delete old table if it exits
drop table if exists picture;

-- create table 'picture'
create table picture (
    id          serial       PRIMARY KEY,
    date        timestamp    DEFAULT CURRENT_TIMESTAMP,
    longitude   float        DEFAULT NULL,
    latitude    float        DEFAULT NULL,
    altitude    float        DEFAULT NULL,
    title       varchar(200) DEFAULT NULL,
    comment     text         DEFAULT NULL,
    url         varchar(200) DEFAULT NULL
) without OIDS;

-- fill table 'picture' with some demo data
INSERT INTO picture (date, longitude, latitude, altitude, title, comment, url)
VALUES
  ('2014-02-25 00:00:00',47.12,  8.12,  422.12, 'kja','sad', 'http://endingcampo.files.wordpress.com/2011/10/coder__smaller__by_dwerg85.png'),
  ('2014-02-26 00:00:00',123.12, 8.12,  422.12, 'df','asdjo', 'http://endingcampo.files.wordpress.com/2011/10/coder__smaller__by_dwerg85.png'),
  ('2014-02-27 00:00:00',47.1233,8.12,  422.12, 'coder','in progess', 'http://endingcampo.files.wordpress.com/2011/10/coder__smaller__by_dwerg85.png'),
  ('2014-03-25 00:00:00',48.2134,8.2413,422.12, 'code monkey','a funny one', 'http://alltech-nologic.com/wp-content/uploads/2012/02/code-monkey.jpg'),
  ('2014-04-01 00:00:00',47.12,  8.12,  422.12, 'code monkey','monkey', 'http://alltech-nologic.com/wp-content/uploads/2012/02/code-monkey.jpg'),
  ('2014-04-02 00:00:00',33.33,  22.22, 422.12, 'test1','khbvlkwpäjpbdvpi', 'http://endingcampo.files.wordpress.com/2011/10/coder__smaller__by_dwerg85.png');