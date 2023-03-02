# TD4 TEST STRUCTUREL + ANALYSE DE MUTATION

Nous allons mettre en perspective les deux techniques 
de couverture de graphe de flot de contrôle et d'analyse de mutation 
en utilisant un cas d'étude abordé l'an dernier :

Un programme qui travaille sur des listes de notes.
Ca va vous rappeler le DS de QDev2 - R304.
SPOILER ALERT C'était le cas d'étude du contrôle du module de test il y a quelques années.

Commencez par cloner ce projet gitlab dans **IntelliJ**.

Le fichier gradle est déjà prêt. 
Normalement, il ne devrait pas nécessiter de mise à jour : 
il vaut même mieux éviter pour que le plugin marche malgré son statut "deprecated".

## PRISE EN MAIN DU CAS D'ETUDE
Si le build du gradle se passe bien (sinon lancez le),
vous obtiendrez un projet configuré avec le programme "ListeNotes" dans une classe du dossier source "main",
ainsi qu'un Main pour lancer un exemple.

Q1 - Lancez le Main.

Il y a aussi une classe de test avec deux tests vierges.

Q2 - Lancez les tests. Que se passe-t-il (bien) ?

Q3 - Lancez pitest. Que se passe-t-il (mal) ?

## TEST DE nombreOccurrence()

Q4 - Concevez les tests structurels de la fonction _nombreOccurrence()_.
À faire sur papier (SPOILER ALERT : comme pour le contrôle à venir).

Q5 - Implémentez ces tests.

Q5.1 - Passent-ils ?

Q5.2 - Vous paraissent-ils suffisants ?

Q6 - Lancez le task Gradle pitest et ouvrez le rapport obtenu.

Q6.1 - Quel score de mutation obtenez-vous (en ne considérant que la fonction _nombreOccurrence()_) ?

Q6.2 - Analysez les mutants vivants : quelles limites de la technique de couverture du graphe de flot de contrôle est mise en défaut ?

Q7 - Créez et implémentez les tests nécessaires pour les tuer tous.

## TEST DE moyenne()

Q8 - Concevez les tests structurels de la fonction _moyenne()_.

Q9 - Implémentez ces tests.

Q10.1 - Passent-ils ?

Q10.2 - Vous paraissent-ils suffisants ?

Q11 - Lancez le task Gradle pitest et ouvrez le rapport obtenu.

Q11.1 - Quel score de mutation obtenez-vous (en ne considérant que la fonction _moyenne()_) ?

Q11.2 - Analysez les mutants vivants : quelle (autre) limite de la technique de couverture du graphe de flot de contrôle est mise en défaut ?

Q12 - Créez et implémentez les tests nécessaires pour les tuer tous.

Q12.1 - Trouvez-vous un bug ? (qu'il faudrait corriger pour faire passer les tests avant de relancer pitest, cf Q3)

## SUPPLEMENT

Q13 - Finissez les précédents TD







