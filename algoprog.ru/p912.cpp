#include <iostream>

using namespace std;

int main()
{
  unsigned long long n;
  cin >> n;
  n += 1;
  unsigned long long a[n];
  a[0] = 1;
  a[1] = 2;
  a[2] = 4;
  for (int i = 3; i < n; i += 1) {
    a[i] = a[i - 1] + a[i - 2] + a[i - 3];
  }
  cout << a[n-1] << endl;
}
