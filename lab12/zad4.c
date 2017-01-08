// int - 4 bajty; double 8 bajtow; char 1 bajt; 1 bajt = 4 bity

#include <stdio.h>

int main() {

  #pragma pack(push)
  #pragma pack(1)

  typedef struct {
    int a:16;
    int b:4;
    int c:4;
  } QQ;

  #pragma pack(pop)

  QQ a;

  printf("%ld\n", sizeof(a));

  return 0;
}
