def union(s1, s2):
    """Returns the union of two sets.

    >>> r = {0, 6, 6}
    >>> s = {1, 2, 3, 4}
    >>> t = union(s, {1, 6})
    >>> t
    {1, 2, 3, 4, 6}
    >>> union(r, t)
    {0, 1, 2, 3, 4, 6}
    """
    while len(s2) != 0:
        element = s2.pop()
        s1.add(element)
    return s1
    

def intersection(s1, s2):
    """Returns the intersection of two sets.

    >>> r = {0, 1, 4, 0}
    >>> s = {1, 2, 3, 4}
    >>> t = intersection(s, {3, 4, 2})
    >>> t
    {2, 3, 4}
    >>> intersection(r, t)
    {4}
    """
    new_set = set()
    while len(s1) != 0:
        element = s1.pop()
        if element in s2:
            new_set.add(element)
    return new_set

x = ['dog', 'cat', 'monkey']
y = ['dog',  'cat',  'monkey',  'giraffe']

a = [1, 2, 3, 4, 5] 
b = [1, 2, 3, 4, 5, 6]

def extra_elem(a,b):
    """B contains every element in A, and has one additional member, find
    the additional member.

    >>> extra_elem(['dog', 'cat', 'monkey'],  ['dog',  'cat',  'monkey',  'giraffe'])
    'giraffe'
    >>> extra_elem([1, 2, 3, 4, 5], [1, 2, 3, 4, 5, 6])
    6
    """
    a, b = set(a), set(b)
    inter_elemets = intersection(a, b)
    while len(b) != 0:
        element = b.pop()
        if element not in inter_elemets:
            return element


def add_up(n, lst):
    """Returns True if any two non identical elements in lst add up to any n.

    >>> add_up(100, [1, 2, 3, 4, 5])
    False
    >>> add_up(7, [1, 2, 3, 4, 2])
    True
    >>> add_up(10, [5, 5])
    False
    """
    lst_set = set(lst)
    element = 0
    while len(lst_set) != 0:
        element += lst_set.pop()
        if element >= n:
            return True
    return False




def find_duplicates(lst):
    """Returns True if lst has any duplicates and False if it does not.

    >>> find_duplicates([1, 2, 3, 4, 5])
    False
    >>> find_duplicates([1, 2, 3, 4, 2])
    True
    """
    while len(lst) != 0:
        element = lst.pop()
        if element in lst:
            return True
    return False

def find_duplicates_k(k, lst):
    """Returns True if there are any duplicates in lst that are within k
    indices apart.

    >>> find_duplicates_k(3, [1, 2, 3, 4, 1])
    False
    >>> find_duplicates_k(4, [1, 2, 3, 4, 1])
    True
    """
    for i in lst:
        kth_lst = lst[i+1:k+1]
        if i in kth_lst:
            return True
    return False



def pow(n,k):
    """Computes n^k.

    >>> pow(2, 3)
    8
    >>> pow(4, 2)
    16
    """
    "*** YOUR CODE HERE ***"


def missing_no(lst):
    """lst contains all the numbers from 1 to n for some n except some
    number k. Find k.

    >>> missing_no([1, 0, 4, 5, 7, 9, 2, 6, 3])
    8
    >>> missing_no(list(filter(lambda x: x != 293, list(range(2000)))))
    293
    """
    "*** YOUR CODE HERE ***"


def find_duplicates_k_l(k, l, lst):
    """Returns True if there are any two values who in lst that are within k
    indices apart AND if the absolute value of their difference is less than
    or equal to l.

    >>> find_duplicates_k_l(4, 0, [1, 2, 3, 4, 5])
    False
    >>> find_duplicates_k_l(4, 1, [1, 2, 3, 4, 5])
    True
    >>> find_duplicates_k_l(4, 0, [1, 2, 3, 4, 1])
    True
    >>> find_duplicates_k_l(2, 0, [1, 2, 3, 4, 1])
    False
    >>> find_duplicates_k_l(1, 100, [100, 275, 320, 988, 27])
    True
    >>> find_duplicates_k_l(1, 100, [100, 199, 275, 320, 988, 27])
    True
    >>> find_duplicates_k_l(1, 100, [100, 23, 199, 275, 320, 988, 27])
    True
    >>> find_duplicates_k_l(2, 100, [100, 23, 199, 275, 320, 988, 27])
    True
    """
    "*** YOUR CODE HERE ***"
