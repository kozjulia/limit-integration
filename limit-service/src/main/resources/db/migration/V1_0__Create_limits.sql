create table limits
(
    id           bigserial not null,
    limit_amount decimal,
    user_id      bigint unique,
    CONSTRAINT limits_pk PRIMARY KEY (id)
);
