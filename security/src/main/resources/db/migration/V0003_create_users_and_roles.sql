CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    owner_id INT UNIQUE REFERENCES owners(id) ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS roles
(
    id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id INTEGER NOT NULL REFERENCES users(id),
    role_id INTEGER NOT NULL REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);