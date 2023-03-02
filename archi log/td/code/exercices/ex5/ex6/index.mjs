import createCsvParser from 'csv-parser'
import fs from 'fs'

const results = [];

fs.createReadStream('ex6/resto.csv')
  .pipe(createCsvParser({separator: ';'}))
  .on('data', (row) => {
    if (row.Département == 'Loire-Atlantique'){
      console.log(row);

    }
  })
  .on('end', () => {
    console.log("succes");
  });