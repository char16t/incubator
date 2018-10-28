#include <iostream>
#include <bitset>
#include <string>
#include <sstream>
#include <vector>

using namespace std;

void recur(string s, int n, int was[]) {
  if (s.length() == n) {
      cout << s << endl;
      return;
  }
  for (int i = 0; i < n; i += 1) {
    if (was[i] == 0) {
      was[i] = 1;
      recur(s + to_string(i + 1), n, was);
      was[i] = 0;
    }
  }
}

int main()
{
  int n;
  cin >> n;
  int a[n] = {0};
  recur("", n, a);
}
