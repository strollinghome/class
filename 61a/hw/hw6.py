#  Name: Carlos Flores
#  Email: carlos.flrs@berkeley.edu

# Q0.
# Q1.

class VendingMachine(object):
    """A vending machine that vends some product for some price.

    >>> v = VendingMachine('candy', 10)
    >>> v.vend()
    'Machine is out of stock.'
    >>> v.restock(2)
    'Current candy stock: 2'
    >>> v.vend()
    'You must deposit $10 more.'
    >>> v.deposit(7)
    'Current balance: $7'
    >>> v.vend()
    'You must deposit $3 more.'
    >>> v.deposit(5)
    'Current balance: $12'
    >>> v.vend()
    'Here is your candy and $2 change.'
    >>> v.deposit(10)
    'Current balance: $10'
    >>> v.vend()
    'Here is your candy.'
    >>> v.deposit(15)
    'Machine is out of stock. Here is your $15.'
    """

    def __init__(self, item, price):
        self.item = item
        self.price = price
        self.balance = 0 
        self.items = {'self.item': 0}
        self.change = 0

    def vend(self):
        if self.items['self.item'] == 0:
            return str('Machine is out of stock.')
        elif self.balance < self.price:
            return str('You must deposit $' + str(self.price - self.balance) + ' more.')
        elif self.balance == self.price:
            self.items['self.item'] -= 1
            self.balance = 0
            return str('Here is your ' + str(self.item) + '.')
        else:
            self.balance -= self.price
            self.items['self.item'] -= 1
            self.change = self.balance
            self.balance = 0
            return str('Here is your ' + str(self.item) + ' and $' + str(self.change) + ' change.')

    def restock(self, num_items):
        self.items['self.item'] += num_items
        return str('Current ' + str(self.item) + ' stock: ' + str(self.items['self.item']))

    def deposit(self, amount):
        self.balance += amount
        if self.items['self.item'] == 0:
            return str('Machine is out of stock. Here is your $' + str(self.balance) + '.')
        else:
            return str('Current balance: $' + str(self.balance))

# Q2.

class MissManners(object):
    """A container class that only forward messages that say please.

    >>> v = VendingMachine('teaspoon', 10)
    >>> v.restock(2)
    'Current teaspoon stock: 2'
    >>> m = MissManners(v)
    >>> m.ask('vend')
    'You must learn to say please first.'
    >>> m.ask('please vend')
    'You must deposit $10 more.'
    >>> m.ask('please deposit', 20)
    'Current balance: $20'
    >>> m.ask('now will you vend?')
    'You must learn to say please first.'
    >>> m.ask('please hand over a teaspoon')
    'Thanks for asking, but I know not how to hand over a teaspoon'
    >>> m.ask('please vend')
    'Here is your teaspoon and $10 change.'
    """
    def __init__(self, machine):
        self.inheritance = machine 

    def ask(self, input, *args):
        lst_inputs = input.split()
        if not lst_inputs[0] == 'please':
            return 'You must learn to say please first.'
        elif not hasattr(VendingMachine, lst_inputs[1]):
            return str('Thanks for asking, but I know not how to ') + str(' '.join(lst_inputs[1:]))
        else:
            return getattr(VendingMachine, lst_inputs[-1])(self.inheritance, *args)

# Q3.

from life import life

class life_lists(life):
    """An implementation of the Game of Life where the board is represented
    as a list of lists, one list per row.  The elements of the row lists
    are integers; odd integers represent cells with living organisms, and
    even integers represent empty cells."""

    def __init__(self, nrows, ncols, init=None):
        """A new Life board containing NROWS rows and NCOLS columns, which wrap around.
        If INIT is not None, then it should be a sequence (any iterable) of rows, each
        of which is itself a sequence (any iterable).   The values fill the board as
        for life.set_board."""
        super().__init__(nrows, ncols)
        self._board = [[0 for c in range(ncols)] for r in range(nrows)]
        if init is not None:
            self.set_board(init)

    def _is_alive(self, row, col):
        return self._board[row][col] % 2 == 1


    def _set_alive(self, row, col, alivep): 
        self._board[row][col] = alivep

    def tick(self):
        """Update the board to the next generation.
        >>> b = life_lists(10, 10,    # Glider
        ...                ("     ",
        ...                 "  *  ",
        ...                 "   *  ",
        ...                 " ***  ",
        ...                 "      "))
        >>> print(b, end="")
        ----------
        --*-------
        ---*------
        -***------
        ----------
        ----------
        ----------
        ----------
        ----------
        ----------
        >>> b.tick()
        >>> print(b, end="")
        ----------
        ----------
        -*-*------
        --**------
        --*-------
        ----------
        ----------
        ----------
        ----------
        ----------
        >>> b.tick()
        >>> b.tick()
        >>> b.tick()
        >>> print(b, end="")
        ----------
        ----------
        ---*------
        ----*-----
        --***-----
        ----------
        ----------
        ----------
        ----------
        ----------
        """
        import copy 
        temp_board = copy.deepcopy(self._board)
        r = 0
        while r < len(temp_board):
            c = 0
            while c < len(temp_board[r]):
                if life.survives(self, r, c):
                    temp_board[r][c] = True
                else: 
                    temp_board[r][c] = False
                c += 1
            r += 1
        self._board = temp_board

