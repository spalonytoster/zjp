// jshint node: true, esversion: 6
'use strict';
  
function sum(array) {
  var recur = (array, index) => {
    if (index < 0) {
      return 0;
    }
    return Number.parseInt(array[index], 10) + recur(array, index-1);
  };
  return recur(array, array.length-1);
}

console.log(sum(process.argv.splice(2)));
