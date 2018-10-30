#include <iostream>
#include <string>

using namespace std;

void recur(string s, int n, int acc, int prev)
{
  if (acc == n) {
    cout << s << endl;
    return;
  }
  if (acc > n) {
    return;
  }
  for (int i = prev; i >= 1; i -= 1) {
    recur(s + to_string(i) + " ", n, acc + i, i);
  }
}

int main()
{
  int n;
  cin >> n;
  recur("", n, 0, n);
}
