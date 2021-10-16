create table app
(
    id     bigserial primary key,
    name   varchar(200) not null,
    device varchar(50)  not null,
    unique (name, device)
);