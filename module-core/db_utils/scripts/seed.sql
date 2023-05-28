-- User seeding --
INSERT INTO app_user (email, first_name, last_name, user_type)
VALUES ('manager@gmail.com', 'John', 'Smith', 0);
INSERT INTO app_user (email, first_name, last_name, user_type)
VALUES ('mick.jagger@gmail.com', 'Mick', 'Jagger', 1);
INSERT INTO app_user (email, first_name, last_name, user_type)
VALUES ('stevie.nicks@gmail.com', 'Stevie', 'Nicks', 1);
INSERT INTO app_user (email, first_name, last_name, user_type)
VALUES ('manager.secondbest@gmail.com', 'John', 'Second', 0);

SELECT * FROM app_user;
-- Band seeding --
INSERT INTO band (genre, image, name)
VALUES (1, 'Image', 'The Rolling Stones');
INSERT INTO band (genre, image, name)
VALUES (1, 'Image', 'Fleetwood Mac');

SELECT * FROM band;
-- Additional user seeding
-- Adding Manager to band
UPDATE app_user SET band_id = (SELECT id from band limit 1) WHERE id = (SELECT id from app_user WHERE user_type = 0 ORDER BY id ASC limit 1);
UPDATE app_user SET band_id = (SELECT id from band limit 1 offset 1) WHERE id = (SELECT id from app_user WHERE user_type = 0 ORDER BY id ASC limit 1 offset 1);

-- Adding members to band
UPDATE app_user SET member_of_band_id = (SELECT id from band ORDER BY id ASC limit 1) WHERE id = (SELECT id from app_user WHERE user_type = 1 ORDER BY id ASC limit 1);

SELECT * FROM app_user;
-- Album seeding --
INSERT INTO album (band_id, genre, name, release_date)
VALUES ((SELECT id from band limit 1), 1, 'Sticky Fingers', '1971-04-23');
INSERT INTO album (band_id, genre, name, release_date)
VALUES ((SELECT id from band limit 1 offset 1), 1, 'Rumours', '1977-02-04');

SELECT * FROM album;

-- Song seeding --
INSERT INTO song (duration, title, album_id)
VALUES (194, 'Brown Sugar', (SELECT id from album limit 1));
INSERT INTO song (duration, title, album_id)
VALUES (256, 'Wild Horses', (SELECT id from album limit 1));
INSERT INTO song (duration, title, album_id)
VALUES (215, 'Cant You Hear Me Knocking', (SELECT id from album limit 1));
INSERT INTO song (duration, title, album_id)
VALUES (229, 'You Gotta Move', (SELECT id from album limit 1));
--
INSERT INTO song (duration, title, album_id)
VALUES (211, 'Second Hand News', (SELECT id from album limit 1 offset 1));
INSERT INTO song (duration, title, album_id)
VALUES (216, 'Dreams', (SELECT id from album limit 1 offset 1));
INSERT INTO song (duration, title, album_id)
VALUES (258, 'Never Going Back Again', (SELECT id from album limit 1 offset 1));
INSERT INTO song (duration, title, album_id)
VALUES (224, 'Dont Stop', (SELECT id from album limit 1 offset 1));

SELECT * FROM song;
-- Tours seeding --
INSERT INTO tour (name) VALUES ('Sticky Fingers Tour');

INSERT INTO tourdates (city, date, venue, tour_id)
VALUES ('New York', '1971-03-21', 'The Apollo theatre', (SELECT id from tour limit 1));
INSERT INTO tourdates (city, date, venue, tour_id)
VALUES ('London', '1971-03-27', 'The Roundhouse', (SELECT id from tour limit 1));
INSERT INTO tourdates (city, date, venue, tour_id)
VALUES ('London', '1971-03-28', 'The Roundhouse', (SELECT id from tour limit 1));

INSERT INTO tour_band_list (tours_id, band_list_id)
VALUES ((SELECT id from tour limit 1), (SELECT id from band limit 1));
INSERT INTO tour_band_list (tours_id, band_list_id)
VALUES ((SELECT id from tour limit 1), (SELECT id from band limit 1 offset 1));

SELECT * FROM tour;
SELECT * FROM tourdates;
SELECT * FROM tour_band_list;
-- Invite seeding --
INSERT INTO invitation (date_received, message, status, user_id, band_id)
VALUES ('2020-01-01', 'Join my band!', 2, (SELECT id from app_user where member_of_band_id is null limit 1), (SELECT id from band limit 1));

INSERT INTO app_user_invitations (user_id, invitations_id)
VALUES ((SELECT id from app_user limit 1 offset 2), (SELECT id from invitation limit 1));

SELECT * FROM invitation;
SELECT * FROM app_user_invitations;
------------------