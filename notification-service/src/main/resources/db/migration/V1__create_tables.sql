CREATE TABLE notification (
    id BIGINT PRIMARY KEY,
    sender_id UUID NOT NULL,
    recipient_id BIGINT NOT NULL,
    type VARCHAR(50) NOT NULL,
    title TEXT,
    content TEXT NOT NULL,
    retry_attempts INTEGER NOT NULL DEFAULT 3,
    status VARCHAR(50) NOT NULL DEFAULT 'NEW',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
