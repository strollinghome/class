def abs(x):
	if x >= 0:
		return x
	else: 
		return -x

def abs1(x):
	if x >= 0:
		return x
	return -x

def exp_decay(n):
	if n % 2 != 0:
		return
	while n > 0:
		print(n)
		n = n // 2

def funky(k):
	while k < 50:
		if k % 2 == 0:
			k += 13
		else: 
			k += 1
		print (k)
	return k

def function():
	n, i = 7, 0
	while i < n:
		i += 2
		print(i)


def infinite_loop():
	n = 4 
	while True:
		n -= 1
		print(n)


def factors(n):
	count = n
	while count > 0:
		if n % count == 0:
			print(count)
		count -= 1

def square(x):
	return x*x

def neg(f, x):
	return -f(x)

def first(x):
	x += 8
	def second(y):
		print('second')
		return x + y
	print('first')
	return second

def foo(x):
	def bar(y):
		return x + y
	return bar

boom = foo(23)

def troy():
	abed = 0
	while abed < 10:
		def britta():
			return abed
		abed += 1
	abed = 20
	return britta

annie = troy()
def shirley():
	return annie

pierce = shirley()

pierce()




































