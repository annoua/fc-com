ALTER TABLE flash_card AUTO_INCREMENT=100000;
INSERT INTO flash_card(id, back_text, front_text, title) VALUES
(100000, 'Dies ist ein Text, der Hinten auf einer Flashcard stehen könnte!', 'Dies ist ein Text, der Vorne auf einer Flashcard stehen könnte! Dies ist ein Text, der Vorne auf einer Flashcard stehen könnte! Dies ist ein Text, der Vorne auf einer Flashcard stehen könnte!', 'Hello World'),
(100001, 'Lösung', 'Frage', 'TestCard');

ALTER TABLE user AUTO_INCREMENT=100000;

INSERT INTO user(id, username, email, password, role_id) VALUES
(100000, 'testuser', 'test.user@test.de', '123456', 1),
(100001, 'taken', 'taken.mail@fc.de', 'pw123456', 1);

INSERT INTO role(id, name) VALUES
(1, 'user'),
(2, 'admin');