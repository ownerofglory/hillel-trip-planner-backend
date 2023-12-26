CREATE TABLE users (
    user_id AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    email VARCHAR(255) UNIQUE,
    password_hash VARCHAR(255),
    user_type VARCHAR(255) NOT NULL,
    external_provider VARCHAR(255),
    external_user_id VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);

CREATE TABLE address (
    address_id AUTO_INCREMENT PRIMARY KEY,
    user_id INT REFERENCES users(user_id) NOT NULL,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    home VARCHAR(255) NOT NULL,
    apartment VARCHAR(255)
);

CREATE TABLE roles (
    role_id AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user_roles (
    user_id INT REFERENCES users(user_id),
    role_id INT REFERENCES roles(role_id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE REFERENCES users(user_id)
);

CREATE TABLE staff (
    staff_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE REFERENCES users(user_id)
);

CREATE TABLE administrators (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE REFERENCES users(user_id)
);
