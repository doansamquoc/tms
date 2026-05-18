ALTER TABLE tasks
    ADD CONSTRAINT fk_tasks_assignor_users FOREIGN KEY (assignor_id) REFERENCES users (id);
ALTER TABLE tasks
    ADD CONSTRAINT fk_tasks_assignee_users FOREIGN KEY (assignee_id) REFERENCES users (id);