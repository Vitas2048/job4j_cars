create table engine(
    id serial primary key
);

create table car(
    id serial primary key,
    engine_id int not null unique references engine(id)
);

create table marks (
    id serial primary key,
    name varchar unique  not null
);

CREATE TABLE auto_post (
    id              serial  primary     key,
    description     varchar             not null,
    auto_user_id    int     references  auto_user(id),
    car_id          int     references  car(id),
    mark_id         int     references  marks(id),
    created         timestamp
);
