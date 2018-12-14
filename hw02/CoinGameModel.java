////
//// WARNING: DO NOT MODIFY THIS FILE! Your code will be compiled and tested
//// with the original, distributed version of this file, not with any
//// version you might happen to submit. So if you change this file, there's
//// a very good chance your code won't compile and you will automatically lose
//// 50% on the homework.
////
//// (You don't need to submit this file, either.)
////
package cs3500.hw02;

/**
 * An interface for playing a coin game. The rules of a particular coin game
 * will be implemented by classes that implement this interface.
 */
public interface CoinGameModel {
  /**
   * Gets the size of the board (the number of squares)
   *
   * @return the board size
   */
  int boardSize();

  /**
   * Gets the number of coins.
   *
   * @return the number of coins
   */
  int coinCount();

  /**
   * Gets the (zero-based) position of coin number {@code coinIndex}.
   *
   * @param coinIndex which coin to look up
   * @return the coin's position
   * @throws IllegalArgumentException
   *     if there is no coin with the requested index
   */
  int getCoinPosition(int coinIndex);

  /**
   * Returns whether the current game is over. The game is over if there are
   * no valid moves.
   *
   * @return whether the game is over
   */
  boolean isGameOver();

  /**
   * Moves coin number {@code coinIndex} to position {@code newPosition}.
   * Throws {@code IllegalMoveException} if the requested move is illegal,
   * which can happen in several ways:
   *
   * <ul>
   *   <li>There is no coin with the requested index.
   *   <li>The new position is occupied by another coin.
   *   <li>There is some other reason the move is illegal,
   *       as specified by the concrete game class.
   * </ul>
   *
   * Note that {@code coinIndex} refers to the coins as numbered from 0
   * to {@code coinCount() - 1}, not their absolute position on the board.
   * However, coins have no identity, so if one coin passes another, their
   * indices are exchanged. The leftmost coin is always coin 0, the next
   * leftmost is coin 1, and so on.
   *
   * @param coinIndex   which coin to move (numbered from the left)
   * @param newPosition where to move it to
   * @throws IllegalMoveException the move is illegal
   */
  void move(int coinIndex, int newPosition);

  /**
   * The exception thrown by {@code move} when the requested move is illegal.
   *
   * <p>(Implementation Note: Implementing this interface doesn't require
   * "implementing" the {@code IllegalMoveException} class—it's already
   * implemented right here. Nesting a class within an interface is a way to
   * strongly associate that class with the interface, which makes sense here
   * because the exception is intended to be used specifically by
   * implementations and clients of this interface.)
   */
  static class IllegalMoveException extends IllegalArgumentException {
    /**
     * Constructs a illegal move exception with no description.
     */
    public IllegalMoveException() {
      super();
    }

    /**
     * Constructs a illegal move exception with the given description.
     *
     * @param msg the description
     */
    public IllegalMoveException(String msg) {
      super(msg);
    }
  }
}
