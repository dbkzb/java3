-- erste Aktion: Datei speichern (immer mal wieder!!!!)
DROP TABLE IF EXISTS 
  tiere, naturschutzstation, beobachter, beobachtet CASCADE;

-- Aufgabe 4 b)
CREATE TABLE tiere (
  namen VARCHAR(25)[],
  wiss_name VARCHAR(50),
  art VARCHAR(30),
  gattung VARCHAR(30),
  id_nr INT PRIMARY KEY) WITH OIDS;

CREATE TABLE voegel (
  rote_liste BOOLEAN) INHERITS (tiere) WITH OIDS;

CREATE TABLE lurche (
  giftig BOOLEAN) INHERITS (tiere) WITH OIDS;

CREATE TABLE saeugetiere (
  eingeschleppt BOOLEAN) INHERITS (tiere) WITH OIDS;

CREATE TABLE naturschutzstation (
  stationsname VARCHAR(20),
  breitengrad VARCHAR(15),
  laengengrad VARCHAR(15),
  id INT PRIMARY KEY) WITH OIDS;

CREATE TABLE beobachter (
  name VARCHAR(30),
  strasse VARCHAR(30),
  plz CHAR(5),
  ort VARCHAR(30),
  qualifikation VARCHAR(30),
  id INT PRIMARY KEY) WITH OIDS;

CREATE TABLE beobachtet (
  zeitpunkt TIMESTAMP,
  breitengrad VARCHAR(15),
  laengengrad VARCHAR(15),
  bemerkungen TEXT,
  id INT PRIMARY KEY,
  tiere_id INT,
  beobachter_id INT,
  FOREIGN KEY (tiere_id) REFERENCES tiere(id_nr),
  FOREIGN KEY (beobachter_id) REFERENCES beobachter(id)) WITH OIDS;

ALTER TABLE naturschutzstation ADD betreuer_id INT;
ALTER TABLE naturschutzstation ADD FOREIGN KEY (betreuer_id) 
  REFERENCES beobachter(id);

-- 4 c)

INSERT INTO voegel VALUES 
  ('{"Großtrappe"}', 'Otis tarda', 'Großtrappe', 'Otis', 1, TRUE),
  ('{"Haussperling", "Spatz"}', 'Passer domesticus', 'Haussperling',
						'Passer', 2, FALSE);

INSERT INTO lurche VALUES
  ('{"Feuersalamander", "Feuermolch", "Erdmolch"}',
	'Salamandra salamandra', 'Feuersalamander', 'Salamandra', 3, TRUE);

INSERT INTO saeugetiere VALUES
  ('{"Kleine Hufeisennase"}','Rhinolophus hipposideros',
	'Kleine Hufeisennase', 'Hufeisennasen', 4, FALSE);

INSERT INTO naturschutzstation VALUES
  ('Zippendorf', '53,602947 N', '11.454985 O', 1),
  ('Pobershau',  '50,755572 N', '12.974854 O', 2);

INSERT INTO beobachter VALUES
  ('Carl von Linné', NULL, NULL, 'Stockholm',
	'Arzt und Naturwissenschaftler', 1),
  ('Alexander von Humbold', 'Oranienburger Straße 67', 13437, 'Berlin',
	'Naturforscher', 2);


-- 4 d)
-- Tabelle Lurche ergänzen
ALTER TABLE lurche ADD datum DATE;
ALTER TABLE lurche ADD uname VARCHAR(15);
-- Triggerfunktion
CREATE OR REPLACE FUNCTION lurche_geaendert() RETURNS TRIGGER AS $$
  BEGIN
    NEW.datum := now();
    NEW.uname := current_user;
    RETURN NEW;
    END
  $$ LANGUAGE plpgsql;
-- Trigger anlegen
CREATE TRIGGER lurche_geaendert
  BEFORE INSERT OR UPDATE ON lurche
  FOR EACH ROW EXECUTE PROCEDURE lurche_geaendert();
