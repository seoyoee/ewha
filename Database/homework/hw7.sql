SELECT * FROM Drivers;
INSERT INTO Drivers VALUES
('Russel', '1998-02-15', 'British', 'BMW Sauber'),
('Villeneuve', '1971-04-09', 'Canadian', 'BMW Sauber');
SELECT * FROM Drivers;

SELECT * FROM Drivers;
SELECT * FROM Results;
CREATE TABLE Drivers2 (
    driver VARCHAR(20),
    birthday DATE,
    country VARCHAR(20),
    constructor VARCHAR(20),
    PRIMARY KEY (driver),
    FOREIGN KEY (constructor) REFERENCES Constructors (constructor)
);
CREATE TABLE Results2 (
    race VARCHAR(30),
    driver VARCHAR(20),
    race_rank VARCHAR(20),
    FOREIGN KEY (race) REFERENCES Races (name),
    FOREIGN KEY (driver) REFERENCES Drivers (driver)
);
INSERT INTO Drivers2
    SELECT driver, birthday, country, constructor
    FROM Drivers
    WHERE driver NOT IN (
        SELECT driver
        FROM Results
            INNER JOIN Races
            ON Results.race = Races.name AND Races.area = 'Asia'
        WHERE race_rank = 'third place'        
    );
INSERT INTO Results2
    SELECT race, driver, race_rank
    FROM Results
    WHERE driver NOT IN (
        SELECT driver
        FROM Results
            INNER JOIN Races
            ON Results.race = Races.name AND Races.area = 'Asia'
        WHERE race_rank = 'third place'        
    );
SELECT * FROM Drivers2;
SELECT * FROM Results2;

SELECT * FROM Results;
UPDATE Results
SET race_rank = 'drop out'
WHERE driver IN (
    SELECT driver
    FROM Drivers
    WHERE constructor IN (
        SELECT constructor
        FROM Constructors
        WHERE engine = 'Honda'
    )
);
SELECT * FROM Results;