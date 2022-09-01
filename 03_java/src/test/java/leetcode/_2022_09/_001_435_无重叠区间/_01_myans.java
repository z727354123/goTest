package leetcode._2022_09._001_435_无重叠区间;

import java.util.Arrays;
import java.util.Comparator;

public class _01_myans {
    public static void main(String[] args) {
        int[][] req1 = {
            {0, 4, 5}, {4, 8, 8}, {8, 6, 1}, {10, 10, 0}
        };
        int[][] req2 = {{12, 11, 16}, {20, 2, 6}, {9, 2, 6}, {10, 18, 3}, {8, 14, 9}};
        Solution sol = new Solution();

    }

    static
        /**
         *435. 无重叠区间
         * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
         *
         *
         *
         * 示例 1:
         *
         * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
         * 输出: 1
         * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
         * 示例 2:
         *
         * 输入: intervals = [ [1,2], [1,2], [1,2] ]
         * 输出: 2
         * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
         * 示例 3:
         *
         * 输入: intervals = [ [1,2], [2,3] ]
         * 输出: 0
         * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
         *
         *
         * 提示:
         *
         * 1 <= intervals.length <= 105
         * intervals[i].length == 2
         * -5 * 104 <= starti < endi <= 5 * 104
         * 通过次数170,118提交次数333,000
         */
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            int len = intervals.length;
            // 长度
            if (len <= 1) {
                return 0;
            }
            int ans = 1;
            // 开始排序
            Arrays.sort(intervals, Comparator.comparingInt(left -> left[1]));
            // 记录第一个 Right
            int right = intervals[0][1];
            for (int i = 1; i < len; i++) {
                if (intervals[i][0] >= right) {
                    ++ans;
                    right = intervals[i][1];
                }
            }
            return len - ans;
        }
    }

}
