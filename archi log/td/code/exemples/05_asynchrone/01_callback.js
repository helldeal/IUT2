"use strict"
// setTimeout() est une fonction asynchrone qui exécute la fonction de callback
// après quelques millisecondes d'attente
setTimeout(() => console.log('a'), 50); // afficher a dans 50 millisecondes
setTimeout(() => console.log('b'), 90); // afficher b dans 90 millisecondes
setTimeout(() => console.log('c'), 20); // afficher c dans 20 millisecondes
// => ordre d'affichage: c, a, puis b
//    car les opérations asynchrones s'exécutent en parallèle
console.log("debut")
//s'affiche avant la fin des timers