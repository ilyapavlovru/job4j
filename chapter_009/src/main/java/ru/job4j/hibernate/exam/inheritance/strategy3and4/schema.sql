create database job4jhiberinheritance5;

create table billing_details
(
    id integer not null
        constraint billing_details_pkey
            primary key,
    owner varchar(20),
    account integer,
    bank_name varchar(20),
    swift varchar(20)
)
;

create table credit_card
(
    card_number integer not null,
    exp_month varchar(255) not null,
    exp_year varchar(255) not null,
    id integer not null
        constraint credit_card_pkey
            primary key
        constraint fksf645frtr6h3i4d179ff4ke9h
            references billing_details
)
;
