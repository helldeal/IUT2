"use strict"
const timer = (temps) =>  new Promise((resolve) =>{
    setTimeout(() => resolve("fini après " + temps), temps)
})


   console.log(await timer(20))
    console.log(await timer(10))

console.log("début")

// fini après 20
// fini après 10
// /!\ début