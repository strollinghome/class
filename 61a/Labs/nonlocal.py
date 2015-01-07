def make_fib():
    """Returns a function that returns the next Fibonacci number
    every time it is called.

    >>> fib = make_fib()
    >>> fib()
    0
    >>> fib()
    1
    >>> fib()
    1
    >>> fib()
    2
    >>> fib()
    3
    """
    previous, current = 0, 1
    def fib():
        nonlocal previous, current
        to_return = previous
        previous, current = current, current + previous
        return to_return 
    return fib

def make_test_dice(seq):
    """Makes deterministic dice.

    >>> dice = make_test_dice([2, 6, 1])
    >>> dice()
    2
    >>> dice()
    6
    >>> dice()
    1
    >>> dice()
    2
    >>> other = make_test_dice([1])
    >>> other()
    1
    >>> dice()
    6
    """
    current = 0
    def current_dice():
        nonlocal current
        if current == len(seq):
            current = 0
        dice_number = seq[current]
        current +=1 
        return dice_number
    return current_dice 





















