#include <stdio.h>

int main() {

  #pragma pack(push)
  #pragma pack(1)

  typedef struct {
    int a;
    double b;
  } QQ;
  #pragma pack(pop)

  QQ a;

  printf("%ld\n", sizeof(a));

  return 0;
}
