INSERT INTO CLIENT (LASTNAME, FIRSTNAME, BIRTHDATE, ADDRESS, JOB, INCOME) 
SELECT * FROM (
    SELECT 'Doe' AS LASTNAME, 'John' AS FIRSTNAME, '1990-01-01' AS BIRTHDATE, '1234 Elm St' AS ADDRESS, 'Engineer' AS JOB, 100000.00 AS INCOME
    UNION ALL
    SELECT 'Smith', 'Jane', '1995-02-02', '5678 Oak St', 'Doctor', 200000.00
    UNION ALL
    SELECT 'Johnson', 'Alice', '2000-03-03', '9101 Pine St', 'Teacher', 30000.00
    UNION ALL
    SELECT 'Brown', 'Bob', '2005-04-04', '1121 Maple St', 'Nurse', 40000.00
    UNION ALL
    SELECT 'Williams', 'Charlie', '2010-05-05', '3141 Birch St', 'Pilot', 50000.00
    UNION ALL
    SELECT 'Jones', 'David', '2015-06-06', '4151 Cedar St', 'Firefighter', 60000.00
    UNION ALL
    SELECT 'Garcia', 'Eve', '2020-07-07', '5161 Walnut St', 'Police Officer', 70000.00
    UNION ALL
    SELECT 'Martinez', 'Frank', '2025-08-08', '6171 Spruce St', 'Soldier', 80000.00
    UNION ALL
    SELECT 'Hernandez', 'Grace', '2030-09-09', '7181 Fir St', 'Astronaut', 90000.00
    UNION ALL
    SELECT 'Lopez', 'Henry', '2035-10-10', '8191 Pine St', 'Scientist', 100000.00
) AS clients
WHERE NOT EXISTS (
    SELECT 1 FROM Client
);