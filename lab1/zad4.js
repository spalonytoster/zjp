// jshint node: true, esversion: 6
'use strict';

function originalFactorial(val) {
  if (val === 1) {
    return 1;
  }
  return val * factorial(val - 1);
}

let factorial = {
  arg: Number.parseInt(process.argv[2], 10), // first command line argument
  stack: [],
  temp: 1,
  recur: function() {
    if (this.arg === 1 && this.stack.length === 0) {
      return this.temp;
    }
    if (this.arg === 1 && this.stack.length > 0) {
      this.temp *= this.stack.pop();
      return this.recur();
    }
    this.stack.push(this.arg);
    this.arg--;
    return this.recur();
  }
};

console.log('result: ' + factorial.recur());
