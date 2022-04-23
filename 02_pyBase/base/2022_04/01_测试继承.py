import inspect

if __name__ == '__main__':
	if True:
		class D:
			name = 'd'

			def say(self):
				print("DD")

			pass


		class B(D):
			# name = 'b'
			# def say(self):
			# 	print("BB")
			pass


		class E(D):
			name = 'e'

			def say(self):
				print("EEE")

			pass


		class C(E):
			name = 'c'

			def say(self):
				print("CCC")

			pass


		class A(B, C):
			# name = 'a'
			def say(self):
				print("AA")

			pass


		print(inspect.getmro(A))
		a = A()
		print(a.name)
	if True:
		class E(object):
			name = 'e'

			def say(self):
				print("EEE")

			pass


		class C(E):
			name = 'c'

			def say(self):
				print("CC")

			pass


		class D(object):
			# name = 'd'
			# def say(self):
			# 	print("DD")
			pass


		class B(D):
			# name = 'b'
			# def say(self):
			# 	print("BB")
			pass


		class A(B, C):
			# name = 'a'
			# def say(self):
			# 	print("AA")
			pass


		a = A()
		print(a.name)
		a.say()
