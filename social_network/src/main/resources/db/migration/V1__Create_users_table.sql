/* Создание таблицы пользователей */
create table if not exists users
(
    id         varchar(36)
            primary key,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    age        integer,
    city       varchar(255),
    interest   varchar(255),
    sex        varchar(1)
);