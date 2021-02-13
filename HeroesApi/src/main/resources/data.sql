DROP TABLE IF EXISTS heroes;

CREATE TABLE heroes (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO heroes (name) VALUES
  ('Superman'),
  ('batman'),
  ('asdmanasd'),
  ('asdmanasd asdmanmas');