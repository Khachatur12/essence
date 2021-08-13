# add roles
insert into roles (id, name) VALUES (1, 'ADMIN');
insert into roles (id, name) VALUES (2, 'USER');

# add admins
insert into users (name, password, phone) VALUES ('admin', '$2a$10$eXIEycNQBJLVfOAwY08h8ueRJ3KsKPwWdoVN5GNkzFJaFuZDQszRC', '+995568746436');
insert into user_roles (user_name, role_id) VALUES ('admin', 1);

# add users
insert into users (name, password, phone) VALUES ('user1', '$2a$10$eXIEycNQBJLVfOAwY08h8ueRJ3KsKPwWdoVN5GNkzFJaFuZDQszRC', '+995592756483');
insert into user_roles (user_name, role_id) VALUES ('user1', 2);

insert into users (name, password, phone) VALUES ('user3', '$2a$10$eXIEycNQBJLVfOAwY08h8ueRJ3KsKPwWdoVN5GNkzFJaFuZDQszRC', '+995574657493');
insert into user_roles (user_name, role_id) VALUES ('user3', 2);