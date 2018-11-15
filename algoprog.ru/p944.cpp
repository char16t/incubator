#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

int main()
{
  long n, m;
  cin >> m >> n;
  long a[m][n];
  long p[m][n];
  for (long i = 0; i < m; i += 1) {
    for (long j = 0; j < n; j += 1) {
      cin >> p[i][j];
    }
  }
  a[0][0] = p[0][0];
  for (long i = 1; i <= m; i += 1) {
    a[i][0] = a[i-1][0] + p[i][0];
  }
  for (long j = 1; j <= n; j += 1) {
    a[0][j] = a[0][j - 1] + p[0][j];
  }
  for (long i = 1; i < m; i += 1) {
    for (long j = 1; j < n; j += 1) {
      a[i][j] = min(a[i][j - 1], a[i - 1][j]) + p[i][j];
    }
  }
  cout << a[m - 1][n - 1] << endl;
}
