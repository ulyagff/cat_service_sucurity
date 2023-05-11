ALTER TABLE cats
    ADD tail_length INTEGER;

CREATE TABLE IF NOT EXISTS fleas (
                        id SERIAL PRIMARY KEY,
                        name varchar(255) NOT NULL,
                        cat_id INTEGER REFERENCES cats(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS owners (
                                      id SERIAL PRIMARY KEY,
                                      name varchar(255) NOT NULL,
                                      birthday date NOT NULL
);