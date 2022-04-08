CREATE TABLE Constructors (
    constructor VARCHAR(20),
    country VARCHAR(20),
    engine VARCHAR(20),
    races_entered INT,
    height INT,
    width INT,
    PRIMARY KEY (constructor)
);
describe Constructors;

CREATE TABLE Drivers (
    driver VARCHAR(20),
    birthday DATE,
    country VARCHAR(20),
    constructor VARCHAR(20),
    PRIMARY KEY (driver),
    FOREIGN KEY (constructor) REFERENCES Constructors (constructor)
);
describe Drivers;

CREATE TABLE Races (
    name VARCHAR(30),
    beginDate DATE,
    area VARCHAR(20),
    PRIMARY KEY (name)
) ;
describe Races;

CREATE TABLE Results (
    race VARCHAR(30),
    driver VARCHAR(20),
    race_rank VARCHAR(20),
    FOREIGN KEY (race) REFERENCES Races (name),
    FOREIGN KEY (driver) REFERENCES Drivers (driver)
);
describe Results;