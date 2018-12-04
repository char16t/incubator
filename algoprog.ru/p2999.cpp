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
    int n;
    cin >> n;
    int a[n][10];
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < 10; j++) {
            a[i][j] = i == 0 ? 1 : 0;
        }
    }

    for(int i = 0; i < n - 1; i++) {
        a[i + 1][0] = a[i][4] + a[i][6];
        a[i + 1][1] = a[i][6] + a[i][8];
        a[i + 1][2] = a[i][7] + a[i][9];
        a[i + 1][3] = a[i][4] + a[i][8];
        a[i + 1][4] = a[i][3] + a[i][9] + a[i][0];
        a[i + 1][6] = a[i][1] + a[i][7] + a[i][0];
        a[i + 1][7] = a[i][2] + a[i][6];
        a[i + 1][8] = a[i][1] + a[i][3];
        a[i + 1][9] = a[i][4] + a[i][2];
    }

    int sum = 0;
    for(int i = 1; i < 10; i++) {
        if (i != 8) {
            sum += a[n-1][i];
        }
    }
    cout << sum << endl;
}
