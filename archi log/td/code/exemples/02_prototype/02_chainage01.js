"use strict"

//un constructeur comme début de la chaine des prototypes
console.log(Object);
//[Function: Object]
console.log(Object.prototype) //un attribut de la fonction constructrice
//[Object: null prototype] {}
console.log(Object.getPrototypeOf(Object)) //Le prototype
//{}

Object.prototype.age = 10;
//un attribut prototype pour enrichir les prototypes

const Etudiant = function (nom,note) {
    this.nom = nom || 'John Do'
    this.note = note || 0
}

const etudiant1 = new Etudiant();

console.log(etudiant1.age);
//chainage des prototypes
//10
console.log(typeof etudiant1)
//object