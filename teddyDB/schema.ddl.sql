-- Dropping tables in reverse dependency order
DROP TABLE IF EXISTS favorites;
DROP TABLE IF EXISTS annonce_purchase_methods;
DROP TABLE IF EXISTS annonce_category;
DROP TABLE IF EXISTS annonces;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS purchase_methods;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS cities;

-- Creating tables
CREATE TABLE cities (
    zip_code VARCHAR(5) NOT NULL,
    city VARCHAR(150) NOT NULL,
    PRIMARY KEY (zip_code, city)
);

CREATE TABLE categories (
    id_category SERIAL PRIMARY KEY,
    category VARCHAR(150) NOT NULL
);

CREATE TABLE purchase_methods (
    id_purchase_method SERIAL PRIMARY KEY,
    purchase_method VARCHAR(150) NOT NULL
);

CREATE TABLE users (
    id_user SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    pseudo_user VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    zip_code VARCHAR(5) NOT NULL,
    city VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    FOREIGN KEY (zip_code, city) REFERENCES cities(zip_code, city) ON DELETE CASCADE
);

CREATE TABLE annonces (
    id_annonce SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    annonce_text TEXT NOT NULL,
    photo VARCHAR(255),
    date DATE NOT NULL,
    id_user INT,
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE
);

CREATE TABLE favorites (
    id_user INT NOT NULL,
    id_annonce INT NOT NULL,
    PRIMARY KEY (id_user, id_annonce),
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE,
    FOREIGN KEY (id_annonce) REFERENCES annonces(id_annonce) ON DELETE CASCADE
);

CREATE TABLE annonce_purchase_methods (
    id_annonce INT NOT NULL,
    id_purchase_method INT NOT NULL,
    PRIMARY KEY (id_annonce, id_purchase_method),
    FOREIGN KEY (id_annonce) REFERENCES annonces(id_annonce) ON DELETE CASCADE,
    FOREIGN KEY (id_purchase_method) REFERENCES purchase_methods(id_purchase_method) ON DELETE CASCADE
);

CREATE TABLE annonce_category (
    id_annonce INT NOT NULL,
    id_category INT NOT NULL,
    PRIMARY KEY (id_annonce, id_category),
    FOREIGN KEY (id_annonce) REFERENCES annonces(id_annonce) ON DELETE CASCADE,
    FOREIGN KEY (id_category) REFERENCES categories(id_category) ON DELETE CASCADE
);
