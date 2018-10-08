#include <iostream>
#include <limits.h>

using namespace std;

int main()
{
  int maxmax = 0, maxcurr = 1, prev = 0, curr = 1;
  while (curr != 0) {
    cin >> curr;
    if (prev == curr) {
      maxcurr += 1;
    } else if (maxcurr > maxmax) {
      maxmax = maxcurr;
      maxcurr = 1;
    } else {
      maxcurr = 1;
    }
    prev = curr;
  }
  cout << maxmax << endl;
}
