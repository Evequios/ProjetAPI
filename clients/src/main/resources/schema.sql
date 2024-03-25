CREATE TABLE IF NOT EXISTS CLIENT (
    id INT PRIMARY KEY AUTO_INCREMENT,
    lastName VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    birthDate DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    job VARCHAR(255) NOT NULL,
    income DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS CreditDemand (
    id INT AUTO_INCREMENT PRIMARY KEY,
    clientId INT,
    advisorId INT,
    amount DECIMAL(10, 2) NOT NULL,
    duration INT NOT NULL,
    incomes DECIMAL(10, 2) NOT NULL,
    status VARCHAR(255),
    creationDate DATE, 
    decisionDate DATE,
    FOREIGN KEY (clientId) REFERENCES Client(id),
    FOREIGN KEY (advisorId) REFERENCES BankAdvisor(id)
);