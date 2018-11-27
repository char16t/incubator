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
  int n, m, n1, m1;
  cin >> n >> m;
  n1 = n - 1;
  m1 = m - 1;
  while (m1 != 0) {
    int t = n1;
    n1 = m1;
    m1 = t % m1;
  }
  cout << n1 + 1 << endl;
}
