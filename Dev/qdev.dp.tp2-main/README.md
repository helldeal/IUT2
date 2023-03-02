# qdev.dp.tp2

## Contexte

_"Pâques est le dimanche qui suit la première pleine lune du printemps,
c'est-à-dire, selon la définition établie par le Concile de Nicée en 325 :
Pâques est le dimanche qui suit le 14e jour de la Lune qui atteint cet âge
le 21 mars ou immédiatement après."_

Il y a plusieurs algorithmes permettant de calculer la date de Pâques 
en fonction  de quelle Pâques on parle : 
**la Pâques du calendrier Julien** ou **la Pâques du calendrier Grégorien**

voir : https://fr.wikipedia.org/wiki/Calcul_de_la_date_de_P%C3%A2ques

## Travail à réaliser

**Traitez les questions dans l'ordre indiqué**

- `src/` contient différents fichiers source à modifier
- `test/` contient différents cas de tests pour tester votre code ;
vous ne devriez pas avoir à modifier les cas de tests fournis

L'interface `Paques` définit plusieurs fonctionnalités
qu'il vous faudra implémenter, tout au long du projet.

1. Implémenter la méthode `calculeDatePaques()` pour
   la classe `PaquesJulienne` : 
[wikipedia "Paques julienne"](https://fr.wikipedia.org/wiki/Calcul_de_la_date_de_P%C3%A2ques_selon_la_m%C3%A9thode_de_Meeus#Calcul_de_la_date_de_P%C3%A2ques_julienne) ;
   Des cas de tests dans `TestPaquesJulienne` vous permettent de valider votre dev.

2. Implémenter la méthode `calculeDatePaques()` pour
   la classe `PaquesGregorienne` :
[Wikipedia "Paques gregorienne"](https://fr.wikipedia.org/wiki/Calcul_de_la_date_de_P%C3%A2ques_selon_la_m%C3%A9thode_de_Meeus#Calcul_de_la_date_de_P%C3%A2ques_gr%C3%A9gorienne) ;
   Des cas de tests dans `TestPaquesGregorienne`
   vous permettent de valider votre dev.

3. Implémenter la méthode  `historiqueResultats()` ;
   Des cas de tests dans `TestHistorique` vous
   permettent de valider votre dev. **NB : réfléchir à éviter toute
   duplication inutile du code.**

4. Implémenter les méthodes `historiqueResultatsTries()`
   et `historiqueResultatsTriesAutrement()`;
   Des cas de tests dans` TestHistoriqueTrie`
   vous permettent de valider votre dev. 

5. Donner des cas de tests pour tester l'implémentation
   de la méthode `dateValide()` dans la classe `Date`. 
   **NB : on commence par donner des cas de tests avant de réaliser 
   l'implémentation = TDD(Test Driven-Development)**.

6. Implémenter la méthode `dateValide()`.

7. Implémenter la méthode `fabrique(String str)`
   dans la classe `Date`.