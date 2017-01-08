// jshint node: true, esversion: 6
'use strict';

function randomNumberBetween(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function invokeCommandForParam(param) {
  param.command.fun.apply(null, param.command.parameters);
}

function invokeRandomCommandForFulfilledParam(params, indexes) {
  let index = randomNumberBetween(0, indexes.length-1);
  if (params[index].condition()) {
    invokeCommandForParam(params[index]);
    return true;
  }
  return false;
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
    if (params[i].condition()) fulfilledConditionIndexes.push(i);
  }
  if (fulfilledConditionIndexes.length === 1) {
    invokeCommandForParam(params[fulfilledConditionIndexes[0]]);
    return true;
  }
  if (fulfilledConditionIndexes.length > 1) {
    invokeRandomCommandForFulfilledParam(params, fulfilledConditionIndexes);
    return true;
  }
  return false;
}

// encapsulated global scope object
let ctx = {}, params = [];

// zad 7a
function swap(a, b, arr) {
  let temp = arr[a];
  arr[a] = arr[b];
  arr[b] = temp;
}

ctx.arr = [3, 2, 4, 1];

for (let i = 0; i < ctx.arr.length-1; i++) {
  params.push({
    condition: () => ctx.arr[i] > ctx.arr[i+1],
    command: {
      fun: swap,
      parameters: [i, i+1, ctx.arr]
    }
  });
}

console.log(`array before: ${ctx.arr}`);
nonDeterministicWhile(ctx, params);
console.log(`array after: ${ctx.arr}`);
// end zad 7a
