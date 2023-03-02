# TP n°8 : Design patterns

Commencez par terminer le TP n°6, au moins jusqu'à avoir traité le DAO "Database H2"

## Design patterns Strategy et Template Method

Il va s'agir d'implémenter l'exercice 1 du TD n°7.

Dans le package `tp8.exo1.strategy`, implémentez la solution utilisant le design pattern _Strategy_.

Dans le package  `tp8.exo1.templatemethod`, implémentez la solution utilisant le design pattern
_Template method_.

Des cas de tests sont donnés dans `TestDeclarationFiscale`.

## Design pattern Iterator

Il va s'agir d'implémenter l'exercice 2 du TD n°7.

Une correction des classes `SimpleHashTable` et `HashTable` vous est fournie.

Il ne vous reste plus qu'à implémenter la méthode `iterator()` 
dans les classes `SimpleHashTable` et `HashTable` : pour cela, on retournera une classe **anonyme**, 
implémentant _"à la volée"_ l'interface `Iterator<E>`.

