class Humain {
    constructor(sex,age){
        this.sex=sex;
        this.age=age;
    }
    toString(){
        const mensonge = function(){
            console.log(this);
            //probleme est que le this n'est pas celui de Humain
            //mais celui de mensonge
            return 20;
        }
        return "Humain "+this.sex+" "+mensonge();
    }
}

const robert = new Humain("F",18);
console.log(robert.toString());
//undefined
//Humain F 20

//Une version avec une fonction flechee qui na pas this
class Humain2 {
    constructor(sex,age){
        this.sex=sex;
        this.age=age;
    }
    toString(){
        const mensonge = () =>{
            console.log(this);
            //le this est celui de Humain2, mensonge n'en a pas
            this.age -=10;
            return this.age;
        }
        return "Humain "+this.sex+" "+mensonge();
    }
}
const robert2 = new Humain2("F",18);
console.log(robert2.toString());
console.log(robert2.age);
//Humain2 { sex: 'F', age: 18 }
//Humain F 8
//8