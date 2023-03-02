"use strict"
const timer = (temps) =>  new Promise((resolve) =>{
    setTimeout(() => resolve("fini après " + temps), temps)
})

const f = async () => {
   console.log(await timer(20))
    console.log(await timer(10))
}
console.log("début")
f()

//début
// fini après 20
// fini après 10