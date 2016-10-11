// jshint node: true, esversion: 6
'use strict';

const fs = require('fs');

const modes = {
  INTEGER: 'integer',
  FLOAT: 'float'
};

const limits = {
  MIN: 0,
  MAX: 1000
};

let generate = (mode, amount, min, max) => {
  let result = [];
  if (typeof min === 'undefined') {
    min = limits.MIN;
  }
  if (typeof max === 'undefined') {
    max = limits.MAX;
  }
  for (let i = 0; i < amount; i++) {
    let number = Math.random() * (max-min+1) + min;
    if (mode.toLowerCase() === modes.INTEGER) {
      number = Math.floor(number);
    }
    else if (mode.toLowerCase() !== modes.FLOAT) {
      new Error('Valid modes are: INTEGER and FLOAT');
    }
    result.push(number);
  }
  return result;
};

let saveToFile = (filename, text) => {
  fs.writeFile(filename, text, 'utf-8', (err) => {
    if (err) throw err;
    console.log(`Saved to '${filename}'`);
  });
};

let mode     = process.argv[2],
    amount   = process.argv[3],
    filename = process.argv[4],
    min      = process.argv[5],
    max      = process.argv[6];

// Main program
saveToFile(filename, generate(mode, amount, min, max));
