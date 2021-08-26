from enum import Enum


# 定义状态枚举
class Status(Enum):
	START = 0
	SIGN = 1
	IN_NUMBER = 2
	END = 3


# 定义最大值
INT_MAX = 2 ** 31 - 1
INT_MIN_DE = 2 ** 31


class Automaton:
	def __init__(self):
		# 符号
		self.sign = 1
		# 大小
		self.ans = 0
		# 状态
		self.status = Status.START
		# 状态map
		self.table = {
			Status.START: [Status.START, Status.SIGN, Status.IN_NUMBER, Status.END],
			Status.SIGN: [Status.END, Status.END, Status.IN_NUMBER, Status.END],
			Status.IN_NUMBER: [Status.END, Status.END, Status.IN_NUMBER, Status.END],
			Status.END: [Status.END, Status.END, Status.END, Status.END],
		}

	def calc(self, char: str) -> bool:
		self.updateStatus(char)
		if self.status == Status.SIGN:
			if char == '-':
				self.sign = -1
		elif self.status == Status.IN_NUMBER:
			# 计算内容
			self.ans = self.ans * 10 + int(char)
			if self.sign < 0:
				if self.ans > INT_MIN_DE:
					self.ans = INT_MIN_DE
					return True
			else:
				if self.ans > INT_MAX:
					self.ans = INT_MAX
					return True
		return False
	def get_ans(self) -> int:
		return self.ans * self.sign

	def updateStatus(self, char: str):
		if char.isspace():
			status = Status.START
		elif char == '+' or char == '-':
			status = Status.START
		elif char.isdigit():
			status = Status.START
		else:
			status = Status.END
		self.status = self.table[self.status][status]


class Solution:
	def myAtoi(self, s: str) -> int:
		maton = Automaton()
		for item in s:
			if maton.calc(item):
				return maton.getAns()
		return maton.getAns()


maton = Automaton()
print(maton)
print(maton.get_ans())