#include <stdio.h>

int main() {
  int n;
  unsigned long long int
    fact = 1;
  double
    sum = 1;

  scanf("%d", &n);

  // 1/n! where n > 9 is too small
  if (n > 9) {
    n = 9;
  }

  for (int i = 1; i <= n; i += 1) {
    fact *= i;
    sum += ((double)1 / (double)fact);
  }

  printf("%.5f\n", sum);

  return 0;
}
