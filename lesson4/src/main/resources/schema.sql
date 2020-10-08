create schema IF NOT EXISTS new_schema;
set schema new_schema;


create table IF NOT EXISTS customer
(
    id int auto_increment,
    name varchar,
    email varchar
);

create unique index customer_id_uindex
    on customer (id);

alter table customer
    add constraint customer_pk
        primary key (id);

create table IF NOT EXISTS "order"
(
    id int auto_increment,
    name varchar,
    price int,
    customer_id int,
    constraint customer_id
        foreign key (customer_id) references customer (id)
);

create unique index order_id_uindex
    on "order" (id);

alter table "order"
    add constraint order_pk
        primary key (id);



