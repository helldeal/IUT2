#  `Compte Rendu du DS d'SQL dans un langage de programmation`

 Alexandre Clénet / Groupe 2 / `i2c09a`

## Exercice 1 :
```sql
create or replace TRIGGER supprimer_étudiant after delete on etudiant
BEGIN 
delete from souhait where numetudiant not in (select numetudiant from etudiant);
delete from affect_stage_etudiant where numetudiant not in (select numetudiant from etudiant);
END;
```
TEST :
```sql
delete from etudiant
where numetudiant=10;
```
1 ligne supprimé.
## Exercice 2 :
```sql
CREATE OR REPLACE TRIGGER maxsouhait AFTER
insert or update ON souhait
DECLARE
REC_etudiant etudiant%ROWTYPE;
BEGIN
Select * into REC_etudiant from etudiant e
where (select count(*) from souhait p where e.numetudiant=p.numetudiant)> 5 ;
RAISE_APPLICATION_ERROR (-20064,'viol de la règle : etudiant a trop de souhait');
EXCEPTION
WHEN NO_DATA_FOUND THEN NULL;
WHEN TOO_MANY_ROWS THEN
RAISE_APPLICATION_ERROR ( -20065,'viol de la règle : plusieurs etudiant a trop de souhait' );
END;
```
TEST :
```sql
-- L'étudiant 1 a 4 souhait
insert into souhait VALUES (1,101); --ajoute un 5e souhait
insert into offre_stage VALUES (601,60,'z',100); --ajoute une offre
insert into souhait VALUES (1,601);-- ajoute un 6e souhait
```
Rapport d'erreur -
ORA-20064: viol de la règle : etudiant a trop de souhait
ORA-06512: à "I2C09A.MAXSOUHAIT", ligne 6
ORA-04088: erreur lors d'exécution du déclencheur 'I2C09A.MAXSOUHAIT'
## Exercice 3 : 
Trigger 1 :
```sql
CREATE OR REPLACE TRIGGER remunmax AFTER
insert or update ON affect_stage_etudiant
DECLARE
REC_entreprise entreprise%ROWTYPE;
BEGIN
Select * into REC_entreprise from entreprise e
where (select sum(remuneration) from offre_stage t where e.nument=t.nument)*(select count(numetudiant) from affect_stage_etudiant a,offre_stage t where e.nument=t.nument and t.numoffre=a.numoffre)>0;
RAISE_APPLICATION_ERROR (-20074,'viol de la règle : remuneration maximale dépassé pour une entreprise');
EXCEPTION
WHEN NO_DATA_FOUND THEN NULL;
WHEN TOO_MANY_ROWS THEN
RAISE_APPLICATION_ERROR ( -20075,'viol de la règle : remuneration maximale dépassé pour plusieurs entreprises' );
END;
```
TEST :
```sql
insert into offre_stage VALUES (203,20,'z',3000); --je mets une offre à 3000 (l'entreprise 20 dépense deja 940€)
insert into affect_stage_etudiant VALUES (2,203); --j'ajoute à l'entreprise un etudiant sur l'offre (on dépasse les 3500€)
```
Rapport d'erreur -
ORA-20075: viol de la règle : remuneration maximale dépassé pour plusieurs entreprises
ORA-06512: à "I2C09A.REMUNMAX", ligne 10
ORA-04088: erreur lors d'exécution du déclencheur 'I2C09A.REMUNMAX'

Trigger 2 :
```sql
CREATE OR REPLACE TRIGGER remunmax2 AFTER
insert or update ON offre_stage
DECLARE
REC_entreprise entreprise%ROWTYPE;
BEGIN
Select * into REC_entreprise from entreprise e
where (select sum(remuneration) from offre_stage t where e.nument=t.nument)*(select count(numetudiant) from affect_stage_etudiant a,offre_stage t where e.nument=t.nument and t.numoffre=a.numoffre)>0;
RAISE_APPLICATION_ERROR (-20074,'viol de la règle : remuneration maximale dépassé pour une entreprise');
EXCEPTION
WHEN NO_DATA_FOUND THEN NULL;
WHEN TOO_MANY_ROWS THEN
RAISE_APPLICATION_ERROR ( -20075,'viol de la règle : remuneration maximale dépassé pour plusieurs entreprises' );
END;
```
TEST :
```sql
update offre_stage
set offre_stage.remuneration = 4000 where offre_stage.nument=20;
```
Rapport d'erreur -
ORA-20075: viol de la règle : remuneration maximale dépassé pour plusieurs entreprises
ORA-06512: à "I2C09A.REMUNMAX2", ligne 10
ORA-04088: erreur lors d'exécution du déclencheur 'I2C09A.REMUNMAX'