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
  for (int i = 0; i < n - 1; i += 1) {
    int max = n - i - 1;
    for (int j = 0; j < n - i; j += 1) {
      if (a[max] < a[j]) {
        max = j;
      }
    }
    int t = a[n - i - 1];
    a[n - i - 1] = a[max];
    a[max] = t;
  }
  for (int i = 0; i < n; i += 1) {
    cout << a[i] << " ";
  }
  cout << endl;
}
