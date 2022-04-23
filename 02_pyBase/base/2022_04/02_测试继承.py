if __name__ == '__main__':
	if True:
		class A:
			name = 'a'
			pass
		class B(A):
			name = 'bb'
			pass
		class C(A):
			# name = 'c'
			pass
		class M(C, B):
			pass
		a = M()
		print(M.mro())
		print(a.name)
	if True:
		class A:
			name = 'a'
			pass
		class B:
			name = 'bb'
			pass
		class C(A):
			# name = 'c'
			pass
		class M(C, B):
			pass
		a = M()
		print(M.mro())
		print(a.name)

	if True:
		class A:
			name = 'a'
			pass
		class B:
			name = 'bb'
			pass
		class C(A):
			# name = 'c'
			pass
		class M(C, B):
			pass
		a = M()
		print(M.mro())
		print(a.name)

	if True:
		class D:
			name = 'a'
			pass
		class C(D):
			name = 'cc'
			def say(self):
				return self.name + ' say c'
			pass
		class B(D):
			name = 'bb'
			# def say(self):
			# 	return self.name
			pass
		class M(B, C):
			pass
		a = M()
		print(M.mro())
		print(a.name)
		print(a.say())