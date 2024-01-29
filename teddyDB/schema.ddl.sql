-- Dropping tables in reverse dependency order
DROP TABLE IF EXISTS favorites;
DROP TABLE IF EXISTS annonces;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS sharing_methods;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS localities;

-- Creating tables
CREATE TABLE localities (
    zip_code VARCHAR (5) NOT NULL,
    locality_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (zip_code, locality_name)
);

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    category_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE sharing_methods (
    id SERIAL PRIMARY KEY,
    sharing_method_name VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    nick_name VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    zip_code VARCHAR(5) NOT NULL,
    locality_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (zip_code, locality_name) REFERENCES localities(zip_code, locality_name) ON DELETE CASCADE
);

CREATE TABLE annonces (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    text_annonce TEXT NOT NULL,
    photo_link VARCHAR(255),
    user_id INT,
    sharing_method_id INT,
    category_id INT,
    date_added DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (sharing_method_id) REFERENCES sharing_methods(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

CREATE TABLE favorites (
    user_id INT,
    annonce_id INT,
    PRIMARY KEY (user_id, annonce_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (annonce_id) REFERENCES annonces(id) ON DELETE CASCADE
);
