create table engine(
    id serial primary key
);

create table car(
    id serial primary key,
    engine_id int not null references engine(id),
    mark_id int references marks(id)
);

create table driver(
    id serial primary key
);

create table history_owner(
    id serial primary key,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);

ALTER TABLE auto_post ADD COLUMN car_id int REFERENCES car(id);
ALTER TABLE auto_post ADD COLUMN price int;