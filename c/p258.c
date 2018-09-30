#include <stdio.h>

static const char *yes = "YES";
static const char *no = "NO";

int main() {
    long m, n, k;
    scanf("%ld", &n);
    scanf("%ld", &m);
    scanf("%ld", &k);

    printf("%s\n", (((k % m) == 0) || ((k % n) == 0)) && (n * m >= k)  ? yes : no);
    return 0;
}
