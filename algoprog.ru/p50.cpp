#include <bits/stdc++.h>

using namespace std;

//#define DEBUG
#ifdef DEBUG
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    #define cin fin
    #define cout fout
#endif // DEBUG

int main() {
  bool isBotva = false;
  long n = 0;
  deque<int> first;
  deque<int> second;
  for (int i = 0; i < 5; i += 1) {
    int k;
    cin >> k;
    first.push_back(k);
  }
  for (int i = 0; i < 5; i += 1) {
    int k;
    cin >> k;
    second.push_back(k);
  }
  while(first.size() != 0 && second.size() != 0) {
    n += 1;
    int a = first.front();
    first.pop_front();

    int b = second.front();
    second.pop_front();

    if ((a > b && a != 9) || (a == 9 && b != 0) || (a == 0 && b == 9)) {
      first.push_back(a);
      first.push_back(b);
    } else {
      second.push_back(a);
      second.push_back(b);
    }
    if (n == 1000000) {
      cout << "botva" << endl;
      isBotva = true;
      break;
    }
  }
  if (!isBotva) {
    cout << (first.size() != 0 ? "first " : "second ") << n << endl;
  }
}
