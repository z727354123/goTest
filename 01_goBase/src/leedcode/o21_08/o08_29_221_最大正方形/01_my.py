from typing import List


class Solution:
	def maximalSquare(self, matrix: List[List[str]]) -> int:
		# 获取长度
		rowLen, colLen = len(matrix), len(matrix[0])
		# 记录结果
		tmpList = [[0] * colLen for _ in range(rowLen)]
		# 定义结果
		max = 0
		# 遍历内容
		for row in range(rowLen):
			if matrix[row][0] == "1":
				tmpList[row][0] = max = 1
		for col in range(1, colLen):
			if matrix[0][col] == "1":
				tmpList[0][col] = max = 1
		# 开始计算
		for row in range(1, rowLen):
			for col in range(1, colLen):
				if matrix[row][col] == "0":
					continue
				# 取最小值 + 1
				tmpList[row][col] = lastVal = min(tmpList[row - 1][col - 1], tmpList[row - 1][col],
				                                  tmpList[row][col - 1]) + 1
				if lastVal > max:
					max = lastVal
		return max * max


lists = [["1", "0", "1", "0", "0"], ["1", "0", "1", "1", "1"], ["1", "1", "1", "1", "1"], ["1", "0", "0", "1", "0"]]
sol = Solution()
print(sol.maximalSquare(lists))
