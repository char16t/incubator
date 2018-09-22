#include <stdio.h>

int main() {
  float h, m, s;
  
  scanf("%f", &h);
  scanf("%f", &m);
  scanf("%f", &s);

  float ha = 360.0 / 12.0;
  float ma = 360.0 / (12.0 * 60.0);
  float sa = 360.0 / (12.0 * 60.0 * 60.0);
  float angle = (ha * h) + (ma * m) + (sa * s);

  printf("%f\n", angle);
  
  return 0;
}
