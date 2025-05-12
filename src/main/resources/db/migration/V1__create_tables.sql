CREATE TABLE notification (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    recipient_id BIGINT NOT NULL,
    type VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    credential TEXT,
    retry_attempts INTEGER NOT NULL DEFAULT 0,
    status VARCHAR(50) NOT NULL DEFAULT 'NEW',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
