"""Starter file for tuples lab"""

##########
# TUPLES #
##########

def reverse_iter(tup):
    """Returns the reverse of the given tuple.

    >>> reverse_iter((1, 2, 3, 4))
    (4, 3, 2, 1)
    """
    new, index = (), 0
    while index < len(tup):
        new = (tup[index],) + new
        index += 1
    return new



def reverse_recursive(tup):
    """Returns the reverse of the given tuple.

    >>> reverse_recursive((1, 2, 3, 4))
    (4, 3, 2, 1)
    """
    if not tup:
        return ()
    else:
        return reverse_recursive(tup[1:]) + (tup[0],)  

def merge(tup1, tup2):
    """Merges two sorted tuples.

    >>> merge((1, 3, 5), (2, 4, 6))
    (1, 2, 3, 4, 5, 6)
    >>> merge((), (2, 4, 6))
    (2, 4, 6)
    >>> merge((1, 2, 3), ())
    (1, 2, 3)
    """
    if not tup1 or not tup2:
        return tup1 + tup2
    elif tup1[0] < tup2[0]:
        return (tup1[0],) + merge(tup1[1:], tup2)
    else:
        return (tup2[0],) + merge(tup1, tup2[1:])
        
