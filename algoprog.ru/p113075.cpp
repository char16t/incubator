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
    int n, k, s;
    cin >> n >> k;
    cin >> s;
    int a[s+1] = {0};
    for (int i = 1; i < s+1; i += 1) {
        cin >> a[i];
    }
    int acc = 0;
    int count = 0;
    for (int i = 0; i < s /* + 1 - 1 */; i += 1) {
        if (a[i + 1] - a[i] > k) {
            cout << -1 << endl;
            return 0;
        }
        acc += a[i + 1] - a[i];
        if (acc >= k) {
            acc = 0;
            count += 1;
        }
    }
    cout << count << endl;
}
