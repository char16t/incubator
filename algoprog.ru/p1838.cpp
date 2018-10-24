#include <iostream>
#include <cmath>

using namespace std;

int main()
{
  long a, b, c, d, x, y, m, n, t;
  cin >> a >> b >> c >> d;
  x = abs(a - c);
  y = abs(b - d);
  m = x;
  n = y;

  while (y != 0) {
    t = x % y;
    x = y;
    y = t;
  }
  cout << m + n - x << endl;
}
