-- Clean up existing data
DELETE FROM annonce_category;
DELETE FROM annonce_purchase_methods;
DELETE FROM favorites;
DELETE FROM annonces;
DELETE FROM users;
DELETE FROM purchase_methods;
DELETE FROM categories;
DELETE FROM cities;

-- Insert data into `cities`
INSERT INTO cities(zip_code, city) 
	VALUES 
	('75000', 'Paris'), 
	('94400','Créteil'),
	('93120','La Courneuve'),	
	('67890', 'Lyon');

-- Insert data into `categories`
INSERT INTO categories(category) 
	VALUES 
	('book'),
	('toy'),
	('educational material'),
	('sport material');

-- Insert data into `purchase_methods`
INSERT INTO purchase_methods(purchase_method) 
	VALUES 
	('buy'),
	('exchange'),
	('give'),
	('sell');

-- Insert data into `users`
INSERT INTO users(first_name, last_name, pseudo_user, email, zip_code, city, password) 
VALUES ('John', 'Doe', 'john_doe', 'john.doe@example.com', '75000', 'Paris', 'password123'),
	   ('Marie', 'Dupont', 'mdupont', 'mdupont@example.com', '94400', 'Créteil', 'password456'),
       ('Jane', 'Smith', 'jane_smith', 'jane.smith@example.com', '67890', 'Lyon', 'password123');

-- Insert data into `annonces` using JOINs for `users`, `categories`, and `purchase_methods`
INSERT INTO annonces(title, product_name, annonce_text, photo, date, id_user) 
VALUES ('Old book for sale', 'Classic Novel', 'An old classic novel in good condition.', 'link_to_photo1', '2023-08-20',
        (SELECT u.id_user FROM users u WHERE u.pseudo_user = 'john_doe')),
       ('Kids toy', 'Rubber Duck', 'Yellow rubber duck for kids.', 'link_to_photo2', '2023-08-21', 
        (SELECT u.id_user FROM users u WHERE u.pseudo_user = 'jane_smith'));

-- Insert data into `annonce_category` and `annonce_purchase_methods` using JOINs
INSERT INTO annonce_category(id_annonce, id_category) 
VALUES ((SELECT a.id_annonce FROM annonces a WHERE a.title = 'Old book for sale'),
        (SELECT c.id_category FROM categories c WHERE c.category = 'book')),
       ((SELECT a.id_annonce FROM annonces a WHERE a.title = 'Kids toy'),
        (SELECT c.id_category FROM categories c WHERE c.category = 'toy'));

INSERT INTO annonce_purchase_methods(id_annonce, id_purchase_method) 
VALUES ((SELECT a.id_annonce FROM annonces a WHERE a.title = 'Old book for sale'),
        (SELECT pm.id_purchase_method FROM purchase_methods pm WHERE pm.purchase_method = 'buy')),
       ((SELECT a.id_annonce FROM annonces a WHERE a.title = 'Kids toy'),
        (SELECT pm.id_purchase_method FROM purchase_methods pm WHERE pm.purchase_method = 'give'));
