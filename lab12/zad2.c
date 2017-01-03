#include <stdio.h>

int main() {

  typedef struct {
    int a;
    double b;
  } QQ;

  QQ a;

  printf("%ld\n", sizeof(a));

  return 0;
}
