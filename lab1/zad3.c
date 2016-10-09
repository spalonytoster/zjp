/*
* This program simulates iteration over 2-dimensional
* matrix using 'jumping' (labels and goto keyword) 
*/

#include <stdio.h>

int MATRIX_LEN = 3;

void simulateDoubleLoop(int matrix[MATRIX_LEN][MATRIX_LEN], int MATRIX_LEN) {
  int x = 0, y = 0;
  outerLoop:
    if (x != MATRIX_LEN) { // stands for JNE - jump not equal
      innerLoop:
        if (y != MATRIX_LEN) {
          printf("%i ", matrix[x][y]);
          y++;
          goto innerLoop;
        }
        else {
          y = 0;
        }
      printf("\n");
      x++;
      goto outerLoop;
    }
}

int main(int argc, char const *argv[]) {
  int matrix[3][3] = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
  };
  simulateDoubleLoop(matrix, MATRIX_LEN);
  return 0;
}
