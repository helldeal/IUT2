"use strict"
const timer = (temps) =>  new Promise((resolve) =>{
    setTimeout(() => resolve("fini après " + temps), temps)
})

timer(10)
    .then(s => {console.log(s); return timer(20)})
    .then(s => {console.log(s)})
