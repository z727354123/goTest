#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;
class Solution {
public:
    int maximumGain(string str, int x, int y) {
        // 获取长度
        char max, min;
        int maxV, minV;
        if (x > y) {
            max = 'a';
            min = 'b';
            maxV = x;
            minV = y;
        } else {
            max = 'b';
            min = 'a';
            maxV = y;
            minV = x;
        }
        return maximumGain(str, max, min, maxV, minV);
    }

    int maximumGain(string str, char maxChar, char minChar, int maxV, int minV) {
        // 开始遍历
        int len = str.length();
        int i = 0;
        // 定义结果
        int res = 0;
        while (i < len) {
            // 移除无用的
            // 定义和
            int maxC = 0, minC = 0;
            while (i < len && (str[i] == maxChar || str[i] == minChar)) {
                if (str[i] == maxChar) {
                    // 大开头
                    ++maxC;
                } else {
                    if (maxC > 0) {
                        res += maxV;
                        --maxC;
                    } else {
                        ++minC;
                    }
                }
                ++i;
            }
            res += min(maxC, minC) * minV;
            ++i;
        }
        return res;
    }
};

int main() {


    cout << "Hello" << endl;
    vector<int> vec;
    vec.push_back(1);
    vec.push_back(2);

    Solution solution;
    return 0;
}