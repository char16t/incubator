#include <iostream>

using namespace std;

int main()
{
  int n;
  cin >> n;
  long a[n] = {};
  a[0] = 1;
  a[1] = 1;
  if (n == 1 || n == 2) {
    cout << 1 << endl;
    return 0;
  }
  for (int i = 2; i < n; i += 1) {
    a[i] = a[i - 1] + a[i - 2];
  }
  cout << a[n - 1] << endl;
}
