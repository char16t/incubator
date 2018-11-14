#include <iostream>

using namespace std;

int main()
{
  long n, m;
  cin >> m >> n;
  long a[m][n];
  for (long i = 0; i < m; i += 1) {
    for (long j = 0; j < n; j += 1) {
      a[i][j] = 0;
    }
  }
  for (long i = 0; i <= m; i += 1) {
    a[i][0] = 1;
  }
  for (long j = 0; j <= n; j += 1) {
    a[0][j] = 1;
  }
  for (long i = 1; i < m; i += 1) {
    for (long j = 1; j < n; j += 1) {
        a[i][j] = a[i][j - 1] + a[i - 1][j];
    }
  }
  cout << a[m - 1][n - 1] << endl;
  return 0;
}
