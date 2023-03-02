"use strict"
const timer = (temps) =>  new Promise((resolve) =>{
    setTimeout(() => resolve("fini après " + temps), temps)
})

Promise.race([timer(10),timer(20)]).then(s => console.log(s))
//fini après 10
Promise.any([timer(10),timer(20)]).then(s => console.log(s))
//fini après 10
Promise.all([timer(10),timer(20)]).then(s => console.log(s))
//[ 'fini après 10', 'fini après 20' ]