DROP TABLE IF EXISTS heroes;

CREATE TABLE heroes (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO heroes (name) VALUES
  ('Superman'),
  ('Batman'),
  ('Antman'),
  ('Spiderman'),
  ('Hulk'),
  ('Sentry'),
  ('Hawkeye'),
  ('Flash'),
  ('Hawkman'),
  ('Aquaman'),
  ('Nigthwing');