#include <iostream>

using namespace std;

int main()
{
  int n, c = 0;
  cin >> n;
  for (int i = 1; i <= n && c < n; i += 1) {
    for (int j = 0; j < i && c < n; j += 1) {
      cout << i << " ";
      c += 1;
    }
  }
  cout << endl;
}
