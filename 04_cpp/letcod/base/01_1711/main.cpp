#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;
// 定义map
// unordered_map<int, int> mp;

// map 判断是否存在
// mp.count(sum - val) ?

// map 获取
// mp[sum - val]

// map 自增
// mp[val]++;


class Solution {
public:
    int countPairs(vector<int> &deliciousness) {
        // 获取最大值
        int maxSum = 0;
        for (const auto &item: deliciousness) {
            maxSum = max(item, maxSum);
        }
        maxSum <<= 1;
        // 定义map
        unordered_map<int, int> map;
        // 定义结果
        int res = 0;
        // 遍历
        for (const auto &item: deliciousness) {
            // 遍历sum
            for (int sum= 1; sum <= maxSum; sum<<=1) {
                // 获取元素
                int count = map.count(sum - item) ? map[sum - item] : 0;
                // int count = map[sum - item];
                res = (res + count) % 1000000007;
            }
            // 添加进map
            map[item]++;
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
    solution.countPairs(vec);
    return 0;
}