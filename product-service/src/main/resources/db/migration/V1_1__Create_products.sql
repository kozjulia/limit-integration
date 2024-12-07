create table products
(
    id             bigserial not null,
    account_number varchar(32) unique,
    balance        decimal,
    account_type   varchar(32),
    user_id        bigint,
    CONSTRAINT products_pk PRIMARY KEY (id),
    CONSTRAINT fk_products_users FOREIGN KEY (user_id) REFERENCES users(id)
);
