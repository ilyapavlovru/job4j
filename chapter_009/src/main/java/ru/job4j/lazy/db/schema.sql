create table category
(
    id   serial primary key,
    name varchar(2000)
);

create table task
(
    id          serial primary key,
    description varchar(2000),
    category_id int not null references category (id)
);
