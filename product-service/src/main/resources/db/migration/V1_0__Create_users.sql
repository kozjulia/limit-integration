create table users
(
    id       bigserial not null,
    username varchar(255) unique,
    CONSTRAINT users_pk PRIMARY KEY (id)
);
