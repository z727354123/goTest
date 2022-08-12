#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>

using namespace std;


struct comp {
    bool operator()(pair<int, int> left, pair<int, int> right) {
        return left.first < right.first;
    }
};

class Solution {
public:
    int maxResult(vector<int> &nums, int k) {
        // 最小堆 + 动态规划
        priority_queue<pair<int, int>, vector<pair<int, int>>, comp> queue;
        // 获取长度
        // 定义结果
        int res = nums[0];
        // 初次添加
        queue.emplace(res, 0);
        int size = nums.size();
        for (int i = 1; i < size; ++i) {
            // 遍历 移除超过步数的
            while (i - queue.top().second > k) {
                queue.pop();
            }
            // 算进结果, 添加
            res = queue.top().first + nums[i];
            queue.emplace(res, i);
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