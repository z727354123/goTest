#include <stdio.h>
#include <uthash.h>
#include <math.h>


struct HashTable {
    int key, val;
    UT_hash_handle hh;
};

// // 查询
// int target = 0;
// HASH_FIND_INT(tab, &target, tmp);

// // 添加
// tmp = malloc(sizeof(struct HashTable));
// tmp->key = 0, tmp->val = 1;
// HASH_ADD_INT(tab, key, tmp);

// // 自增
// tmp->val++;
int countPairs(int *deliciousness, int deliciousnessSize) {
    // 定义最大值
    int maxVal = 0;
    for (int i = 0; i < deliciousnessSize; i++) {
        maxVal = fmax(maxVal, deliciousness[i]);
    }
    // 获取最大和
    maxVal = maxVal << 1;
    // 创建 hash
    struct HashTable *map = NULL, *item;
    // 定义取余
    int MOD = 1000000007;
    // 定义结果
    int res = 0;
    // 遍历数组
    for (int i = 0; i < deliciousnessSize; i++) {
        // 单独一个
        int val = deliciousness[i];
        // 遍历
        for (int sum = 1; sum <= maxVal; sum <<= 1) {
            // 获取 map 中数字, 默认0
            int tar = sum - val;
            HASH_FIND_INT(map, &tar, item);
            int count = item == NULL ? 0 : item->val;
            res = (res + count) % MOD;
        }
        // 添加到 map
        HASH_FIND_INT(map, &val, item);
        if (item == NULL) {
            item = malloc(sizeof(struct HashTable));
            item->key = val, item->val = 1;
            HASH_ADD_INT(map, key, item);
        } else {
            item->val++;
        }
    }
    return res;
}

// int main() {
//     printf("hello World \n");
//
//     int *arr = malloc(2);
//     arr[0] = 1;
//     arr[1] = 2;
//     int res = countPairs(arr, 2);
//
//     printf("%d", res);
//
//     return 0;
// }