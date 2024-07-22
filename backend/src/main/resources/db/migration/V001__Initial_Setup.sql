CREATE TABLE customer (
  id bigserial PRIMARY KEY,
  name text NOT NULL,
  email text NOT NULL UNIQUE,
  age integer NOT NULL
);
