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