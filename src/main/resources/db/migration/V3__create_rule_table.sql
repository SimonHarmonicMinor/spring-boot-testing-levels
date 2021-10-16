create table rule
(
    id           bigserial primary key,
    name         varchar(200) not null,
    key          varchar(100) not null,
    value        varchar(300) not null,
    app_id       bigint       not null references app (id),
    rule_type_id bigint       not null references rule_type (id)
);