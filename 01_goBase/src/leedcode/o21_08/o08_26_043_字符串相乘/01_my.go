package main

import (
	"fmt"
	"strconv"
)

/**
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:

输入: num1 = "2", num2 = "3"
输出: "6"
示例 2:

输入: num1 = "123", num2 = "456"
输出: "56088"
说明：

num1 和 num2 的长度小于110。
num1 和 num2 只包含数字 0-9。
num1 和 num2 均不以零开头，除非是数字 0 本身。
不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/multiply-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
func main() {
	fmt.Println(multiply("123", "456"))
}
func multiply(left string, right string) string {
	if left == "0" || right == "0" {
		return "0"
	}
	// 定义长度
	leftLen, rightLen := len(left), len(right)
	// 定义结果 数组
	res := make([]int, leftLen+rightLen)
	// 开始计算结果
	for lIdx, lRune := range left {
		lVal := lRune - '0'
		for rIdx, rRune := range right {
			rVal := rRune - '0'
			itemVal := int(lVal) * int(rVal)
			res[lIdx+rIdx+1] += itemVal
		}
	}
	// 合并结果, 从下往上
	for i := len(res) - 1; i > 0; i-- {
		res[i-1] += res[i] / 10
		res[i] %= 10
	}
	idx := 0 // 从0开始
	if res[0] == 0 {
		idx = 1
	}
	cur := ""
	for ; idx < len(res); idx++ {
		cur += strconv.Itoa(res[idx])
	}
	return cur
}
