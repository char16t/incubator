#include <stdio.h>
#include <math.h>

int main() {
  unsigned int k, n, page, row;
  scanf("%d %d", &k, &n);
  page = (n + k - 1) / k;
  row = n - k * (page - 1);
  printf("%d %d\n", page, row);
  return 0;
}
