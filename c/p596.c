#include <stdio.h>

int main() {
  double start_len, max_len;
  scanf("%lf %lf", &start_len, &max_len);

  double len = start_len;
  int day = 1;
  while ((max_len - len) >= 1e-7) {
    len += len * 0.7;
    day += 1;
  }
  printf("%d\n", day);

  return 0;
}
