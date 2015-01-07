def decToBin(n):
    if n==0: return ''
    else:
        return decToBin(n//2) + str(n%2)
