ALTER TABLE auto_post ADD COLUMN file_id int REFERENCES files(id);
ALTER TABLE auto_post ADD COLUMN mark_id int REFERENCES marks(id);
ALTER TABLE auto_post ADD COLUMN created TIMESTAMP;