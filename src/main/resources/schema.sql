DROP TABLE IF EXISTS Post;

CREATE TABLE Post (
  id INT NOT NULL AUTO_INCREMENT,
  title varchar(255) NOT NULL,
  slug varchar(255) NOT NULL,
  date date NOT NULL,
  time_to_read int NOT NULL,
  tags varchar(255),
  PRIMARY KEY (id)
);
