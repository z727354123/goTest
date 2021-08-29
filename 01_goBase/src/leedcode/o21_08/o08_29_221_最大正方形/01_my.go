package main

import "fmt"

/**
在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。



示例 1：


输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
输出：4
示例 2：


输入：matrix = [["0","1"],["1","0"]]
输出：1
示例 3：

输入：matrix = [["0"]]
输出：0


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] 为 '0' 或 '1'

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximal-square
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
func main() {
	arr := [][]byte{
		{1, 0, 1, 0, 0},
		{1, 0, 1, 1, 1},
		{1, 1, 1, 1, 1},
		{1, 0, 0, 1, 0},
	}
	val := maximalSquare(arr)
	fmt.Println(val)
	fmt.Println('0')
}

func minVal(left int, right int) int {
	if left < right {
		return left
	} else {
		return right
	}
}
func maximalSquare(matrix [][]byte) int {
	rowLen := len(matrix)
	colLen := len(matrix[0])
	// 定义结果装载
	tmpArr := make([][]int, rowLen)
	// 基于默认值
	for row := 0; row < rowLen; row++ {
		tmpArr[row] = make([]int, colLen)
	}
	// 赋值 第一列
	// 直接在原数据改
	var resMax int = 0
	for row := 0; row < rowLen; row++ {
		for col := 0; col < colLen; col++ {
			// 0值忽略
			if matrix[row][col] == '0' {
				continue
			}
			if row == 0 || col == 0 {
				// 装载结果
				tmpArr[row][col] = 1
				// 直接对比大小
				if 1 > resMax {
					resMax = 1
				}
				continue
			}
			// 其他情况, 需要计算, 获取 上边数据
			val1 := tmpArr[row-1][col-1]
			val2 := tmpArr[row-1][col]
			val3 := tmpArr[row][col-1]
			minVal := minVal(minVal(val1, val2), val3)
			lastVal := minVal + 1
			tmpArr[row][col] = lastVal
			if lastVal > resMax {
				resMax = lastVal
			}
		}
	}
	return resMax * resMax
}
