#include <iostream>

using namespace std;

int main()
{
  long a, b, t, c, d;
  cin >> a >> b;
  c = a; d = b;
  while (b != 0) {
    t = (b + (a % b)) % b;
    a = b;
    b = t;
  }
  cout << c / a << " " << d / a << endl;
}
