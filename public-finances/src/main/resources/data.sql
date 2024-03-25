INSERT INTO BankAdvisor (LASTNAME, FIRSTNAME)
SELECT * FROM (
    SELECT 'Smith' AS LASTNAME, 'John' AS FIRSTNAME
    UNION
    SELECT 'Johnson', 'Jane'
    UNION
    SELECT 'Brown', 'Alice'
    UNION
    SELECT 'Williams', 'Bob'
    UNION
    SELECT 'Jones', 'Charlie'
    UNION
    SELECT 'Garcia', 'David'
    UNION
    SELECT 'Martinez', 'Eve'
    UNION
    SELECT 'Hernandez', 'Frank'
    UNION
    SELECT 'Lopez', 'Grace'
    UNION
    SELECT 'Gonzalez', 'Henry'
) AS bankAdvisors
WHERE NOT EXISTS (
    SELECT 1 FROM BankAdvisor
);