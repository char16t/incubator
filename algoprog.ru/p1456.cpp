#include <iostream>

using namespace std;

int main()
{
  int n, m, p;
  cin >> n;
  p = n + 1;
  int a[n] = {};
  for (int i = 0; i < n; i += 1) {
    cin >> a[i];
  }
  cin >> m;
  for (int i = 0; i < n; i += 1) {
    if (m > a[i]) {
      p = i + 1;
      break;
    }
  }
  cout << p << endl;
}
