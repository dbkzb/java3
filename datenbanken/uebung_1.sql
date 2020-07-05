DROP TABLE IF EXISTS angestellte CASCADE;

-- Aufgabe 1)

CREATE TABLE angestellte(
  nachname VARCHAR(30),
  vorname VARCHAR(30),
  g_datum DATE,
  pid INT PRIMARY KEY)
	WITH OIDS;

CREATE TABLE v_mitarbeiter(
  v_bereiche VARCHAR(30)[],
  j_umsatz DOUBLE PRECISION)
	INHERITS (angestellte) WITH OIDS ;

CREATE TABLE techniker(
  quali VARCHAR(30)[])
	INHERITS (angestellte) WITH OIDS ;

-- Aufgabe 3
CREATE OR REPLACE VIEW v_mitarbeiter_umsatz AS
  SELECT vorname, nachname, j_umsatz FROM v_mitarbeiter
  ORDER BY j_umsatz DESC; -- absteigende Sortierung
  
SELECT * FROM v_mitarbeiter_umsatz WHERE j_umsatz > 1e6;

-- Aufgabe 4
ALTER TABLE techniker ADD username VARCHAR(20);
ALTER TABLE techniker ADD a_datum TIMESTAMP;

CREATE OR REPLACE FUNCTION techniker_geaendert()
  RETURNS TRIGGER AS
  $$
    BEGIN
      NEW.username = CURRENT_USER;
      NEW.a_datum = CURRENT_TIMESTAMP;
      RETURN NEW;
      END
    $$ LANGUAGE plpgsql;
CREATE TRIGGER techniker_geaendert
  BEFORE INSERT OR UPDATE ON techniker
  FOR EACH ROW EXECUTE PROCEDURE techniker_geaendert();

-- Aufgabe 2
INSERT INTO
v_mitarbeiter(nachname, vorname, g_datum, v_bereiche, j_umsatz, pid)
VALUES
('Duck','Donald','1940-01-01','{"Tresore","Pleiten und Pannen"}', 100.00, 1),
('Duck','Dagobert','1940-01-01','{"Aktien","Gold"}', 5.37E12, 2);

INSERT INTO
techniker(nachname, vorname, g_datum, quali, pid)
VALUES
('Düsentrieb','Daniel','1940-01-01','{"Erfindungen","Flugzeuge"}', 3);

-- View als SQL Funktion
-- DROP FUNCTION umsatz_liste();

DROP TYPE umsatz CASCADE;
CREATE TYPE umsatz AS (
  nachname VARCHAR(30),
  vorname VARCHAR(30),
  j_umsatz DOUBLE PRECISION);

CREATE OR REPLACE FUNCTION umsatz_liste()
  RETURNS SETOF umsatz AS 
  $$
    SELECT nachname, vorname, j_umsatz FROM v_mitarbeiter
      ORDER BY j_umsatz DESC;
    $$ LANGUAGE SQL;

SELECT * FROM umsatz_liste();