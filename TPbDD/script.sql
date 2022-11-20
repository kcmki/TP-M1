CREATE OR REPLACE PROCEDURE Ajouter(num INTERVENTIONS.numIntervention %type,numv INTERVENTIONS.NUMVEHICULE%type , typeI INTERVENTIONS.typeIntervention%type ,ddeb INTERVENTIONS.dateDebinterv%type , dfin INTERVENTIONS.dateFininterv%type ,prix INTERVENTIONS.coutInterv%type )
AS
	cursor cr1 is SELECT * FROM INTERVENTIONS WHERE numIntervention = num;
	cursor cr2 is SELECT * FROM Vehicule  WHERE numVehicule = numv;

	c1 cr1%rowtype;
	c2 cr2%rowtype;

	i integer;
	j integer;

BEGIN
    i := 0;
    j := 0;

    FOR c1 IN cr1 LOOP
        i := i+1;
    END LOOP;

    IF(i = 0) THEN
        FOR c2 IN cr2 LOOP
            j := j+1;
        END LOOP;
        
        IF(j > 0) THEN
            INSERT INTO INTERVENTIONS (numIntervention ,numVehicule ,typeIntervention ,dateDebinterv ,dateFininterv ,coutInterv ) VALUES (num , numv , typeI , ddeb , dfin , prix);
            dbms_output.put_line('insertion effectué');
        ELSE
            dbms_output.put_line('ERREUR : violation de la clé étrangère');
        END IF;
    ELSE
        CLOSE cr1;
        dbms_output.put_line('ERREUR : violation de la clé primaire');
    END IF;

END;
/
CREATE OR REPLACE TRIGGER IntervEmp 
BEFORE 
INSERT ON Intervenant
FOR EACH ROW 
InterV INTERVENTIONS%rowtype;
begin
        SELECT * INTO InterV FROM INTERVENTIONS WHERE numIntervention = :new.numIntervention;
        IF(InterV.dateDebinterv > :new.datedebut or InterV.dateFininterv < :new.datefin) THEN
            raise_application_error(20100,'Erreur date intervenant invalide');
        end if
   IF
end;


UPDATE EMPLOYE e SET TOTAL_INTERVENTION = (SELECT count(*) FROM INTERVENANT WHERE numemploye = e.numeploye GROUP BY numemploye);


CREATE OR REPLACE TRIGGER TOTAL_INTERVENTIONS_TRIGGER 
AFTER 
INSERT ON Intervenant
FOR EACH ROW 
begin
    UPDATE EMPLOYE SET TOTAL_INTERVENTIONS = TOTAL_INTERVENTIONS+1;
end;

CREATE TABLE CHIFFRE_AFFAIRE(
    MOIS INTEGER IN (1,2,3,4,5,6,7,8,9,10,11,12),
    ANNEE INTEGER,
    TOTAL_GAINS INTEGER,

    CONSTRAINT PK_CA PRIMARY KEY (MOIS,ANNEE)
);

/* Extract pour extraire le mois / année de la date */
CREATE OR REPLACE TRIGGER CHIFFRE_AFFAIRE 
AFTER
INSERT ON INTERVENTIONS
FOR EACH ROW 
begin
  
end;


