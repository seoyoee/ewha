SELECT * FROM Constructors;
ALTER TABLE Constructors
DROP COLUMN races_entered;
SELECT * FROM Constructors;

SELECT * FROM Constructors;
ALTER TABLE Constructors
ADD tire VARCHAR(20)
DEFAULT 'KoreanCompany';
SELECT * FROM Constructors;