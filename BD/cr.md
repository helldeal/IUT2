#  `Compte Rendu du TD2 d'SQL dans un langage de programmation`

 Alexandre Clénet / Groupe 2
 
## `Tableau des triggers`

| Nom du trigger              | Type : `BEFORE` ou `AFTER` | `INSERT`, `DELETE`, `UPDATE` | Nom de la table       | `FOR EACH ROW` : oui ou non |
| --------------------------- | -------------------------- | ---------------------------- | --------------------- | --------------------------- |
| *modif_salaire*      | `AFTER`                    | `UPDATE`                     | *employe*             | Oui                         |
| *augment_hebdo*       | `AFTER`                    | `UPDATE`                     | *employe*             | Oui                         |
| *supprimer_employe*       | `AFTER`                    | `DELETE`                     | *travail*             | Non                         |
| *supprimer_projet*          | `AFTER`                    | `DELETE`                     | *travail*, *concerne* | Non                         |
| *hebdoduree*   | `AFTER`                    | `INSERT`, `UPDATE`           | *travail*             | Non                         |
| *nbrprojet*    | `AFTER`                    | `INSERT`, `UPDATE`           | *projet*              | Non                         |
| *nbrprojet2* | `AFTER`                    | `INSERT`, `UPDATE`           | *concerne*            | Non                         |


## `Exercice 1 : Triggers de type For each row et utilisation de « :NEW » et « :OLD »`

### `A- Ecrire un trigger de type for each row qui interdit la diminution du salaire d'un employé. Ce trigger se déclenche après la modification du salaire.`
```sql
create or replace TRIGGER modif_salaire after update
of salaire ON employe for each row
BEGIN
if :new.salaire < :old.salaire then raise_application_error
(-20001,'viol de la règle :un salaire ne peut etre diminué') ;
end if ;
END;
```
TEST :
```sql
update employe
set employe.salaire = 0;
```
Rapport d'erreur -
ORA-20001: viol de la rÃ¨gle :un salaire ne peut etre diminuÃ©
ORA-06512: à "I2B09A.MODIF_SALAIRE", ligne 2
ORA-04088: erreur lors d'exécution du déclencheur 'I2B09A.MODIF_SALAIRE'


### `B- Il y a une autre contrainte qui n'est pas spécifiée "la durée hebdomadaire d'un employé ne peut pas augmenter", elle n'est pas descriptible. Vous écrivez le trigger nécessaire à la vérification de cette contrainte`
```sql
create or replace TRIGGER augment_hebdo after update of hebdo ON employe for each row 
BEGIN 
if :new.hebdo > :old.hebdo then raise_application_error
(-20001,'viol de la règle : la durée hebdomadaire dun employé ne peut
pas augmenter') ;
end if;
END;
```
TEST :
```sql
update employe
set employe.hebdo = 40;
```
Rapport d'erreur -
ORA-20001: viol de la rÃ¨gle : la durÃ©e hebdomadaire dun employÃ© ne peut
pas augmenter
ORA-06512: à "I2B09A.AUGMENT_HEBDO", ligne 2
ORA-04088: erreur lors d'exécution du déclencheur 'I2B09A.AUGMENT_HEBDO'
## `Exercice 2 : Trigger de type : Delete from T2 where a not in (select a from T1)`
### `A – La spécification de l'opération supprimer_employe impose que la suppression d'un employé soit accompagnée de la suppression des lignes de travail correspondantes. Mettez en place un trigger table qui le fait. (pas de problème si on a déclaré "deferred" la contrainte FK_employe de la table travail vers la table employe "les employés de travail existent").`
```sql
create or replace TRIGGER supprimer_employe after delete on employe
BEGIN 
delete from travail where nuempl not in (select nuempl from employe);
END;
```
TEST :
```sql
delete from employe where nuempl=14;
```
1 ligne supprimé. (Le 14 a bien disparue dans les deux tables)
### `B – La spécification de l'opération supprimer_projet impose que la suppression d'un projet soit accompagnée de la suppression des lignes de travail et de la table concerne correspondantes. Mettez en place un trigger table qui effectue cela. (pas de problème si on a déclaré "deferred" la contrainte FK_nuproj de la table travail et de la table concerne vers la table projet).`
```sql
create or replace TRIGGER supprimer_projet after delete on projet 
BEGIN 
delete from travail where nuproj not in (select nuproj from projet);
delete from concerne where nuproj not in (select nuproj from projet);
END;
```
TEST :
```sql
delete from projet where nuproj=135;
```
1 ligne supprimé. (Le projet 135 est bien supprimer dans toutes les tables)
## `Exercice 3 : Pour les triggers qui suivent, vous faites des M.A.J(insert, update) de la base de données qui déclenchent les différentes erreurs`

