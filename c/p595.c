#include <stdio.h>

int main() {
  double x, y, z;
  int n;
  double a, b, c, q;
  double
    acc_a = 0,
    acc_b = 0,
    acc_c = 0;

  scanf("%lf %lf %lf", &x, &y, &z);
  scanf("%d", &n);
  for (int i = 0; i < n; i += 1) {
    scanf("%lf %lf %lf %lf", &a, &b, &c, &q);
    acc_a += a * q;
    acc_b += b * q;
    acc_c += c * q;
  }

  if (((x - acc_a) < 1e-7)
      && ((y - acc_b) < 1e-7)
      && ((z - acc_c) < 1e-7)) {
    printf("%s\n", "YES");
  } else {
    printf("%s\n", "NO");
  }

  return 0;
}
