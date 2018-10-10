#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main() {
  long k, t, a, position;
  scanf("%ld %ld", &k, &t);

  a = (t - 1) % (2*k);
  position = k - labs(a - k + 1);

  printf("%ld\n", position);
  return 0;
}
