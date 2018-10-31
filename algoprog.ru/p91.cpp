#include <iostream>
#include <string>

using namespace std;

void recur(string s, int n, int sum, int prev)
{
  if (sum > n)
    return;
  if (sum == n) {
    cout << s << endl;
    return;
  }
  for (int i = prev; i <= n; i += 1) {
    recur(s + to_string(i) + " ", n, sum + i, i);
  }
}

int main()
{
  int n;
  cin >> n;
  recur("", n, 0, 1);
}
