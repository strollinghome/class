package game2048;

import ucb.util.CommandArgs;

import game2048.gui.Game;
import static game2048.Main.Side.*;

/** The main class for the 2048 game.
 *  @author Carlos Flores.
 */
public class Main {

    /** Size of the board: number of rows and of columns. */
    static final int SIZE = 4;
    /** Number of squares on the board. */
    static final int SQUARES = SIZE * SIZE;

    /** Symbolic names for the four sides of a board. */
    static enum Side { NORTH, EAST, SOUTH, WEST };

    /** The main program.  ARGS may contain the options --seed=NUM,
     *  (random seed); --log (record moves and random tiles
     *  selected.); --testing (take random tiles and moves from
     *  standard input); and --no-display. */
    public static void main(String... args) {
        CommandArgs options =
            new CommandArgs("--seed=(\\d+) --log --testing --no-display",
                            args);
        if (!options.ok()) {
            System.err.println("Usage: java game2048.Main [ --seed=NUM ] "
                               + "[ --log ] [ --testing ] [ --no-display ]");
            System.exit(1);
        }

        Main game = new Main(options);

        while (game.play()) {
            /* No action */
        }
        System.exit(0);
    }

    /** A new Main object using OPTIONS as options (as for main). */
    Main(CommandArgs options) {
        boolean log = options.contains("--log"),
            display = !options.contains("--no-display");
        long seed = !options.contains("--seed") ? 0 : options.getLong("--seed");
        _testing = options.contains("--testing");
        _game = new Game("2048", SIZE, seed, log, display, _testing);
    }

    /** Reset the score for the current game to 0 and clear the board. */
    void clear() {
        int oldScore = _score;
        _score = 0;
        _count = 0;
        _game.clear();
        if (oldScore > _maxScore) {
            _maxScore = oldScore;
        }
        _game.setScore(_score, _maxScore);
        for (int r = 0; r < SIZE; r += 1) {
            for (int c = 0; c < SIZE; c += 1) {
                _board[r][c] = 0;
            }
        }
    }

    /** Play one game of 2048, updating the maximum score. Return true
     *  iff play should continue with another game, or false to exit. */
    boolean play() {
        clear();
        setRandomPiece();
        while (true) {
            setRandomPiece();
            if (gameOver()) {
                if (_score > _maxScore) {
                    _maxScore = _score;
                }
                _game.setScore(_score, _score);
                _game.endGame();
            }
        GetMove:
            while (true) {
                String key = _game.readKey();
                Side side = null;
                if (key.equals("↑")) {
                    key = "Up";
                    side = keyToSide(key);
                } else if (key.equals("↓")) {
                    key = "Down";
                    side = keyToSide(key);
                } else if (key.equals("←")) {
                    key = "Left";
                    side = keyToSide(key);
                } else if (key.equals("→")) {
                    key = "Right";
                    side = keyToSide(key);
                }
                switch (key) {
                case "Up": case "Down": case "Left": case "Right":
                    if (!gameOver() && tiltBoard(keyToSide(key))) {
                        break GetMove;
                    }
                    break;
                case "Quit":
                    return false;
                case "New Game":
                    clear();
                    _game.clear();
                    return true;
                default:
                    break;
                }
            }
        }
    }

