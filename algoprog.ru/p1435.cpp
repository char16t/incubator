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
    string ipAddress;
    cin >> ipAddress;
    
    vector<string> parts = split(ipAddress, '.');
    if (parts.size() != 4) {
        cout << "0" << endl;
        return 0;
    }
    for (unsigned i=0; i < parts.size(); i++) {
        if (parts[i].compare("") == 0) {
            cout << "0" << endl;
            return 0;
        }
        int part = stoi(parts[i]);
        if (part < 0 || part > 255) {
            cout << "0" << endl;
            return 0;
        }
    }
    cout << "1" << endl;
}
