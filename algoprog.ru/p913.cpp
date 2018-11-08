#include <iostream>

using namespace std;

int main()
{
  int n;
  cin >> n;
  int a[n + 1][2];
  a[n][0] = 1;
  a[n][1] = 1;
  for (int i = n - 1; i >= 0; i -= 1) {
    a[i][1] = a[i + 1][0];
    a[i][0] = a[i + 1][0] + a[i + 1][1];
  }
  cout << a[0][0] << endl;
}
