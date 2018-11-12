#include <iostream>

using namespace std;

int main()
{
  int n;
  cin >> n;
  n += 1;
  int a[n] = {0};
  a[0] = 1;
  a[1] = 1;
  a[2] = 2;
  for (int i = 3; i < n; i += 1) {
    a[i] = a[i - 1] + a[i - 2] + a[i - 3];
  }
  cout << a[n - 1] << endl;
}
