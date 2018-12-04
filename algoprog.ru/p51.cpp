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
  stack<char> st;
  map<char, char> pairs;
  pairs[')'] = '(';
  pairs['}'] = '{';
  pairs[']'] = '[';

  string str;
  cin >> str;
  for (int i = 0; i < str.length(); i += 1) {
    if (str[i] == '(' || str[i] == '{' || str[i] == '[') {
      st.push(str[i]);
    } else {
        if (st.size() == 0 || st.top() != pairs[str[i]]) {
          cout << "no" << endl;
          return 0;
        } else {
          st.pop();
        }
    }
  }
  cout << (st.size() == 0 ? "yes" : "no") << endl;
}
