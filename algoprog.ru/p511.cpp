#include <iostream>
#include <cmath>

using namespace std;

int main()
{
  int n, a, b;
  cin >> n >> a >> b;

  int c = (n + (-abs(a-b) % n)) % n;
  int d = (n + (abs(a-b) % n)) % n;
  cout << (c < d ? c : d) - 1 << endl; // min (-|a-b| % n, |a-b| % n) - 1
}
