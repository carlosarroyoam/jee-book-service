INSERT INTO authors(id, name, created_at, updated_at) VALUES (1, 'Douglas Adams', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO authors(id, name, created_at, updated_at) VALUES (2, 'Sebastian Daschner', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO authors(id, name, created_at, updated_at) VALUES (3, 'David R. Heffelfinger', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO authors(id, name, created_at, updated_at) VALUES (4, 'Kapila Bogahapitiya', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO books(isbn, title, price, is_available_online, published_at, created_at, updated_at) VALUES ('0345391802', 'The Hitchhiker''s Guide to the Galaxy', 12.99, 1, '2023-10-29', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO books(isbn, title, price, is_available_online, published_at, created_at, updated_at) VALUES ('1788393856', 'Architecting Modern Java EE Applications', 15.87, 2, '2017-10-29', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO books(isbn, title, price, is_available_online, published_at, created_at, updated_at) VALUES ('1788293673', 'Java EE 8 Application Development', 9.99, 3, CURRENT_TIMESTAMP, '2023-02-23', CURRENT_TIMESTAMP);
INSERT INTO books(isbn, title, price, is_available_online, published_at, created_at, updated_at) VALUES ('1786469200', 'Mastering Java EE 8 Application Development', 25.99, 4, '2019-07-26', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO book_authors(book_id, author_id) VALUES (1, 1);
INSERT INTO book_authors(book_id, author_id) VALUES (2, 2);
INSERT INTO book_authors(book_id, author_id) VALUES (3, 3);
INSERT INTO book_authors(book_id, author_id) VALUES (4, 4);
