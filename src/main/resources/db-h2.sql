DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS author_1;
DROP TABLE IF EXISTS author_2;

DROP SEQUENCE IF EXISTS s_author_id;
CREATE SEQUENCE s_author_id START WITH 1;
DROP SEQUENCE IF EXISTS s_author_id_1;
CREATE SEQUENCE s_author_id_1 START WITH 1;
DROP SEQUENCE IF EXISTS s_author_id_2;
CREATE SEQUENCE s_author_id_2 START WITH 1;

CREATE TABLE author(
  id INT NOT NULL,
  first_name VARCHAR(50),
  last_name VARCHAR(50) NOT NULL,
  date_of_birth DATE,
  year_of_birth INT,
  address VARCHAR(50),

  CONSTRAINT pk_t_author PRIMARY KEY (ID)
);

CREATE TABLE author_1(
  id INT NOT NULL PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50) NOT NULL,
  date_of_birth DATE,
  year_of_birth INT,
  address VARCHAR(50),

  CONSTRAINT pk_t_author_1 PRIMARY KEY (ID)
);

CREATE TABLE author_2(
  id INT NOT NULL PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50) NOT NULL,
  date_of_birth DATE,
  year_of_birth INT,
  address VARCHAR(50),

  CONSTRAINT pk_t_author_2 PRIMARY KEY (ID)
);