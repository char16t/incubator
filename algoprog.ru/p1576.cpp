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
  int s, n;
  cin >> s >> n;
  int a[n] = {0};
  for (int i = 0; i < n; i += 1) {
    cin >> a[i];
  }
  for (int i = 0; i < n - 1; i += 1) {
    int min = i;
    for (int j = i; j < n; j += 1) {
      if (a[j] < a[min]) {
        min = j;
      }
    }
    int t = a[min];
    a[min] = a[i];
    a[i] = t;
  }
  int sum = 0;
  int i;
  for (i = 0; i < n; i += 1) {
    if (sum + a[i] > s) {
      break;
    }
    sum += a[i];
  }
  cout << i << endl;
}
