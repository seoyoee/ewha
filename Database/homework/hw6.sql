SELECT COUNT(*)
FROM Constructors
WHERE engine = 'Ferrari';

SELECT MAX(birthday)
FROM Drivers
GROUP BY constructor;

SELECT AVG(height)
FROM Constructors
WHERE constructor IN (
    SELECT constructor
    FROM Drivers
    WHERE country = 'German'
);

SELECT COUNT(*)
FROM Drivers
WHERE constructor IN (
    SELECT constructor
    FROM Drivers
    WHERE driver IN (
        SELECT driver
        FROM Results
        WHERE race_rank = 'first place'
    )
)
GROUP BY constructor
HAVING COUNT(*) > 1;