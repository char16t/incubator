#include <bits/stdc++.h>

//#define DEBUG
#ifdef DEBUG
   ifstream fin("input.txt");
   ofstream fout("output.txt");
   #define cin fin
   #define cout fout
#endif // DEBUG

using namespace std;

int main() {
  long n, m;
  cin >> n >> m;
  long w[n+1] = {0};
  long a[n+1][10001]; // max weight + 1
  for (int i = 0; i < n + 1; i++) {
    for (int j = 0; j < 10001; j++) {
      a[i][j] = 0;
    }
  }
  for (long i = 1; i <= n; i++) {
    cin >> w[i];
  }
  for (long i = 1; i <= n; i++) {
    for (long j = 0; j <= m; j++) {
      a[i][j] = a[i-1][j];
      if (j >= w[i] && a[i-1][j-w[i]]+w[i] > a[i][j]) {
        a[i][j] = a[i-1][j-w[i]]+w[i];
      }
    }
  }
  cout << a[n][m] << endl;
}
