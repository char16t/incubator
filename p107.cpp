#include <iostream>
#include <string>
#include <sstream>
#include <vector>

using namespace std;

vector<string> split(const string& s, char delimiter)
{
  vector<string> tokens;
  string token;
  istringstream tokenStream(s);
  while (getline(tokenStream, token, delimiter))
    {
      tokens.push_back(token);
    }
  return tokens;
}

int main()
{
  string str;
  getline(cin, str);
  vector<string> words = split(str, ' ');
  string longest = words.at(0);
  for(auto const& word: words) {
    if (word.length() > longest.length()) {
      longest = word;
    }
  }
  cout << longest << endl << longest.length() << endl;
}
