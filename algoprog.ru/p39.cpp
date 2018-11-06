#include <iostream>

using namespace std;

int main()
{
  int s, n;
  cin >> s;
  cin >> n;
  int a[n] = {};
  for (int i = 0; i < n; i += 1) {
    cin >> a[i];
  }
  for (int i = 0; i < n; i += 1) {
    int min = i;
    for (int j = i + 1; j < n; j += 1) {
      if (a[j] < a[min]) {
        min = j;
      }
    }
    int t = a[i];
    a[i] = a[min];
    a[min] = t;
  }
  int sum = 0;
  int prev = a[0];
  bool first = true;
  for (int i = 0; i < n; i += 1) {
    if (a[i] < s)
      continue;
    else if (first && a[i] >= s) {
      prev = a[i];
      first = false;
      sum += 1;
    }
    else if (a[i] - prev > 2) {
      prev = a[i];
      sum += 1;
    }
  }
  cout << sum << endl;
}
