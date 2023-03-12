CREATE TABLE auto_user (
id serial primary key,
login varchar unique not null,
password varchar not null
);
CREATE TABLE car_body (
id serial primary key,
name varchar
);
CREATE TABLE engine(
id serial primary key,
name varchar unique not null
);
CREATE TABLE marks (
id serial primary key,
name varchar unique not null
);
CREATE TABLE driver(
id serial primary key,
name varchar,
user_id int not null unique REFERENCES auto_user(id)
);
CREATE TABLE car(
id serial primary key,
name varchar,
engine_id int REFERENCES engine(id),
mark_id int REFERENCES marks(id),
car_body_id int REFERENCES car_body(id)
);
CREATE TABLE history_owner(
id serial primary key,
driver_id int not null REFERENCES driver(id),
car_id int not null REFERENCES car(id)
);
CREATE TABLE auto_post (
id serial primary key,
description varchar not null,
sold boolean,
car_body_id int REFERENCES car_body(id),
auto_user_id int REFERENCES auto_user(id),
car_id int REFERENCES car(id),
price int,
mark_id int REFERENCES marks(id),
created TIMESTAMP
);
CREATE TABLE files (
id serial primary key,
name varchar not null,
path varchar unique not null,
auto_post_id int REFERENCES auto_post(id)
);
CREATE TABLE participates (
user_id int REFERENCES auto_user(id),
post_id int REFERENCES auto_post(id)
);