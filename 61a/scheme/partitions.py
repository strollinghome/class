def part(n):
    """Return the number of partitions of positive integer n.
 
   >>> part(5)
   7
   """
    n = n+1
    def count(n, m = list(range(1, n+1))):
        if n <= 0:
            return 0
 
        if not m:
            return 0
             
        elif n == 1:
            return 1
             
        else:
            d = m[0]
            return count(n, m[1:]) + count(n-d, m)
    return count(n)