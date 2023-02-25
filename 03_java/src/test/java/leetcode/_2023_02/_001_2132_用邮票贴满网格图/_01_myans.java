package leetcode._2023_02._001_2132_用邮票贴满网格图;


/**
 * 给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
 * <p>
 * 给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
 * <p>
 * 覆盖所有 空 格子。
 * 不覆盖任何 被占据 的格子。
 * 我们可以放入任意数目的邮票。
 * 邮票可以相互有 重叠 部分。
 * 邮票不允许 旋转 。
 * 邮票必须完全在矩阵 内 。
 * 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
 * 输出：true
 * 解释：我们放入两个有重叠部分的邮票（图中标号为 1 和 2），它们能覆盖所有与空格子。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
 * 输出：false
 * 解释：没办法放入邮票覆盖所有的空格子，且邮票不超出网格图以外。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/stamping-the-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _01_myans {
    public static void main(String[] args) {
        Solution sol = new Solution();

    }

    static
        /**
         *
         */
    class Solution {
        public boolean possibleToStamp(int[][] grid, int sh, int sw) {
            // 特殊情况返回
            if (sh == 1 && sw == 1) {
                return true;
            }
            // 计算二维前缀和
            int rowCount = grid.length;
            int colCount = grid[0].length;
            int[][] sum = new int[rowCount + 1][colCount + 1];
            // 赋值到sum
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    sum[row + 1][col + 1] = grid[row][col];
                }
            }
            // 二维前缀和
            calcArr(sum);
            // 开始使用差分数组
            int[][] diff = new int[rowCount + 2][colCount + 2];
            for (int row = sh; row < rowCount + 1; row++) {
                for (int col = sw; col < colCount + 1; col++) {
                    if (grid[row - 1][col - 1] != 0) {
                        // 无需判断
                        continue;
                    }
                    // 判断是否能塞进去
                    // (row,col) - (row-sh, col) - (row,col-sw) + (row-sh, col-sw)
                    int tRow = row - sh;
                    int tCol = col - sw;
                    int val = sum[row][col] - sum[tRow][col] - sum[row][tCol] + sum[tRow][tCol];
                    if (val == 0) {
                        // 放的下
                        // 问题在这
                        diff[tRow + 1][tCol + 1] += 1;
                        diff[row+ 1][col+ 1] += 1;
                        diff[tRow+ 1][col+ 1] -= 1;
                        diff[row+ 1][tCol+ 1] -= 1;
                    }
                }
            }
            // 求和
            calcArr(diff);

            // 判断
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    if (grid[row][col] != 0) {
                        continue;
                    }
                    if (diff[row+ 1][col+ 1] == 0) {
                        return false;
                    }
                }
            }
            return true;
        }

        private void calcArr(int[][] sum) {
            // 二维前缀和
            for (int row = 1; row < sum.length; row++) {
                for (int col = 1; col < sum[0].length; col++) {
                    sum[row][col] = sum[row][col] + sum[row - 1][col] + sum[row][col - 1] - sum[row - 1][col - 1];
                }
            }
        }
    }

}
