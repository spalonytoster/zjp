// jshint node: true, esversion: 6
'use strict';

const fs = require('fs');

function dotProduct(A, B) {
  let result = [],
      longer,
      shorter;
  if (A.length > B.length) {
    longer = A;
    shorter = B;
  }
  else {
    longer = B;
    shorter = A;
  }
  shorter.forEach((val, index) => {
    result.push(shorter[index] * longer[index]);
  });
  return result;
}

function parseFileToVectors(filename) {
  let temp = fs.readFileSync(filename).toString().split('\n');
  let A = temp[0],
      B = temp[1];
  A = A.split(',');
  B = B.split(',');
  return {
    A: A,
    B: B
  };
}

let data = [
  parseFileToVectors('input1.txt'),
  parseFileToVectors('input2.txt'),
  parseFileToVectors('input3.txt')
];

data.forEach((val, index) => {
  fs.writeFile(`output${index+1}.txt`, dotProduct(val.A, val.B), 'utf-8', err => {
    if (err) throw err;
  });
});
