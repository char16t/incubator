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
  bool isPrime = true;
  int64_t n;
  cin >> n;
  for (int64_t i = 2; i <= n / i; i += 1) {
    if (n % i == 0) {
      cout << "composite" << endl;
      isPrime = false;
      break;
    }
  }
  if (isPrime) {
    cout << "prime" << endl;
  }
}
