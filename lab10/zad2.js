// jshint esversion: 6

function multiplyMatrixes(a, b) {
  let multipied = [];
  let size = a.length;
  for (let c = 0; c < size; c++) {
    for (let d = 0; d < size; d++) {
      let sum = 0;
      for (let k = 0; k < size; k++) {
        sum += a[c][k] * b[k][d];
      }
      multiplied[c][d] = sum;
    }
  }


}

function printMatrix(matrix) {
  for (let i = 0; i < matrix.length; i++) {
    for (let j = 0; j < matrix.length; j++) {
      process.stdout.write(matrix[i][j].toString() + ' ');
    }
    console.log('');
  }
}

(function main() {
  let sizes = [1000, 10000, 100000];

  let a = [ [1, 2, 3], [4, 5, 6], [7, 8, 9]];
  let b = [ [1, 2, 3], [4, 5, 6], [7, 8, 9]];

  printMatrix(a);
  // multiplyMatrixes(a, b);

})();
