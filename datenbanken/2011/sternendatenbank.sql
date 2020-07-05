-- Aufgabe 7b
DROP TABLE Objekte, Teleskop, Beobachter, betreut, beobachtet CASCADE;

CREATE TABLE Objekte(
	ID_Nr INT PRIMARY KEY,
	Bezeichnungen VARCHAR(50)[],
	Deklination VARCHAR(20),
	Rektaszension VARCHAR(20),
	Helligkeit VARCHAR(10));
CREATE TABLE Mehrfachsterne(
	Anzahl_Komponenten INT) INHERITS (Objekte);
CREATE TABLE Pulsare(
	Rotationszeit FLOAT) INHERITS (Objekte);
CREATE TABLE Nebel(
	Nebel_Typ VARCHAR(20)) INHERITS (Objekte);

CREATE TABLE Teleskop(
	TID INT PRIMARY KEY,
	Teleskop_Name VARCHAR(20),
	Standort VARCHAR(20),
	Spiegeldurchmesser FLOAT);

CREATE TABLE Beobachter(
	BID INT PRIMARY KEY,
	Name VARCHAR(40),
	Adresse VARCHAR(50),
	Qualifikation VARCHAR(20));
-- Abbildung der n:m Beziehung betreut in eine Tabelle
CREATE TABLE betreut(
	TID INT,
	BID INT,
	FOREIGN KEY (TID) REFERENCES Teleskop(TID),
	FOREIGN KEY (BID) REFERENCES Beobachter(BID));
-- Abbildung der n:m:k Beziehung beobachtet in eine Tabelle
CREATE TABLE beobachtet(
	ID_Nr INT,
	TID INT,
	BID INT,
	FOREIGN KEY (ID_Nr) REFERENCES Objekte(ID_Nr),
	FOREIGN KEY (TID) REFERENCES Teleskop(TID),
	FOREIGN KEY (BID) REFERENCES Beobachter(BID));
	
-- Aufgabe 7c
INSERT INTO Mehrfachsterne VALUES 
	(1,'{"61 Cyngi"}','21 h 06 m 53,9 s', '+38° 44'' 57,9"', '+5,21 mag',2);
INSERT INTO Mehrfachsterne VALUES 
	(2,'{"Mizar"}','13 h 23 m 55,50 s', '+54° 55'' 31,0"', '+2,3 mag',4);
INSERT INTO Pulsare VALUES 
	(3,'{"PSR B1919+21"}','19 h 19 m 16 s', '+21° 47''', '',1.337);
INSERT INTO Nebel VALUES 
	(4,'{"Krebsnebel","M 1","NGC 1952"}','5 h 34 m 31,97 s', '+22° 0'' 52,10"', '+8,4 mag','Emissionsnebel');
INSERT INTO Teleskop VALUES
	(1,'Leviatan','Birr (Irland)',1.83);
INSERT INTO Teleskop VALUES
	(2,'Hooker-Spiegel','Mount-Wilson',2.54);
INSERT INTO Beobachter VALUES
	(1,'William Parsons, 3. Earl of Rosse','Birr Castle, Ireland','Astronom');
INSERT INTO Beobachter VALUES
	(2,'George Ellery Hale','100 N. Garfield Avenue, Pasadena, CA 91109','Astrophysiker');

-- Aufgabe 7d
-- Tabelle Pulsare aendern
ALTER TABLE Pulsare ADD Aenderungsdatum DATE;
ALTER TABLE Pulsare ADD Bearbeiter VARCHAR(20);
-- Triggerfunktion anlegen
CREATE OR REPLACE FUNCTION Pulsare_geandert() RETURNS TRIGGER AS
	'
	BEGIN
	NEW.Aenderungsdatum := ''now'';
	NEW.Bearbeiter := current_user;
	RETURN NEW;
	END
	' LANGUAGE plpgsql;
-- Trigger anlegen
CREATE TRIGGER Pulsare_geandert BEFORE INSERT OR UPDATE ON Pulsare
	FOR EACH ROW EXECUTE PROCEDURE Pulsare_geandert();

-- Aufgabe 7e
CREATE VIEW Objektview AS
	SELECT Bezeichnungen, Deklination, Rektaszension, Helligkeit FROM Objekte;
