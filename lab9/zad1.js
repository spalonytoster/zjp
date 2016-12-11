// jshint esversion: 6
const now = require('performance-now');
let start;

start = now();
let tab1 = new Array(10000);
let t1 = now() - start;
console.log('Execution time: %dms', t1);

start = now();
let tab2 = new Array(100000);
let t2 = now() - start;
console.log('Execution time: %dms', t1);

start = now();
let tab3 = new Array(10000000);
let t3 = now() - start;
console.log('Execution time: %dms', t1);
