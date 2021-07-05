create database job4jhiberinheritance3;

create table billing_details
(
    id serial not null
        constraint billing_details_pkey
            primary key,
    bd_type varchar(2),
    owner varchar(20),
    card_number integer,
    exp_month varchar(9),
    exp_year varchar(4),
    account integer,
    bank_name varchar(20),
    swift varchar(20)
)
;

create unique index billing_details_card_number_uindex
    on billing_details (card_number)
;
