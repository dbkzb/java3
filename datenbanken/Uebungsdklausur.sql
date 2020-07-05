DROP TABLE IF EXISTS
  person, objekt, haus, wohnung, adresse, besichtigung
  CASCADE;
-- Aufgabe 4b

CREATE TABLE person(
  vorname VARCHAR(30),
  nachname VARCHAR(30),
  telefon VARCHAR(20),
  id INT PRIMARY KEY)
	WITH OIDS;

CREATE TABLE makler(
  url VARCHAR(30),
  mid INT PRIMARY KEY)
	INHERITS (person) WITH OIDS;

CREATE TABLE kunde(
  email VARCHAR(30),
  kid INT PRIMARY KEY)
	INHERITS (person) WITH OIDS;

CREATE TABLE objekt(
  flaeche FLOAT,
  zimmerzahl FLOAT,
  preis DOUBLE PRECISION,
  id INT PRIMARY KEY)
	WITH OIDS;

CREATE TABLE haus(
  nebengebaeude VARCHAR(50)[],
  hid INT,
  FOREIGN KEY (hid) REFERENCES objekt(ID))
	WITH OIDS;

CREATE TABLE wohnung(
  etage SMALLINT,
  wid INT,
  FOREIGN KEY (wid) REFERENCES objekt(ID))
	WITH OIDS;

CREATE TABLE adresse(
  strasse VARCHAR(30),
  hausnummer VARCHAR(5),
  plz CHAR(5),  -- wegen führender Null
  ort VARCHAR(30),
  id INT PRIMARY KEY)
	WITH OIDS;

CREATE TABLE besichtigung(
  mid INT,
  kid INT,
  obid INT,
  datum DATE,
  zeit TIME,
  FOREIGN KEY (mid) REFERENCES makler(mid),
  FOREIGN KEY (kid) REFERENCES kunde(kid),
  FOREIGN KEY (obid) REFERENCES objekt(id))
	WITH OIDS;

ALTER TABLE person ADD aid INT;
ALTER TABLE person ADD CONSTRAINT adresse_aid_fkey
	FOREIGN KEY (aid) REFERENCES  adresse(id);
ALTER TABLE objekt ADD aid INT;
ALTER TABLE objekt ADD CONSTRAINT objekt_aid_fkey
	FOREIGN KEY (aid) REFERENCES  adresse(id);

-- Aufgabe 4 c)
INSERT INTO adresse (strasse, hausnummer, plz, ort, id) VALUES
  ('Dorfplatz', 1, '12345', 'Ödland', 1),
  ('Bahnhofsplatz', 2, '34567', 'Großmusterstadt', 2),
  ('Am Wald', 5, '12345', 'Ödland', 3),
  ('Am Wald', 8, '12345', 'Ödland', 4),
  ('Bahnhofsplatz', 45, '12345', 'Ödland', 5),
  ('Lindenallee', 34, '12345', 'Ödland', 6);
  
INSERT INTO makler(vorname, nachname, telefon, url, id, mid, aid) VALUES
  ('Max', 'Muster','0123/456', 'www.muster.test', 1, 1, 1);

INSERT INTO kunde(vorname, nachname, telefon, email, id, kid, aid) VALUES
  ('Erika', 'Mustermann', '0456 / 123456', 'em@mail.org',2, 1, 2);

INSERT INTO objekt(flaeche, zimmerzahl, preis, id, aid) VALUES
  (140, 5, 158000.00, 1, 3),
  (210, 8, 210000.00, 2, 4);
INSERT INTO haus(nebengebaeude, hid) VALUES
  ('{"Garage", "Gartenhaus"}', 1),
  ('{"Garage", "Stall", "Scheune"}', 2);
  
INSERT INTO objekt(flaeche, zimmerzahl, preis, id, aid) VALUES
  (56, 2, 68000.00, 3, 5),
  (86, 3, 12000.00, 4, 6);
INSERT INTO wohnung(etage, wid) VALUES
  (2, 3),
  (3, 4);

-- Aufgabe 4 d)
ALTER TABLE besichtigung ADD uname VARCHAR(10);
ALTER TABLE besichtigung ADD aenderungsdatum DATE;

CREATE OR REPLACE FUNCTION besichtigung_datum_geandert()
  RETURNS TRIGGER AS
  $$
    BEGIN
    NEW.uname := CURRENT_USER;
    NEW.aenderungsdatum := CURRENT_DATE;
    RETURN NEW;
    END
    $$ LANGUAGE plpgsql;

 CREATE TRIGGER besichtigung_datum_geandert
   BEFORE INSERT OR UPDATE ON besichtigung
   FOR EACH ROW EXECUTE PROCEDURE besichtigung_datum_geandert();
