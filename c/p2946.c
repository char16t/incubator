#include <stdio.h>
#include <math.h>

int main()
{
  unsigned int a, b, c, d;

  scanf("%d", &a);
  scanf("%d", &b);
  scanf("%d", &c);

  d = (a + 1) / 2 + (b + 1) / 2 + (c + 1) / 2;

  printf("%d\n", d);
}
