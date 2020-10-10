DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM users;
DELETE FROM restaurant_votes;
ALTER SEQUENCE USER_SEQ RESTART WITH 100000;

INSERT INTO users (email, password) VALUES
('admin@gmail.com', 'admin'),
('user@mail.com', '1234'),
('user@yandex.ru', '1234');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 100000),
('ROLE_ADMIN', 100000),
('ROLE_USER', 100001),
('ROLE_USER', 100002);

INSERT INTO restaurants (name, date) VALUES
('Диканька', '2019-11-10'),
('Пинокио', '2019-11-10'),
('Рис', '2019-11-10'),
('БумБараш', '2019-11-10'),
('Стан', '2019-11-10'),
('Угли-угли', '2020-01-01'),
('Балкан Гриль', now()),
('Венсент', now()),
('The Great Kraken', now()),
('ЧО-ЧО', now()),
('Мясоlove', now());

INSERT INTO restaurant_votes (restaurant_id, user_id) VALUES
(100001, 100001),
(100001, 100002),
(100006, 100000),
(100007, 100001),
(100008, 100002),
(100010, 100002),
(100009, 100000),
(100009, 100001),
(100004, 100000);

INSERT  INTO meals (name, price, restaurant_id) VALUES
('Баранина на гриле', 60.00, 100001 ),
('Лаваш', 25.00, 100001 ),
('Борщ', 36.00, 100002 ),
('Солянка', 40.00, 100002),
('Суши', 25.00, 100003),
('Роллы', 30.00, 100003),
('Бигус', 34.00, 100004),
('Курица на пару', 28.00, 100004),
('Шашлык', 35.00, 100005),
('Бургер', 45.00, 100005),
('Свинина на гриле', 60.00, 100006 ),
('Лаваш', 25.00, 100006 ),
('Рыба', 36.00, 100007 ),
('Солянка', 40.00, 100007),
('Суши', 25.00, 100008),
('Колла', 30.00, 100008),
('Плов', 34.00, 100009),
('Курица на пару', 28.00, 100009),
('Люля', 35.00, 100010),
('Бургер', 45.00, 100010);


