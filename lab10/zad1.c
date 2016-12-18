#include <stdio.h>

void swap(int* tab, int a, int b) {
  int temp = *(tab+a);
  *(tab+a) = *(tab+b);
  *(tab+b) = temp;
}

void bubbleSort(int* tab, int size) {
  int sorted = 0; // boolean
  while (!sorted) {
    sorted = 1;
    for (size_t i = 0; i < size-1; i++) {
      if (*(tab+i) > *(tab+(i+1))) {
        swap(tab, i, i+1);
        sorted = 0;
      }
    }
  }
}

int main(int argc, char const *argv[]) {
  int numbers[] = { 3, 1, 2 };
  int size = 3;

  bubbleSort(numbers, size);
  for (size_t i = 0; i < size; i++) {
    printf("%d ", numbers[i]);
  }



  return 0;
}
