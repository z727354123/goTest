#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>

using namespace std;

class Solution {
public:
    int minMoves(vector<int> &nums, int limit) {
        // 定义数组
        int len = limit * 2 + 2;
        int *resArr = new int[len]();
        // 开始定义区间
        int size = nums.size();
        for (int i = 0; i < size >> 1; ++i) {

            // 获取最大值, 最小值
            int max = nums[i];
            int min = nums[size - i - 1];
            if (max < min) {
                swap(max, min);
            }
            // 和
            int sum = max + min;
            // -1 区间 [min + 1, max + limit]
            resArr[min + 1] += -1;
            resArr[max + limit + 1] -= -1;
            // -1 区间 [sum, sum]
            resArr[sum] += -1;
            resArr[sum + 1] -= -1;
        }

        int res = size;
        int tmp = res;
        // 计算结果
        for (int i = 2; i < len - 1; ++i) {
            tmp += resArr[i];
            res = min(res, tmp);
        }
        return res;
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
    cout << solution.minMoves(vec, 4) << endl;

    return 0;
}