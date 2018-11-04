#include <iostream>

using namespace std;

int main()
{
  int n;
  cin >> n;
  n += 1;
  int d[n] = {0};
  d[0] = 1;
  d[1] = 1;
  for (int i = 2; i < n; i += 1) {
    d[i] = (d[i - 1] + d[i - 2]) % 10;
  }
  cout << d[n - 1] << endl;
}
