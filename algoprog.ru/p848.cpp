#include <iostream>
#include <string>

using namespace std;

int main()
{
  string s;
  cin >> s;
  char prev = 'z';
  for(char& c : s) {
    if (c >= '6' && c <= '9') {
      cout << "NO" << endl;
      return 0;
    }
    if (c >= '0' && c <= '5' && prev >= '0' && prev <= '5') {
      cout << "NO" << endl;
      return 0;
    }
    prev = c;
  }
  cout << "YES" << endl;
}
