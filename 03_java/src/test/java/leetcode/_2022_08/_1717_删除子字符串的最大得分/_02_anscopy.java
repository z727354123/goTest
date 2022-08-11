package leetcode._2022_08._1717_删除子字符串的最大得分;

public class _02_anscopy {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }

    static
        /**
         * 给你一个字符串 s 和两个整数 x 和 y 。你可以执行下面两种操作任意次。
         *
         * 删除子字符串 "ab" 并得到 x 分。
         * 比方说，从 "cabxbae" 删除 ab ，得到 "cxbae" 。
         * 删除子字符串"ba" 并得到 y 分。
         * 比方说，从 "cabxbae" 删除 ba ，得到 "cabxe" 。
         * 请返回对 s 字符串执行上面操作若干次能得到的最大得分。
         *
         *  
         *
         * 示例 1：
         *
         * 输入：s = "cdbcbbaaabab", x = 4, y = 5
         * 输出：19
         * 解释：
         * - 删除 "cdbcbbaaabab" 中加粗的 "ba" ，得到 s = "cdbcbbaaab" ，加 5 分。
         * - 删除 "cdbcbbaaab" 中加粗的 "ab" ，得到 s = "cdbcbbaa" ，加 4 分。
         * - 删除 "cdbcbbaa" 中加粗的 "ba" ，得到 s = "cdbcba" ，加 5 分。
         * - 删除 "cdbcba" 中加粗的 "ba" ，得到 s = "cdbc" ，加 5 分。
         * 总得分为 5 + 4 + 5 + 5 = 19 。
         * 示例 2：
         *
         * 输入：s = "aabbaaxybbaabb", x = 5, y = 4
         * 输出：20
         *  
         *
         * 提示：
         *
         * 1 <= s.length <= 105
         * 1 <= x, y <= 104
         * s 只包含小写英文字母。
         *
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode.cn/problems/maximum-score-from-removing-substrings
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
    class Solution {
        public int maximumGain(String str, int ab, int ba) {
            // 答案
            int len = str.length();
            if (len <= 1) {
                return 0;
            }
            char max, min;
            int maxVal, minVal;
            if (ab > ba) {
                max = 'a';
                min = 'b';
                maxVal = ab;
                minVal = ba;
            } else {
                max = 'b';
                min = 'a';
                maxVal = ba;
                minVal = ab;
            }
            return maximumGain(str, max, min, maxVal, minVal);
        }

        private int maximumGain(String str, char max, char min, int maxVal, int minVal) {
            int res = 0;
            char[] chars = str.toCharArray();
            int len = str.length();
            int i = 0;
            while (i < len) {
                // 计算数量
                int maxC = 0, minC = 0;
                while (i < len && (chars[i] == max || chars[i] == min)) {
                    if (chars[i] == max) {
                        // 大的加一
                        maxC += 1;
                    } else {
                        if (maxC > 0) {
                            // 大的消除
                            res += maxVal;
                            maxC -= 1;
                        } else {
                            // 小的计数
                            minC += 1;
                        }
                    }
                    i++;
                }
                // 最终计算
                res += Math.min(maxC, minC) * minVal;
                i++;
            }
            return res;
        }
    }
}
