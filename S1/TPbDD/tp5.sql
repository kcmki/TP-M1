/*4*/

CREATE OR REPLACE TRIGGER PERIODE_verif 
BEFORE INSERT OR UPDATE OF DATEDEBUT,DATEFIN ON Intervenants
FOR EACH ROW
DECLARE
mROW INTERVENTIONS%ROWTYPE;
BEGIN

IF(INSERTING) THEN
  SELECT * INTO mROW FROM INTERVENTIONS WHERE NUMINTERVENTION = :NEW.NUMINTERVENTION;
  IF((mROW.DATEDEBINTERV <= :NEW.DATEDEBUT) AND (mROW.DATEFININTERV >= :NEW.DATEFIN ))
	THEN
		DBMS_OUTPUT.PUT_LINE('PERIODE VALIDE');
	ELSE RAISE_APPLICATION_ERROR(-20010,'PERIODE INVALIDE ');
  END IF;
  ELSE  SELECT * INTO mROW FROM INTERVENTIONS WHERE NUMINTERVENTION = :NEW.NUMINTERVENTION;
  IF((mROW.DATEDEBINTERV <= :NEW.DATEDEBUT) AND (mROW.DATEFININTERV >= :NEW.DATEFIN ))
	THEN
		DBMS_OUTPUT.PUT_LINE('PERIODE VALIDE');
	ELSE RAISE_APPLICATION_ERROR(-20010,'PERIODE INVALIDE mouahahahhaah ');
  END IF;
  END IF;
END;
/

/*5*/
ALTER TABLE EMPLOYE ADD (TOTAL_INTERVENTIONS INTEGER DEFAULT 0);

CREATE OR REPLACE TRIGGER TOTAL_INTERV
AFTER INSERT ON INTERVENANT
FOR EACH ROW
BEGIN
	UPDATE EMPLOYE E SET TOTAL_INTERVENTIONS = TOTAL_INTERVENTIONS + 1 WHERE E.NUMEMPLOYE = :NEW.NUMEMPLOYE ;
END;
/

/*6*/

create table CHIFFRE_AFFAIRE (MOIS integer, ANNEE integer, TOTAL_GAINS float );


Create or replace trigger CHIFFREAFF
after insert on interventions
For each row
DECLARE 
CURSOR CR1 IS SELECT Mois,Annee FROM CHIFFRE_AFFAIRE;
c1 CR1%rowtype;
dat interventions.dateDebinterv%type;
m CHIFFRE_AFFAIRE.Mois%type;
a CHIFFRE_AFFAIRE.Annee%type;
i binary_integer;
BEGIN
dat := :new.dateDebinterv;
m := EXTRACT(MONTH FROM dat);
a := EXTRACT(YEAR FROM dat);
i:=0;
for c1 in CR1 loop 

     if(c1.Mois= m and c1.Annee= a)
         then i:=i+1;
     end if;
end loop;
if(i=1) 
	then 
       update CHIFFRE_AFFAIRE set TOTAL_GAINS= TOTAL_GAINS +:new.coutInterv where Mois= m and Annee= a;
			dbms_output.put_line('une ligne dans la table chiffre_aff modifiée');
else
  insert into CHIFFRE_AFFAIRE values(m,a,:new.coutInterv  );
  dbms_output.put_line('une ligne dans la table chiffre_aff ajouté');
end if;
END;
/