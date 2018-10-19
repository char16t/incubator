#include <iostream>
#include <string>

using namespace std;

int main()
{
  // in
  int n;
  cin >> n;
  int seq[n] = {};
  for (int i = 0; i < n; i += 1) {
    cin >> seq[i];
  }

  int j;
  for (j = 0; j < n - 1; j += 1) {
    bool isPalindrome = true;
    for (int i = j; i < (n + i) / 2; i += 1) {
      if (seq[i] != seq[n-i+j-1]) {
        isPalindrome = false;
        break;
      }
    }
    if (isPalindrome) {
      break;
    }
  }
  cout << j << endl;
  for(int i = j; i > 0; i -= 1) {
    cout << seq[i - 1] << " ";
  }
  cout << endl;
}
