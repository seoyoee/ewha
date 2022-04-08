SELECT constructor, country
FROM Constructors
WHERE races_entered >= 100;

SELECT driver AS newBoys
FROM Drivers
WHERE birthday >= '1980-01-01';

SELECT name
FROM Races
WHERE area = 'Europe'
ORDER BY name;

SELECT constructor
FROM Constructors
WHERE constructor LIKE '% %';