#include <iostream>

using namespace std;

int main()
{
  int n;
  cin >> n;
  int m = 45 * n
    + ((n - 1) % 2 == 0
       ? ((n - 1) / 2) * 5 + ((n - 1) / 2) * 15
       : ((n - 1) / 2 + 1) * 5 + ((n - 1) / 2) * 15);
  cout << 9 + (m / 60) << " " << m % 60 << endl;
}
