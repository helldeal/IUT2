import Classe from './ex1.1.mjs';

let maClasse = new Classe("Mathématiques");
maClasse.ajouterEtudiant("Alice", 15);
maClasse.ajouterEtudiant("Bob", 12);
console.log(maClasse);
maClasse.supprimerEtudiants();
console.log(maClasse);