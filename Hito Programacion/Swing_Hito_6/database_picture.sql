create database picture;
use picture;
create table pictures(
	pictureid int primary key,
    title VARCHAR(30),
    picturedate date,
    picfile VARCHAR(100),
    visits int,
    photographerid int,
    FOREIGN KEY (photographerid) REFERENCES photographers(photographerid)
);

create table photographers(
	photographerid int primary key,
    picname VARCHAR(100),
    awarded boolean
);

INSERT INTO pictures (pictureid, title, picturedate, picfile, visits, photographerid)
VALUES (1, 'ansealadams1', '1970-01-01', 'ansealdams1.jpg', 256, 1001);

INSERT INTO pictures (pictureid, title, picturedate,  picfile, visits, photographerid)
VALUES (2, 'ansealadams2', '1900-01-01', 'ansealdams2.jpg', 123, 1001);

INSERT INTO pictures (pictureid, title, picturedate,  picfile, visits, photographerid)
VALUES (3, 'rothklo1', '2000-12-12', 'rothko1.jpg', 789, 1002);

INSERT INTO pictures (pictureid, title, picturedate,  picfile, visits, photographerid)
VALUES (4, 'vangogh1', '1995-05-05', 'vangogh1.jpg', 789, 1003);

INSERT INTO pictures (pictureid, title, picturedate,  picfile, visits, photographerid)
VALUES (5, 'vangogh2', '1950-04-04', 'vangogh2.jpg', 789, 1003);

INSERT INTO photographers (photographerid, picname, awarded)
VALUES (1001, 'Ansel Adams', true);

INSERT INTO photographers (photographerid, picname, awarded)
VALUES (1002, 'Roth Klo', false);

INSERT INTO photographers (photographerid, picname, awarded)
VALUES (1003, 'Van Gogh', true);