CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    username     VARCHAR(255) UNIQUE NOT NULL,
    email        VARCHAR(64) UNIQUE  NOT NULL,
    password     VARCHAR(255)        NULL,
    display_name VARCHAR(64)         NOT NULL,
    gender       VARCHAR(24),
    dob          DATE,
    avatar       TEXT,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tasks
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    description  TEXT, -- Markdown
    status       VARCHAR(16)  NOT NULL,
    priority     VARCHAR(16)  NOT NULL,
    deadline_at  TIMESTAMP    NULL,
    completed_at TIMESTAMP    NULL,
    canceled_at  TIMESTAMP    NULL,
    assignee_id  BIGINT       NULL,
    assignor_id  BIGINT       NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);