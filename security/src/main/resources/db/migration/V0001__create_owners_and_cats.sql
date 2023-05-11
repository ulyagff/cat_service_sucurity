CREATE TABLE IF NOT EXISTS owners (
                        id SERIAL PRIMARY KEY,
                        name varchar(255) NOT NULL,
                        birthday date NOT NULL
);

CREATE TABLE IF NOT EXISTS cats (
                      id SERIAL PRIMARY KEY,
                      name varchar(255) NOT NULL,
                      birthday date NOT NULL,
                      breed varchar(255) NOT NULL,
                      color varchar(255) NOT NULL,
                      owner_id INTEGER REFERENCES owners(id) ON DELETE CASCADE
);

INSERT INTO owners(name, birthday) VALUES ('base_owner', DATE '2020-06-06');

