"use strict";
var modale = document.getElementById('myModal')
document.getElementById('myBtn')
.addEventListener('click', function(event) {
    modale.classList.add("displayBlock")
});

document.querySelector('span.close')
.addEventListener('click', function(event) {
    modale.classList.remove("displayBlock")
});