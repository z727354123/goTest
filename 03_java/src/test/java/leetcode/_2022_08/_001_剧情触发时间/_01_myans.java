package leetcode._2022_08._001_剧情触发时间;

import java.util.Arrays;
import java.util.Comparator;

public class _01_myans {
    public static void main(String[] args) {
        int[][] req1 = {
            {0, 4, 5}, {4, 8, 8}, {8, 6, 1}, {10, 10, 0}
        };
        int[][] req2 = {{12, 11, 16}, {20, 2, 6}, {9, 2, 6}, {10, 18, 3}, {8, 14, 9}};
        new Solution().getTriggerTime(req1, req2);
    }

    static
    class Solution {
        public int[] getTriggerTime(int[][] increase, int[][] requirements) {
            int reqLen = requirements.length;
            if (reqLen == 0) {
                return new int[0];
            }
            // 定义结果
            int[] res = new int[reqLen];
            Arrays.fill(res, -1);
            // 定义中间值
            int[] tmp = new int[3];

            // 0 初步判断, 并创建新的
            int[][][] newReq = new int[3][reqLen][];
            for (int i = 0; i < reqLen; i++) {
                int[] item = requirements[i];
                newReq[0][i] = newReq[1][i] = newReq[2][i] = new int[]{0, 0, 0, i};
                System.arraycopy(item, 0, newReq[0][i], 0, 3);
                if (item[0] == 0 && item[1] == 0 && item[2] == 0) {
                    res[i] = 0;
                }
            }
            // 排序
            Arrays.sort(newReq[0], Comparator.comparing(item -> item[0]));
            Arrays.sort(newReq[1], Comparator.comparing(item -> item[1]));
            Arrays.sort(newReq[2], Comparator.comparing(item -> item[2]));
            // idx
            int[] indexArr = {0, 0, 0};
            // 判断触发
            int[] trg = new int[reqLen];
            // 自增判断
            for (int inIdx = 0; inIdx < increase.length; inIdx++) {
                int[] inArr = increase[inIdx];
                tmp[0] += inArr[0];
                tmp[1] += inArr[1];
                tmp[2] += inArr[2];
                // 判断
                for (int out = 0; out < 3; out++) {
                    // 排过序的 ints
                    int[][] ints = newReq[out];
                    // 每个坐标的 idx
                    int endIdx;
                    while ((endIdx = indexArr[out])< reqLen && tmp[out] >= ints[endIdx][out]) {
                        // index 要超越了
                        indexArr[out] = endIdx + 1;
                        // 判断触发
                        int resIdx = ints[endIdx][3];
                        trg[resIdx] += 1;
                        if (trg[resIdx] >= 3 && res[resIdx] == -1) {
                            // 触发3次 加入
                            res[resIdx] = inIdx + 1;
                        }
                    }
                }
            }
            return res;
        }
    }

}
