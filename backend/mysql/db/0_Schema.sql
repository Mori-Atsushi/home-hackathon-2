
DROP DATABASE IF EXISTS ouchi;
CREATE DATABASE ouchi;

DROP TABLE IF EXISTS ouchi.user;

CREATE TABLE ouchi.user
(
    uuid            CHAR(36)        NOT NULL PRIMARY KEY,
    name            VARCHAR(64)     NOT NULL,
    access_token    CHAR(36)        NOT NULL
);
