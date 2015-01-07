#  Name: Carlos Flores
#  Email: carlos.flrs@berkeley.edu

# Q1.

def g(n):
    """Return the value of G(n), computed recursively.

    >>> g(1)
    1
    >>> g(2)
    2
    >>> g(3)
    3
    >>> g(4)
    10
    >>> g(5)
    22
    """
    if n <= 3:
        return n
    else:
        return g(n - 1) + 2 * g(n - 2) + 3 * g(n - 3)
    

def g_iter(n):
    """Return the value of G(n), computed iteratively.

    >>> g_iter(1)
    1
    >>> g_iter(2)
    2
    >>> g_iter(3)
    3
    >>> g_iter(4)
    10
    >>> g_iter(5)
    22
    """
    n_1, n_2, n_3, index = 1, 2, 3, 3
    if n <= 3:
        return n
    while index < n:
        current = n_3 + 2 * n_2 + 3 * n_1
        n_1, n_2, n_3 = n_2, n_3, current  
        index += 1
    return current



    
# Q2.

def has_seven(n):
    """Has a has_seven
    >>> has_seven(3)
    False
    >>> has_seven(7)
    True
    >>> has_seven(2734)
    True
    >>> has_seven(2634)
    False
    >>> has_seven(734)
    True
    >>> has_seven(7777)
    True
    """
    if  n % 10 == 7:
        return True
    elif n == 0:
        return False
    else:
        return has_seven(n // 10)    
# Q3.

"1 2 3 4 5 6 [7] 6 5 4 3 2 1 [0] 1 2 [3] 2 1 0 [-1] 0 1 2 3 4 [5] [4] 5 6"


def pingpong(n):
    """Return the nth element of the ping-pong sequence.

    >>> pingpong(7)
    7
    >>> pingpong(8)
    6
    >>> pingpong(15)
    1
    >>> pingpong(21)
    -1
    >>> pingpong(22)
    0
    >>> pingpong(30)
    6
    >>> pingpong(68)
    2
    >>> pingpong(69)
    1
    >>> pingpong(70)
    0
    >>> pingpong(71)
    1
    >>> pingpong(72)
    0
    >>> pingpong(100)
    2
    """
    #Write recursive function with n, k, direction
    #Update the arguments 
    #Direction can be a boolean if true up if not down

    def ping_pong(n, counter=1, k=1, direction=1):
        if counter == n:
            return k
        if has_seven(counter) or counter % 7 == 0:
            return ping_pong(n, counter + 1, k - direction, -direction)
        else:
            return ping_pong(n, counter + 1, k + direction, direction)
        
    return ping_pong(n,)

# Q4.

def ten_pairs(n):
    """Return the number of ten-pairs within positive integer n.

    >>> ten_pairs(7823952)
    3
    >>> ten_pairs(55055)
    6
    >>> ten_pairs(9641469)
    6
    """
    def check(first_num, rest_num):
        if not(rest_num):
            return 0
        elif first_num + rest_num % 10 == 10:
            return 1 + check(first_num, rest_num // 10)
        else:
            return check(first_num, rest_num // 10)
    
    if n == 0:
        return 0
    else:
        return check(n % 10, n // 10) + ten_pairs(n // 10)

# Q5.
from math import pow

def count_change(amount):
    """Return the number of ways to make change for amount.
    ((n // (10 ** digits(n) - 2)) % 10)
    >>> count_change(7)
    6
    >>> count_change(10)
    14
    >>> count_change(20)
    60
    >>> count_change(100)
    9828
    """
    "*** YOUR CODE HERE ***"
    #Three recursion

    largest_coin = 0
    while pow(2, largest_coin) < amount:
        largest_coin += 1
    largest_coin = pow(2, largest_coin - 1)

    def make_change(amount, coin_value):
        if coin_value == 1:
            return 1
        elif amount < coin_value:
            return make_change(amount, coin_value // 2)
        else:
            return make_change(amount - coin_value, coin_value) + make_change(amount, coin_value // 2)

    return make_change(amount, largest_coin)
    
    

# Q6.

from operator import sub, mul

def make_anonymous_factorial():
    """Return the value of an expression that computes factorial.

    >>> make_anonymous_factorial()(5)
    120
    """
    return "YOUR EXPRESSION HERE"
