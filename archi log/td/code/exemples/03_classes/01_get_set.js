class Rectangle {
    constructor(hauteur, largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    get area() {
        return this.calcArea();
    }

    set setLargeur(largeur) {
        if (largeur < 0)
            largeur = -largeur
        this.largeur = largeur
    }

    calcArea() {
        return this.largeur * this.hauteur;
    }
}

const carré = new Rectangle(10, 10);
carré.setLargeur = -10;
console.log(carré.area);