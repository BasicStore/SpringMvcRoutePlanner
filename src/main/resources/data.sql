insert into user (id, email, password, name, last_name, active) values (1, 'email@sadf.com', 'password', 'user1', 'x', 1);
insert into user (id, email, password, name, last_name, active) values (2, 'email@sadf.com', 'password', 'user2', 'y', 1);
insert into user (id, email, password, name, last_name, active) values (3, 'email@sadf.com', 'password', 'user3', 'z', 1);
insert into role (id, role) values(1, 'USER');
insert into role (id, role) values(2, 'MEMBER');
insert into role (id, role) values(3, 'ADMIN');
insert into user_role (user_id, roles_id) values (1, 1);
insert into user_role (user_id, roles_id) values (2, 2);
insert into user_role (user_id, roles_id) values (3, 3);


