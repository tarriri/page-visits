CREATE TABLE db.visit_event
(
    id uuid not null CONSTRAINT visitevent_pkey PRIMARY KEY,
    entity_type int NOT NULL,
    entity_id uuid NOT NULL,
    session_id uuid NOT NULL,
    event_type varchar NOT NULL,
    medium varchar NOT NULL,
    created_at timestamp NOT NULL
);