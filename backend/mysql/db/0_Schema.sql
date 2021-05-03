
DROP DATABASE IF EXISTS ouchi;
CREATE DATABASE ouchi;

DROP TABLE IF EXISTS ouchi.user;

CREATE TABLE ouchi.user
(
    id          INTEGER             NOT NULL PRIMARY KEY,
    name        VARCHAR(64)         NOT NULL,
    access_token VARCHAR(4096)       NOT NULL
);
