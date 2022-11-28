USE ENCHERES;
GO

-- remove contraint
ALTER TABLE ENCHERES DROP CONSTRAINT fk_encheres_articles_vendus;
GO
ALTER TABLE ARTICLES_VENDUS DROP CONSTRAINT fk_ventes_utilisateur;
GO
ALTER TABLE ARTICLES_VENDUS DROP CONSTRAINT fk_articles_vendus_categories;
GO
ALTER TABLE ARTICLES_VENDUS DROP CONSTRAINT fk_encheres_utilisateur;
GO
ALTER TABLE UTILISATEURS DROP CONSTRAINT fk_utilsateur_adresse;
GO

-- delete table
DROP TABLE CRYPTO;
DROP TABLE ADRESSES;
DROP TABLE CATEGORIES;
DROP TABLE ENCHERES;
DROP TABLE UTILISATEURS;
DROP TABLE ARTICLES_VENDUS;
GO

USE ENCHERES;
-- create table
CREATE TABLE CRYPTO (
    id          INT  IDENTITY (1, 1) NOT NULL,
    params      NVARCHAR (MAX) NOT NULL,
    masterKey   NVARCHAR (MAX) NOT NULL,
    seed        NVARCHAR (MAX) NOT NULL,
    CONSTRAINT pk_crypto PRIMARY KEY (id ASC)
);

CREATE TABLE ADRESSES (
    id          INT          IDENTITY (1, 1) NOT NULL,
    rue         VARCHAR (30) NOT NULL,
    code_postal VARCHAR (10) NOT NULL,
    ville       VARCHAR (30) NOT NULL,
    CONSTRAINT pk_adresse PRIMARY KEY (id ASC)
);

CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL,
    CONSTRAINT pk_categorie PRIMARY KEY (no_categorie ASC)
);

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(20) NOT NULL,
    telephone        VARCHAR(15),
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   bit NOT NULL,
    adresse_id       INT NOT NULL,
    CONSTRAINT pk_utilisateur PRIMARY KEY (no_utilisateur ASC),
    CONSTRAINT fk_utilsateur_adresse FOREIGN KEY (adresse_id) REFERENCES ADRESSES (id)
);

CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           DATETIME2 NOT NULL,
    date_fin_encheres             DATETIME2 NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL,
    CONSTRAINT pk_articles_vendus PRIMARY KEY (no_article ASC),
    CONSTRAINT fk_articles_vendus_categories FOREIGN KEY (no_categorie) REFERENCES CATEGORIES (no_categorie),
    CONSTRAINT fk_encheres_utilisateur FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur),
    CONSTRAINT fk_ventes_utilisateur FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur)
)

CREATE TABLE ENCHERES (
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     DATETIME2 NOT NULL,
	montant_enchere  INTEGER NOT NULL,
	CONSTRAINT pk_encheres PRIMARY KEY (no_utilisateur ASC, no_article ASC),
    CONSTRAINT fk_encheres_articles_vendus FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS (no_article)
);

USE ENCHERES
GO

--Insert CRYPTO
INSERT INTO CRYPTO (masterKEY, seed, params)
VALUES ('masterKEY','nZS1hrEMp2pwltGTwEWnuFJlk7uiDIjB5Lf5m/2Sgpr1G3ZT8jbj2PriYnzRoz9tDblCmLBZKMrsMOyJ8ehaaBWaxMDxPC5Kyt4CuHEK3NSPPfCE8eDNqTYgYg1HVkQpjY4NALFrLZ8qf/N5WQr5mcNZtspV3xpDogi54qNmxwAw9hhRiDOwejRR5LpZi4EhnZwhDI5s/onS70GZ0EApUDta3wwJffFC907B3uIKyp9rF4uOmc50psv7NLHpjiN1m/HWCt6wbrFH2xiKbkzYuCE9y+YFUfcb4xtkr+yS5v57sYRVrjnCnkP5DP6KvincWHUXA0Xh35W+V5ctqApOqg==' ,'MFkwOAYJKoZIhvcNAQUMMCsEFKNlki+1C3Bss6DGC8kRsfKX/GUeAgIQAAIBIDAMBggqhkiG9w0CCQUAMB0GCWCGSAFlAwQBKgQQIdihH4f+cOX/s4WUnD12cQ==');

-- Insert adresses
INSERT INTO ADRESSES (rue, code_postal, ville)
VALUES ('Rue Montgalet',75000,'PARIS');
INSERT INTO ADRESSES (rue, code_postal, ville)
VALUES ('Rue Truc',22000,'BRETAGNE');
INSERT INTO ADRESSES (rue, code_postal, ville)
VALUES ('Rue Bidule',22000,'VILLE');

-- Insert users
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,adresse_id)
VALUES ('patrice','ALLARY','patrice','patrice@patrice.fr','06.01.02.03.04','tYXhFzRSgWUzmYlh4Sus2A==',0,0,1);
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,adresse_id)
VALUES ('vincent','FILY','Vincent','vincent@vincent.fr','06.01.02.03.04','tYXhFzRSgWUzmYlh4Sus2A==',0,0,2);
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,adresse_id)
VALUES ('aurelie','ROZIER','Aurélie','aurelie@aurelie.fr','06.01.02.03.04','tYXhFzRSgWUzmYlh4Sus2A==',0,0,3);

-- Insert Categories
INSERT INTO CATEGORIES (libelle) VALUES ('Maison');
INSERT INTO CATEGORIES (libelle) VALUES ('Loisir');
INSERT INTO CATEGORIES (libelle) VALUES ('Multimedia');
INSERT INTO CATEGORIES (libelle) VALUES ('Mode');

-- Insert objets
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Vélo','Superbe vélo tout neuf','2022-10-10 20:30:00', '2022-11-10 20:30:00',50,0,1,2);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Ordinateur Windows','Le PC fonctionne mais Eclipse ne veut plus démarrer','2022-11-02 20:30:00','2022-12-02 20:30:00',70,0,1,3);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Pantalon','Quelques trous, couture à prévoir','2022-11-13 20:30:00','2022-12-13 20:30:00',5,0,1,4);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Casquette','Belle casquette rouge, bleu, noir, vert, jaune','2022-12-15 20:30:00','2022-12-15 20:30:00',10,0,1,4);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Téléphone','Marque Alcatel','2022-11-01 20:30:00','2022-12-15 20:30:00',2,0,2,3);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Lampe','Fonctionne très bien','2022-11-09 20:30:00','2022-12-09 20:30:00',10,0,2,1);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Armoire','Un peu lourde','2022-12-03 20:30:00','2022-12-25 20:30:00',50,0,3,1);

-- Insert enchère
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('2','7','2022-12-20 20:30:00', 55);
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('2','1','2022-11-10 18:30:00', 75);

USE ENCHERES;
GO

-- Select test
SELECT c.no_categorie, c.libelle
FROM CATEGORIES AS c
INNER JOIN ARTICLES_VENDUS AS av ON av.no_categorie = c.no_categorie
WHERE av.no_article = 1;

SELECT no_article, nom_article, no_categorie FROM ARTICLES_VENDUS WHERE no_categorie = 3;

SELECT a.no_article, a.nom_article
FROM ARTICLES_VENDUS AS a
INNER JOIN ENCHERES AS e ON e.no_article = a.no_article
WHERE e.no_utilisateur = 2;