"use strict";

var img =  document.querySelectorAll('img')
for (let pas = 0; pas < 2; pas++) {
    img[pas].addEventListener("click", (event) => {
        img[2].src=img[pas].src;
      });
}
