DROP SCHEMA PUBLIC CASCADE;
CREATE SCHEMA PUBLIC;

CREATE TABLE account (
  id BIGSERIAL PRIMARY KEY,
  license CHAR(36) UNIQUE NOT NULL,
  email VARCHAR NOT NULL,
  pin CHAR(7) NOT NULL,
  activated VARCHAR(10) NOT NULL
);

CREATE TABLE fault (
  cause VARCHAR NOT NULL,
  occurred VARCHAR NOT NULL
);