#include <iostream>
#include <bitset>
#include <string>
#include <sstream>
#include <vector>

using namespace std;

void recur(string s, int ones, int n, int k) {
  if (s.length() == n + 1) {
    if (ones == k) {
      cout << s.substr(1, s.length()) << endl;
    }
    return;
  }
  if (n + 1 - s.length() < k - ones) {
    return;
  }
  recur(s + "0", ones, n, k);
  if (ones == k) {
    return;
  }
  recur(s + "1", ones + 1, n, k);
}

int main()
{
  int n, k;
  cin >> n >> k;
  recur("0", 0, n, k);
}
