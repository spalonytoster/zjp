// int - 4 bajty; double 8 bajtow; char 1 bajt; 1 bajt = 4 bity

#include <stdio.h>

int main() {

  // #pragma pack(push)
  // #pragma pack(1)

  typedef struct {
    int a;
    char b;
    char c;
  } QQ4;

  typedef struct {
    char a;
    int b;
    char c;
  } QQ5;

  // #pragma pack(pop)

  QQ4 a;
  QQ5 b;

  printf("%ld\n", sizeof(a));
  printf("%ld\n", sizeof(b));

  return 0;
}
