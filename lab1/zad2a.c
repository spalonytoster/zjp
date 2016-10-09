#include <stdio.h>
#include <errno.h>   // for errno
#include <limits.h>  // for INT_MAX
#include <stdlib.h>  // for strtol

int main(int argc, char* argv[]) {

  int i;
  long conv;
  char *p;
  long sum = 0;

  for (i = 1; i < argc; i++) {
    conv = strtol(argv[i], &p, 10);
    sum += conv;
  }
  printf("%ld\n", sum);

  return 0;
};
