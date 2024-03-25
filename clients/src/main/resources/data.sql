INSERT INTO CLIENT (LASTNAME, FIRSTNAME, BIRTHDATE, ADDRESS, JOB, INCOME) 
SELECT * FROM (
    SELECT 'Doe' AS LASTNAME, 'John' AS FIRSTNAME, '1990-01-01' AS BIRTHDATE, '1234 Elm St' AS ADDRESS, 'Engineer' AS JOB, 100000.00 AS INCOME
    UNION
    SELECT 'Smith', 'Jane', '1995-02-02', '5678 Oak St', 'Doctor', 200000.00
    UNION
    SELECT 'Johnson', 'Alice', '2000-03-03', '9101 Pine St', 'Teacher', 30000.00
    UNION
    SELECT 'Brown', 'Bob', '2005-04-04', '1121 Maple St', 'Nurse', 40000.00
    UNION
    SELECT 'Williams', 'Charlie', '2010-05-05', '3141 Birch St', 'Pilot', 50000.00
    UNION
    SELECT 'Jones', 'David', '2015-06-06', '4151 Cedar St', 'Firefighter', 60000.00
    UNION
    SELECT 'Garcia', 'Eve', '2020-07-07', '5161 Walnut St', 'Police Officer', 70000.00
    UNION
    SELECT 'Martinez', 'Frank', '2025-08-08', '6171 Spruce St', 'Soldier', 80000.00
    UNION
    SELECT 'Hernandez', 'Grace', '2030-09-09', '7181 Fir St', 'Astronaut', 90000.00
    UNION
    SELECT 'Lopez', 'Henry', '2035-10-10', '8191 Pine St', 'Scientist', 100000.00
) AS clients
WHERE NOT EXISTS (
    SELECT 1 FROM Client
);

-- INSERT INTO CreditDemand (CLIENTID, ADVISORID, AMOUNT, DURATION, INCOMES, STATUS, DECISION, CREATIONDATE, DECISIONDATE)
-- SELECT * FROM (
--     SELECT 1 AS CLIENTID, 1 AS ADVISORID, 10000.00 AS AMOUNT, 12 AS DURATION, 100000.00 AS INCOMES, 'PENDING' AS STATUS, 'PENDING' AS DECISION, '2021-01-01' AS CREATIONDATE, NULL AS DECISIONDATE
--     UNION
--     SELECT 2, 2, 20000.00, 24, 200000.00, 'PENDING', 'PENDING', '2021-02-02', NULL
--     UNION
--     SELECT 3, 3, 30000.00, 36, 30000.00, 'PENDING', 'PENDING', '2021-03-03', NULL
--     UNION
--     SELECT 4, 4, 40000.00, 48, 40000.00, 'PENDING', 'PENDING', '2021-04-04', NULL
--     UNION
--     SELECT 5, 5, 50000.00, 60, 50000.00, 'PENDING', 'PENDING', '2021-05-05', NULL
--     UNION
--     SELECT 6, 6, 60000.00, 72, 60000.00, 'PENDING', 'PENDING', '2021-06-06', NULL
--     UNION
--     SELECT 7, 7, 70000.00, 84, 70000.00, 'PENDING', 'PENDING', '2021-07-07', NULL
--     UNION
--     SELECT 8, 8, 80000.00, 96, 80000.00, 'PENDING', 'PENDING', '2021-08-08', NULL
--     UNION
--     SELECT 9, 9, 90000.00, 108, 90000.00, 'PENDING', 'PENDING', '2021-09-09', NULL
--     UNION
--     SELECT 10, 10, 100000.00, 120, 100000.00, 'PENDING', 'PENDING', '2021-10-10', NULL
-- ) AS creditDemands
-- WHERE NOT EXISTS (
--     SELECT 1 FROM CreditDemand
-- );