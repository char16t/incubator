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
  int k, z = 0;
  cin >> k;
  int a[4];
  for (int i = 0; i < 4; i += 1) {
    if (k % 10 == 0) {
      z += 1;
    }
    a[4 - i - 1] = k % 10;
    k = k / 10;
  }
  for (int i = 0; i < 4; i += 1) {
    int min = i;
    for (int j = i; j < 4; j += 1) {
      if (a[j] < a[min]) {
        min = j;
      }
    }
    swap(a[i], a[min]);
  }

  for (int i = z - 1; i >= 0; i -= 1) {
    swap(a[i], a[i + 1]);
  }

  for (int i = 0; i < 4; i += 1) {
    cout << a[i];
  }
  cout << endl;
}
