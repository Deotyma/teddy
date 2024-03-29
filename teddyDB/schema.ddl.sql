-- Dropping tables in reverse dependency order
DROP TABLE IF EXISTS favorites CASCADE;
DROP TABLE IF EXISTS annonces CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS sharing_methods CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS localities CASCADE;

-- Creating tables
CREATE TABLE localities (
    id SERIAL PRIMARY KEY,
    zip_code VARCHAR (5) NOT NULL,
    city_code VARCHAR(50) NOT NULL,
    longitude DECIMAL(12,9),
    latitude DECIMAL(12,9),
    UNIQUE(zip_code, city_code, latitude, longitude)
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
    email VARCHAR(100) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    nick_name VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE annonces (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    text_annonce TEXT NOT NULL,
    photo_link VARCHAR(255),
    user_id INT,
    sharing_method_id INT,
    category_id INT,
    locality_id INT,
    date_added DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (sharing_method_id) REFERENCES sharing_methods(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
    FOREIGN KEY (locality_id) REFERENCES localities(id) ON DELETE CASCADE
);

CREATE TABLE favorites (
    user_id INT,
    annonce_id INT,
    PRIMARY KEY (user_id, annonce_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (annonce_id) REFERENCES annonces(id) ON DELETE CASCADE
);
