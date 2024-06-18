-- Dropping tables in reverse dependency order
DROP TABLE IF EXISTS t_annonces CASCADE;
DROP TABLE IF EXISTS t_users CASCADE;
DROP TABLE IF EXISTS t_sharing_methods CASCADE;
DROP TABLE IF EXISTS t_categories CASCADE;
DROP TABLE IF EXISTS t_localities CASCADE;

-- Creating tables
CREATE TABLE t_localities (
    id SERIAL PRIMARY KEY,
    zip_code VARCHAR (5) NOT NULL,
    city_code VARCHAR(50) NOT NULL,
    longitude DECIMAL(12,9),
    latitude DECIMAL(12,9),
    UNIQUE(zip_code, city_code, latitude, longitude)
);

CREATE TABLE t_categories (
    id SERIAL PRIMARY KEY,
    category VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE t_sharing_methods (
    id SERIAL PRIMARY KEY,
    sharing_method VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE t_users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    nick_name VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE t_annonces (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    text_annonce TEXT NOT NULL,
    photo_link VARCHAR(255),
    user_id INT,
    sharing_method_id INT,
    category_id INT,
    locality_id INT,
    date_added DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES t_users(id) ON DELETE CASCADE,
    FOREIGN KEY (sharing_method_id) REFERENCES t_sharing_methods(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES t_categories(id) ON DELETE CASCADE,
    FOREIGN KEY (locality_id) REFERENCES t_localities(id) ON DELETE CASCADE
);
