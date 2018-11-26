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
  int64_t n, k, p;
  cin >> n >> k;
  p = n * k;
  while (k != 0) {
    int64_t t = k;
    k = n % k;
    n = t;
  }
  int64_t lcm = p / n;
  cout << lcm << endl;
}
