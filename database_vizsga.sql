DROP DATABASE IF EXISTS vizsgaremek_raktar;
CREATE DATABASE vizsgaremek_raktar DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci;
USE vizsgaremek_raktar;

CREATE TABLE felhasznalok (
    id INT(11)AUTO_INCREMENT PRIMARY KEY,
    nev VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    jelszo VARCHAR(60) NOT NULL, 
    jogkor ENUM('admin', 'felhasznalo') NOT NULL DEFAULT 'felhasznalo'
);

CREATE TABLE beszallitok (
    id INT(11)AUTO_INCREMENT PRIMARY KEY,
    nev VARCHAR(60) NOT NULL,
    cim VARCHAR(255),
    telefonszam VARCHAR(13),
    rangsor INT DEFAULT 0
);


CREATE TABLE termekek (
    id INT(11)AUTO_INCREMENT PRIMARY KEY,
    nev VARCHAR(60) NOT NULL,
    jelenlegi_szint INT DEFAULT 0,
    min_szint INT DEFAULT 0,
    ajanlott_szint INT DEFAULT 0
);


CREATE TABLE beszallito_arak (
    beszallito_id INT(11),
    termek_id INT(11),
    ar DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (beszallito_id, termek_id),
    FOREIGN KEY (beszallito_id) REFERENCES beszallitok(id),
    FOREIGN KEY (termek_id) REFERENCES termekek(id)
);



CREATE TABLE rendelesek (
    id INT(11)AUTO_INCREMENT PRIMARY KEY,
    user_id INT(11) NOT NULL, 
    jovahagyo_id INT(11), 
    beszallito_id INT(11),
    statusz ENUM('tervezet', 'jovahagyasra_var', 'megrendelve', 'teljesitve', 'elutasitva') DEFAULT 'tervezet',
    osszar DECIMAL(10, 2),
    letrehozas_datum DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES felhasznalok(id),
    FOREIGN KEY (jovahagyo_id) REFERENCES felhasznalok(id),
    FOREIGN KEY (beszallito_id) REFERENCES beszallitok(id)
);



CREATE TABLE rendeles_tetelek (
    id INT(11)AUTO_INCREMENT PRIMARY KEY,
    rendeles_id INT(11) NOT NULL,
    termek_id INT(11) NOT NULL,
    mennyiseg INT NOT NULL,
    ar DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (rendeles_id) REFERENCES rendelesek(id),
    FOREIGN KEY (termek_id) REFERENCES termekek(id)
);




CREATE TABLE keszletmozgasok (
    id INT(11)AUTO_INCREMENT PRIMARY KEY,
    termek_id INT(11) NOT NULL,
    tipus ENUM('bevetel', 'kiadas', 'korrekcio') NOT NULL,
    mennyiseg INT NOT NULL,
    datum DATETIME DEFAULT CURRENT_TIMESTAMP,
    megjegyzes VARCHAR(255),
    FOREIGN KEY (termek_id) REFERENCES termekek(id)
);



