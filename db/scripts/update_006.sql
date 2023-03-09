ALTER TABLE auto_post ADD COLUMN mark_id int REFERENCES marks(id);
ALTER TABLE auto_post ADD COLUMN created TIMESTAMP;

ALTER TABLE engine ADD COLUMN name varchar(255);

ALTER TABLE driver ADD COLUMN name varchar(255);
ALTER TABLE driver ADD COLUMN user_id int not null unique references auto_user(id);

ALTER TABLE files ADD COLUMN auto_post_id int REFERENCES auto_post(id);
ALTER TABLE price_history ADD COLUMN auto_post_id int REFERENCES auto_post(id);