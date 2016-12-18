// jshint esversion: 6, node: true
'use strict';
const fs = require('fs');

let collisions = 0;

function hash(input) {
  let length = 500,
    prime = 31,
    key = 7;

  input.toString().split('').forEach((letter) => {
    key = (key * prime) + letter.charCodeAt(0);
  });

  return key % length;
}

let hashMap = [];

fs.readFileSync('i', 'utf-8').split(' ').forEach((word) => {
  let wordHash = hash(word);

  if (!hashMap[wordHash]) {
    hashMap[wordHash] = {
      key: word,
      value: 0
    };
  }
  else {
    let collisionOccured = false;
    while (hashMap[wordHash] && hashMap[wordHash].key !== word) {
      wordHash = (wordHash * 31) + word.charCodeAt(0) % 500;
      collisionOccured = true;
      collisions++;
    }
    if (collisionOccured && !hashMap[wordHash]) {
      hashMap[wordHash] = {
        key: word,
        value: 0
      };
    }
  }
  hashMap[wordHash].value++;
});

hashMap.forEach((element) => {
  if (element) {
    console.log(element);
  }
});
console.log('total collisions: ' + collisions);
