create table participates (
    user_id int references auto_user(id),
    post_id int references auto_post(id)
);