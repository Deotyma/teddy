-- Clean up existing data
DELETE FROM favorites;
DELETE FROM annonces;
DELETE FROM users;
DELETE FROM sharing_methods;
DELETE FROM categories;
DELETE FROM localities;

-- Insert localities
INSERT INTO localities(zip_code, locality_name) 
VALUES 
('75000', 'Paris'), 
('94400', 'Créteil'),
('93120', 'La Courneuve'),  
('67890', 'Lyon');

-- Insert categories
INSERT INTO categories(category_name) 
VALUES 
('book'),
('toy'),
('educational material'),
('sport material');

-- Insert sharing methods
INSERT INTO sharing_methods(sharing_method_name) 
VALUES 
('looking for'),
('exchange'),
('give'),
('sell');

-- Insert users
INSERT INTO users(email, first_name, last_name, nick_name, password, zip_code, locality_name) 
VALUES 
('user1@example.com', 'Alice', 'Smith', 'AliceS', 'password1', '75000', 'Paris'),
('user2@example.com', 'Bob', 'Johnson', 'BobJ', 'password2', '94400', 'Créteil'),
('user3@example.com', 'Charlie', 'Davis', 'CharlieD', 'password3', '93120', 'La Courneuve');

-- Insert annonces
INSERT INTO annonces(title, text_annonce, photo_link, user_id, sharing_method_id, category_id, date_added) 
VALUES 
('Educational Items', 'Educational items for children with autism', 'link1.jpg', (SELECT id FROM users WHERE nick_name = 'AliceS'), (SELECT id FROM sharing_methods WHERE sharing_method_name = 'give'), (SELECT id FROM categories WHERE category_name = 'educational material'), '2023-08-20'),
('Toys for children with autism', 'Toys suitable for Toys for children with autism', 'link2.jpg', (SELECT id FROM users WHERE nick_name = 'BobJ'), (SELECT id FROM sharing_methods WHERE sharing_method_name = 'exchange'), (SELECT id FROM categories WHERE category_name = 'toy'), '2023-08-20'),
('Sports Equipment', 'Sports equipment for special needs children', 'link3.jpg', (SELECT id FROM users WHERE nick_name = 'CharlieD'), (SELECT id FROM sharing_methods WHERE sharing_method_name = 'looking for'), (SELECT id FROM categories WHERE category_name = 'sport material'), '2023-08-20');

-- Insert favorites
INSERT INTO favorites(user_id, annonce_id) 
VALUES 
((SELECT id FROM users WHERE nick_name = 'AliceS'), (SELECT id FROM annonces WHERE title = 'Sports Equipment')),
((SELECT id FROM users WHERE nick_name = 'BobJ'), (SELECT id FROM annonces WHERE title = 'Educational Items')),
((SELECT id FROM users WHERE nick_name = 'CharlieD'), (SELECT id FROM annonces WHERE title = 'Toys for children with autism'));

