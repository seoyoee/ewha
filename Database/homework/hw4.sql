SELECT driver
From Drivers
WHERE country = 'Italian';

SELECT Results.driver, Constructors.engine, Constructors.races_entered
FROM Results
    INNER JOIN Drivers
    ON Results.driver = Drivers.driver
    INNER JOIN Constructors
    ON Drivers.constructor = Constructors.constructor
WHERE race = 'Monaco Grand Prix';

SELECT driver FROM Drivers WHERE constructor = 'BMW Sauber'
UNION
SELECT driver FROM Results WHERE race = 'Spanish Grand Prix';

SELECT Co1.country
FROM Constructors Co1
    INNER JOIN Constructors Co2 
    ON Co1.country = Co2.country AND Co2.engine = 'Cosworth'
WHERE Co1.engine = 'Mercedes';

SELECT DISTINCT R1.driver
FROM Results R1
    INNER JOIN Results R2
    ON R1.driver = R2.driver AND R2.race_rank != 'first place'
WHERE R1.race_rank = 'first place';