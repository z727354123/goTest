package leetcode._2022_08._1772_最小汉明距离;

import java.util.HashMap;
import java.util.Map;

public class _01_myans {
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
            int n = source.length;

            // 并查集记录联通关系
            UnionFind unionFind = new UnionFind(n);
            for (int[] allow : allowedSwaps) {
                unionFind.union(allow[0], allow[1]);
            }

            // 联通分支的根-->source中属于本联通分支的元素集合
            Map<Integer, CountedMap<Integer>> sMap = new HashMap<>();
            // 联通分支的根-->target中属于本联通分支的元素集合
            Map<Integer, CountedMap<Integer>> tMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int root = unionFind.find(i);
                sMap.computeIfAbsent(root, k->new CountedMap<>())
                    .increment(source[i]);
                tMap.computeIfAbsent(root, k->new CountedMap<>())
                    .increment(target[i]);
            }

            int ans = 0;
            // 比较不同，累加答案
            for (int i = 0; i < n; i++) {
                // 非连通分支的根元素，跳过
                if(unionFind.find(i) != i)continue;

                CountedMap<Integer> s = sMap.get(i);
                CountedMap<Integer> t = tMap.get(i);

                // 比较两个集合s和t的不同元素个数
                for (Map.Entry<Integer, Integer> entry : s.entrySet()) {
                    Integer key = entry.getKey();
                    Integer count = entry.getValue();
                    Integer countT = t.get(key);

                    if(countT == null) {
                        // s中存在，而t中不存在
                        ans += count;
                    } else {
                        // s中比t中多出来的个数，也是答案
                        if(count > countT) {
                            ans += count - countT;
                        }
                        t.remove(key);
                    }
                }
            }
            return ans;
        }

        // UnionFind.java
        public class UnionFind {
            int[] roots;

            public UnionFind(int size) {
                this.roots = new int[size];
                for (int i = 0; i < size; i++) {
                    roots[i] = i;
                }
            }

            public boolean isConnected(int x, int y) {
                return find(x) == find(y);
            }

            public int find(int index) {
                int i = index;
                while (i != roots[i]) {
                    i = roots[i];
                }
                return roots[index] = i;
            }

            public void union(int x, int y) {
                int xf = find(x);
                int yf = find(y);
                roots[xf] = yf;
            }
        }
        // CountedMap.java
        public class CountedMap<T> extends HashMap<T, Integer> {
            public void increment(T key) {
                put(key, getOrDefault(key, 0)+1);
            }
        }
    }

}
