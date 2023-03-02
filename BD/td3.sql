rollback;

execute maj.creer_employe(24,'alex',35,3,200000);

execute maj.modif_hebdo(2000,30);




variable li REFCURSOR ;
execute lecture.liste_employes(:li);
print :li;


execute lecture.liste_ppours(:li,1);
print :li;

execute lecture.liste_pte(:li,14);
print :li;