#include <iostream>

using namespace std;

int main()
{
  int n;
  long long t;
  cin >> n;
  long long a[n] = {};
  for (int i = 0; i < n; i += 1) {
    cin >> a[i];
  }
  for (int i = 0; i < n - 1; i += 1) {
    bool ch = false;
    for (int j = i; j >= 0; j -= 1) {
      if (a[j + 1] < a[j]) {
        t = a[j + 1];
        a[j + 1] = a[j];
        a[j] = t;
        ch = true;
      }
    }
    if (ch == true) {
      for (int k = 0; k < n; k += 1) {
        cout << a[k] << " ";
      }
      cout << endl;
    }
  }
}
