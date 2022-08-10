package leetcode._2022_08._1717_删除子字符串的最大得分;

public class _01_my {
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
        public int maximumGain(String str, int x, int y) {
            // 动态规划试试
            int len = str.length();
            if (len <= 1) {
                return 0;
            }
            boolean[] isA = new boolean[len];
            boolean[] isB = new boolean[len];
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                isA[i] = chars[i] == 'a';
                isB[i] = chars[i] == 'b';
            }
            // 开始执行吧...
            return maximumGain(isA, isB, x, y, len);
        }

        private int maximumGain(boolean[] isA, boolean[] isB, int ab, int ba, int len) {
            if (len <= 1) {
                return 0;
            }
            if (len == 2) {
                if (isA[0] && isB[1]) {
                    return ab;
                } else if (isB[0] && isA[1]) {
                    return ba;
                }
                return 0;
            }
            int max = 0;
            int newLen = len - 2;
            boolean[] newIsA = new boolean[newLen];
            boolean[] newIsB = new boolean[newLen];
            for (int i = 1; i < len; i++) {
                if (isA[i-1] && isB[i]) {
                    // 拷贝数组
                    System.arraycopy(isA, 0, newIsA, 0, i - 1);
                    System.arraycopy(isA, i + 1, newIsA, i - 1, len - i - 1);
                    System.arraycopy(isB, 0, newIsB, 0, i - 1);
                    System.arraycopy(isB, i + 1, newIsB, i - 1, len - i - 1);
                    max = Math.max(max, maximumGain(newIsA, newIsB, ab, ba, newLen) + ab);
                } else if (isB[i-1] && isA[i]) {
                    System.arraycopy(isA, 0, newIsA, 0, i - 1);
                    System.arraycopy(isA, i + 1, newIsA, i - 1, len - i - 1);
                    System.arraycopy(isB, 0, newIsB, 0, i - 1);
                    System.arraycopy(isB, i + 1, newIsB, i - 1, len - i - 1);
                    max = Math.max(max, maximumGain(newIsA, newIsB, ab, ba, newLen) + ba);
                }
            }
            return max;
        }
    }
}
