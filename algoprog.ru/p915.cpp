#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
  int n;
  cin >> n;
  n += 1;
  int a[n] = {0};
  int p[n] = {0};
  for (int i = 1; i < n; i += 1) {
    cin >> p[i];
  }
  a[0] = p[0];
  a[1] = p[1];
  for (int i = 2; i < n; i += 1) {
    a[i] = min(a[i - 1], a[i - 2]) + p[i];
  }
  cout << a[n - 1] << endl;
}
