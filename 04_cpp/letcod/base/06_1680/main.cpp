#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>

using namespace std;

class Solution {
public:
    int concatenatedBinary(int n) {
        long res = 0;
        // 定义num + count
        int num = 2;
        int count = 1;
        // MOD
        int MOD = 1000000007;
        for (int i = 1; i <= n; ++i) {
            while (i >= num) {
                num <<= 1;
                count++;
            }
            res <<= count;
            res += i;
            res %= MOD;
        }
        return res;
    }
};

int main() {


    cout << "Hello" << endl;
    vector<int> vec;
    vec.push_back(4);
    vec.push_back(2);
    vec.push_back(4);
    vec.push_back(5);
    vec.push_back(6);

    Solution solution;
    return 0;
}