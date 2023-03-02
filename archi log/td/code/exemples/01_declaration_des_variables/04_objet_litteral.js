const clio = {
    nom: "Clio",
    marque : "Renault",
    demarree : false,
    equipage : [],
    demarre : function (){
        this.demarree = true
    },
    stop : function (){
        this.demarree = false
    },
    charge : function (passager) {
        this.equipage.push(passager)
    }
}
console.log(clio, typeof clio)
clio.demarre()
console.log(clio)
clio.charge("Raoul")
clio.charge({nom: "neuneu", poids : 28});
console.log(clio)
console.log(clio.equipage[1].nom)

clio.poids = 1000
console.log(clio)

clio.decharge = function () {
    this.equipage = []
}
clio.decharge()
console.log(clio)

console.log(clio.prototype)