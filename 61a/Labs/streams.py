class Stream(object):
    class empty(object):
        def __repr__(self):
            return 'Stream.empty'

    empty = empty()

    def __init__(self, first, compute_rest, empty= False):
        self.first = first
        self._compute_rest = compute_rest
        self.empty = empty
        self._rest = None
        self._computed = False

    @property
    def rest(self):
        assert not self.empty, 'Empty streams have no rest.'
        if not self._computed:
            self._rest = self._compute_rest()
            self._computed = True
        return self._rest

    def __repr__(self):
        return 'Stream({0}, <...>)'.format(repr(self.first))

def make_integer_stream(first=1):
    def compute_rest():
        return make_integer_stream(first+1)
    return Stream(first, compute_rest)

s1 = Stream(1, lambda: Stream(3, lambda: Stream(4))) 
s2 = Stream(2, lambda: Stream(2, lambda: Stream(4)))

def add_streams(s1, s2):
    def compute_rest_sum():
        return add_streams(s1.rest, s2.rest)
    return Stream((s1.first + s2.first), compute_rest_sum)

def make_fib_stream():
    "*** YOUR CODE HERE ***"
    def fib_num(x, y):
       make_fib_stream()

def stream_map(func, stream):
    "*** YOUR CODE HERE ***"

def my_stream():
    def compute_rest():
        return add_streams(map_stream(double,
                                      my_stream()),
                                      my_stream())
    return Stream(1, compute_rest)

def interleave(stream1, stream2):
    "*** YOUR CODE HERE ***"

