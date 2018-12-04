#include <bits/stdc++.h>

using namespace std;

//#define DEBUG
#ifdef DEBUG
  ifstream fin("input.txt");
  ofstream fout("output.txt");
  #define cin fin
  #define cout fout
#endif // DEBUG

int exec(int a, int b, string& op) {
  int r = 0;
  if (op == "*") {
    r = a * b;
  } else if (op == "-") {
    r = b - a;
  } else if (op == "+") {
    r = a + b;
  }
  return r;
}

int main() {
  stack<int> st;
  string input;
  while (cin >> input) {
    if (input == "*" || input == "-" || input == "+") {
      int a = st.top(); st.pop();
      int b = st.top(); st.pop();
      st.push(exec(a, b, input));
    } else {
      st.push(stoi(input));
    }
  }
  cout << st.top() << endl;
}
