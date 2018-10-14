#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

int main()
{
  long n;
  cin >> n;

  int h = (n / 3600) % 24;
  int m = (n % 3600) / 60;
  int s = (n % 3600) % 60;

  cout << h << ":";
  cout << setw(2) << setfill('0') << m << ":";
  cout << setw(2) << setfill('0') << s << endl;
}
