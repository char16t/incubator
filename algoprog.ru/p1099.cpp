#include <iostream>

using namespace std;

int main()
{
  int n;
  cin >> n;
  int a[n] = {};
  for (int i = 0; i < n; i += 1) {
    cin >> a[i];
  }
  int sum = 0;
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
  for (int i = n - 1; i >= n % 3; i -= 3) {
    sum += a[i] + a[i - 1];
  }
  for (int j = 0; j < n % 3; j += 1) {
    sum += a[j];
  }
  cout << sum << endl;
}
