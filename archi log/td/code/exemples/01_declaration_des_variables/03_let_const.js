"use strict"
//c est un nombre constant.
const c = 10
console.log(c, typeof c)
//10 number
//c = 11 => TypeError: Assignment to constant variable

let v = 10
v = 11
console.log(v, typeof v)
//11 number

v="hello"
console.log(v, typeof v)
//hello string

v= function (p) {
    console.log(p)
}
console.log(v, typeof v)
//[Function: v] function
v(10)
//10

console.log(console)
//Object [console] {
//   log: [Function: log],
//...
console.log(console.log)
//[Function: log]
console.log(console.log("hello"))
//hello
//undefined