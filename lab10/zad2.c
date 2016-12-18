#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void multiplyMatrixes(int* a, int* b, int size) {
  int multiply[size][size];
  int sum = 0;
  for (size_t c = 0; c < size; c++) {
    for (size_t d = 0; d < size; d++) {
      for (size_t k = 0; k < size; k++) {
        sum += a[c][k] * b[k][d];
      }
      multiply[c][d] = sum;
      sum = 0;
    }
  }

  for (size_t i = 0; i < size; i++) {
    for (size_t j = 0; j < size; j++) {
      printf("%d\t", multiply[i][j]);
    }
    printf("\n");
  }
}

int main(int argc, char const *argv[]) {
  int size = 3;

  int nums1[3*3] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
  int nums11[3][3] = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };

  int nums2[3*3] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
  int nums22[3][3] = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };

  // printf("%d\n", nums1[size*1+0]);
  // printf("%d\n", nums11[1][0]);

  // srand(time(NULL));
  // int r = rand();

  // multiplyMatrixes(num2, b, size);

  return 0;
}
