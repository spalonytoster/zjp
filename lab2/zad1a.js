// jshint node: true, esversion: 6
'use strict';

const generator = require('./../lab1/zad1');

let mode          = 'integer',
    filename      = 'input',
    fileExtension = 'txt',
    min           = 1,
    max           = 1000,
    amounts       = [1000, 10000, 100000];

let dataSets = [];

amounts.forEach((amount) => {
  let data = '';
  data += generator.generate(mode, amount, min, max);
  data += '\n';
  data += generator.generate(mode, amount, min, max);
  dataSets.push(data);
});

dataSets.forEach((dataSet, index) => {
  generator.saveToFile(`${filename}${index+1}.${fileExtension}`, dataSet);
});
