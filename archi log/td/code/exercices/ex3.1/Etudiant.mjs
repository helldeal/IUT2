import Humain from './Humain.mjs';
class Etudiant extends Humain {
    constructor(nom, note) {
      super(nom);
      this.note = note;
    }
  }

export default Etudiant