CREATE TABLE IF NOT EXISTS BankAdvisor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    lastName VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Client (
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
    clientId INT NOT NULL,
    advisorId INT,
    amount DECIMAL(10, 2) NOT NULL,
    duration INT NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    birthDate DATE NOT NULL,
    incomesOnDemand DECIMAL(10, 2) NOT NULL,
    jobOnDemand VARCHAR(255) NOT NULL,
    currentStatus VARCHAR(255)NOT NULL DEFAULT 'PENDING',
    creationDate DATE DEFAULT CURRENT_DATE, 
    decisionDate DATE,
    FOREIGN KEY (clientId) REFERENCES Client(id),
    FOREIGN KEY (advisorId) REFERENCES BankAdvisor(id)
);