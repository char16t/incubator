#include <stdio.h>

int main() {
  double n;
  int d;

  scanf("%lf", &n);
  d = (int)(n * 10) % 10;
  printf("%d\n", d);
  return 0;
}
