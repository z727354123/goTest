package main

import (
	"fmt"
)

/**
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。



示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4


提示：

1 <= k <= nums.length <= 104
-104 <= nums[i] <= 104


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
func main() {
	if true {
		arr := []int{3, 2, 1, 5, 6, 4}
		k := 2
		realRes := 5
		res := findKthLargest(arr, k)
		if res != realRes {
			panic(fmt.Sprintf("[%#v]!=[%#v]", realRes, res))
		}
	}
	if true {
		arr := []int{3, 2, 3, 1, 2, 4, 5, 5, 6}
		k := 4
		realRes := 4
		res := findKthLargest(arr, k)
		if res != realRes {
			panic(fmt.Sprintf("[%#v]!=[%#v]", realRes, res))
		}
	}
	if true {
		arr := []int{-1, 2, 0}
		k := 2
		realRes := 0
		res := findKthLargest(arr, k)
		if res != realRes {
			panic(fmt.Sprintf("[%#v]!=[%#v]", realRes, res))
		}
	}

}
func findKthLargest(nums []int, k int) int {
	// 用于排序
	arrTmp := make([]int, k)
	for idx, val := range nums {
		if idx < k {
			// 自动插入
			addEle(arrTmp, val, idx)
			continue
		}
		addEle(arrTmp, val, -1)
	}

	return arrTmp[k-1]
}

func addEle(tmp []int, val int, idx int) {
	if idx >= 0 { // 需要添加
		insertIdx := getIndex(tmp[:idx], val)
		insertArr(tmp[:idx+1], insertIdx, val)
		return
	}
	// 二分法 插入
	insertIdx := getIndex(tmp, val)
	if insertIdx >= len(tmp) {
		return
	}
	// 需要插入
	insertArr(tmp, insertIdx, val)
}

func insertArr(tmp []int, idx int, val int) {
	length := len(tmp)
	// 先位移
	for i := length - 1; i > idx; i-- {
		tmp[i] = tmp[i-1]
	}
	// 再赋值
	tmp[idx] = val
}

// 二分法
func getIndex(tmp []int, val int) int {

	lastIdx := len(tmp)
	startIdx := 0
	res := lastIdx
	midIdx := (lastIdx + startIdx) >> 1
	for startIdx <= lastIdx && midIdx < len(tmp) {
		if tmp[midIdx] == val {
			return midIdx
		} else if tmp[midIdx] > val {
			startIdx = midIdx + 1
			res = startIdx
		} else {
			lastIdx = midIdx - 1
			res = lastIdx
		}
		midIdx = (lastIdx + startIdx) >> 1
	}
	if res < 0 {
		return 0
	}
	return res
}
