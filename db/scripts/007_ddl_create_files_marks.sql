create table marks (
    id serial primary key,
    name varchar unique  not null
);

create table files (
    id serial primary key,
    path varchar unique  not null
);