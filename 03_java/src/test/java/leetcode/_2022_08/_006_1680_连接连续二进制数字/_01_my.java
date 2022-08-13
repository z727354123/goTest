package leetcode._2022_08._006_1680_连接连续二进制数字;

import java.util.Arrays;

public class _01_my {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }

    static

        /**
         * 给你一个整数 n ，请你将 1 到 n 的二进制表示连接起来，并返回连接结果对应的 十进制 数字对 109 + 7 取余的结果。
         *
         *  
         *
         * 示例 1：
         *
         * 输入：n = 1
         * 输出：1
         * 解释：二进制的 "1" 对应着十进制的 1 。
         * 示例 2：
         *
         * 输入：n = 3
         * 输出：27
         * 解释：二进制下，1，2 和 3 分别对应 "1" ，"10" 和 "11" 。
         * 将它们依次连接，我们得到 "11011" ，对应着十进制的 27 。
         * 示例 3：
         *
         * 输入：n = 12
         * 输出：505379714
         * 解释：连接结果为 "1101110010111011110001001101010111100" 。
         * 对应的十进制数字为 118505380540 。
         * 对 109 + 7 取余后，结果为 505379714 。
         *  
         *
         * 提示：
         *
         * 1 <= n <= 105
         *
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode.cn/problems/concatenation-of-consecutive-binary-numbers
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
    class Solution {
        class BitWay {
            int num = 2;
            int count = 1;

            public int getWay(int val) {
                while (val >= num) {
                    num <<= 1;
                    count++;
                }
                return count;
            }
        }

        public int concatenatedBinary(int n) {
            // 用于记录位数
            BitWay bitWay = new BitWay();
            long res = 0;
            int MOD = 1000_000_007;
            for (int i = 1; i <= n; i++) {
                // 获取位数
                int way = bitWay.getWay(i);
                res <<= way;
                res += i;
                res %= MOD;
            }
            return (int) res;
        }

    }
}
