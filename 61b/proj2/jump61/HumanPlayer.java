package jump61;

/** A Player that gets its moves from manual input.
 *  @author Carlos A. Flores-Arellano.
 */
class HumanPlayer extends Player {

    /** A new player initially playing COLOR taking manual input of
     *  moves from GAME's input source. */
    HumanPlayer(Game game, Side color) {
        super(game, color);
    }

    @Override
    /** Retrieve moves using getGame().getMove() until a legal one is found and
     *  make that move in getGame().  Report erroneous moves to player. */
    void makeMove() {
        Game myGame = getGame();
        myGame.getMove();
    }

    /** Most recent moves. */
    private int[] _moves = new int[2];

}
