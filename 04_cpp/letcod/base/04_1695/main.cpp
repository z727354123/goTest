#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>

using namespace std;


class Solution {
public:
    int maximumUniqueSubarray(vector<int>& nums) {
        // 定义 map
        unordered_map<int, int> idxMap;
        // 定义结果
        int res = nums[0];
        int tmp = nums[0];
        int left = 0;
        int len = nums.size();
        // 添加进 map
        idxMap[nums[0]] = 0;
        for (int right = 1; right < len; ++right) {
            int val = nums[right];
            // 添加进 tmp
            tmp += val;
            if (idxMap.count(val) == 0) {
                // 需要判断 max
                idxMap[val] = right;
                res = max(res, tmp);
                continue;
            }
            // 需要移除
            int idx = idxMap[val];
            while (left <= idx) {
                int remove = nums[left++];
                idxMap.erase(remove);
                tmp -= remove;
            }
            idxMap[val] = right;
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
    int val = solution.maximumUniqueSubarray(vec);
    cout << val << endl;
    return 0;
}