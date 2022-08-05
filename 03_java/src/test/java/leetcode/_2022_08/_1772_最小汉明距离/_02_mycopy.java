package leetcode._2022_08._1772_最小汉明距离;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

public class _02_mycopy {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] source = {41, 37, 51, 100, 25, 33, 90, 49, 65, 87, 11, 18, 15, 18};


        int[] target = {41, 92, 69, 75, 29, 13, 53, 21, 17, 81, 33, 19, 33, 32};
        int[][] allowedSwaps = {{0, 11}, {5, 9}, {6, 9}, {5, 7}, {8, 13}, {4, 8}, {12, 7}, {8, 2}, {13, 5}, {0, 7}, {6, 4}, {8, 9}, {4, 12}, {6, 1}, {10, 0}, {10, 2}, {7, 3}, {11, 10}, {5, 2}, {11, 1}, {3, 0}, {8, 5}, {12, 6}, {2, 1}, {11, 2}, {4, 9}, {2, 9}, {10, 6}, {12, 10}, {4, 13}, {13, 2}, {11, 9}, {3, 6}, {0, 4}, {1, 10}, {5, 11}, {12, 1}, {10, 4}, {6, 2}, {10, 7}, {3, 13}, {4, 5}, {13, 10}, {4, 7}, {0, 12}, {9, 10}, {9, 3}, {0, 5}, {1, 9}, {5, 10}, {8, 0}, {12, 11}, {11, 4}, {7, 9}, {7, 2}, {13, 9}, {12, 3}, {8, 6}, {7, 6}, {8, 12}, {4, 3}, {7, 13}, {0, 13}, {2, 0}, {3, 8}, {8, 1}, {13, 6}, {1, 4}, {0, 9}, {2, 3}, {8, 7}, {4, 2}, {9, 12}};
        int val = solution.minimumHammingDistance(source, target, allowedSwaps);
        System.out.println(val);
    }

    static
    class Solution {
        public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
            // 获取长度
            int len = source.length;
            // 定义并查集
            UniFind uniFind = new UniFind(len);
            // 并查
            for (int[] allowedSwap : allowedSwaps) {
                uniFind.union(allowedSwap[0], allowedSwap[1]);
            }
            // 开始分组
            Map<Integer, Map<Integer, Integer>> srcMap = new HashMap<>(len << 1);
            Map<Integer, Map<Integer, Integer>> tarMap = new HashMap<>(len << 1);
            // 创建Map
            Function<Integer, Map<Integer, Integer>> func = it -> new HashMap<>();
            // 分组
            for (int i = 0; i < len; i++) {
                // 获取集合
                int fa = uniFind.find(i);
                incre(srcMap.computeIfAbsent(fa, func), source[i]);
                incre(tarMap.computeIfAbsent(fa, func), target[i]);
            }
            // 开始计算
            int ans = 0;
            for (Map.Entry<Integer, Map<Integer, Integer>> srcEntry : srcMap.entrySet()) {
                Map<Integer, Integer> srcCountMap = srcEntry.getValue();
                Map<Integer, Integer> tarCountMap = tarMap.get(srcEntry.getKey());

                // 遍历源 count
                for (Map.Entry<Integer, Integer> srcCountEntry : srcCountMap.entrySet()) {
                    // 值 , 数量
                    Integer key = srcCountEntry.getKey();
                    Integer srcCount = srcCountEntry.getValue();
                    Integer tarCount = tarCountMap.get(key);
                    if (tarCount == null) {
                        ans += srcCount; // 目标都没有
                    } else {
                        // 目标少了几个
                        if (srcCount > tarCount) {
                            ans += srcCount - tarCount;
                        }
                        tarCountMap.remove(key);
                    }
                }
            }
            return ans;
        }

        private void incre(Map<Integer, Integer> item, int source) {
            item.put(source, item.getOrDefault(source, 0) + 1);
        }

        // 并查集
        public class UniFind {
            // 定义并查集
            int[] root;

            public UniFind(int len) {
                this.root = IntStream.range(0, len).toArray();
            }

            // 找到父节点
            public int find(int num) {
                int fa = root[num];
                if (fa == num) {
                    return fa;
                }
                int rfa = find(fa);
                root[num] = rfa;
                return rfa;
            }

            // 两个是同伙
            public void union(int left, int right) {
                int lFa = find(left);
                int rFa = find(right);
                root[lFa] = rFa;
            }
        }
    }

}
