#include <iostream>

using namespace std;

int main()
{
  int n, max, maxi;
  cin >> n;
  int coll[n] = {};
  cin >> coll[0];
  max = coll[0];
  maxi = 1;
  for (int i = 1; i < n; i += 1) {
    cin >> coll[i];
    if (coll[i] > max) {
      maxi = i + 1;
      max = coll[i];
    }
  }
  cout << maxi << endl;
}

