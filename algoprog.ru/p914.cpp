#include <iostream>

using namespace std;

int main()
{
  int n;
  cin >> n;
  int a[n + 1][3];
  a[n][0] = 1; // ..A
  a[n][1] = 1; // ..B
  a[n][2] = 1; // ..C
  for (int i = n - 1; i >= 0; i -= 1) {
    a[i][2] = a[i + 1][0] + a[i + 1][1] + a[i + 1][2];
    a[i][1] = a[i + 1][0] + a[i + 1][2];
    a[i][0] = a[i + 1][0] + a[i + 1][1] + a[i + 1][2];
  }
  cout << a[0][0] << endl;
}
