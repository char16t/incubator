#include <bits/stdc++.h>

using namespace std;

//#define DEBUG
#ifdef DEBUG
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    #define cin fin
    #define cout fout
#endif // DEBUG

bool lesss(int a1, int b1, int a2, int b2) {
    if (a1 > a2) {
        return false;
    } else if (a1 < a2) {
        return true;
    } else if (b1 <= b2) {
        return false;
    } else {
        return true;
    }
}

int main() {
  int n, a;
  cin >> n >> a;
  int ai[n] = {0};
  int bi[n] = {0};
  for (int i = 0; i < n; i += 1) {
    cin >> ai[i] >> bi[i];
  }
  for (int i = 0; i < n - 1; i += 1) {
    int min = i;
    for (int j = i; j < n; j += 1) {
      if (lesss(ai[j], bi[j], ai[min], bi[min])) {
        min = j;
      }
    }
    int t = ai[min];
    ai[min] = ai[i];
    ai[i] = t;

    t = bi[min];
    bi[min] = bi[i];
    bi[i] = t;
  }
  int count = 0;
  int acurr = a;
  for (int i = 0; i < n; i += 1) {
    if (ai[i] > acurr) {
      break;
    }
    acurr += bi[i];
    count += 1;
  }
  cout << count << endl;
}
