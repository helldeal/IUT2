import Etudiant from './Etudiant.mjs';
import Humain from './Humain.mjs';
class Promotion {
    constructor(nom) {
      this.nom = nom;
      this.etudiants = [];
      Promotion.nbInstances++;
    }
  
    ajoutEtudiant(etudiant) {
      if (!(etudiant instanceof Etudiant)) {
        throw new Error("Seul les objets de la classe Etudiant peuvent être ajouté à la promotion.");
      }
      this.etudiants.push(etudiant);
    }
  
    static get nbInstances() {
      return this._nbInstances || 0;
    }
  
    static set nbInstances(val) {
      this._nbInstances = val;
    }
  }

  

  let promotion = new Promotion("Promo INFO2")
  try {
    promotion.ajoutEtudiant(new Etudiant("John", 15));
    promotion.ajoutEtudiant(new Humain("John"));
  } catch (error) {
    console.log(error.message);
  }
  console.log(promotion)