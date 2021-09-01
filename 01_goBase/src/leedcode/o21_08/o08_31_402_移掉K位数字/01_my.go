package main

import (
	"fmt"
	"strings"
)

//import "strconv"

/**
给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。


示例 1 ：

输入：num = "1432219", k = 3
输出："1219"
解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
示例 2 ：

输入：num = "10200", k = 1
输出："200"
解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
示例 3 ：

输入：num = "10", k = 2
输出："0"
解释：从原数字移除所有的数字，剩余为空就是 0 。


提示：

1 <= k <= num.length <= 105
num 仅由若干位数字（0 - 9）组成
除了 0 本身之外，num 不含任何前导零


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-k-digits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
func main() {
	//resStr := "10001"
	fmt.Println(strings.TrimPrefix("0000123", "0"))
	fmt.Println(strings.TrimLeft("0000123", "10"))
	runes := []rune{'0', '1', '0', '1', '1', '0', '1', '1', '0', '1', '1', '0', '1', '1', '0', '1'}
	fmt.Println(string(runes))
	//fmt.Println(removeKdigits_my(resStr, 1))
}
func removeKdigits_ans(num string, k int) string {
	// 装载栈
	stack := []byte{}
	for _, itemRune := range num {
		// 有比他大的就移除
		itemByte := byte(itemRune)
		for k > 0 && len(stack) > 0 && stack[len(stack)-1] > itemByte {
			// 移除
			stack = stack[:len(stack)-1]
			k--
		}
		// 添加入栈
		stack = append(stack, itemByte)
	}
	stack = stack[:len(stack)-k]
	resStr := strings.TrimLeft(string(stack), "0")
	if resStr == "" {
		return "0"
	}
	return resStr
}
func removeKdigits_my(num string, k int) string {
	if k == 0 {
		return num
	}
	if len(num) == k {
		return "0"
	}
	// 解析集合
	resArr := [][]int{}
	// 解析
	for _, itemRune := range num {
		// 开头0忽略
		resLen := len(resArr)
		// 其余方式添加进去
		itemInt := int(itemRune)
		if resLen == 0 || resArr[resLen-1][0] != itemInt {
			resArr = append(resArr, []int{itemInt, 1})
		} else {
			resArr[resLen-1][1]++
		}
	}
	// 开始移除内容
	for len(resArr) > 0 && k > 0 {
		// 移除开头 0, 或者数量0
		if resArr[0][0] == int('0') || resArr[0][1] == 0 {
			resArr = resArr[1:]
			continue
		}
		// 遍历判断移除
		lastIdx := 0
		for i := 1; i < len(resArr); i++ {
			// 数量0, 过滤
			if resArr[i][1] == 0 {
				continue
			}
			// 判断哪个 idx 比较大
			if resArr[i][0] >= resArr[lastIdx][0] {
				lastIdx = i
			} else {
				break // 需要移除lastIdx
			}
		}
		for resArr[lastIdx][1] > 0 && k > 0 {
			resArr[lastIdx][1]--
			k--
		}
	}
	resStr := ""
	// 移除开头 0, 或者数量0
	for len(resArr) > 0 && (resArr[0][0] == int('0') || resArr[0][1] == 0) {
		resArr = resArr[1:]
		continue
	}
	for _, ints := range resArr {
		val := string(ints[0])
		for i := 0; i < ints[1]; i++ {
			resStr += val
		}
	}
	// 兜底
	if len(resStr) == 0 {
		return "0"
	}
	return resStr
}
