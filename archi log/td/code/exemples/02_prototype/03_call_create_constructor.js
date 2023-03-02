"use strict"
// Forme, la classe parente
let Forme = function() {
    this.x = 0;
    this.y = 0;
}

// Méthode de la classe parente
Forme.prototype.deplacer = function(x, y) {
    this.x += x;
    this.y += y;
    console.info('Forme déplacée.');
};

// Rectangle - classe fille
function Rectangle() {
    // on appelle un autre constructeur pour initialiser this
    Forme.call(this);
}

// La classe fille surcharge la classe parente
Rectangle.prototype = Object.create(Forme.prototype);
Rectangle.prototype.constructor = Rectangle;

var rect = new Rectangle();

console.log('instance de Rectangle ? ', (rect instanceof Rectangle));
// true
console.log('une instance de Forme ? ', (rect instanceof Forme));
// true
rect.deplacer(1, 1);
// Affiche 'Forme déplacée.'