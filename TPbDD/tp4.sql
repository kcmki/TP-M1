SET SERVEROUTPUT ON

/** 1 **/
DECLARE 
cursor cr is SELECT marque , COUNT(*) AS nbr FROM MARQUE MA, MODELE MO WHERE MA.NUMMARQUE = MO.NUMMARQUE GROUP BY MA.MARQUE ;
c cr%rowtype;
vide exception;

BEGIN
for c in cr LOOP
	dbms_output.put_line('La marque : '|| c.marque || ' posséde ' || c.nbr || ' modeles .' );
	exit when cr%notfound;
END LOOP; 

EXCEPTION WHEN vide THEN
	dbms_output.put_line('Aucune marque n est trouvé ! ');

END;
/



/** 2 **/


/* ajout de la contrainte de salaire */

ALTER TABLE EMPLOYE ADD CONSTRAINT LIMIT_SALAIRE CHECK(salairEploye >= 10000 AND salairEploye <= 30000)


/* désactiver la contrainte */

ALTER TABLE EMPLOYE DISABLE CONSTRAINT LIMIT_SALAIRE;


/* procedure d'ajout des salaires */
CREATE OR REPLACE PROCEDURE Augmentation_salaire
AS

cursor cr is select numEmploye,nomEmploye,prenomEmploye,categorieEmploye,salairEploye FROM EMPLOYE ;
s INTEGER ;
c cr%rowtype ;
vide exception ;

BEGIN

for c in cr LOOP
	if (c.categorieEmploye = 'MECANICIEN')
	then
		s:= c.salairEploye * 1.50;
	else
		s:= c.salairEploye* 1.30;
	end if;
	
	dbms_output.put_line('L employe : ' || c.nomEmploye || ' ' || c.prenomEmploye || ' de catégorie : ' || c.categorieEmploye || ' passe d un salaire de: ' || c.salairEploye || ' à ' || s || '.');
	UPDATE EMPLOYE E SET salairEploye = s WHERE E.numEmploye = c.numEmploye ;
	
	exit when cr%notfound;
	
END LOOP;

EXCEPTION WHEN vide THEN
	dbms_output.put_line('Aucun employé n est trouvé ! ');


END;
/


EXECUTE Augmentation_salaire;




/** 3 **/ 
CREATE OR REPLACE PROCEDURE Verification(AN VEHICULE.ANNEE%type)
AS
cursor cr is select V.annee,V.numVehicule,dateDebinterv,dateFininterv  
FROM Interventions I, Vehicule V 
WHERE I.numVehicule = V.numVehicule AND V.annee =AN;

c cr%rowtype;
vide exception;

BEGIN

for c in cr LOOP
	if(c.dateDebinterv < c.dateFininterv )
	then
		dbms_output.put_line('vérification positive !');
	else
		dbms_output.put_line('vérification négative !');
	end if;
	
	exit when cr%notfound;

END LOOP;

EXCEPTION WHEN vide THEN
	dbms_output.put_line('Aucun vehicule trouvé !!');

END;
/


EXECUTE Verification('1998');



/** 4 **/

CREATE OR REPLACE FUNCTION Nombre_interv(num in Employe.numEmploye%type )
return number
IS 

cursor cr is SELECT I.numEmploye, E.nomEmploye , E.prenomEmploye, COUNT(*) AS NBR
FROM Intervenants I, Employe E 
WHERE E.numEmploye = num
AND I.numEmploye = E.numEmploye
GROUP BY I.numEmploye, E.nomEmploye , E.prenomEmploye;

c cr%rowtype;
vide exception;
i binary_integer;

BEGIN

for c in cr LOOP
	dbms_output.put_line('L emplyé : '|| c.nomEmploye|| ' ' || c.prenomEmploye || ' a fait ' || c.NBR || ' inteventions. ');
	
	return(c.NBR);
END LOOP;

END;
/


SELECT Nombre_interv(59) from dual;



/** 5 **/


CREATE OR REPLACE PROCEDURE Ajout_interv(num Interventions.numIntervention %type,numv INTERVENTIONS.NUMVEHICULE%type , t Interventions.typeIntervention%type ,dd Interventions.dateDebinterv%type , df Interventions.dateFininterv%type ,cout Interventions.coutInterv%type )
AS
cursor cr1 is SELECT numIntervention ,COUNT(*) AS N1 FROM Interventions WHERE numIntervention = num GROUP BY numIntervention;
cursor cr2 is SELECT numVehicule,COUNT(*) AS N2 FROM Vehicule  WHERE numVehicule = numv GROUP BY numVehicule;

c1 cr1%rowtype;
c2 cr2%rowtype;

i binary_integer;
j binary_integer;

vide1 exception;
vide2 exception;

BEGIN
i := 0;
j := 0;

for c1 in cr1 LOOP
	i := i+1;
END LOOP;

if(i = 0)
then
	
	for c2 in cr2 LOOP
		j := j+1;
	END LOOP;
	
	if(j > 0)
	then
		INSERT INTO Interventions (numIntervention ,numVehicule ,typeIntervention ,dateDebinterv ,dateFininterv ,coutInterv ) VALUES (num , numv , t , dd , df , cout);
		dbms_output.put_line('Intervention ajouté ! ');
	else
		dbms_output.put_line('ERREUR : violation de la clé étrangère');
	end if;
	
else
	close cr1;
	dbms_output.put_line('ERREUR : violation de la clé primaire');
end if;

END;
/


EXECUTE Ajout_interv(39,8,'réparation systeme',to_date('2003-05-15 11:00:00','YYYY-MM-DD HH24:MI:SS' ),to_date('2003-05-15 16:00:00','YYYY-MM-DD HH24:MI:SS' ),580000);










