package jump61;

import static jump61.Side.*;

import java.util.Stack;
import java.util.Arrays;

/** A Jump61 board state that may be modified.
 *  @author Carlos A. Flores-Arellano.
 */
class MutableBoard extends Board {

    /** An N x N board in initial configuration. */
    MutableBoard(int N) {
        _undoStack = new Stack<SquarePosition>();
        _size = N;
        _board = new Square[N * N];
        int i;
        for (i = 0; i < _board.length; i = i + 1) {
            _board[i] = Square.INITIAL;
        }
    }

    /** A board whose initial contents are copied from BOARD0, but whose
     *  undo history is clear. */
    MutableBoard(Board board0) {
        _undoStack = new Stack<SquarePosition>();
        _size = board0.size();
        copy(board0);
    }

    @Override
    void clear(int N) {
        _undoStack = new Stack<SquarePosition>();
        _size = N;
        _board = new Square[N * N];
        int i;
        for (i = 0; i < _board.length; i = i + 1) {
            _board[i] = Square.INITIAL;
        }
    }

    @Override
    void copy(Board board) {
        clear(board.size());
        int i;
        Side color;
        int spots;
        for (i = 0; i < board.size() * board.size(); i = i + 1) {
            color = board.get(i).getSide();
            spots = board.get(i).getSpots();
            _board[i] = Square.square(color, spots);
        }
    }

    /** Copy the contents of BOARD into me, without modifying my undo
     *  history.  Assumes BOARD and I have the same size. */
    private void internalCopy(MutableBoard board) {
        int i;
        Side color;
        int spots;
        for (i = 0; i < board.size() * board.size(); i = i + 1) {
            color = board.get(i).getSide();
            spots = board.get(i).getSpots();
            _board[i] = Square.square(color, spots);
        }
    }

    @Override
    int size() {
        return _size;
    }

    @Override
    Square get(int n) {
        return _board[n];
    }

    @Override
    int numOfSide(Side side) {
        int count;
        count = 0;
        int i;
        Side squareSide;
        String sideColor;
        sideColor = side.toCapitalizedString();
        for (i = 0; i < _board.length; i = i + 1) {
            squareSide = _board[i].getSide();
            if (squareSide.toCapitalizedString().equals(sideColor)) {
                count = count + 1;
            }
        }
        return count;
    }

    @Override
    int numPieces() {
        int count;
        count = 0;
        int spots;
        int i;
        for (i = 0; i < _board.length; i = i + 1) {
            spots = _board[i].getSpots();
            count = count + spots;
        }
        return count;
    }

    @Override
    void addSpot(Side player, int r, int c) {
        if (exists(r, c) && getWinner() == null) {
            int index = super.sqNum(r, c);
            Square prevSquare = _board[index];
            _undoStack.push(new SquarePosition(index, prevSquare));
            int prevSpots = prevSquare.getSpots();
            _board[index] = Square.square(player, prevSpots + 1);
            int numNeighbors  = neighbors(index);
            if (numNeighbors == 2 && prevSpots == 2
                || numNeighbors == 3 && prevSpots == 3
                || numNeighbors == 4 && prevSpots == 4) {
                overflow(player, index);
            }
        }
        announce();
    }

    @Override
    void addSpot(Side player, int n) {
        if (exists(row(n), col(n))) {
            markUndo();
            Square prevSquare = _board[n];
            _undoStack.push(new SquarePosition(n, prevSquare));
            Side prevSide = prevSquare.getSide();
            if (player.playableSquare(prevSide)) {
                int prevSpots = prevSquare.getSpots();
                int numNeighbors  = neighbors(n);
                _board[n] = Square.square(player, prevSpots + 1);
                if (numNeighbors == 2 && prevSpots == 2
                    || numNeighbors == 3 && prevSpots == 3
                    || numNeighbors == 4 && prevSpots == 4) {
                    overflow(player, n);
                }
            } else {
                throw GameException.error(
                                    "invalid move: %d %d", row(n), col(n));
            }
        } else {
            throw GameException.error(
                                    "move %d %d out of bounds", row(n), col(n));
        }
        announce();
    }

    @Override
    void set(int r, int c, int num, Side player) {
        internalSet(sqNum(r, c), Square.square(player, num));
    }

    @Override
    void set(int n, int num, Side player) {
        internalSet(n, Square.square(player, num));
        announce();
    }

    @Override
    void undo() {
        if (!_undoStack.empty()) {
            int index;
            SquarePosition sq;
            sq = _undoStack.pop();
            while (!sq.isSentinel()) {
                index = sq.getIndex();
                _board[index] = sq.getSquare();
                sq = _undoStack.pop();
            }
        }
    }

    /** Record the beginning of a move in the undo history. */
    private void markUndo() {
        SquarePosition sentinel = new SquarePosition();
        _undoStack.push(sentinel);
    }

    /** Set the contents of the square with index IND to SQ. Update counts
     *  of numbers of squares of each color.  */
    private void internalSet(int ind, Square sq) {
        _undoStack = new Stack<SquarePosition>();
        _board[ind] = sq;
    }

    /** Notify all Observers of a change. */
    private void announce() {
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MutableBoard)) {
            return obj.equals(this);
        } else {
            MutableBoard objBoard = (MutableBoard) obj;
            Square[] objBoardRep = objBoard._board;
            int sq0Spots;
            int sq1Spots;
            Side sq0Side;
            Side sq1Side;
            int i;
            if (_size == objBoard._size) {
                for (i = 0; i < _size * _size; i = i + 1) {
                    sq0Spots = _board[i].getSpots();
                    sq0Side = _board[i].getSide();
                    sq1Spots = objBoardRep[i].getSpots();
                    sq1Side = objBoardRep[i].getSide();
                    if (sq0Spots != sq1Spots || sq0Side != sq1Side) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(_board);
    }

    /* My variables. */

    /** Holds the representation of the board. */
    private Square[] _board;

    /** Size of the board. */
    private int _size;

    /** Keeps track of the state of the board. */
    private Stack<SquarePosition> _undoStack;

    @Override
    public Square[] getBoard() {
        return _board;
    }

    /* Classes and methods. */

    /** Handles overflow at square in INDEX of a move made by PLAYER. */
    private void overflow(Side player, int index) {
        int row = row(index);
        int col = col(index);
        _board[index] = Square.square(player, 1);
        addSpot(player, row - 1, col);
        addSpot(player, row, col + 1);
        addSpot(player, row + 1, col);
        addSpot(player, row, col - 1);
    }

    /** Contains a square with an index. */
    private class SquarePosition {

        /** Index in the board. */
        private int _index;

        /** Square at the specified index. */
        private Square _sq;

        /** Instance of square is a marker. */
        private boolean _sentinel;

        /** A position with the specified INDEX in _board and
        SQ square. */
        public SquarePosition(int index, Square sq) {
            _index = index;
            _sq = sq;
            _sentinel = false;
        }

        /** Creates a sentinel to signal when to stop undoing moves. */
        public SquarePosition() {
            _sentinel = true;
        }

        /** @return if this square is a sentinel. */
        private boolean isSentinel() {
            return _sentinel;
        }

        /** @return the index of this square position in _board. */
        private int getIndex() {
            return _index;
        }

        /** @return the square in this square position. */
        private Square getSquare() {
            return _sq;
        }
    }

}