### `A – Il y a une contrainte qui n'est pas spécifiée "la somme des durées de travail d'un employé ne doit pas excéder son temps de travail hebdomadaire", elle n'est pas descriptible. Vous écrivez le (ou les) trigger nécessaire à la vérification de cette contrainte.`
```sql
CREATE OR REPLACE TRIGGER hebdoduree AFTER
insert or update ON employe
DECLARE
REC_employe employe%ROWTYPE;
BEGIN
Select * into REC_employe from employe e
where (select sum(duree) from travail t where e.nuempl=t.nuempl)> hebdo ;
--1 seul enregistrement trouvÃ© en lÃ¨ve une exception
RAISE_APPLICATION_ERROR (-20002,'viol de la rÃ¨gle :temps de travail > hebdo');--1
EXCEPTION
WHEN NO_DATA_FOUND THEN NULL;--0 enregist
WHEN TOO_MANY_ROWS THEN
RAISE_APPLICATION_ERROR ( -20003,'viol de la rÃ¨gle :temps de travail > hebdo (multiple)' );-- 2 ou pl+
END;
```
TEST :
```sql
update employe
set employe.hebdo = 25;
```
Rapport d'erreur -
ORA-20003: viol de la rÃ¨gle :temps de travail > hebdo (multiple)
ORA-06512: à "I2B09A.HEBDODUREE", ligne 11
ORA-04088: erreur lors d'exécution du déclencheur 'I2B09A.HEBDODUREE'
### `B - Ecrire un trigger qui vérifie la contrainte suivante: « un employé est responable au plus sur 3 projets ».`
```sql
CREATE OR REPLACE TRIGGER nbrprojet AFTER
insert or update ON projet
DECLARE
REC_employe employe%ROWTYPE;
BEGIN
Select * into REC_employe from employe e
where (select count(*) from projet p where e.nuempl=p.resp)> 3 ;
--1 seul enregistrement trouvÃ© en lÃ¨ve une exception
RAISE_APPLICATION_ERROR (-20004,'viol de la rÃ¨gle : employÃ© a trop de resp');--1
EXCEPTION
WHEN NO_DATA_FOUND THEN NULL;--0 enregist
WHEN TOO_MANY_ROWS THEN
RAISE_APPLICATION_ERROR ( -20005,'viol de la rÃ¨gle : employÃ© a trop de resp (multiple)' );-- 2 ou pl+
END;
```
TEST :
```sql
insert into projet VALUES (666,'zzz',30);
```
ORA-20004: viol de la rÃ¨gle : employÃ© a trop de resp
ORA-06512: à "I2B09A.NBRPROJET", ligne 7
ORA-04088: erreur lors d'exécution du déclencheur 'I2B09A.NBRPROJET'
### `C- Ecrire un trigger qui vérifie la contrainte suivante : « un service ne peut être concerné par plus de 3 projets ».`
```sql
CREATE OR REPLACE TRIGGER nbrprojet2 AFTER
insert or update ON concerne
DECLARE
REC_service service%ROWTYPE;
BEGIN
Select * into REC_service from service s
where (select count(*) from concerne c where s.nuserv=c.nuserv)> 3 ;
--1 seul enregistrement trouvÃ© en lÃ¨ve une exception
RAISE_APPLICATION_ERROR (-20008,'viol de la rÃ¨gle : Plus de 3 projet dans le service');--1
EXCEPTION
WHEN NO_DATA_FOUND THEN NULL;--0 enregist
WHEN TOO_MANY_ROWS THEN
RAISE_APPLICATION_ERROR ( -20009,'viol de la rÃ¨gle : Plus de 3 projet dans le service (multiple)' );-- 2 ou pl+
END;
```
TEST :
```sql
insert into concerne VALUES (1,237);
```
Rapport d'erreur -
ORA-20009: viol de la rÃ¨gle : Plus de 3 projet dans le service (multiple)
ORA-06512: à "I2B09A.NBRPROJET2", ligne 11
ORA-04088: erreur lors d'exécution du déclencheur 'I2B09A.NBRPROJET2'
### `D- Ecrire un trigger qui vérifie la contrainte suivante : « un chef de service gagne plus que les employés de son service.`
```sql

```
TEST :
```sql

```
### `E- Ecrire un trigger qui vérifie la contrainte suivante : « un chef de service gagne plus que les employés responsables de projets.`
```sql

```
TEST :
```sql

```