create table car_brand
(
    id   serial primary key,
    name varchar(2000)
);

create table car_model
(
    id           serial primary key,
    name         varchar(2000),
    car_brand_id int not null references car_brand (id)
);
