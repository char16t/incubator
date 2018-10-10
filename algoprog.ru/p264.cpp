#include <iostream>
#include <string>

using namespace std;

string yes = "YES";
string no = "NO";

int main()
{
  unsigned long k;
  bool doable;
  cin >> k;
  doable = (k != 0) && (k != 1) && (k != 2) && (k != 4) && (k != 7);
  cout << (doable ? yes : no) << endl;
}
