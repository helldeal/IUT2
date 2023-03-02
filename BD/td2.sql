--TD2
rollback;
--EXERCICE 1 :
/*
create or replace TRIGGER modif_salaire after update
of salaire ON employe for each row
BEGIN
if :new.salaire < :old.salaire then raise_application_error
(-20001,'viol de la règle :un salaire ne peut etre diminué') ;
end if ;
END;


create or replace TRIGGER augment_hebdo after update of hebdo ON employe for each row 
BEGIN 
if :new.hebdo > :old.hebdo then raise_application_error
(-20001,'viol de la règle : la durée hebdomadaire dun employé ne peut
pas augmenter') ;
end if;
END;     
*/
/*
update employe
set employe.salaire = 0;

update employe
set employe.hebdo = 40;

*/
--EXERCICE 2 :
/*
create or replace TRIGGER supprimer_employe after delete on employe
BEGIN 
delete from travail where nuempl not in (select nuempl from employe);
END;

create or replace TRIGGER supprimer_projet after delete on projet 
BEGIN 
delete from travail where nuproj not in (select nuproj from projet);
delete from concerne where nuproj not in (select nuproj from projet);
END;
*/
/*
delete from employe where nuempl=14;
delete from projet where nuproj=135;
*/

--EXERCICE 3 :
-- A
/*
CREATE OR REPLACE TRIGGER hebdoduree AFTER
insert or update ON employe
DECLARE
REC_employe employe%ROWTYPE;
BEGIN
Select * into REC_employe from employe e
where (select sum(duree) from travail t where e.nuempl=t.nuempl)> hebdo ;
--1 seul enregistrement trouvé en lève une exception
RAISE_APPLICATION_ERROR (-20002,'viol de la règle :temps de travail > hebdo');--1
EXCEPTION
WHEN NO_DATA_FOUND THEN NULL;--0 enregist
WHEN TOO_MANY_ROWS THEN
RAISE_APPLICATION_ERROR ( -20003,'viol de la règle :temps de travail > hebdo (multiple)' );-- 2 ou pl+
END;
*/
/*
update employe
set employe.hebdo = 25;
*/

-- B
/*
CREATE OR REPLACE TRIGGER nbrprojet AFTER
insert or update ON projet
DECLARE
REC_employe employe%ROWTYPE;
BEGIN
Select * into REC_employe from employe e
where (select count(*) from projet p where e.nuempl=p.resp)> 3 ;
--1 seul enregistrement trouvé en lève une exception
RAISE_APPLICATION_ERROR (-20004,'viol de la règle : employé a trop de resp');--1
EXCEPTION
WHEN NO_DATA_FOUND THEN NULL;--0 enregist
WHEN TOO_MANY_ROWS THEN
RAISE_APPLICATION_ERROR ( -20005,'viol de la règle : employé a trop de resp (multiple)' );-- 2 ou pl+
END;
*/
/*
insert into projet VALUES (666,'zzz',30);
*/

-- C
/*
CREATE OR REPLACE TRIGGER nbrprojet2 AFTER
insert or update ON concerne
DECLARE
REC_service service%ROWTYPE;
BEGIN
Select * into REC_service from service s
where (select count(*) from concerne c where s.nuserv=c.nuserv)> 3 ;
--1 seul enregistrement trouvé en lève une exception
RAISE_APPLICATION_ERROR (-20008,'viol de la règle : Plus de 3 projet dans le service');--1
EXCEPTION
WHEN NO_DATA_FOUND THEN NULL;--0 enregist
WHEN TOO_MANY_ROWS THEN
RAISE_APPLICATION_ERROR ( -20009,'viol de la règle : Plus de 3 projet dans le service (multiple)' );-- 2 ou pl+
END;
*/

select * from service s
where (select count(*) from concerne c where s.nuserv=c.nuserv)> 3 ;
insert into concerne VALUES (1,237);


--D
/*
CREATE OR REPLACE TRIGGER chefsalaireup AFTER
insert or update ON employe
DECLARE
REC_employe employe%ROWTYPE;
BEGIN
Select * into REC_employe from employe e
where (select sum(duree) from travail t where e.nuempl=t.nuempl)> hebdo ;
--1 seul enregistrement trouvé en lève une exception
RAISE_APPLICATION_ERROR (-20010,'viol de la règle :chef gagne moins que empl');--1
EXCEPTION
WHEN NO_DATA_FOUND THEN NULL;--0 enregist
WHEN TOO_MANY_ROWS THEN
RAISE_APPLICATION_ERROR ( -20011,'viol de la règle : chef gagne moins que empl (multiple)' );-- 2 ou pl+
END;
*/
/*
select * from employe a
where salaire>(select chef from service) ;
chef=nuempl 
*/