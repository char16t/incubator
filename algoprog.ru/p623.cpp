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
  int64_t n;
  vector<int64_t> result;
  cin >> n;
  int64_t d = 2;
  while (d * d <= n) {
    while (n % d == 0) {
      result.push_back(d);
      n /= d;
    }
    d += 1;
  }
  if (n != 1) {
    result.push_back(n);
  }
  cout << result[0];
  for (int i = 1; i < result.size(); i += 1) {
    cout << "*" << result[i];
  }
  cout << endl;
}
