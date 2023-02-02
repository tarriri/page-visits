\c db;
CREATE SCHEMA IF NOT EXISTS db;
ALTER ROLE application SET search_path=db;
DROP SCHEMA IF EXISTS public;
GRANT ALL PRIVILEGES ON DATABASE db to application;
GRANT ALL PRIVILEGES ON SCHEMA db to application;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA db to application;