create table rule_type
(
    id    bigserial primary key,
    name  varchar(200) not null unique,
    key   varchar(100) not null,
    value varchar(300) not null
);