#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>

using namespace std;

class Solution {
public:
    string maximumBinaryString(string binary) {
        for (int pos = -1, i = 0; i < binary.size(); ++i) {
            if (binary[i] == '0') {
                // 0 需要记录
                if (pos == -1 ) {
                    pos = i;
                } else {
                    // 开始移位
                    binary[pos++] = '1';
                    binary[i] = '1';
                    binary[pos] = '0';
                }
            }
        }
        return binary;
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