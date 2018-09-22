#include <stdio.h>

int main() {
  double start_len, max_len;
  scanf("%lf %lf", &start_len, &max_len);

  double len = start_len;
  double sum = start_len;
  int day = 1;
  while ((max_len - sum) >= 1e-7) {
    day += 1;
    len += len * 0.7;
    sum += len;
  }
  printf("%d\n", day);

  return 0;
}
