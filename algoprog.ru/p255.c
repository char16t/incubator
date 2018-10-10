#include <stdio.h>
#include <stdlib.h>
#include <math.h>

static const char *yes = "YES";
static const char *no = "NO";

int main() {
    int x1, y1, x2, y2;
    scanf("%d", &y1);
    scanf("%d", &x1);
    scanf("%d", &y2);
    scanf("%d", &x2);

    printf("%s\n", abs(x1 - x2) == abs(y1 - y2) ? yes : no);
    return 0;
}
