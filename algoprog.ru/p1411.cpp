#include <iostream>

using namespace std;

int main()
{
  int n;
  long long t;
  int c = 0;
  cin >> n;
  long long a[n] = {};
  for (int i = 0; i < n; i += 1) {
    cin >> a[i];
  }
  for(int i = 0; i < n; i += 1) {
    for(int j = n - 1; j > i; j -= 1) {
      if (a[j - 1] > a[j]) {
        t = a[j - 1];
        a[j - 1] = a[j];
        a[j] = t;
        c += 1;
      }
    }
  }
  cout << c << endl;
}
