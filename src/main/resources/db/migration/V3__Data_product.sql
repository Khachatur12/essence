drop table if exists orders;
drop table if exists product_type;
drop table if exists category_type;
drop table if exists product_category;
drop table if exists product_color;
drop table if exists type;
drop table if exists color;
drop table if exists category;
drop table if exists products;


create table products
(
    id           bigint        not null auto_increment,
    name         varchar(255)  not null,
    price        double(10, 2) not null,
    old_price    double(10, 2),
    loading_date date          not null,
    sold         int,

    primary key (id)
) engine = InnoDB;

create table category
(
    name varchar(50) not null,

    primary key (name)
) engine = InnoDB;

create table color
(
    name varchar(50) not null,

    primary key (name)
) engine = InnoDB;

create table type
(
    name varchar(50) not null,

    primary key (name)
) engine = InnoDB;

create table product_category
(
    product_id    bigint      not null,
    category_name varchar(50) not null,

    primary key (product_id),
    foreign key (product_id) references products (id) on delete cascade,
    foreign key (category_name) references category (name) on delete cascade
) engine = InnoDB;

create table product_color
(
    product_id bigint      not null,
    color_name varchar(50) not null,

    primary key (product_id, color_name),
    foreign key (product_id) references products (id) on delete cascade,
    foreign key (color_name) references color (name) on delete cascade
) engine = InnoDB;

create table category_type
(
    category_name varchar(50) not null,
    type_name     varchar(50) not null,

    primary key (category_name, type_name),
    foreign key (category_name) references category (name) on delete cascade,
    foreign key (type_name) references type (name) on delete cascade
) engine = InnoDB;

create table product_type
(
    product_id bigint      not null,
    type_name  varchar(50) not null,

    primary key (product_id, type_name),
    foreign key (product_id) references products (id) on delete cascade,
    foreign key (type_name) references type (name) on delete cascade
) engine = InnoDB;
