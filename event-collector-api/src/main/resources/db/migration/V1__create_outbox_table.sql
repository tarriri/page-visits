CREATE TABLE db.outbox
(
    id uuid not null CONSTRAINT outbox_pkey PRIMARY KEY,
    aggregate_type varchar(255) NOT NULL,
    aggregate_id uuid NOT NULL,
    type varchar(255) NOT NULL,
    payload varchar(255) NOT NULL,
    created_at timestamp NOT NULL
);