#include <iostream>

using namespace std;

int main()
{
  int n, c, max;
  cin >> n;
  cin >> c;
  max = c;
  for (int i = 1; i < n; i += 1) {
    cin >> c;
    if (c > max) {
      max = c;
    }
  }
  cout << max << endl;
}
