// jshint esversion: 6

function construct2DArray(rows) {
  let array = [];
  for (let i = 0; i < rows; i++) {
    array.push([]);
  }
  return array;
}

function multiply1(a, b) {
  let sum = 0;
  let multiplied = construct2DArray(a.length);

  for (let i = 0; i < a.length; i++) {
    for (let c = 0; c < b[0].length; c++) {
      sum = 0;
      for (let j = 0; j < b.length; j++) {
        sum += a[i][j] * b[j][c];
      }
      multiplied[i][c] = sum;
    }
  }
  return multiplied;
}

function multiply2(a, b) {
  let sum = 0;
  let multiplied = {
    arr: [],
    rows: a.rows,
    cols: b.cols
  };

  for (let i = 0; i < a.rows; i++) {
    for (let c = 0; c < b.cols; c++) {
      sum = 0;
      for (let j = 0; j < b.rows; j++) {
        sum += a.arr[i*a.rows + j] * b.arr[j*b.rows + c];
      }
      multiplied.arr[i*multiplied.rows + c] = sum;
    }
  }
  return multiplied.arr;
}

function print2DMatrix(matrix) {
  for (let i = 0; i < matrix.length; i++) {
    for (let j = 0; j < matrix.length; j++) {
      process.stdout.write(matrix[i][j].toString() + ' ');
    }
    console.log('');
  }
}

function print1DMatrix(matrix) {
  for (let i = 0; i < matrix.rows; i++) {
    for (let j = 0; j < matrix.cols; j++) {
      process.stdout.write(matrix.arr[i*matrix.rows + j].toString() + ' ');
    }
    console.log('');
  }
}

function random() {
  return Math.floor(Math.random() * 100);
}

function fillWithRandomNumbers(array, amount) {
  let result = array.slice();
  for (let i = 0; i < amount; i++) {
    result.push(random());
  }
  return result;
}

(function main() {
  let a1 = [[1,2,3],[4,5,6],[7,8,9]];
  let b1 = [[1,2,3],[4,5,6],[7,8,9]];
  let mult1 = multiply1(a1, b1);
  print2DMatrix(mult1);

  console.log('');

  let a2 = [1,2,3,4,5,6,7,8,9];
  let b2 = [1,2,3,4,5,6,7,8,9];
  let mult2 = multiply2({ arr: a2, rows: 3, cols: 3 }, { arr: b2, rows: 3, cols: 3 });
  print1DMatrix({ arr: mult2, rows: 3, cols: 3 });

  // let sizes = [1000, 10000, 20000];
  //
  // sizes.forEach((size) => {
  //   console.log('for size of ' + size);
  //   let a1 = fillWithRandomNumbers(construct2DArray(size), size);
  //   let b1 = fillWithRandomNumbers(construct2DArray(size), size);
  //
  //   let multiplied1 = multiply1(a1, b1);
  //   console.log('ok1');
  //
  //   let a2 = fillWithRandomNumbers([], size*size);
  //   let b2 = fillWithRandomNumbers([], size*size);
  //
  //   let multiplied2 = multiply2({ arr: a2, rows: size, cols: size }, { arr: b2, rows: size, cols: size });
  //   console.log('ok2');
  // });
})();
