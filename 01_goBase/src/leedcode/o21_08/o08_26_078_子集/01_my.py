from typing import List


class Solution:
	def subsets(self, nums: List[int]) -> List[List[int]]:
		# 默认大小
		res = [[]]
		for num in nums:
			tmp = []
			for itemList in res:
				tmp.append(itemList.copy())
				itemList.append(num)
				tmp.append(itemList)
			res = tmp
		return res


