create table aggregate
(
    id          bigserial primary key,
    date        timestamp with time zone not null,
    rule_id     bigint                   not null references rule (id),
    users_count integer                  not null
);