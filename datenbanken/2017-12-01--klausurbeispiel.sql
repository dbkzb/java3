DROP TABLE IF EXISTS tiere, beobachter, 
		naturschutzstation, beobachtet CASCADE;

-- Aufgabe 4 b)
CREATE TABLE tiere(
  tid INT PRIMARY KEY,
  namen VARCHAR(30)[],
  wiss_name VARCHAR(30),
  art VARCHAR(30),
  gattung VARCHAR(30)) WITH OIDS;

CREATE TABLE voegel(
  rote_liste BOOLEAN) INHERITS (tiere) WITH OIDS;

CREATE TABLE lurche(
  giftig BOOLEAN) INHERITS (tiere) WITH OIDS;

CREATE TABLE saeugetiere(
  eingeschleppt BOOLEAN) INHERITS (tiere) WITH OIDS;

CREATE TABLE naturschutzstation(
  nid INT PRIMARY KEY,
  stationsname VARCHAR(30),
  breitengrad VARCHAR(15),
  laengengrad VARCHAR(15)) WITH OIDS;

CREATE TABLE beobachter(
  bid INT PRIMARY KEY,
  name VARCHAR(30),
  strasse VARCHAR(30),
  plz CHAR(5),
  ort VARCHAR(30),
  qualifikation VARCHAR(30)) WITH OIDS;

-- Beziehung betreut
-- zweistellig, 1:n --> Attribute einer Tabelle zuschlagen
ALTER TABLE naturschutzstation ADD bid INT;
ALTER TABLE naturschutzstation ADD FOREIGN KEY (bid)
  REFERENCES beobachter(bid);

-- Beziehung beobachtet
-- zweistellig, n:m --> bildet in Tabelle ab
CREATE TABLE beobachtet(
  tid INT, FOREIGN KEY (tid)REFERENCES tiere(tid),
  bid INT, FOREIGN KEY (bid) REFERENCES beobachter(bid),
  zeitpunkt TIMESTAMP,
  breitengrad VARCHAR(15),
  laengengrad VARCHAR(15),
  bemerkungen VARCHAR(255)) WITH OIDS;

-- Aufgabe 4 c)
INSERT INTO voegel VALUES
  (1, '{"Großtrappe"}', 'Otis tarda', 'Großtrappe', 'Otis', TRUE),
  (2, '{"Haussperling", "Spatz"}', 'Passer domesticus', 'Haussperling',
		'Passer', FALSE);

INSERT INTO lurche VALUES
  (3, '{"Feuersalamander", "Feuermolch", "Erdmolch"}', 
	'Salamandra salamandra', 'Feuersalamander', 'Salamandra', TRUE);

INSERT INTO saeugetiere VALUES
  (4, '{"Kleine Hufeisennase"}', 'Rhinolophus hipposideros',
	'Kleine Hufeisennase', 'Hufeisennase', FALSE);

INSERT INTO naturschutzstation VALUES
  (1, 'Zippendorf', '53,602947 N', '11,454985 O', NULL),
  (2, 'Pobershau',  '50,755572 N', '12,974854 O', NULL);

INSERT INTO beobachter VALUES
  (1, 'Carl von Linné', NULL, NULL, 'Stockholm',
     'Arz und Naturwissenschaftler'),
  (2, 'Alexander von Humbold', 'Oranienburger Str. 67', '13437',
     'Berlin', 'Naturforscher');

-- Aufgabe 4 d)
ALTER TABLE lurche ADD datum DATE;
ALTER TABLE lurche ADD benutzer VARCHAR(20);

CREATE OR REPLACE FUNCTION lurche_geaendert()
  RETURNS TRIGGER AS
  $$
    BEGIN
      NEW.datum := NOW();
      NEW.benutzer := CURRENT_USER;
      RETURN NEW;
    END
  $$ LANGUAGE plpgsql;

CREATE TRIGGER lurche_geaendert
  BEFORE INSERT OR UPDATE ON lurche
  FOR EACH ROW EXECUTE PROCEDURE lurche_geaendert();

     
