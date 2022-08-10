#include <stdio.h>
#include <uthash.h>


struct HashTable {
    int key, val;
    UT_hash_handle hh;
};

int countPairs(int *deliciousness, int deliciousnessSize) {
    // 定义最大值
    int maxVal = 0;
    for (int i = 0; i < deliciousnessSize; i++) {
        maxVal = fmax(maxVal, deliciousnessSize[i]);
    }
    return maxVal;
}

int main() {
    printf("hello World");

    int *arr = {1, 2};
    countPairs(arr, 2);

    return 0;
}