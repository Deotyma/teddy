-- Clean up existing data
DELETE FROM favorites;
DELETE FROM annonces;
DELETE FROM users;
DELETE FROM shering_methods;
DELETE FROM categories;
DELETE FROM localities;

-- Insert data into `localities`
INSERT INTO localities(zip_code, locality_name) 
	VALUES 
	('75000', 'Paris'), 
	('94400','Créteil'),
	('93120','La Courneuve'),	
	('67890', 'Lyon');

-- Insert data into `categories`
INSERT INTO categories(category_name) 
	VALUES 
	('book'),
	('toy'),
	('educational material'),
	('sport material');

-- Insert data into `shering_methods`
INSERT INTO shering_methods(shering_method_name) 
	VALUES 
	('looking for'),
	('exchange'),
	('give'),
	('sell');

-- Insert data into `users`
INSERT INTO users(first_name, last_name, nick_name, email, zip_code, locality_name, password) 
VALUES ('John', 'Doe', 'john_doe', 'john.doe@example.com', '75000', 'Paris', 'password123'),
	   ('Marie', 'Dupont', 'mdupont', 'mdupont@example.com', '94400', 'Créteil', 'password456'),
       ('Jane', 'Smith', 'jane_smith', 'jane.smith@example.com', '67890', 'Lyon', 'password123');

-- Insert data into `annonces` for `users`, `categories`, and `shering_methods`
INSERT INTO annonces (title, text_annonce, email, shering_method_name, category_name, date) VALUES
    ('Old book for sale', 'Montessori book foe sale.', 'john.doe@example.com', 'sell', 'book', '2023-11-02'),
    ('Kids toy', 'Puzzle of 3 elements to give.', 'mdupont@example.com', 'give', 'toy', '2023-11-03');




INSERT INTO favorites(id_annonce, email) 
VALUES ((SELECT a.id_annonce FROM annonces a WHERE a.title = 'Old book for sale'),
        (SELECT u.email FROM users u WHERE u.nick_name = 'mdupont')),
       ((SELECT a.id_annonce FROM annonces a WHERE a.title = 'Kids toy'),
        (SELECT u.email FROM users u WHERE u.nick_name = 'jane_smith'));
