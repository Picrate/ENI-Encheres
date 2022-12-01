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
VALUES ('patrice','ALLARY','patrice','patrice@patrice.fr','06.01.02.03.04','tYXhFzRSgWUzmYlh4Sus2A==',200,0,1);
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,adresse_id)
VALUES ('vincent','FILY','Vincent','vincent@vincent.fr','06.01.02.03.04','tYXhFzRSgWUzmYlh4Sus2A==',200,0,2);
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,adresse_id)
VALUES ('aurelie','ROZIER','Aurélie','aurelie@aurelie.fr','06.01.02.03.04','tYXhFzRSgWUzmYlh4Sus2A==',200,0,3);
-- Insert Categories
INSERT INTO CATEGORIES (libelle) VALUES ('Maison');
INSERT INTO CATEGORIES (libelle) VALUES ('Loisir');
INSERT INTO CATEGORIES (libelle) VALUES ('Multimedia');
INSERT INTO CATEGORIES (libelle) VALUES ('Mode');
-- Insert objets
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Vélo','Superbe vélo tout neuf','20221110', '20221010',50,0,1,1);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Ordinateur Windows','Le PC fonctionne mais Eclipse ne veut plus démarrer','20221102','20221202',70,0,1,3);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Pantalon','Quelques trous, couture à prévoir','20221113','20221213',5,0,1,4);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Casquette','Belle casquette rouge, bleu, noir, vert, jaune','20221215','20221215',10,0,1,4);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Téléphone','Marque Alcatel','20221101','20221215',2,0,3,3);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Lampe','Fonctionne très bien','20221109','20221209',10,0,2,1);
INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
VALUES ('Armoire','Un peu lourde','20221203','20221203',50,0,3,1);
-- Insert enchère
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('2','7','2022-12-20 20:30:00', 55);
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('1','7','2022-12-20 20:30:00', 105);
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('2','1','2022-11-10 18:30:00', 75);
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('3','1','2022-11-10 18:30:00', 85);
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('2','5','2022-11-10 18:30:00', 25);
INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere)
VALUES ('3','5','2022-11-10 18:30:00', 30);
