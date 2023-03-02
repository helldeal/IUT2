"use strict"
const unePromesse = new Promise((resolve,reject) =>{
    if (Math.floor(Math.random() * 2)==0)
        resolve("gagné")
    else
        reject("perdu")
})

unePromesse.then(res=>console.log(res)).catch(res=>console.log(res))
console.log("début")
//début
//perdu ou gagné