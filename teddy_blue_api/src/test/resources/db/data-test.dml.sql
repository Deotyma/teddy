-- Clean up existing data
DELETE FROM t_annonces;
DELETE FROM t_users;
DELETE FROM t_sharing_methods;
DELETE FROM t_categories;
DELETE FROM t_localities;

--Insert localities
INSERT INTO t_localities(zip_code, city_code,longitude, latitude)
VALUES
('75001', 'paris','2.336419316', '48.862549876'),
('94000', 'creteil','2.454728618', '48.783768446'),
('69001', 'lyon','4.828518526', '45.770061445'),
('13001', 'marseille','5.382708717', '43.299974296'),
('06150', 'cannes','7.004779118', '43.552044875'),
('84000', 'avignon','4.841098248', '43.935441391'),
('45000', 'orleans','1.917316017', '47.873503729'),
('54000', 'nancy','6.175258299', '48.690068976'),
('94400', 'vitry sur seine','2.394516098', '48.788118568'),
('94140', 'alfortville','2.421407444', '48.796134091');

-- Insert categories
INSERT INTO t_categories(category) 
VALUES 
('book'),
('toy'),
('educational material'),
('sport material');

-- Insert sharing methods
INSERT INTO t_sharing_methods(sharing_method) 
VALUES 
('looking for'),
('exchange'),
('give'),
('sell');

-- Insert users
INSERT INTO t_users(email, first_name, last_name, nick_name, password)
VALUES 
('user1@example.com', 'Alice', 'Smith', 'AliceS', 'password1'),
('user2@example.com', 'Bob', 'Johnson', 'BobJ', 'password2'),
('user3@example.com', 'Charlie', 'Davis', 'CharlieD', 'password3');

-- Insert annonces
INSERT INTO t_annonces(title, text_annonce, photo_link, user_id, sharing_method_id, category_id, locality_id, date_added) 
VALUES 
('Educational Items', 'Educational items for children with autism', 'link1.jpg', (SELECT id FROM t_users WHERE nick_name = 'AliceS'), (SELECT id FROM t_sharing_methods WHERE sharing_method = 'give'), (SELECT id FROM t_categories WHERE category = 'educational material'), (SELECT id FROM t_localities WHERE zip_code = '75001' AND city_code = 'paris'), '2023-08-20'),
('Toys for children with autism', 'Toys suitable for Toys for children with autism', 'link2.jpg', (SELECT id FROM t_users WHERE nick_name = 'BobJ'), (SELECT id FROM t_sharing_methods WHERE sharing_method = 'exchange'), (SELECT id FROM t_categories WHERE category = 'toy'), (SELECT id FROM t_localities WHERE zip_code = '94000' AND city_code = 'creteil'), '2023-08-20'),
('Sports Equipment', 'Sports equipment for special needs children', 'link3.jpg', (SELECT id FROM t_users WHERE nick_name = 'CharlieD'), (SELECT id FROM t_sharing_methods WHERE sharing_method = 'looking for'), (SELECT id FROM t_categories WHERE category = 'sport material'), (SELECT id FROM t_localities WHERE zip_code = '69001' AND city_code = 'lyon'), '2023-08-20');
