package leetcode._2022_08._007_1674_使数组互补的最少操作次数;

public class _01_my {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }

    static
        /**
         * 1674. 使数组互补的最少操作次数
         * 给你一个长度为 偶数 n 的整数数组 nums 和一个整数 limit 。每一次操作，你可以将 nums 中的任何整数替换为 1 到 limit 之间的另一个整数。
         *
         * 如果对于所有下标 i（下标从 0 开始），nums[i] + nums[n - 1 - i] 都等于同一个数，则数组 nums 是 互补的 。例如，数组 [1,2,3,4] 是互补的，因为对于所有下标 i ，nums[i] + nums[n - 1 - i] = 5 。
         *
         * 返回使数组 互补 的 最少 操作次数。
         *
         *
         *
         * 示例 1：
         *
         * 输入：nums = [1,2,4,3], limit = 4
         * 输出：1
         * 解释：经过 1 次操作，你可以将数组 nums 变成 [1,2,2,3]（加粗元素是变更的数字）：
         * nums[0] + nums[3] = 1 + 3 = 4.
         * nums[1] + nums[2] = 2 + 2 = 4.
         * nums[2] + nums[1] = 2 + 2 = 4.
         * nums[3] + nums[0] = 3 + 1 = 4.
         * 对于每个 i ，nums[i] + nums[n-1-i] = 4 ，所以 nums 是互补的。
         * 示例 2：
         *
         * 输入：nums = [1,2,2,1], limit = 2
         * 输出：2
         * 解释：经过 2 次操作，你可以将数组 nums 变成 [2,2,2,2] 。你不能将任何数字变更为 3 ，因为 3 > limit 。
         * 示例 3：
         *
         * 输入：nums = [1,2,1,2], limit = 2
         * 输出：0
         * 解释：nums 已经是互补的。
         *
         *
         * 提示：
         *
         * n == nums.length
         * 2 <= n <= 105
         * 1 <= nums[i] <= limit <= 105
         * n 是偶数。π
         */
    class Solution {
        public int minMoves(int[] nums, int limit) {
            // 两数之和 = [2, 2*limit]
            // 定义差分数组, 1跟作为All , 2*limit + 1 作为末尾, 防止越界
            int newLen = (limit << 1) + 2;
            int[] resArr = new int[newLen];
            // 数组长度
            int len = nums.length;
            for (int i = 0; i < (len >> 1); i++) {
                int max = nums[i];
                int min = nums[len - i - 1];
                // 最大值, 最小值
                if (max < min) {
                    int tmp = max;
                    max = min;
                    min = tmp;
                }
                // 和
                int sum = max + min;
                // [min + 1, max + limit]  范围 -1
                resArr[min + 1] -= 1;
                resArr[max + limit + 1] += 1;
                // [sum, sum] 范围 -1
                resArr[sum] -= 1;
                resArr[sum + 1] += 1;
            }
            // 全部换次数
            int tmp = len;
            int res = len;
            for (int i = 2; i < (newLen - 1); i++) {
                tmp += resArr[i];
                res = Math.min(res, tmp);
            }
            return res;
        }
    }
}
