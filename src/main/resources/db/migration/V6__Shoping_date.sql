create table orders
(
    user_name     varchar(50)   not null,
    product_id    bigint        not null,
    product_count int           not null,
    price         double(10, 2) not null,
    data          date          not null,
    state         varchar(50)   not null,

    primary key (user_name, product_id),
    foreign key (user_name) references users (name),
    foreign key (product_id) references products (id)
);