rollback;
/*
create or replace TRIGGER supprimer_étudiant after delete on etudiant
BEGIN 
delete from souhait where numetudiant not in (select numetudiant from etudiant);
delete from affect_stage_etudiant where numetudiant not in (select numetudiant from etudiant);
END;
*/
/*
delete from etudiant
where numetudiant=10;
*/
/*
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
*/
/*
insert into souhait VALUES (1,101);
insert into offre_stage VALUES (601,60,'z',100);
insert into souhait VALUES (1,601);
*/
/*
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
*/
/*
insert into offre_stage VALUES (203,20,'z',3000);
insert into affect_stage_etudiant VALUES (2,203);
*/
/*
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
*/
update offre_stage
set offre_stage.remuneration = 4000 where offre_stage.nument=20;