#include <iostream>

using namespace std;

int main()
{
  long a, b, t;
  cin >> a >> b;
  while (b != 0) {
    t = a % b;
    a = b;
    b = t;
  }
  cout << a << endl;
}
