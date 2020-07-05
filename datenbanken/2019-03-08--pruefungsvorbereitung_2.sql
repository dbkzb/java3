DROP TABLE IF EXISTS personen, objekte, adressen, besichtigungen CASCADE;

-- Entities in Tabellen abbilden

CREATE TABLE personen(
  p_id INT PRIMARY KEY,
  vorname VARCHAR(30),
  nachname VARCHAR(30),
  telefon VARCHAR(20)) WITH OIDS;

CREATE TABLE makler(
  url VARCHAR(50)) INHERITS (personen) WITH OIDS;

CREATE TABLE kunden(
  email VARCHAR(50)) INHERITS (personen) WITH OIDS;

CREATE TABLE objekte(
  o_id INT PRIMARY KEY,
  flaeche INT,
  zimmerzahl INT,
  preis NUMERIC(12,2)) WITH OIDS;

CREATE TABLE haeuser(
  nebengebaeude VARCHAR(50)[]) INHERITS (objekte) WITH OIDS;

CREATE TABLE wohnungen(
  etage INT) INHERITS (objekte) WITH OIDS;

CREATE TABLE adressen(
  a_id INT PRIMARY KEY,
  strasse VARCHAR(20),
  hausnummer VARCHAR(5),
  plz CHAR(5),
  ort VARCHAR(25)) WITH OIDS;

-- Beziehungen erstellen

ALTER TABLE personen ADD a_id INT;
ALTER TABLE personen ADD CONSTRAINT a_id_fk FOREIGN KEY (a_id) REFERENCES adressen(a_id);

ALTER TABLE objekte ADD a_id INT;
ALTER TABLE objekte ADD CONSTRAINT a_id_fk FOREIGN KEY (a_id) REFERENCES adressen(a_id);

CREATE TABLE besichtigungen(
  makler_id INT,
  kunden_id INT,
  o_id INT,
  datum DATE,
  zeit TIME,
  FOREIGN KEY (makler_id) REFERENCES personen(p_id),
  FOREIGN KEY (kunden_id) REFERENCES personen(p_id),
  FOREIGN KEY (o_id) REFERENCES objekte(o_id)) WITH OIDS;

-- Daten eintragen

INSERT INTO adressen VALUES
  (1, 'Dorfplatz', '1', '12345', 'Ödland'); -- erst Adresse, da a_id Fremdschlüssel in makler (personen)
INSERT INTO makler VALUES
  (1, 'Max', 'Muster', '0123/456', 'http://www.muster.test', 1);

INSERT INTO adressen VALUES
  (2, 'Bahnhofsplatz', '2', '34567', 'Großmusterstadt'); -- erst Adresse, da a_id Fremdschlüssel in kunden (personen)
INSERT INTO kunden VALUES
  (2, 'Erika', 'Mustermann', '0456/123456', 'mailto://em@mail.org', 2);

INSERT INTO adressen VALUES
  (3, 'Am Wald', '5', '12345', 'Ödland'),
  (4, 'Am Wald', '8', '12345', 'Ödland'); -- erst Adresse, da a_id Fremdschlüssel in objekte
INSERT INTO haeuser VALUES
  (1, 140, 5, 158000, '{"Garage", "Gartenhaus"}', 3),
  (2, 210, 8, 210000, '{"Garage", "Stall", "Scheune"}', 4);

INSERT INTO adressen VALUES
  (5, 'Bahnhofsplatz', '45', '12345', 'Ödland'),
  (6, 'Lindenallee', '34', '12345', 'Ödland'); -- erst Adresse, da a_id Fremdschlüssel in objekte
INSERT INTO wohnungen VALUES
  (3, 56, 2, 68000, 2, 5),
  (4, 86, 3, 12000, 3, 6);

-- Trigger erstellen

ALTER TABLE besichtigungen ADD zeitpunkt TIMESTAMP;
ALTER TABLE besichtigungen ADD username VARCHAR(20);

CREATE OR REPLACE FUNCTION besichtigungen_geaendert()
  RETURNS TRIGGER AS
  $$
    BEGIN
    NEW.zeitpunkt := NOW();
    NEW.username := CURRENT_USER;
    RETURN NEW;
    END;
  $$ LANGUAGE plpgsql;

CREATE TRIGGER besichtigungen_geaendert
  BEFORE INSERT OR UPDATE ON besichtigungen 
  FOR EACH ROW EXECUTE PROCEDURE besichtigungen_geaendert();

