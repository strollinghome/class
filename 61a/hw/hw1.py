#  Name: Carlos Flores
#  Email: carlos.flrs@berkeley.edu

# Q1.

from operator import add, sub
def a_plus_abs_b(a, b):
    """Return a+abs(b), but without calling abs.

    >>> a_plus_abs_b(2, 3)
    5
    >>> a_plus_abs_b(2, -3)
    5
    """
    if b < 0:
        op = add(a, b *-1)
    else:
        op = add(a, b)
    return op



# Q2.

def two_of_three(a, b, c):
    """Return x*x + y*y, where x and y are the two largest of a, b, c.

    >>> two_of_three(1, 2, 3)
    13
    >>> two_of_three(5, 3, 1)
    34
    >>> two_of_three(10, 2, 8)
    164
    >>> two_of_three(5, 5, 5)
    50
    """
    if a > c and b > c:
        return a*a + b*b
    elif b > a and c > a:
        return b*b + c*c
    else: 
        if a > b and c > b:
            return a*a + c*c
        elif a == b == c:
            return a*a + a*a 



# Q3.

def if_function(condition, true_result, false_result):
    """Return true_result if condition is a true value, and false_result otherwise."""
    if condition:
        return true_result
    else:    
        return false_result


def with_if_statement():
    if c():
        return t()
    else:
        return f()

def with_if_function():
    return if_function(c(), t(), f())

def c():
    return True 

def t():
    return 1

def f():
    return 0/0

# Q4.

def hailstone(n):
    """Print the hailstone sequence starting at n and return its length.

    >>> a = hailstone(10)  # Seven elements are 10, 5, 16, 8, 4, 2, 1
    10
    5
    16
    8
    4
    2
    1
    >>> a
    7
    """
    print (int(n))
    count = 1
    while n > 1: 
        if n % 2 == 0:
            n = n / 2
            print (int(n))
            count += 1 
        else:
            n = (3 * n) + 1
            print (int(n))
            count += 1
    return count



