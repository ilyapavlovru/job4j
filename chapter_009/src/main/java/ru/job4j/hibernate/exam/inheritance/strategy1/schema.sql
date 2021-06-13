create table credit_card
(
    id serial not null
        constraint bank_account_pkey
            primary key,
    cc_owner varchar(20) not null,
    card_number integer not null,
    exp_month varchar(9) not null,
    exp_year varchar(4) not null
)
;

create table bank_account
(
    id serial not null
        primary key,
    owner varchar(20),
    account integer not null,
    bank_name varchar(20) not null,
    swift varchar(20) not null
)
;