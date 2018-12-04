#include <bits/stdc++.h>

using namespace std;

//#define DEBUG
#ifdef DEBUG
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    #define cin fin
    #define cout fout
#endif // DEBUG

int main() {
  int n;
  cin >> n;
  int a[n][10];

  a[0][0] = 0;
  for (int i = 1; i < 10; i += 1) {
    a[0][i] = 1;
  }

  for (int i = 1; i < n; i += 1) {
    a[i][0] = a[i - 1][0] + a[i - 1][1];
    for (int j = 1; j <= 8; j += 1) {
      a[i][j] = a[i - 1][j] + a[i - 1][j - 1] + a[i - 1][j + 1];
    }
    a[i][9] = a[i - 1][9] + a[i - 1][8];
  }

  int ans = 0;
  for (int i = 0; i < 10; i += 1) {
    ans += a[n - 1][i];
  }
  cout << ans << endl;
}
