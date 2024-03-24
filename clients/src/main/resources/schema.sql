CREATE TABLE IF NOT EXISTS CLIENT (
    id INT PRIMARY KEY AUTO_INCREMENT,
    lastName VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    birthDate DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    job VARCHAR(255) NOT NULL,
    income DECIMAL(10, 2) NOT NULL
);