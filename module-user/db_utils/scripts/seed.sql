-- User auth seeding --
INSERT INTO user_auth (email, password)
VALUES ('john.doe@gmail.com', 'pass12345');
INSERT INTO user_auth (email, password)
VALUES ('mick.jagger@gmail.com', 'testpass');
INSERT INTO user_auth (email, password)
VALUES ('stevie.nicks@gmail.com', 'test12345pass');
INSERT INTO user_auth (email, password)
VALUES ('manageracc@gmail.com', 'test12345789');
INSERT INTO user_auth (email, password)
VALUES ('keith.richards@gmail.com', 'easypassword');

SELECT * FROM user_auth;
------------------