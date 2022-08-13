package leetcode._2022_08._005_1702_修改后的最大二进制字符串;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _01_my {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }

    static

        /**
         *1702. 修改后的最大二进制字符串
         * 给你一个二进制字符串 binary ，它仅有 0 或者 1 组成。你可以使用下面的操作任意次对它进行修改：
         *
         * 操作 1 ：如果二进制串包含子字符串 "00" ，你可以用 "10" 将其替换。
         * 比方说， "00010" -> "10010"
         * 操作 2 ：如果二进制串包含子字符串 "10" ，你可以用 "01" 将其替换。
         * 比方说， "00010" -> "00001"
         * 请你返回执行上述操作任意次以后能得到的 最大二进制字符串 。如果二进制字符串 x 对应的十进制数字大于二进制字符串 y 对应的十进制数字，那么我们称二进制字符串 x 大于二进制字符串 y 。
         *
         *
         *
         * 示例 1：
         *
         * 输入：binary = "000110"
         * 输出："111011"
         * 解释：一个可行的转换为：
         * "000110" -> "000101"
         * "000101" -> "100101"
         * "100101" -> "110101"
         * "110101" -> "110011"
         * "110011" -> "111011"
         * 示例 2：
         *
         * 输入：binary = "01"
         * 输出："01"
         * 解释："01" 没办法进行任何转换。
         *
         *
         * 提示：
         *
         * 1 <= binary.length <= 105
         * binary 仅包含 '0' 和 '1' 。
         */
    class Solution {
        public String maximumBinaryString(String binary) {
            char[] chars = binary.toCharArray();
            // 0个数
            int zeroCount = 0;
            // 第一个0位置
            int zeroIdx = -1;
            // 第一个0后, 1 的数量
            int lastOneCount = 0;
            for (int i = 0; i < chars.length; i++) {
                char val = chars[i];
                if (val == '0') {
                    zeroCount++;
                    if (zeroIdx == -1) {
                        zeroIdx = i;
                    }
                    continue;
                } else if (zeroIdx != -1) {
                    lastOneCount++;
                }
            }
            // 只有一个 0 , 没必要变化
            if (zeroCount <= 1) {
                return binary;
            }
            // 开始组装
            char[] newChar = new char[chars.length];
            // 中间0的位置
            zeroIdx = chars.length - lastOneCount - 1;
            Arrays.fill(newChar, '1');
            newChar[zeroIdx] = '0';
            return new String(newChar);
        }
    }
}
