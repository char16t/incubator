#include <iostream>

using namespace std;

int main()
{
    int n, a, b, c;
    cin >> n;
    cin >> a >> b;
    for (int i = n - 1; i > 0; i -= 1) {
        c = b - a;
        b = a;
        a = c;
    }
    cout << a << " " << b << endl;
}
