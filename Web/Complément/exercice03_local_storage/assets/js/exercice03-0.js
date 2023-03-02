"use strict";
var input = document.querySelector('input')
input.addEventListener("keypress", function(event) {
    if (event.key == "Enter") {
        sessionStorage.setItem("a",input.value)
        document.location.href = "exercice03-1.html"
    }
  });