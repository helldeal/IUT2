"use strict"
import { readdir } from 'node:fs';

readdir("./td/code/exercices", (err, files) => {
  if (err) {
    console.error(err);
    return;
  }

  files.forEach(file => console.log(file))
});