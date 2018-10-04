#include <iostream>
#include <string>

using namespace std;

int main()
{
  short n;
  cin >> n;

  if (n > 10 && n < 20) {
    cout << to_string(n) + " korov" << endl;
    return 0;
  }

  switch (n % 10) {
  case 1:
    cout << to_string(n) + " korova" << endl;
    break;
  case 2:
  case 3:
  case 4:
    cout << to_string(n) + " korovy" << endl;
    break;
  case 5:
  case 6:
  case 7:
  case 8:
  case 9:
  case 0:
    cout << to_string(n) + " korov" << endl;
    break;
  }
}
