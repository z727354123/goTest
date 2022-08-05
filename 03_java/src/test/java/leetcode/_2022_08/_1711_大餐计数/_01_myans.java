package leetcode._2022_08._1711_大餐计数;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class _01_myans {
    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.countPairs(new int[]{
            2160,1936,3,29,27,5,2503,1593,2,0,16,0,3860,28908,6,2,15,49,6246,1946,23,105,7996,196,0,2,55,457,5,3,924,7268,16,48,4,0,12,116,2628,1468
        });
    }

    static
        /**
         * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
         *
         * 你可以搭配 任意 两道餐品做一顿大餐。
         *
         * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
         *
         * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
         *
         *  
         *
         * 示例 1：
         *
         * 输入：deliciousness = [1,3,5,7,9]
         * 输出：4
         * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
         * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
         * 示例 2：
         *
         * 输入：deliciousness = [1,1,1,3,3,3,7]
         * 输出：15
         * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
         *  
         *
         * 提示：
         *
         * 1 <= deliciousness.length <= 105
         * 0 <= deliciousness[i] <= 220
         *
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode.cn/problems/count-good-meals
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
    class Solution {
        public int countPairs(int[] deliciousness) {
            int ans = 0;
            // 排列组合
            final int MOD = 1000000007;
            Map<Integer, AtomicInteger> countMap = new HashMap<>();
            for (int num : deliciousness) {
                countMap.computeIfAbsent(num, it -> new AtomicInteger(0)).incrementAndGet();
            }
            // 开始计算
            Set<Integer> doneSet = new HashSet<>();
            for (Map.Entry<Integer, AtomicInteger> outEntry : countMap.entrySet()) {
                Integer outNum = outEntry.getKey();
                if (doneSet.contains(outNum)) {
                    continue;
                }
                doneSet.add(outNum);
                int outCount = outEntry.getValue().get();
                // 超过1个
                if (outCount > 1) {
                    // 求和
                    if (isTowMi(outNum)) {
                        ans += (outCount * (outCount - 1)) >> 1;
                        ans %= MOD;
                    }
                }
                for (Map.Entry<Integer, AtomicInteger> inEntry : countMap.entrySet()) {
                    Integer inNum = inEntry.getKey();
                    if (doneSet.contains(inNum)) {
                        continue;
                    }
                    if (isTowMi(outNum + inNum)) {
                        int inCount = inEntry.getValue().get();
                        ans += outCount * inCount;
                        ans %= MOD;
                    }
                }
            }
            // 判断是否是 2 的幂
            return ans;
        }

        private boolean isTowMi(int i) {
            if (i == 0) {
                return false;
            }
            return (i & (i - 1)) == 0;
        }
        //
    }

}
