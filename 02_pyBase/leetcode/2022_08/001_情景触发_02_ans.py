class Solution(object):
	def getTriggerTime(self, day_increase, story_requires):
		"""
		:type day_increase: List[List[int]]
		:type story_requires: List[List[int]]
		:rtype: List[int]
		"""
		tmp = [0 for i in range(3)]
		# 将剧情的下标记录在story中，方便映射结果
		story_requires = [x + [i] for i, x in enumerate(story_requires)]
		# 将requirements按三种维度分别排序，得到 s
		newS = [sorted(story_requires, key=lambda x: x[i]) for i in range(3)]
		index = [0 for i in range(3)]

		reqLen = len(story_requires)
		trigger = [0 for i in range(reqLen)]
		ans = [-1 for i in range(reqLen)]
		# 枚举每一天
		for d, (na, nb, nc) in enumerate(day_increase):
			# 计算当天的属性
			tmp[0] += na;
			tmp[1] += nb;
			tmp[2] += nc
			# 遍历三种属性的排序序列，计算当前可以被触发的剧情
			for i in range(3):
				while index[i] < reqLen and tmp[i] >= newS[i][index[i]][i]:
					trigger[newS[i][index[i]][-1]] += 1
					# 如果某个剧情触发次数等于3次(三种属性均触发，剧情被实际触发)
					if trigger[newS[i][index[i]][-1]] == 3:
						ans[newS[i][index[i]][-1]] = d + 1
					index[i] += 1
		# 第0天单独考虑
		for i, (na, nb, nc, _) in enumerate(story_requires):
			if na == 0 and nb == 0 and nc == 0:
				ans[_] = 0
		return ans


if __name__ == '__main__':
	Ss = Solution()
	Ss.getTriggerTime([[0, 4, 5], [4, 8, 8], [8, 6, 1], [10, 10, 0]],
	                  [[12, 11, 16], [20, 2, 6], [9, 2, 6], [10, 18, 3], [8, 14, 9]])
