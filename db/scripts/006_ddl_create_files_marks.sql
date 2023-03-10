
create table files (
    id serial primary key,
    post_id int references auto_post(id),
    path varchar unique  not null
);