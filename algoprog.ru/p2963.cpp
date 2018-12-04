#include <bits/stdc++.h>

using namespace std;

//#define DEBUG
#ifdef DEBUG
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    #define cin fin
    #define cout fout
#endif // DEBUG

int main()
{
  int n;
  cin >> n;
  int a[n+3] = {0};
  a[1] = 0;
  a[2] = 1;
  a[3] = 1;
  for (int i = 4; i <= n; i++) {
    a[i] = min(min(a[i / 3] + (i % 3) + 1, a[i / 2] + (i % 2) + 1), a[i - 1] + 1);
  }
  cout << a[n] << endl;
}
