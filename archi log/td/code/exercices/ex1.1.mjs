class classe {
    constructor(nom) {
      this.nom = nom;
      this.etudiants = [];
    }
    ajouterEtudiant(nom, note){
        this.etudiants.push({nom: nom, note: note});
    }
    supprimerEtudiants(){
        this.etudiants = []
    }
  }

export default classe