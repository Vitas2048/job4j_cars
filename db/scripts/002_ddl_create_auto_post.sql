create table car_body (
    id             serial   primary     key,
    name           varchar
);


CREATE TABLE auto_post (
    id              serial  primary     key,
    description     varchar             not null,
    sold            boolean,
    car_body_id     int     references  car_body(id),
    auto_user_id    int     references  auto_user(id)
);

