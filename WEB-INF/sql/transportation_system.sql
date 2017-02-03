DROP DATABASE IF EXISTS transportation_system;
 
CREATE DATABASE transportation_system DEFAULT CHARACTER SET 'utf8';
 
USE transportation_system;
 
CREATE TABLE buses
(
  bus_id int unsigned NOT NULL auto_increment,
  bus_number int NOT NULL,
  all_seats int NOT NULL,
  free_seats int NOT NULL,
  PRIMARY KEY (bus_id)
) engine=InnoDB;
 
CREATE TABLE stations
(
  station_id int unsigned NOT NULL auto_increment,
  name varchar(255) NOT NULL,
  PRIMARY KEY (station_id)
) engine=InnoDB;

CREATE TABLE passengers
(
  passenger_id int unsigned NOT NULL auto_increment,
  name varchar(255) NOT NULL,
  surname varchar(255) NOT NULL,
  date_of_birth date NOT NULL,
  PRIMARY KEY (passenger_id)
) engine=InnoDB;

CREATE TABLE schedule
(
  schedule_id int unsigned NOT NULL auto_increment,
  arrival_time DATETIME NOT NULL,
  bus_id int unsigned NOT NULL,
  station_id int unsigned NOT NULL,
  PRIMARY KEY (schedule_id),
  FOREIGN KEY (bus_id) REFERENCES buses(bus_id),
  FOREIGN KEY (station_id) REFERENCES stations(station_id)
) engine=InnoDB;

CREATE TABLE tickets
(
  ticket_id int unsigned NOT NULL auto_increment,
  bus_id int unsigned NOT NULL,
  station_id int unsigned NOT NULL,
  passenger_id int unsigned NOT NULL,
  arrival_time DATETIME NOT NULL,
  PRIMARY KEY (ticket_id),
  FOREIGN KEY (bus_id) REFERENCES buses(bus_id),
  FOREIGN KEY (station_id) REFERENCES stations(station_id),
  FOREIGN KEY (passenger_id) REFERENCES passengers(passenger_id)
) engine=InnoDB;

CREATE TABLE bus_station
(
  bus_id int unsigned NOT NULL,
  station_id int unsigned NOT NULL,
  PRIMARY KEY (bus_id, station_id),
  FOREIGN KEY (bus_id) REFERENCES buses(bus_id),
  FOREIGN KEY (station_id) REFERENCES stations(station_id)
) engine=InnoDB;
 
set names 'utf8';

# Add 5 buses, 5 stations, 1 passenger, 0 tickets, 10 schedules 
INSERT INTO buses (bus_number, all_seats, free_seats) 
VALUES (1, 15, 15);
INSERT INTO buses (bus_number, all_seats, free_seats) 
VALUES (2, 20, 20);
INSERT INTO buses (bus_number, all_seats, free_seats) 
VALUES (3, 21, 21);
INSERT INTO buses (bus_number, all_seats, free_seats) 
VALUES (4, 5, 5);
INSERT INTO buses (bus_number, all_seats, free_seats) 
VALUES (5, 10, 10);

INSERT INTO stations (name) 
VALUES ("station1");
INSERT INTO stations (name) 
VALUES ("station2");
INSERT INTO stations (name) 
VALUES ("station3");
INSERT INTO stations (name) 
VALUES ("station4");
INSERT INTO stations (name) 
VALUES ("station5");

INSERT INTO passengers (name, surname, date_of_birth) 
VALUES ("name1", "surname1", '1996-08-15');
INSERT INTO passengers (name, surname, date_of_birth) 
VALUES ("name2", "surname2", '1986-08-18');

INSERT INTO schedule (arrival_time, bus_id, station_id) 
VALUES ('2017-02-01 12:15:00', 1, 1);
INSERT INTO schedule (arrival_time, bus_id, station_id) 
VALUES ('2017-02-01 13:25:00', 1, 2);
INSERT INTO schedule (arrival_time, bus_id, station_id) 
VALUES ('2017-02-01 12:10:00', 2, 1);
INSERT INTO schedule (arrival_time, bus_id, station_id) 
VALUES ('2017-02-01 13:30:00', 2, 2);
INSERT INTO schedule (arrival_time, bus_id, station_id) 
VALUES ('2017-02-01 11:00:00', 3, 3);
INSERT INTO schedule (arrival_time, bus_id, station_id) 
VALUES ('2017-02-01 12:15:00', 3, 4);
INSERT INTO schedule (arrival_time, bus_id, station_id) 
VALUES ('2017-02-01 13:33:00', 4, 1);
INSERT INTO schedule (arrival_time, bus_id, station_id) 
VALUES ('2017-02-01 14:15:00', 4, 2);
INSERT INTO schedule (arrival_time, bus_id, station_id) 
VALUES ('2017-02-01 15:00:00', 4, 3);
INSERT INTO schedule (arrival_time, bus_id, station_id) 
VALUES ('2017-02-01 15:15:00', 4, 4);

INSERT INTO bus_station (bus_id, station_id) 
VALUES (1, 1);
INSERT INTO bus_station (bus_id, station_id) 
VALUES (1, 2);
INSERT INTO bus_station (bus_id, station_id) 
VALUES (2, 1);
INSERT INTO bus_station (bus_id, station_id) 
VALUES (2, 2);
INSERT INTO bus_station (bus_id, station_id) 
VALUES (3, 3);
INSERT INTO bus_station (bus_id, station_id) 
VALUES (3, 4);
INSERT INTO bus_station (bus_id, station_id) 
VALUES (4, 1);
INSERT INTO bus_station (bus_id, station_id) 
VALUES (4, 2);
INSERT INTO bus_station (bus_id, station_id) 
VALUES (4, 3);
INSERT INTO bus_station (bus_id, station_id) 
VALUES (4, 4);

INSERT INTO tickets (bus_id, station_id, passenger_id, arrival_time) 
VALUES (1, 1, 1, '2017-02-01 15:15:00');