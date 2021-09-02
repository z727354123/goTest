# #
#
# 给你一个以字符串表示的非负整数
# num
# 和一个整数
# k ，移除这个数中的
# k
# 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
#
#
# 示例
# 1 ：
#
# 输入：num = "1432219", k = 3
# 输出："1219"
# 解释：移除掉三个数字
# 4, 3, 和
# 2
# 形成一个新的最小的数字
# 1219 。
# 示例
# 2 ：
#
# 输入：num = "10200", k = 1
# 输出："200"
# 解释：移掉首位的
# 1
# 剩下的数字为
# 200.
# 注意输出不能有任何前导零。
# 示例
# 3 ：
#
# 输入：num = "10", k = 2
# 输出："0"
# 解释：从原数字移除所有的数字，剩余为空就是
# 0 。
#
#
# 提示：
#
# 1 <= k <= num.length <= 105
# num
# 仅由若干位数字（0 - 9）组成
# 除了
# 0
# 本身之外，num
# 不含任何前导零

class Solution:
	def removeKdigits(self, nums: str, k: int) -> str:
		# 定义一个栈存储
		stack = []
		# 遍历 nums内容
		for num in nums:
			# 无需转int
			while len(stack) > 0 and k > 0 and stack[-1] > num:
				k-=1
				stack.pop()
			# 添加到 stack
			stack.append(num)
		# k 截取
		stack = stack[:len(stack)-k]
		# 获取成 string
		# res = 0
		# for num in stack:
		# 	res = res * 10 + int(num)
		# return str(res)
		# 抹去前导零
		return "".join(stack).lstrip('0') or "0"


# 测试代码
myList = [1,2,3,4,5]

def myFunc(*args):
	print(len(args))
	print(args)
	print(type(args))

myFunc(myList)
print("-------------------华丽分割线----------------------")
myFunc(*myList)