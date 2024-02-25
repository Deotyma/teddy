-- Clean up existing data
DELETE FROM favorites;
DELETE FROM annonces;
DELETE FROM users;
DELETE FROM sharing_methods;
DELETE FROM categories;
DELETE FROM localities;

-- Define the stored procedure
CREATE OR REPLACE PROCEDURE copy_unique_records()
LANGUAGE plpgsql
AS $procedure$
BEGIN
    WITH unique_cities AS (
        SELECT DISTINCT ON (zip_code, city_code, latitude, longitude) zip_code, city_code, latitude, longitude
        FROM cities
        ORDER BY zip_code, city_code, latitude, longitude
    )
    INSERT INTO localities(zip_code, city_code, latitude, longitude)
    SELECT zip_code, city_code, latitude, longitude
    FROM unique_cities
    ON CONFLICT (zip_code, city_code, latitude, longitude) 
    DO NOTHING;
END;
$procedure$;

-- Call the stored procedure to populate localities
CALL copy_unique_records();

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
INSERT INTO users(email, first_name, last_name, nick_name, password)
VALUES 
('user1@example.com', 'Alice', 'Smith', 'AliceS', 'password1'),
('user2@example.com', 'Bob', 'Johnson', 'BobJ', 'password2'),
('user3@example.com', 'Charlie', 'Davis', 'CharlieD', 'password3');

-- Insert annonces
INSERT INTO annonces(title, text_annonce, photo_link, user_id, sharing_method_id, category_id, locality_id, date_added) 
VALUES 
('Educational Items', 'Educational items for children with autism', 'link1.jpg', (SELECT id FROM users WHERE nick_name = 'AliceS'), (SELECT id FROM sharing_methods WHERE sharing_method_name = 'give'), (SELECT id FROM categories WHERE category_name = 'educational material'), (SELECT id FROM localities WHERE zip_code = '75009' AND city_code = 'paris 09'), '2023-08-20'),
('Toys for children with autism', 'Toys suitable for Toys for children with autism', 'link2.jpg', (SELECT id FROM users WHERE nick_name = 'BobJ'), (SELECT id FROM sharing_methods WHERE sharing_method_name = 'exchange'), (SELECT id FROM categories WHERE category_name = 'toy'), (SELECT id FROM localities WHERE zip_code = '94000' AND city_code = 'creteil'), '2023-08-20'),
('Sports Equipment', 'Sports equipment for special needs children', 'link3.jpg', (SELECT id FROM users WHERE nick_name = 'CharlieD'), (SELECT id FROM sharing_methods WHERE sharing_method_name = 'looking for'), (SELECT id FROM categories WHERE category_name = 'sport material'), (SELECT id FROM localities WHERE zip_code = '69001' AND city_code = 'lyon 01'), '2023-08-20');

-- Insert favorites
INSERT INTO favorites(user_id, annonce_id) 
VALUES 
((SELECT id FROM users WHERE nick_name = 'AliceS'), (SELECT id FROM annonces WHERE title = 'Sports Equipment')),
((SELECT id FROM users WHERE nick_name = 'BobJ'), (SELECT id FROM annonces WHERE title = 'Educational Items')),
((SELECT id FROM users WHERE nick_name = 'CharlieD'), (SELECT id FROM annonces WHERE title = 'Toys for children with autism'));

