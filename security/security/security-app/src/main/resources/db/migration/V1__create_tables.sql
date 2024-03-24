CREATE TABLE IF NOT EXISTS users
(
    id         UUID         NOT NULL,
    birth_date DATE         NULL,
    email      VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NULL,
    username   VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "role"
(
    authority VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role_junction
(
    user_id UUID         NOT NULL,
    role_id VARCHAR(255) NOT NULL,
    CONSTRAINT user_role_junction_pkey PRIMARY KEY (user_id, role_id)
);


ALTER TABLE IF EXISTS users ADD CONSTRAINT pk_user_id PRIMARY KEY (id);
ALTER TABLE IF EXISTS users ADD CONSTRAINT un_user_username UNIQUE (username);
ALTER TABLE IF EXISTS users ADD CONSTRAINT un_user_email UNIQUE (email);
ALTER TABLE IF EXISTS users ADD CONSTRAINT un_user_phone UNIQUE (phone);

ALTER TABLE IF EXISTS role ADD CONSTRAINT pk_role_id PRIMARY KEY (authority);

ALTER TABLE IF EXISTS user_role_junction ADD CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE IF EXISTS user_role_junction ADD CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES "role" (authority);
