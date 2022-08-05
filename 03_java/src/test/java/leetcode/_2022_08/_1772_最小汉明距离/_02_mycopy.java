package leetcode._2022_08._1772_最小汉明距离;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

public class _02_mycopy {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] source = {1, 2, 3, 4};
        int[] target = {2, 1, 4, 5};
        int[][] allowedSwaps = {{0, 1}, {2, 3}};
        solution.minimumHammingDistance(source, target, allowedSwaps);
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
                    }
                }
            }
            return ans;
        }

        private void incre(Map<Integer, Integer> item, int source) {
            item.put(source, item.getOrDefault(source, 1));
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
