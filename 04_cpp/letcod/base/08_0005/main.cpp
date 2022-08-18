#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>

using namespace std;

class Solution {
public:
    string longestPalindrome(string s) {
        // 获取长度
        int allLen = s.length();
        // 定义数组
        bool **array; // array[M][N]
        array = new bool*[allLen];
        for(int i = 0; i < allLen; i++) {
            array[i] = new bool[allLen]();
        }
        // 定义idx
        int startIdx = 0;
        int resLen = 0; //长度
        // 定义 跨度
        for (int len = 0; len < allLen; ++len) {
            // 范围
            int end = allLen - len;
            for (int i = 0; i < end; ++i) {
                int idx = i + len;
                if (len == 0) {
                    array[i][i] = true;
                } else if (len <= 2) {
                    array[i][idx] = s[i] == s[idx];
                } else {
                    array[i][idx] = array[i + 1][idx - 1] && s[i] == s[idx];
                }
                if (array[i][idx] && resLen < len) {
                    resLen = len;
                    startIdx = i;
                }
            }
        }
        // 开始遍历
        return s.substr(startIdx, resLen + 1);
    }
};

int main() {


    cout << "Hello" << endl;
    vector<int> vec;
    vec.push_back(1);
    vec.push_back(2);

    vec.push_back(4);
    vec.push_back(3);

    Solution solution;
    string str = "aacabdkacaa";
    cout << str.substr(1, 2) << endl;
    cout << str.substr(2, 2) << endl;
    solution.longestPalindrome(str);

    return 0;
}