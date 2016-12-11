#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int main(int argc, char const *argv[]) {
  clock_t start;

  start = clock() / (CLOCKS_PER_SEC / 1000);
  clock_t t1 = clock() / (CLOCKS_PER_SEC / 1000);
  int tab1[10000];
  printf("%Lfms", (long double)(t1-start));

  start = clock() / (CLOCKS_PER_SEC / 1000);
  int tab2[100000];

  start = clock() / (CLOCKS_PER_SEC / 1000);
  int tab3[1000000];

  int *ptr1 = malloc(10000 * sizeof *ptr1);
  int *ptr2 = malloc(100000 * sizeof *ptr2);
  int *ptr3 = malloc(1000000 * sizeof *ptr3);

  return 0;
}
