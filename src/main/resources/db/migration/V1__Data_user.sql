drop table if exists user_roles;
drop table if exists roles;
drop table if exists users;

create table users (
    name     varchar(50)  not null,
    password varchar(255) not null,
    phone    varchar(20)  not null,

    primary key (name)
) engine = InnoDB;

create table roles (
    id   int         not null auto_increment,
    name varchar(50) not null,

    primary key (id)
) engine = InnoDB;

create table user_roles (
    user_name varchar(50) not null,
    role_id   int         not null,

    primary key pk_user_role (user_name, role_id),
    foreign key (user_name) references users (name) on delete cascade,
    foreign key (role_id) references roles (id) on delete cascade
) engine = InnoDB;

