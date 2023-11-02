-- Dropping tables in reverse dependency order
DROP TABLE IF EXISTS favorites;
DROP TABLE IF EXISTS annonces;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS shering_methods;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS localities;

CREATE TABLE localities (
zip_code VARCHAR (5) NOT NULL,
locality_name VARCHAR(150) NOT NULL,
PRIMARY KEY (zip_code, locality_name)

);

CREATE TABLE categories (
	category_name VARCHAR(50) PRIMARY KEY
);

CREATE TABLE shering_methods (
	shering_method_name VARCHAR(15) PRIMARY KEY
);

CREATE TABLE users (
 	email VARCHAR(255) PRIMARY KEY UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    nick_name VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    zip_code VARCHAR(5) NOT NULL,
    locality_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (zip_code, locality_name) REFERENCES localities(zip_code, locality_name) ON DELETE CASCADE
);


CREATE TABLE annonces (
    id_annonce SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    text_annonce TEXT NOT NULL,
    photo_link VARCHAR(255),
    email VARCHAR(255),
    shering_method_name VARCHAR(15),
    category_name VARCHAR(50),
    date DATE NOT NULL,
    FOREIGN KEY (email) REFERENCES users(email) ON DELETE CASCADE,
    FOREIGN KEY (shering_method_name) REFERENCES shering_methods(shering_method_name) ON DELETE CASCADE,
    FOREIGN KEY (category_name) REFERENCES categories(category_name) ON DELETE CASCADE
);


CREATE TABLE favorites (
    email VARCHAR(255),
    id_annonce INT NOT NULL,
    PRIMARY KEY (email, id_annonce),
    FOREIGN KEY (email) REFERENCES users(email) ON DELETE CASCADE,
    FOREIGN KEY (id_annonce) REFERENCES annonces(id_annonce) ON DELETE CASCADE
);

