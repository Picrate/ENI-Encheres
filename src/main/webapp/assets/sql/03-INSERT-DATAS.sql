USE ENCHERES
GO
--Insert CRYPTO
INSERT INTO CRYPTO (masterKEY)
VALUES ('masterKEY');
-- Insert adresses 
INSERT INTO ADRESSES (rue, code_postal, ville)
VALUES ('Rue Machin',41000,'BLOIS');
INSERT INTO ADRESSES (rue, code_postal, ville)
VALUES ('Rue Truc',22000,'LANNION');
INSERT INTO ADRESSES (rue, code_postal, ville)
VALUES ('Rue Bidule',49000,'ANGERS');
-- Insert users
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,adresse_id)
VALUES ('patrice','ALLARY','patrice','patrice@patrice.fr','06.01.02.03.04','GdCmPmJXwDavS7dqzaGeyA==',35,0,1);
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,adresse_id)
VALUES ('vincent','FILY','Vincent','vincent@vincent.fr','06.01.02.03.04','GdCmPmJXwDavS7dqzaGeyA==',35,0,2);
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,adresse_id)
VALUES ('aurelie','ROZIER','Aurélie','aurelie@aurelie.fr','06.01.02.03.04','GdCmPmJXwDavS7dqzaGeyA==',35,0,3);
-- Insert parametres utilsateurs (parametres de cryptage mot de passe)
INSERT INTO USER_PARAMETERS (user_id, login_parameters) VALUES (1,'MFkwOAYJKoZIhvcNAQUMMCsEFJwpz4HBmVUHeF/mPfwGMxos487bAgIQAAIBIDAMBggqhkiG9w0CCQUAMB0GCWCGSAFlAwQBKgQQi2Kdq7Er0QPgwTG1hdsaqw==');
INSERT INTO USER_PARAMETERS (user_id, login_parameters) VALUES (2,'MFkwOAYJKoZIhvcNAQUMMCsEFJwpz4HBmVUHeF/mPfwGMxos487bAgIQAAIBIDAMBggqhkiG9w0CCQUAMB0GCWCGSAFlAwQBKgQQi2Kdq7Er0QPgwTG1hdsaqw==');
INSERT INTO USER_PARAMETERS (user_id, login_parameters) VALUES (3,'MFkwOAYJKoZIhvcNAQUMMCsEFJwpz4HBmVUHeF/mPfwGMxos487bAgIQAAIBIDAMBggqhkiG9w0CCQUAMB0GCWCGSAFlAwQBKgQQi2Kdq7Er0QPgwTG1hdsaqw==');
-- Insert Categories
INSERT INTO CATEGORIES (libelle) VALUES ('Maison');
INSERT INTO CATEGORIES (libelle) VALUES ('Loisir');
INSERT INTO CATEGORIES (libelle) VALUES ('Multimedia');
INSERT INTO CATEGORIES (libelle) VALUES ('Mode');
-- Insert objets
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Vélo','Superbe vélo tout neuf','2022-10-10 20:30:00','2022-11-10 20:30:00',50,0,1,2);
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
-- Objet : Vélo ; Vendeur : patrice ; Acheteur gagnant : vincent
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('2','1','2022-10-10 18:30:00', 105);
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('3','1','2022-11-10 18:30:00', 85);

-- Objet : Téléphone ; Vendeur : vincent 2;
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('1','5','2022-11-10 18:30:00', 25);
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('3','5','2022-11-10 18:30:00', 30);

-- Objet : Armoire ; Vendeur : aurelie ;
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('2','7','2022-12-20 20:30:00', 55);
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('1','7','2022-12-20 20:30:00', 105);
