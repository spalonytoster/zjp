#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

int main() {
  if (fork()==0)
    while (1) {
      printf("Proces potomny %d\n",getpid());
      sleep(3);
    }
  else
    while (1) {
      printf("Proces rodzicielski %d\n",getpid());
      sleep(5);
    }
return EXIT_SUCCESS;
}
