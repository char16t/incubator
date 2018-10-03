#include <iostream>
#include <string>

using namespace std;

int main()
{
    int a,b,c;
    cin >> a >> b >> c;
    
    if (a == 0 && c >= 0) {
        cout << (c*c == b ? "MANY SOLUTIONS" : "NO SOLUTION") << endl;
    }
    else if (a == 0 && c < 0) {
        cout << "NO SOLUTION" << endl;
    }    
    else if (((c*c - b) % a == 0) && (c >= 0)) {
        cout << (c*c - b) / a <<endl;
    } else {
        cout << "NO SOLUTION" << endl;
    }
}
