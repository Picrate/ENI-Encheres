USE ENCHERES;
GO

-- remove contraint
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ENCHERES]') AND type in (N'U'))
ALTER TABLE ENCHERES DROP CONSTRAINT fk_encheres_articles_vendus;
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ARTICLES_VENDUS]') AND type in (N'U'))
ALTER TABLE ARTICLES_VENDUS DROP CONSTRAINT fk_articles_vendus_categories;
ALTER TABLE ARTICLES_VENDUS DROP CONSTRAINT fk_ventes_utilisateur;
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[UTILISATEURS]') AND type in (N'U'))
ALTER TABLE UTILISATEURS DROP CONSTRAINT fk_utilisateur_adresse;
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
    CONSTRAINT fk_utilisateur_adresse FOREIGN KEY (adresse_id) REFERENCES ADRESSES (id)
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
    CONSTRAINT fk_ventes_utilisateur FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur) ON DELETE CASCADE
)

CREATE TABLE ENCHERES (
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     DATETIME2 NOT NULL,
	montant_enchere  INTEGER NOT NULL,
	CONSTRAINT pk_encheres PRIMARY KEY (no_utilisateur ASC, no_article ASC),
	CONSTRAINT fk_encheres_utilisateur FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur),
    CONSTRAINT fk_encheres_articles_vendus FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS (no_article) ON DELETE CASCADE
);