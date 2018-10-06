#include <iostream>
#include <limits.h>

using namespace std;

int main()
{
  int max = INT_MIN, maxn, curr;
  while (curr != 0) {
    cin >> curr;
    if (curr > max) {
      maxn = 1;
      max = curr;
    } else if (curr == max) {
      maxn += 1;
    }
  }
  cout << maxn << endl;
}
