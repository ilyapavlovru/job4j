create database job4jhiberinheritance4;

create table billing_details
(
    id integer not null
        constraint billing_details_pkey
            primary key,
    owner varchar(20) not null
)
;


create table credit_card
(
    id integer not null
        constraint credit_card_pkey
            primary key
        constraint credit_card_billing_details_id_fk
            references billing_details,
    card_number integer not null,
    exp_month varchar(255) not null,
    exp_year varchar(255) not null
)
;
create unique index credit_card_card_number_uindex
    on credit_card (card_number)
;


create table bank_account
(
    id integer not null
        constraint bank_account_pkey
            primary key
        constraint bank_account_billing_details_id_fk
            references billing_details,
    account integer not null,
    bank_name varchar(255) not null,
    swift varchar(255) not null
)
;
create unique index bank_account_account_uindex
    on bank_account (account)
;