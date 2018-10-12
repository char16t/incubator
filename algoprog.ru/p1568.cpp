#include <iostream>

using namespace std;

int main()
{
  int curr = 1;
  int c[9] = {0};
  while (true) {
    cin >> curr;
    if (curr == 0) {
      break;
    }
    c[curr - 1] += 1;
  }
  for (int i = 0; i < 9; i += 1) {
    cout << c[i] << " ";
  }
  cout << endl;
}
