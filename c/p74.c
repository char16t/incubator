#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

static const char *yes = "YES";
static const char *no = "NO";

int main() {
  double a, b, c;
  
  scanf("%lf", &a);
  scanf("%lf", &b);
  scanf("%lf", &c);

  if (fabs(a + b - c) < 1e-8) {
    printf("%s\n", yes);
  } else {
    printf("%s\n", no);
  }
  
  return 0;
}
