SELECT country
FROM Constructors
WHERE races_entered IN (
    SELECT max(races_entered)
    FROM Constructors
);

SELECT DISTINCT constructor
FROM Drivers
WHERE driver IN (
    SELECT driver
    FROM Results
    WHERE race_rank = 'first place'
);

SELECT driver
FROM Drivers
WHERE constructor IN (
    SELECT constructor
    FROM Constructors
    WHERE engine = 'Mercedes'
);