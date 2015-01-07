"""Starter file for recursion lab."""

def sum(n):
    """Adds up all integers between 0 and n.

    >>> sum(4)
    10
    """
    if n == 1:
        return 1
    else:
        return n + sum(n-1)


def summation(n, term):
    """Return the sum of the 0th to nth terms in the sequence defined
    by term.

    >>> summation(4, lambda x: x*x) # 0 + 1 + 4 + 9 + 16
    30
    """
    if n == 1:
        return term(n)
    else:
        return term(n) + summation(n-1, term) 


def gcd(a, b):
    """Return the greatest common divisor of a and b.

    >>> gcd(24, 18)
    6
    >>> gcd(2, 4)
    2
    """
    a, b = max(a,b), min(a,b)
    if a % b == 0:
        return b
    else:
        return gcd(a, a % b)
 

def hailstone(n):
    """Print out the hailstone sequence starting at n, and return the
    number of elements in the sequence.

    >>> a = hailstone(10)
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
    print(n)
    if n == 1:
        return 1
    elif n % 2 == 0:
        return 1 + hailstone(n / 2)
    else:
        return 1 + hailstone(n*3 + 1)

def paths(m,n):
    if n == 1 or m == 1:
        return 1
    else:
        return paths(m-1, n) + paths(m, n-1)












