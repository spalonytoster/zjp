// sizeof - rozmiar w bajtach
// T tab[n][m][p]
// tab[i][j][k]
// adres + ((i*m + j) * p+k) * rozmiar
#include <stdio.h>

int main() {

  typedef struct {
    double a;
    char b[10][15];
    int c[12];
  } QQ;

  QQ a[10][100][20];

  unsigned long structSize = sizeof(a[0][0][0]);
  printf("structSize: %ld\n", structSize);

  int address = 1000;
  printf("Pocz. adres tablicy: %d\n", address);

  int start, end;

  // a)
  start = address;
  end = address + ((0*100 + 0) * 20 + 0 + 1) * structSize;
  printf("tab[0][0][0]: %d-%d\n", start, end);

  // b)
  start = address;
  end = address + ((2*100 + 3) * 20 + 4) * structSize;
  printf("tab[2][3][4]: %d-%d\n", start, end);

  return 0;
}