# Q4.

def make_instance(some_class):
    """Return a new object instance of some_class."""
    def get_value(name):
        if name in attributes:
            return attributes[name]
        else:
            value = some_class['get'](name)
            return bind_method(value, instance)

    def set_value(name, value):
        attributes[name] = value

    attributes = {}
    instance = {'get': get_value, 'set': set_value}
    return instance

def bind_method(value, instance):
    """Return value or a bound method if value is callable."""
    if callable(value):
        def method(*args):
            return value(instance, *args)
        return method
    else:
        return value

def make_class(attributes, base_classes=()):
    """Return a new class with attributes.

    attributes -- class attributes
    base_classes -- a sequence of classes
    """
    "*** YOUR CODE HERE ***"

def init_instance(some_class, *args):
    """Return a new instance of some_class, initialized with args."""
    instance = make_instance(some_class)
    init = some_class['get']('__init__')
    if init:
        init(instance, *args)
    return instance

# AsSeenOnTVAccount example from lecture.

def make_account_class():
    """Return the Account class, which has deposit and withdraw methods."""

    interest = 0.02

    def __init__(self, account_holder):
        self['set']('holder', account_holder)
        self['set']('balance', 0)

    def deposit(self, amount):
        """Increase the account balance by amount and return the new balance."""
        new_balance = self['get']('balance') + amount
        self['set']('balance', new_balance)
        return self['get']('balance')

    def withdraw(self, amount):
        """Decrease the account balance by amount and return the new balance."""
        balance = self['get']('balance')
        if amount > balance:
            return 'Insufficient funds'
        self['set']('balance', balance - amount)
        return self['get']('balance')

    return make_class(locals())

Account = make_account_class()

def make_checking_account_class():
    """Return the CheckingAccount class, which imposes a $1 withdrawal fee.

    >>> checking = CheckingAccount['new']('Jack')
    >>> checking['get']('interest')
    0.01
    >>> checking['get']('deposit')(20)
    20
    >>> checking['get']('withdraw')(5)
    14
    """
    interest = 0.01
    withdraw_fee = 1

    def withdraw(self, amount):
        fee = self['get']('withdraw_fee')
        return Account['get']('withdraw')(self, amount + fee)

    return make_class(locals(), [Account])

CheckingAccount = make_checking_account_class()

def make_savings_account_class():
    """Return the SavingsAccount class, which imposes a $2 deposit fee.

    >>> savings = SavingsAccount['new']('Jack')
    >>> savings['get']('interest')
    0.02
    >>> savings['get']('deposit')(20)
    18
    >>> savings['get']('withdraw')(5)
    13
    """
    deposit_fee = 2

    def deposit(self, amount):
        fee = self['get']('deposit_fee')
        return Account['get']('deposit')(self, amount - fee)

    return make_class(locals(), [Account])

SavingsAccount = make_savings_account_class()

def make_as_seen_on_tv_account_class():
    """Return an account with lots of fees and a free dollar.

    >>> such_a_deal = AsSeenOnTVAccount['new']('Jack')
    >>> such_a_deal['get']('balance')
    1
    >>> such_a_deal['get']('interest')
    0.01
    >>> such_a_deal['get']('deposit')(20)
    19
    >>> such_a_deal['get']('withdraw')(5)
    13
    """
    def __init__(self, account_holder):
        self['set']('holder', account_holder)
        self['set']('balance', 1)

    return make_class(locals(), [CheckingAccount, SavingsAccount])

AsSeenOnTVAccount = make_as_seen_on_tv_account_class()

