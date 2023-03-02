"use strict"

const Etudiant = function (nom='John Do',note=0){
    this.nom = nom
    this.note = note

    this.setNote = function (note) {
        this.note = note
    }

}

const etudiant1 = new Etudiant()
console.log(etudiant1, typeof etudiant1, etudiant1 instanceof Etudiant, etudiant1 instanceof Object)
//Etudiant { nom: 'John Do', note: 0, setNote: [Function (anonymous)] } object true true
etudiant1.setNote(10)
console.log(etudiant1)
//Etudiant { nom: 'John Do', note: 10, setNote: [Function (anonymous)] }

etudiant1.age = 20;
console.log(etudiant1)
//Etudiant {
//   nom: 'John Do',
//   note: 10,
//   setNote: [Function (anonymous)],
//   age: 20
// }

const etudiant2 = new Etudiant("etudiant2",20)
console.log(etudiant2)
//Etudiant {
//    nom: 'etudiant2',
//        note: 20,
//        setNote: [Function (anonymous)]
//}

//Ajout de l'age au prototype
Etudiant.prototype.age = 10

console.log(etudiant1)
//Etudiant {
//   nom: 'John Do',
//   note: 10,
//   setNote: [Function (anonymous)],
//   age: 20
// }
console.log(etudiant2)
//Etudiant {
//   nom: 'etudiant2',
//   note: 20,
//   setNote: [Function (anonymous)]
// }

const etudiant3 = new Etudiant("etudiant3",10)
console.log(etudiant3)
//Etudiant {
//   nom: 'etudiant3',
//   note: 10,
//   setNote: [Function (anonymous)]
// }


