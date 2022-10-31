/*se connecter à SQLPlus avec l'utilisateur system*/
connect system/mekki123
/*Partie I : Création des TablesSpaces et des utilisateurs*/
/*--- création des tablespace-------------*/
/*si les tablespaces existent déjà voici la commande pour les supprimer
drop tablespace nom de tablespace including contents and datafiles;
*/
/*si l'utilsateur existe déjà voici la commande pour le supprimer
drop user nom utilisateur cascade;
*/
create tablespace intervention_tbs datafile 'c:\intervention_tbs.dat' size 100M autoextend on online;
\
create temporary tablespace intervention_temptbs tempfile 'c:\intervention_temptbstbs.dat' size 100M autoextend on;
\
/*--- création de l'utilisateur------------*/
create user dbaintervention identified by mekki123 
default tablespace intervention_tbs 
temporary tablespace intervention_temptbs ;
\
/*vérfier la création*/
desc dba_users;
\
select username, created from dba_users where username=upper('dbaintervention');
\
/*---- accorder les touts les droits----------*/
grant all privileges to dbaintervention ;
\
/*--- connexion avec l'utilisateur dbaintervention*/
connect dbaintervention/mekki123
\
/*vérfier la connexion*/
show user;
\
---modifier le format date 
alter session set nls_date_format = 'DD/MM/RRRR HH24:MI:SS';
\
/*----création des tables de la BD--------------*/
---table client
create table CLIENT (NUMCLIENT integer, CIV varchar2(3),
NOM varchar2(50), PRENOM varchar2(50), DATENAISSANCE date,  
ADRESSE varchar2(100), TELPROF varchar2(10), 
TELPRIV varchar2(10), FAX varchar2(10), 
constraint pk_client primary key(NUMCLIENT), 
constraint ck_civ check (CIV in ('M.','Mle', 'Mme')) );
/*vérifier la création*/
desc client;
/*ou bien*/
select table_name from tabs;
---table employe
create table EMPLOYE (NUMEMPLOYE integer, NOMEMP varchar2(50), 
PRENOMEMP varchar2(50), CATEGORIE varchar2(30), SALAIRE number, 
constraint pk_employe primary key(NUMEMPLOYE), 
constraint ck_categorie check(CATEGORIE in('Mécanicien','Assistant')));
---table MARQUE 
create table MARQUE (NUMMARQUE integer, MARQUE varchar2(50), 
PAYS varchar2(50), constraint pk_marque primary key (NUMMARQUE));
---table modele
create table  MODELE (NUMMODELE integer, NUMMARQUE integer, 
MODELE varchar2(100), constraint pk_modele primary key(NUMMODELE),
constraint fk_marque foreign key(NUMMARQUE) references marque(NUMMARQUE) 
on delete cascade);
---table vehicule
create table  VEHICULE (NUMVEHICULE integer, NUMCLIENT integer, 
NUMMODELE integer, NUMIMMAT varchar2(20), ANNEE varchar2(4),
constraint pk_vehicule primary key(NUMVEHICULE), 
constraint fk_client foreign key(NUMCLIENT) references client(NUMCLIENT) 
on delete cascade,
constraint fk_modele foreign key(NUMMODELE) references modele (NUMMODELE) 
on delete cascade);
---table  INTERVENTIONS 
create table INTERVENTIONS(NUMINTERVENTION integer, NUMVEHICULE integer, TYPEINTERVENTION varchar2(50), DATEDEBINTERV date, 
DATEFININTERV date,  COUTINTERV number, constraint pk_intervention primary key(NUMINTERVENTION),
constraint fk_vehicule foreign key( NUMVEHICULE) references vehicule (NUMVEHICULE) on delete cascade);
--- table INTERVENANTS 
create table INTERVENANTS(NUMINTERVENTION integer, NUMEMPLOYE integer, DATEDEBUT date, DATEFIN date,
constraint pk_intervenants primary key(NUMINTERVENTION, NUMEMPLOYE),
constraint fk_interventions foreign key(NUMINTERVENTION) references interventions (NUMINTERVENTION) on delete cascade,
constraint fk_employe foreign key (NUMEMPLOYE) references employe (NUMEMPLOYE) on delete cascade);
---table erreurs
CREATE TABLE TableErreurs   (adresse ROWID, utilisateur VARCHAR2(30), nomTable VARCHAR2(30),nomContrainte VARCHAR2(30));
/*vérifier la création de tous les tables*/
select table_name from tabs;
/*---------- modification du schéma-------------------*/
/*5.	Ajouter l’attribut  DATEINSTALLATION  de type Date dans la relation EMPLOYE.*/
desc employe
alter table employe add DATEINSTALLATION   date;
desc employe
/*6.	Ajouter la contrainte not null pour les attributs CATEGORIE, SALAIRE de la relation EMPLOYE.*/
desc employe
alter table employe modify categorie not null;
desc employe
alter table employe add constraint ck_salaire_null check(SALAIRE  is not null);
select constraint_name, constraint_type from user_constraints where table_name=upper('employe');
/*7.	Modifier la longueur de l’attribut PRENOMEMP (agrandir, réduire).*/
desc employe
alter table employe modify PRENOMEMP varchar2(51);
desc employe
alter table employe modify PRENOMEMP varchar2(50);
desc employe
/*8.	Supprimer la colonne DATEINSTALLATION  dans la table EMPLOYE. Vérifier la suppression. */
desc employe
alter table employe drop column DATEINSTALLATION;
desc employe
/*9.	Renommer la colonne ADRESSE dans la table CLIENT par  ADRESSECLIENT. Vérifier.*/
desc client
alter table  client rename column ADRESSE  to ADRESSECLIENT;
desc client
/*10.	Ajouter la contrainte suivante : Date de début d’intervention doit être inferieur à la date de fin d’intervention.*/
select constraint_name, constraint_type from user_constraints where table_name=upper('interventions');
alter table interventions add constraint ck_dateinter check(DATEDEBINTERV<DATEFININTERV);
select constraint_name, constraint_type from user_constraints where table_name=upper('interventions');
/*Partie III : Langage de manipulation de données*/
/* 11.	Remplir toutes les tables par les instances 
représentées ci-dessus en exécutant le script insert.sql. 
Quels sont les problèmes rencontrés?
--utiliser une des deux commandes 
--START Nom_Fichier[.sql] ou @Nom_Fichier[.sql] il faut mettre
le chemin  jusqu'à insert.sql
-- ou bien exécuter par table (copier coller)*/
--table client
INSERT INTO CLIENT VALUES (1,'Mme','Cherifa','MAHBOUBA','08/08/1957','CITE 1013 LOGTS BT 61 Alger','0561381813','0562458714','');
INSERT INTO CLIENT VALUES (2,'Mme','Lamia','TAHMI','31/12/1955','CITE BACHEDJARAH BATIMENT 38 -Bach Djerrah-Alger','0562467849','0561392487','');
INSERT INTO CLIENT VALUES (3,'Mle','Ghania','DIAF AMROUNI','31/12/1955','43, RUE ABDERRAHMANE SBAA BELLE VUE-EL HARRACH-ALGER','0523894562','0619430945','0562784254');
INSERT INTO CLIENT VALUES (4,'Mle','Chahinaz','MELEK','27/06/1955','HLM AISSAT IDIR CAGE 9 3EME ETAGE-EL HARRACH ALGER','0634613493','0562529463','');
INSERT INTO CLIENT VALUES (5,'Mme','Noura','TECHTACHE','22/03/1949','16, ROUTE EL DJAMILA-AINBENIAN-ALGER','0562757834','','0562757843');
INSERT INTO CLIENT VALUES (6,'Mme','Widad','TOUATI','14/08/1965','14 RUE DES FRERES AOUDIA-EL MOURADIA-ALGER','0561243967','0561401836','');
INSERT INTO CLIENT VALUES (7,'Mle','Faiza','ABLOUL','28/10/1967','CITE DIPLOMATIQUE BT BLEU 14B N 3 DERGANA- ALGER','0562935427','0561486203','');
INSERT INTO CLIENT VALUES (8,'Mme','Assia','HORRA','08/12/1963','32 RUE AHMED OUAKED-DELY BRAHIM-ALGER','0561038500','','0562466733');
INSERT INTO CLIENT VALUES (9,'Mle','Souad','MESBAH','30/08/1972','RESIDENCE CHABANI-HYDRA-ALGER','0561024358','','');
INSERT INTO CLIENT VALUES (10,'Mme','Houda','GROUDA','20/02/1950','EPSP THNIET ELABED BATNA','0562939495','0561218456','');
INSERT INTO CLIENT VALUES (11,'Mle','Saida','FENNICHE','','CITE DE L''INDEPENDANCE LARBAA BLIDA','0645983165','0562014784','');
INSERT INTO CLIENT VALUES (12,'Mme','Samia','OUALI','17/11/1966','CITE 200 LOGEMENTS BT1 N1-JIJEL','0561374812','0561277013','');
INSERT INTO CLIENT VALUES (13,'Mme','Fatiha','HADDAD','20/09/1980','RUE BOUFADA LAKHDARAT-AIN OULMANE-SETIF','0647092453','0562442700','');
INSERT INTO CLIENT VALUES (14,'M.','Djamel','MATI','','DRAA KEBILA HAMMAM GUERGOUR SETIF','0561033663','0561484259','');
INSERT INTO CLIENT VALUES (15,'M.','Mohamed','GHRAIR','24/06/1950','CITE JEANNE D''ARC ECRAN B5- GAMBETTA – ORAN','0561390288','','0562375849');
INSERT INTO CLIENT VALUES (16,'M.','Ali','LAAOUAR','','CITE 1ER MAI EX 137 LOGEMENTS-ADRAR','0639939410','0561255412','');
INSERT INTO CLIENT VALUES (17,'M.','Messoud','AOUIZ','24/11/1958','RUE SAIDANI ABDESSLAM -AIN BESSEM-BOUIRA','0561439256','0561473625','');
INSERT INTO CLIENT VALUES (18,'M.','Farid','AKIL','06/05/1961','3 RUE LARBI BEN M''HIDI-DRAA EL MIZAN-TIZI OUZOU','0562349254','0561294268','');
INSERT INTO CLIENT VALUES (19,'Mme','Dalila','MOUHTADI','','6, BD TRIPOLI ORAN','0506271459','0506294186','');
INSERT INTO CLIENT VALUES (20,'M.','Younes','CHALAH','','CITE DES 60 LOGTS BT D N 48- NACIRIA-BOUMERDES','','0561358279','');
INSERT INTO CLIENT VALUES (21,'M.','Boubeker','BARKAT','08/11/1935','CITE MENTOURI N 71 BT AB SMK Constantine','0561824538','0561326179','');
INSERT INTO CLIENT VALUES (22,'M.','Seddik','HMIA','','25 RUE BEN YAHIYA-JIJEL','0562379513','','0562493627');
INSERT INTO CLIENT VALUES (23,'M.','Lamine','MERABAT','09/13/1965','CITE JEANNE D''ARC ECRAN B2-GAMBETTA – ORAN','0561724538','0561724538','');
/*vérifier l'insertion des lignes dans la table client*/
/*ajuster l'affichage nombre de caractères sur une lignes et nombre de lignes sur une page*/
set linesize 150
set pagesize 100
select * from client;
--table employe
INSERT INTO EMPLOYE VALUES(53,'LACHEMI','Bouzid','Mécanicien',25000);
INSERT INTO EMPLOYE VALUES(54,'BOUCHEMLA','Elias','Assistant',10000);
INSERT INTO EMPLOYE VALUES(55,'HADJ','Zouhir','Assistant',12000);
INSERT INTO EMPLOYE VALUES(56,'OUSSEDIK','Hakim','Mécanicien',20000);
INSERT INTO EMPLOYE VALUES(57,'ABAD','Abdelhamid','Assistant',13000);
INSERT INTO EMPLOYE VALUES(58,'BABACI','Tayeb','Mécanicien',21300);
INSERT INTO EMPLOYE VALUES(59,'BELHAMIDI','Mourad','Mécanicien',19500);
INSERT INTO EMPLOYE VALUES(60,'IGOUDJIL','Redouane','Assistant',15000);
INSERT INTO EMPLOYE VALUES(61,'KOULA','Bahim','Mécanicien',23100);
INSERT INTO EMPLOYE VALUES(62,'RAHALI','Ahcene','Mécanicien',24000);
INSERT INTO EMPLOYE VALUES(63,'CHAOUI','Ismail','Assistant',13000);
INSERT INTO EMPLOYE VALUES(64,'BADI','Hatem','Assistant',14000);
INSERT INTO EMPLOYE VALUES(65,'MOHAMMEDI','Mustapha','Mécanicien',24000);
INSERT INTO EMPLOYE VALUES(66,'FEKAR','Abdelaziz','Assistant',13500);
INSERT INTO EMPLOYE VALUES(67,'SAIDOUNI','Wahid','Mécanicien',25000);
INSERT INTO EMPLOYE VALUES(68,'BOULARAS','Farid','Assistant',14000);
INSERT INTO EMPLOYE VALUES(69,'CHAKER','Nassim','Mécanicien',26000);
INSERT INTO EMPLOYE VALUES(71,'TERKI','Yacine','Mécanicien',23000);
INSERT INTO EMPLOYE VALUES(72,'TEBIBEL','Ahmed','Assistant',17000);
INSERT INTO EMPLOYE VALUES(80,'LARDJOUNE','Karim','',25000);
-- table marque
INSERT INTO MARQUE VALUES(1,'LAMBORGHINI','ITALIE');
INSERT INTO MARQUE VALUES(2,'AUDI','ALLEMAGNE');
INSERT INTO MARQUE VALUES(3,'ROLLS-ROYCE','GRANDE-BRETAGNE');
INSERT INTO MARQUE VALUES(4,'BMW','ALLEMAGNE');
INSERT INTO MARQUE VALUES(5,'CADILLAC','ETATS-UNIS');
INSERT INTO MARQUE VALUES(6,'CHRYSLER','ETATS-UNIS');
INSERT INTO MARQUE VALUES(7,'FERRARI','ITALIE');
INSERT INTO MARQUE VALUES(8,'HONDA','JAPON');
INSERT INTO MARQUE VALUES(9,'JAGUAR','GRANDE-BRETAGNE');
INSERT INTO MARQUE VALUES(10,'ALFA-ROMEO','ITALIE');
INSERT INTO MARQUE VALUES(11,'LEXUS','JAPON');
INSERT INTO MARQUE VALUES(12,'LOTUS','GRANDE-BRETAGNE');
INSERT INTO MARQUE VALUES(13,'MASERATI','ITALIE');
INSERT INTO MARQUE VALUES(14,'MERCEDES','ALLEMAGNE');
INSERT INTO MARQUE VALUES(15,'PEUGEOT','FRANCE');
INSERT INTO MARQUE VALUES(16,'PORSCHE','ALLEMAGNE');
INSERT INTO MARQUE VALUES(17,'RENAULT','FRANCE');
INSERT INTO MARQUE VALUES(18,'SAAB','SUEDE');
INSERT INTO MARQUE VALUES(19,'TOYOTA','JAPON');
INSERT INTO MARQUE VALUES(20,'VENTURI','FRANCE');
INSERT INTO MARQUE VALUES(21,'VOLVO','SUEDE');
--table modele
INSERT INTO MODELE VALUES(2,1,'Diablo');
INSERT INTO MODELE VALUES(3,2,'Serie 5');
INSERT INTO MODELE VALUES(4,10,'NSX');
INSERT INTO MODELE VALUES(5,14,'Classe C');
INSERT INTO MODELE VALUES(6,17,'Safrane');
INSERT INTO MODELE VALUES(7,20,'400 GT');
INSERT INTO MODELE VALUES(8,12,'Esprit');
INSERT INTO MODELE VALUES(9,15,'605');
INSERT INTO MODELE VALUES(10,19,'Previa');
INSERT INTO MODELE VALUES(11,7,'550 Maranello');
INSERT INTO MODELE VALUES(12,3,'Bentley-Continental');
INSERT INTO MODELE VALUES(13,10,'Spider');
INSERT INTO MODELE VALUES(14,13,'Evoluzione');
INSERT INTO MODELE VALUES(15,16,'Carrera');
INSERT INTO MODELE VALUES(16,16,'Boxter');
INSERT INTO MODELE VALUES(17,21,'S 80');
INSERT INTO MODELE VALUES(18,6,'300 M');
INSERT INTO MODELE VALUES(19,4,'M 3');
INSERT INTO MODELE VALUES(20,9,'XJ 8');
INSERT INTO MODELE VALUES(20,9,'XJ 8');
INSERT INTO MODELE VALUES(21,15,'406 Coupe');
INSERT INTO MODELE VALUES(22,20,'300 Atlantic');
INSERT INTO MODELE VALUES(23,14,'Classe E');
INSERT INTO MODELE VALUES(24,11,'GS 300');
INSERT INTO MODELE VALUES(25,5,'Seville');
INSERT INTO MODELE VALUES(26,18,'95 Cabriolet');
INSERT INTO MODELE VALUES(27,2,'TT Coupé');
INSERT INTO MODELE VALUES(28,7,'F 355');
INSERT INTO MODELE VALUES(29,45,'POLO');
---table vehicule
INSERT INTO VEHICULE VALUES(1,2,6,0012519216,1992);
INSERT INTO VEHICULE VALUES(2,9,20,0124219316,1993);
INSERT INTO VEHICULE VALUES(3,17,8,1452318716,1987);
INSERT INTO VEHICULE VALUES(4,6,12,3145219816,1998);
INSERT INTO VEHICULE VALUES(5,16,23,1278919816,1998);
INSERT INTO VEHICULE VALUES(6,20,6,3853319735,1997);
INSERT INTO VEHICULE VALUES(7,7,8,1453119816,1998);
INSERT INTO VEHICULE VALUES(8,16,14,8365318601,1986);
INSERT INTO VEHICULE VALUES(9,13,15,3087319233,1992);
INSERT INTO VEHICULE VALUES(10,20,22,9413119935,1999);
INSERT INTO VEHICULE VALUES(11,9,16,1572319801,1998);
INSERT INTO VEHICULE VALUES(12,14,20,6025319733,1997);
INSERT INTO VEHICULE VALUES(13,19,17,5205319736,1997);
INSERT INTO VEHICULE VALUES(14,22,21,7543119207,1992);
INSERT INTO VEHICULE VALUES(15,4,19,6254319916,1999);
INSERT INTO VEHICULE VALUES(16,16,21,9831419701,1997);
INSERT INTO VEHICULE VALUES(17,12,11,4563117607,1976);
INSERT INTO VEHICULE VALUES(18,1,2,7973318216,1982);
INSERT INTO VEHICULE VALUES(19,18,77,3904318515,1985);
INSERT INTO VEHICULE VALUES(20,22,2,1234319707,1997);
INSERT INTO VEHICULE VALUES(21,3,19,8429318516,1985);
INSERT INTO VEHICULE VALUES(22,8,19,1245619816,1998);
INSERT INTO VEHICULE VALUES(23,7,25,1678918516,1985);
INSERT INTO VEHICULE VALUES(24,80,9,1789519816,1998);
INSERT INTO VEHICULE VALUES(25,13,5,1278919833,1998);
INSERT INTO VEHICULE VALUES(26,3,10,1458919316,1993);
INSERT INTO VEHICULE VALUES(27,10,7,1256019804,1998);
INSERT INTO VEHICULE VALUES(28,10,3,1986219904,1999);
--table interventions
INSERT INTO INTERVENTIONS  VALUES(1,3,'Réparation',TO_DATE('2006-02-25 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-26 12:00:00','RRRR-MM-DD HH24:MI:SS'),30000);
INSERT INTO INTERVENTIONS  VALUES(2,21,'Réparation',TO_DATE('2006-02-23 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-24 18:00:00','RRRR-MM-DD HH24:MI:SS'),10000);
INSERT INTO INTERVENTIONS  VALUES(3,25,'Réparation',TO_DATE('2006-04-06 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-04-09 12:00:00','RRRR-MM-DD HH24:MI:SS'),42000);
INSERT INTO INTERVENTIONS  VALUES(4,10,'Entretien',TO_DATE('2006-05-14 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-14 18:00:00','RRRR-MM-DD HH24:MI:SS'),10000);
INSERT INTO INTERVENTIONS  VALUES(5,6,'Réparation',TO_DATE('2006-02-22 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-25 18:00:00','RRRR-MM-DD HH24:MI:SS'),40000);
INSERT INTO INTERVENTIONS  VALUES(6,14,'Entretien',TO_DATE('2006-03-03 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-03-04 18:00:00','RRRR-MM-DD HH24:MI:SS'),7500);
INSERT INTO INTERVENTIONS  VALUES(7,1,'Entretien',TO_DATE('2006-04-09 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-04-09 18:00:00','RRRR-MM-DD HH24:MI:SS'),8000);
INSERT INTO INTERVENTIONS  VALUES(8,17,'Entretien',TO_DATE('2006-05-11 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-12 18:00:00','RRRR-MM-DD HH24:MI:SS'),9000);
INSERT INTO INTERVENTIONS  VALUES(9,22,'Entretien',TO_DATE('2006-02-22 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-22 18:00:00','RRRR-MM-DD HH24:MI:SS'),7960);
INSERT INTO INTERVENTIONS  VALUES(10,2,'Entretien et Reparation',TO_DATE('2006-04-08 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-04-09 18:00:00','RRRR-MM-DD HH24:MI:SS'),45000);
INSERT INTO INTERVENTIONS  VALUES(11,28,'Réparation',TO_DATE('2006-03-08 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-03-17 12:00:00','RRRR-MM-DD HH:MI:SS'),36000);
INSERT INTO INTERVENTIONS  VALUES(12,20,'Entretien et Reparation',TO_DATE('2006-05-03 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-05 18:00:00','RRRR-MM-DD HH24:MI:SS'),27000);
INSERT INTO INTERVENTIONS  VALUES(13,8,'Réparation Systeme',TO_DATE('2006-05-12 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-12 18:00:00','RRRR-MM-DD HH24:MI:SS'),17846);
INSERT INTO INTERVENTIONS  VALUES(14,1,'Réparation',TO_DATE('2006-05-10 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-12 12:00:00','RRRR-MM-DD HH24:MI:SS'),39000);
INSERT INTO INTERVENTIONS  VALUES(15,20,'Réparation Systeme',TO_DATE('2006-06-25 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-06-25 12:00:00','RRRR-MM-DD HH24:MI:SS'),27000);
INSERT INTO INTERVENTIONS  VALUES(16,77,'Réparation',TO_DATE('2006-06-27 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-06-30 12:00:00','RRRR-MM-DD HH24:MI:SS'),25000);
--table intervenants
INSERT INTO INTERVENANTS  VALUES(1,54,To_DATE('2006-02-26 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-26 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(1,59,TO_DATE('2006-02-25 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-25 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(2,57,TO_DATE('2006-02-24 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-24 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(2,59,TO_DATE('2006-02-23 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-24 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(3,60,TO_DATE('2006-04-09 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-04-09 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(3,65,TO_DATE('2006-04-06 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-04-08 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(4,62,TO_DATE('2006-05-14 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-14 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(4,66,TO_DATE('2006-02-14 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-14 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(5,56,TO_DATE('2006-02-22 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-25 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(5,60,TO_DATE('2006-02-23 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-25 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(6,53,TO_DATE('2006-03-03 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-03-04 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(6,57,TO_DATE('2006-03-04 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-03-04 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(7,55,TO_DATE('2006-04-09 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-04-09 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(7,65,TO_DATE('2006-04-09 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-04-09 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(8,54,TO_DATE('2006-05-12 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-12 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(8,62,TO_DATE('2006-05-11 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-12 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(9,59,TO_DATE('2006-02-22 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-22 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(9,60,TO_DATE('2006-02-22 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-02-22 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(10,63,TO_DATE('2006-04-09 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-04-09 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(10,67,TO_DATE('2006-04-08 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-04-09 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(11,59,TO_DATE('2006-03-09 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-03-11 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(11,64,TO_DATE('2006-03-09 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-03-17 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(11,53,TO_DATE('2006-03-08 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-03-16 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(12,55,TO_DATE('2006-05-05 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-05 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(12,56,TO_DATE('2006-05-03 09:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-05 12:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(13,64,TO_DATE('2006-05-12 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-12 18:00:00','RRRR-MM-DD HH24:MI:SS'));
INSERT INTO INTERVENANTS  VALUES(14,88,TO_DATE('2006-05-07 14:00:00','RRRR-MM-DD HH24:MI:SS'),TO_DATE('2006-05-10 18:00:00','RRRR-MM-DD HH24:MI:SS'));
--12.	Supposons que le salaire de l’employé  BADI Hatem  est augmenté par 5000DA Que faut-il faire ?
Select salaire from employe where nomemp='BADI' and prenomemp='Hatem';
update employe set salaire=salaire+5000 where nomemp='BADI' and prenomemp='Hatem';
Select salaire from employe where nomemp='BADI' and prenomemp='Hatem';
--13.	Pour les interventions de mois de Février, ajouter cinq jours à la date de début. Désactiver la contrainte pour autoriser la modification. Réactiver la contrainte. 
select * from interventions where DATEDEBINTERV like '%/02/%';
update interventions set DATEDEBINTERV=DATEDEBINTERV+5 where DATEDEBINTERV like '%/02/%';
---message d'erreur violation de la CI ck_dateinter
--Désactiver la CI "ck_dateinter"
select status from user_constraints where constraint_name='CK_DATEINTER';
alter table interventions disable constraint ck_dateinter;
select status from user_constraints where constraint_name='CK_DATEINTER';
--effectuer les MAJ
select * from interventions where DATEDEBINTERV like '%/02/%';
update interventions set DATEDEBINTERV=DATEDEBINTERV+5 where DATEDEBINTERV like '%/02/%';
select * from interventions where DATEDEBINTERV like '%/02/%' or DATEDEBINTERV>DATEFININTERV;
--réactiver la CI
select * from tableerreurs;
alter table interventions enable constraint ck_dateinter exceptions into tableerreurs;
select * from tableerreurs;
--la CI n'est pas réactivée car il existe des données qui viole la CI
select status from user_constraints where table_name=upper('interventions')and constraint_name='CK_DATEINTER';
delete from interventions where rowid in (select adresse from tableerreurs);
alter table interventions enable constraint ck_dateinter exceptions into tableerreurs;
select status from user_constraints where table_name=upper('interventions') and constraint_name='CK_DATEINTER';
--14.	Supprimer tous les véhicules de modèle Série 5. Quels sont les problèmes rencontrés.
select * from vehicule
 where nummodele in (select nummodele 
                     from modele where modele='Serie 5');
delete from vehicule
where nummodele in(select nummodele 
                 from modele where modele='Serie 5');
select * from vehicule where nummodele in (select nummodele from modele where modele='Serie 5');
--suppression sans problème car les CI référentielles (FOREIGN KEY (clé étrangère)) sont crées avec l'option on delete cascade
/*Partie IV : Langage d’interrogation de données */ 
--15.	Lister les modèles et leur marque
select modele, marque 
from modele mo, marque ma
where mo.nummarque=ma.nummarque
order by modele;
---16.	Lister les véhicules sur lesquels, il y a au moins une intervention.
select distinct numimmat, annee 
from vehicule v, interventions i
where v.numvehicule=i.numvehicule
order by annee;
---17.	Quelle est la durée moyenne d’une intervention?
--en seconde
select avg(datefininterv-datedebinterv)*24*60*60
from interventions;
---18.	Donner le montant global des interventions dont le coût d’intervention est supérieur à  30000 DA?
select sum(coutinterv) 
from interventions
where coutinterv>30000;
/*19.	Donner la liste des employés ayant fait le plus grand nombre d’interventions.*/
create or replace view employe_nb_intervention (num_emp, nb_interv) as
(select  NUMEMPLOYE, count(NUMINTERVENTION)from  intervenants group by NUMEMPLOYE);
select * from employe_nb_intervention;

select e.NUMEMPLOYE, NOMEMP, PRENOMEMP
from employe e, employe_nb_intervention nb
where e.NUMEMPLOYE=nb.num_emp
and nb_interv=(select   max(nb_interv)  from  employe_nb_intervention)
order by e.NUMEMPLOYE desc;
---sans la vue
select e.NUMEMPLOYE, NOMEMP, PRENOMEMP
from employe e, intervenants i
where e.NUMEMPLOYE=i.NUMEMPLOYE
group by e.NUMEMPLOYE,  NOMEMP, PRENOMEMP
having count(NUMINTERVENTION)=(select   max(count(NUMINTERVENTION))from  intervenants group by NUMEMPLOYE )
order by e.NUMEMPLOYE desc;





