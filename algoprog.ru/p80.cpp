#include <iostream>
#include <bitset>
#include <string>
#include <sstream>
#include <vector>

using namespace std;

int main()
{
  int k;
  cin >> k;
  for (int i = 0; i < (1 << k); i += 1) {
    string c = bitset<11>(i).to_string();
    cout << c.substr(c.length() - k, c.length()) << endl;
  }
}
