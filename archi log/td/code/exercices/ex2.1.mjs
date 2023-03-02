"use strict"
const Animal = function(nom)  {
    this.nom = nom;
}

Animal.prototype.crie = function() {
return this.nom + " crie";
};

function Chien(nom){
    Animal.call(this,nom)
}

Chien.prototype=Object.create(Animal.prototype);
Chien.prototype.constructor=Chien;

Chien.prototype.crie = function() {
    return Animal.prototype.crie.call(this)+" wafwaf"
};

let lion = new Animal("Lion");
console.log(lion.crie()); 

let chien = new Chien("Fido");
console.log(chien.crie());