    /** Moves and merges tiles. It BOARD in orientation SIDE, and keeps track
     * of merged tiles with BOOLEANBOARD. */
    void moveTiles(int[][] board, Side side, boolean[][] booleanBoard) {
        boolean lastMerged = false;
        for (int r = 1; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] != 0) {
                    int value = board[r][c];
                    int spaces = nearestTile(board, r, c);
                    int mergingRow = r - spaces - 1;
                    int movingRow = r - spaces;
                    int row = tiltRow(side, r, c);
                    int col = tiltCol(side, r, c);
                    if (mergingRow >= 0 && board[mergingRow][c] == value
                            && !booleanBoard[tiltRow(side, mergingRow, c)]
                                            [tiltCol(side, mergingRow, c)]) {
                        _game.mergeTile(value, value * 2, row, col,
                                tiltRow(side, mergingRow, c),
                                tiltCol(side, mergingRow, c));
                        board[mergingRow][c] = value * 2;
                        booleanBoard[tiltRow(side, mergingRow, c)]
                                    [tiltCol(side, mergingRow, c)] = true;
                        board[r][c] = 0;
                        lastMerged =
                            booleanBoard[tiltRow(side, mergingRow, c)]
                                        [tiltCol(side, mergingRow, c)];
                        _count--;
                        _score = _score + value * 2;
                        _game.setScore(_score, _maxScore);
                    } else if (spaces != 0) {
                        _game.moveTile(value, row, col,
                                tiltRow(side, movingRow, c),
                                tiltCol(side, movingRow, c));
                        board[movingRow][c] = value;
                        board[r][c] = 0;
                    }
                }
            }
        }
        _game.displayMoves();
    }

    /** Calculates and returns the distance to the nearest tile in R, C
     * in BOARD. */
    int nearestTile(int[][] board, int r, int c) {
        int spaces = 0;
        while (r > 0 && board[r - 1][c] == 0) {
            spaces++;
            r--;
        }
        return spaces;
    }

    /** Return true iff the current game is over (no more moves
     *  possible). */
    boolean gameOver() {
        boolean canMove = false;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (_board[r][c] == WINNER) {
                    return true;
                } else {
                    canMove = canMove
                        || _board[r][c] == validMove(_board, r, c, r - 1, c)
                        || _board[r][c] == validMove(_board, r, c, r, c + 1)
                        || _board[r][c] == validMove(_board, r, c, r + 1, c)
                        || _board[r][c] == validMove(_board, r, c, r, c - 1);
                }
            }
        }
        return !canMove;
    }

    /** Returns if the specified NEWR and NEWC in BOARD are a valid space.
     * If so, it returns the value, if its value is 0 it return the value of
     * the adjecent tile in R and C.*/
    int validMove(int[][] board, int r, int c, int newR, int newC) {
        if (newR >= 0 && newR <= 3 && newC >= 0 && newC <= 3) {
            if (board[newR][newC] == 0) {
                return board[r][c];
            } else {
                return board[newR][newC];
            }
        } else {
            return -1;
        }
    }

    /** Add a tile to a random, empty position, choosing a value (2 or
     *  4) at random.  Has no effect if the board is currently full. */
    void setRandomPiece() {
        if (_count == SQUARES) {
            return;
        }
        while (true) {
            int[] tile = _game.getRandomTile();
            if (_board[tile[1]][tile[2]] == 0) {
                _game.addTile(tile[0], tile[1], tile[2]);
                _board[tile[1]][tile[2]] = tile[0];
                break;
            }
        }
        _count++;
        _game.displayMoves();
    }

    /** Perform the result of tilting the board toward SIDE.
     *  Returns true iff the tilt changes the board. **/
    boolean tiltBoard(Side side) {
        /* As a suggestion (see the project text), you might try copying
         * the board to a local array, turning it so that edge SIDE faces
         * north.  That way, you can re-use the same logic for all
         * directions.  (As usual, you don't have to). */
        int[][] board = new int[SIZE][SIZE];
        int[][] oldBoard = new int[SIZE][SIZE];
        boolean[][] booleanBoard = new boolean[SIZE][SIZE];
        boolean movedTiles = false;
        for (int r = 0; r < SIZE; r += 1) {
            for (int c = 0; c < SIZE; c += 1) {
                board[r][c] =
                    _board[tiltRow(side, r, c)][tiltCol(side, r, c)];
                booleanBoard[r][c] = false;
                oldBoard[r][c] = _board[r][c];
            }
        }
        moveTiles(board, side, booleanBoard);
        for (int r = 0; r < SIZE; r += 1) {
            for (int c = 0; c < SIZE; c += 1) {
                _board[tiltRow(side, r, c)][tiltCol(side, r, c)]
                    = board[r][c];
            }
        }
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                movedTiles = movedTiles || (oldBoard[r][c] != _board[r][c]);
            }
        }
        return movedTiles;
    }

    /** Return the row number on a playing board that corresponds to row R
     *  and column C of a board turned so that row 0 is in direction SIDE (as
     *  specified by the definitions of NORTH, EAST, etc.).  So, if SIDE
     *  is NORTH, then tiltRow simply returns R (since in that case, the
     *  board is not turned).  If SIDE is WEST, then column 0 of the tilted
     *  board corresponds to row SIZE - 1 of the untilted board, and
     *  tiltRow returns SIZE - 1 - C. */
    int tiltRow(Side side, int r, int c) {
        switch (side) {
        case NORTH:
            return r;
        case EAST:
            return c;
        case SOUTH:
            return SIZE - 1 - r;
        case WEST:
            return SIZE - 1 - c;
        default:
            throw new IllegalArgumentException("Unknown direction");
        }
    }

    /** Return the column number on a playing board that corresponds to row
     *  R and column C of a board turned so that row 0 is in direction SIDE
     *  (as specified by the definitions of NORTH, EAST, etc.). So, if SIDE
     *  is NORTH, then tiltCol simply returns C (since in that case, the
     *  board is not turned).  If SIDE is WEST, then row 0 of the tilted
     *  board corresponds to column 0 of the untilted board, and tiltCol
     *  returns R. */
    int tiltCol(Side side, int r, int c) {
        switch (side) {
        case NORTH:
            return c;
        case EAST:
            return SIZE - 1 - r;
        case SOUTH:
            return SIZE - 1 - c;
        case WEST:
            return r;
        default:
            throw new IllegalArgumentException("Unknown direction");
        }
    }

    /** Return the side indicated by KEY ("Up", "Down", "Left",
     *  or "Right"). */
    Side keyToSide(String key) {
        switch (key) {
        case "Up":
            return NORTH;
        case "Down":
            return SOUTH;
        case "Left":
            return WEST;
        case "Right":
            return EAST;
        default:
            throw new IllegalArgumentException("unknown key designation");
        }
    }

    /** Represents the board: _board[r][c] is the tile value at row R,
     *  column C, or 0 if there is no tile there. */
    private final int[][] _board = new int[SIZE][SIZE];

    /** True iff --testing option selected. */
    private boolean _testing;
    /** THe current input source and output sink. */
    private Game _game;
    /** The score of the current game, and the maximum final score
     *  over all games in this session. */
    private int _score, _maxScore;
    /** Number of tiles on the board. */
    private int _count;

    /** Winner tile. */
    public static final int WINNER = 2048;
}
