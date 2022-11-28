USE master;
GO
DROP DATABASE IF EXISTS ENCHERES;
GO
CREATE DATABASE ENCHERES;
GO
USE ENCHERES;
GO
CREATE USER utilisateurBDD FOR LOGIN utilisateurBDD;
GO
ALTER ROLE db_owner	ADD MEMBER utilisateurBDD;
GO
/*
* DROP CONTRAINTES
*/
ALTER TABLE [dbo].[ARTICLES_VENDUS] DROP CONSTRAINT [articles_vendus_categories_fk]
ALTER TABLE [dbo].[ARTICLES_VENDUS] DROP CONSTRAINT [encheres_utilisateur_fk]
ALTER TABLE [dbo].[ARTICLES_VENDUS] DROP CONSTRAINT [ventes_utilisateur_fk]
GO
ALTER TABLE [dbo].[ENCHERES] DROP CONSTRAINT [encheres_articles_vendus_fk]
GO
ALTER TABLE [dbo].[RETRAITS] DROP CONSTRAINT [retraits_articles_vendus_fk]
GO
ALTER TABLE [dbo].[UTILISATEURS] DROP CONSTRAINT [FK_utilsateur_adresse]
GO
/*
* DROP TABLE CRYPTO
*/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CRYPTO]') AND type in (N'U'))
DROP TABLE [dbo].[CRYPTO]
GO
/*
* DROP TABLE ADRESSE
*/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ADRESSES]') AND type in (N'U'))
DROP TABLE [dbo].[ADRESSES]
GO
/*
* DROP TABLE CATEGORIES
*/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CATEGORIES]') AND type in (N'U'))
DROP TABLE [dbo].[CATEGORIES]
GO
/*
* DROP TABLE ENCHERES
*/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ENCHERES]') AND type in (N'U'))
DROP TABLE [dbo].[ENCHERES]
GO
/*
* DROP TABLE ENCHERES
*/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[RETRAITS]') AND type in (N'U'))
DROP TABLE [dbo].[RETRAITS]
GO
/*
* DROP TABLE UTILISATEURS
*/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[UTILISATEURS]') AND type in (N'U'))
DROP TABLE [dbo].[UTILISATEURS]
GO
/*
* CREATION DES TABLES
*/
/*
* CREATION TABLE CRYPTO
*/
CREATE TABLE [dbo].[CRYPTO] (
    [id]        INT            IDENTITY (1, 1) NOT NULL,
    [params]    NVARCHAR (MAX) NOT NULL,
    [masterKey] NVARCHAR (MAX) NOT NULL,
    [seed]      NVARCHAR (MAX) NOT NULL,
    CONSTRAINT [PK_CRYPTO] PRIMARY KEY CLUSTERED ([id] ASC)
);
GO
/*
* CREATION TABLE ADRESSE
*/
CREATE TABLE [dbo].[ADRESSES] (
    [id]          INT          IDENTITY (1, 1) NOT NULL,
    [rue]         VARCHAR (30) NOT NULL,
    [code_postal] VARCHAR (10) NOT NULL,
    [ville]       VARCHAR (30) NOT NULL,
    CONSTRAINT [pk_adresse] PRIMARY KEY CLUSTERED ([id] ASC)
);
GO
/*
* CREATION TABLE CATEGORIES
*/
CREATE TABLE [dbo].[CATEGORIES] (
    [no_categorie] INT          IDENTITY (1, 1) NOT NULL,
    [libelle]      VARCHAR (30) NOT NULL,
    CONSTRAINT [categorie_pk] PRIMARY KEY CLUSTERED ([no_categorie] ASC)
);
GO
/*
* CREATION TABLE UTILISATEURS
*/
CREATE TABLE [dbo].[UTILISATEURS] (
    [no_utilisateur] INT          IDENTITY (1, 1) NOT NULL,
    [pseudo]         VARCHAR (30) NOT NULL,
    [nom]            VARCHAR (30) NOT NULL,
    [prenom]         VARCHAR (30) NOT NULL,
    [email]          VARCHAR (50) NOT NULL,
    [telephone]      VARCHAR (15) NULL,
    [mot_de_passe]   VARCHAR (30) NOT NULL,
    [credit]         INT          DEFAULT ((0)) NOT NULL,
    [administrateur] BIT          DEFAULT ((0)) NOT NULL,
    [adresse_id]     INT          NOT NULL,
    CONSTRAINT [utilisateur_pk] PRIMARY KEY CLUSTERED ([no_utilisateur] ASC),
    CONSTRAINT [FK_utilsateur_adresse] FOREIGN KEY ([adresse_id]) REFERENCES [dbo].[ADRESSES] ([id])
);
GO
/*
* CREATION TABLE ARTICLES
*/
CREATE TABLE [dbo].[ARTICLES_VENDUS] (
    [no_article]          INT           IDENTITY (1, 1) NOT NULL,
    [nom_article]         VARCHAR (30)  NOT NULL,
    [description]         VARCHAR (300) NOT NULL,
    [date_debut_encheres] DATE          NOT NULL,
    [date_fin_encheres]   DATE          NOT NULL,
    [prix_initial]        INT           NULL,
    [prix_vente]          INT           NULL,
    [no_utilisateur]      INT           NOT NULL,
    [no_categorie]        INT           NOT NULL,
    CONSTRAINT [articles_vendus_pk] PRIMARY KEY CLUSTERED ([no_article] ASC),
    CONSTRAINT [articles_vendus_categories_fk] FOREIGN KEY ([no_categorie]) REFERENCES [dbo].[CATEGORIES] ([no_categorie]),
    CONSTRAINT [encheres_utilisateur_fk] FOREIGN KEY ([no_utilisateur]) REFERENCES [dbo].[UTILISATEURS] ([no_utilisateur]),
    CONSTRAINT [ventes_utilisateur_fk] FOREIGN KEY ([no_utilisateur]) REFERENCES [dbo].[UTILISATEURS] ([no_utilisateur])
);
GO
/*
* CREATION TABLE ENCHERES
*/
CREATE TABLE [dbo].[ENCHERES] (
    [no_utilisateur]  INT      NOT NULL,
    [no_article]      INT      NOT NULL,
    [date_enchere]    DATETIME NOT NULL,
    [montant_enchere] INT      NOT NULL,
    CONSTRAINT [enchere_pk] PRIMARY KEY CLUSTERED ([no_utilisateur] ASC, [no_article] ASC),
    CONSTRAINT [encheres_articles_vendus_fk] FOREIGN KEY ([no_article]) REFERENCES [dbo].[ARTICLES_VENDUS] ([no_article])
);
GO



