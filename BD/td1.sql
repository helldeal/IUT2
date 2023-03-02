--creation de table en local
create table EMPLOYE as select * from basetd.employe;
create table SERVICE as select * from basetd.SERVICE;
create table PROJET as select * from basetd.PROJET;
create table TRAVAIL as select * from basetd.TRAVAIL;
create table CONCERNE as select * from basetd.CONCERNE;

ALTER TABLE EMPLOYE ADD CONSTRAINT PK_EMPLOYE PRIMARY KEY(NUEMPL);
ALTER TABLE SERVICE ADD CONSTRAINT PK_SERVIE PRIMARY KEY(NUSERV);
ALTER TABLE PROJET ADD CONSTRAINT PK_PROJET PRIMARY KEY(NUPROJ);
ALTER TABLE TRAVAIL ADD CONSTRAINT PK_TRAVAIL PRIMARY KEY(NUEMPL, NUPROJ);
ALTER TABLE CONCERNE ADD CONSTRAINT PK_CONCERNE PRIMARY KEY(NUSERV, NUPROJ);


ALTER TABLE EMPLOYE ADD CONSTRAINT FK_AFFECT FOREIGN KEY(AFFECT) REFERENCES SERVICE(NUSERV);
ALTER TABLE PROJET ADD CONSTRAINT FK_RESP FOREIGN KEY(RESP) REFERENCES EMPLOYE(NUEMPL);
ALTER TABLE TRAVAIL ADD CONSTRAINT FK_NUEMPL FOREIGN KEY(NUEMPL) REFERENCES EMPLOYE(NUEMPL);
ALTER TABLE TRAVAIL ADD CONSTRAINT FK_NUPROJ FOREIGN KEY(NUPROJ) REFERENCES PROJET(NUPROJ);
ALTER TABLE CONCERNE ADD CONSTRAINT FK_NUSERV FOREIGN KEY(NUSERV) REFERENCES SERVICE(NUSERV);
ALTER TABLE CONCERNE ADD CONSTRAINT FK_NUPROJC FOREIGN KEY(NUPROJ) REFERENCES PROJET(NUPROJ);

--insertion des key
alter table concerne add constraint pk_concerne primary key(nuserv,nuproj);
alter table travail add constraint pk_travail primary key(nuempl,nuproj);


--try code erreur
/*
insert into employe values (20,'toto',35,1,2000);
insert into employe values (27,'toto',35,6,2000);
drop table employe;
*/
--contraine unique et différée
alter table employe add CONSTRAINT U_empl unique(nuempl,affect);

alter table service drop constraint fk_nuempl;

alter table service add constraint fk_nuempl foreign key(chef,nuserv)
references employe(nuempl,affect)initially deferred;

alter table projet drop constraint fk_utravail;

alter table projet add constraint fk_nutravail foreign key(resp,nuproj)
references travail(nuempl,nuproj)initially deferred;

alter table travail drop constraint fk_nuproj;
alter table travail add constraint fk_nuproj foreign key(nuproj)
references projet(nuproj)initially deferred;

alter table concerne drop constraint fk_nuproj2;
alter table concerne add constraint fk_nuproj2 foreign key(nuproj)
references projet(nuproj)initially deferred;

alter table travail add constraint fk_nuempl foreign key(nuempl)
references employe(nuempl)initially deferred;

insert into service values(88,'a',7);
insert into employe values(3,'a',0,88,2000);
commit;

insert into service values(88,'a',7);
insert into service values(89,'a',7);
insert into employe values(7,'a',0,88,2000);
commit;

update employe
set employe.salaire = 2500
where employe.nuempl in (select resp from projet);

update employe
set employe.salaire = 3500
where employe.nuempl in (select chef from service);

select * from employe 
where hebdo>=(select sum(duree) from travail where travail.nuempl = employe.nuempl);

select * from employe 
where 3>(select count(nuproj) from travail where travail.nuempl = employe.nuempl);

