CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS roles (
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id));


CREATE TABLE IF NOT EXISTS users_roles
(user_id BIGINT NOT NULL,
 role_id INT NOT NULL,
 PRIMARY KEY (user_id, role_id),
 FOREIGN KEY (user_id) REFERENCES users (id),
 FOREIGN KEY (role_id) REFERENCES roles (id));


INSERT INTO users (username, password) VALUES
('admin', '$2a$10$sMJY8vpsBVW/LyQwD5eak.cYrdHZUJw2JdEA0/fa2TOHOO7hMIXqq'),
('user', '$2a$10$pNc3OQ2MYGxnExFx6MyCWuiiohnMfA4sIMYph.1qmxGEL98uSpc/S'),
('superadmin', '$2a$10$KWHh0xwFxNtA0IYlSgDUN.ogyapW7br2/ORYr4.vynLG9tXvFXgJm'),
('manager', '$2a$12$1LvVyp5400mHMh4U4iT5TeJxtdQC/sBOTdjAB5Es394AvxJHxCL0e');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER'), ('ROLE_SUPERADMIN'), ('ROLE_MANAGER');

INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1), (2, 2), (3, 3), (4, 4);