#include <stdio.h>
#include <stdlib.h>
void function() {
  int x = 5;
  int i = 1;
  while(i != 0) {
    int x = 4;
    printf("%d\n",x);
    i--;
  }
  printf("%d\n",x);
}

int main() {
  function();
  return EXIT_SUCCESS;
}
