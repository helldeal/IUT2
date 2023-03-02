
const jeux = (nombre,plage) =>  new Promise((resolve,reject) =>{
    if (Math.floor(Math.random() * plage)==nombre)
        resolve("gagné")
    else
        reject("perdu")
})

jeux(0,1).then(res=>console.log(res)).catch(res=>console.log(res))
console.log("début")
//début
//gagné