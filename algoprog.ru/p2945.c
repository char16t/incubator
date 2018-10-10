#include <stdio.h>
#include <math.h>

int main() {
  unsigned int number, next;
  scanf("%d", &number);
  next = ((number + 2) / 2) * 2;
  printf("%d\n", next);
  return 0;
}
