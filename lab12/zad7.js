// jshint node: true, esversion: 6
'use strict';

function randomNumberBetween(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function invokeCommandForParam(ctx, param) {
  param.command.parameters.push(ctx);
  param.command.fun.apply(null, param.command.parameters);
}

function invokeRandomCommandForFulfilledParam(ctx, params, indexes) {
  let index = randomNumberBetween(0, indexes.length-1);
  invokeCommandForParam(ctx, params[index]);
  return true;
}

function nonDeterministicWhile(ctx, params) {
  while (nonDeterministicIf(ctx, params)) {}
}

/**
* This function returns true if any condition has been fulfilled and thus a command has been executed
*/
function nonDeterministicIf(ctx, params) {
  let fulfilledConditionIndexes = [];
  for (let i = 0; i < params.length; i++) {
    if (params[i].condition(ctx)) fulfilledConditionIndexes.push(i);
  }
  if (fulfilledConditionIndexes.length === 1) {
    invokeCommandForParam(ctx, params[fulfilledConditionIndexes[0]]);
    return true;
  }
  if (fulfilledConditionIndexes.length > 1) {
    invokeRandomCommandForFulfilledParam(ctx, params, fulfilledConditionIndexes);
    return true;
  }
  return false;
}

// encapsulated global scope object
let ctx = {}, params = [];

// zad 7a
function swap(a, b, arr, ctx) {
  let temp = arr[a];
  arr[a] = arr[b];
  arr[b] = temp;
}

ctx.arr = [3, 2, 4, 1];

for (let i = 0; i < ctx.arr.length-1; i++) {
  params.push({
    condition: (ctx) => ctx.arr[i] > ctx.arr[i+1],
    command: {
      fun: swap,
      parameters: [i, i+1, ctx.arr]
    }
  });
}
/**
* utworzone zostaly 3 if-y, ktore beda wykorzystane w ciele while-a
* gdyby podac na wejsciu tablice o n liczbie elementow, utworzy sie wowczas n-1 if-ow
*/

console.log('zad 7a');
console.log('------------------');
console.log(`array before: ${ctx.arr}`);
nonDeterministicWhile(ctx, params);
console.log(`array after: ${ctx.arr}`);
// end zad 7a
console.log('');
// zad 7b

console.log('zad 7b');
console.log('------------------');
// reset data
ctx = {}; params = [];

// referential gcd algorithm
function gcd(a,b) {
  if (b === 0) return a;
  else return gcd(b, a % b);
}

// refactored to iteration instead of recursion
function gcdIter(a, b) {
  let temp;
  while (b !== 0) {
    temp = a;
    a = b;
    b = temp % b;
  }
  return a;
}

// representative example
ctx.a = 175;
ctx.b = 105;

params.push({
  condition: (ctx) => ctx.a < ctx.b,
  command: {
    fun: (ctx) => { ctx.b = ctx.b - ctx.a; },
    parameters: []
  }
}, {
  condition: (ctx) => ctx.b < ctx.a,
  command: {
    fun: (ctx) => { ctx.a = ctx.a - ctx.b; },
    parameters: []
  }
});

console.log(`calculating gcd for ${JSON.stringify(ctx)}`);
nonDeterministicWhile(ctx, params);
console.log(`result: ${ctx.a}`);
// end zad 7b
