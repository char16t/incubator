#include <stdio.h>
#include <math.h>

static const char *yes = "YES";
static const char *no = "NO";

int main() {
    int x1, y1, x2, y2;
    scanf("%d", &y1);
    scanf("%d", &x1);
    scanf("%d", &y2);
    scanf("%d", &x2);
    
    printf("%s\n", (x1 == x2) || (y1 == y2) ? yes : no);
    return 0;
}
