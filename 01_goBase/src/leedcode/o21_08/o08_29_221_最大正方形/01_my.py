from typing import List


class Solution:
	def maximalSquare(self, matrix: List[List[str]]) -> int:
		# 获取长度
		rowLen, colLen = len(matrix), len(matrix[0])
		# 定义结果
		max = 0
		# 遍历内容
		for row in range(rowLen):
			if matrix[row][0] == "1":
				matrix[row][0] = max = 1
			else:
				matrix[row][0] = 0
		for col in range(1, colLen):
			if matrix[0][col] == "1":
				matrix[0][col] = max = 1
			else:
				matrix[0][col] = 0
		# 开始计算
		for row in range(1, rowLen):
			for col in range(1, colLen):
				if matrix[row][col] == "0":
					matrix[row][col] = 0
					continue
				# 取最小值 + 1
				matrix[row][col] = lastVal = min(matrix[row - 1][col - 1], matrix[row - 1][col],
				                                  matrix[row][col - 1]) + 1
				if lastVal > max:
					max = lastVal
		return max * max


lists = [["1", "0", "1", "0", "0"], ["1", "0", "1", "1", "1"], ["1", "1", "1", "1", "1"], ["1", "0", "0", "1", "0"]]
sol = Solution()
print(sol.maximalSquare(lists))
