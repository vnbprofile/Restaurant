DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP TABLE restaurant_votes IF EXISTS;
DROP TABLE meals IF EXISTS;
DROP SEQUENCE USER_SEQ IF EXISTS;
 DROP SEQUENCE MEAL_SEQ IF EXISTS;
DROP SEQUENCE RESTAURANT_START_SEQ IF EXISTS;

CREATE SEQUENCE USER_SEQ START WITH 100000;
CREATE SEQUENCE RESTAURANT_START_SEQ START WITH 100000;
 CREATE SEQUENCE MEAL_SEQ START WITH 100000;

CREATE TABLE users
(
    id               INTEGER DEFAULT USER_SEQ.nextval PRIMARY KEY,
    email            VARCHAR(255)            NOT NULL,
    password         VARCHAR(255)            NOT NULL,
    enabled          BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON users (email);

CREATE TABLE restaurants
(
    id               INTEGER DEFAULT RESTAURANT_START_SEQ.nextval PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    date             TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE meals
(
    id               INTEGER DEFAULT MEAL_SEQ.nextval PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    price            INTEGER                  NOT NULL,
    restaurant_id    INTEGER                 NOT NULL,
    date             DATE DEFAULT now()      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE restaurant_votes
(
    restaurant_id         INTEGER               NOT NULL REFERENCES restaurants (id),
    user_id               INTEGER               NOT NULL REFERENCES users (id),
    date                  TIMESTAMP DEFAULT now() NOT NULL,
    PRIMARY KEY (restaurant_id, user_id)
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);