INSERT INTO users VALUES ('keith', '{noop}keithpw', 'aaa@abc.com', 'aaa');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_ADMIN');

INSERT INTO users VALUES ('john', '{noop}johnpw', 'bbb@abc.com', 'bbb');
INSERT INTO user_roles(username, role) VALUES ('john', 'ROLE_ADMIN');

INSERT INTO users VALUES ('mary', '{noop}marypw', 'ccc@abc.com', 'ccc');
INSERT INTO user_roles(username, role) VALUES ('mary', 'ROLE_USER');