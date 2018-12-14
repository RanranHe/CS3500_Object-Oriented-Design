package cs3500.hw02;

/**
 * Created by Claire on 2015/10/7.
 */

import java.util.ArrayList;

/**
 * Abstract the coin game
 */
public abstract class AbstractCoinGame implements CoinGameModel {
    /**
     * List of coins value : position each Integer represents a coin
     */
    protected ArrayList<Integer> coins = new ArrayList<Integer>();

    /**
     * number of squares in the game
     */
    protected int boardSize;

    /**
     * @param board A string represents a board
     * @throws NullPointerException     for a empty board
     * @throws IllegalArgumentException for out of bounds
     */
    public AbstractCoinGame(String board) {
        boardSize = board.length();
        if (board == null) {
            throw new NullPointerException("Null board");
        }
        for (int i = 0; i < boardSize; i++) {

            if (board.charAt(i) == 'O') {
                coins.add(i);
            } else {
                if (board.charAt(i) != '-') {
                    throw new IllegalArgumentException("Illegal char " +
                            board.charAt(i) + " for board " + board);
                }
            }
        }
    }

    /**
     * Gets the size of the board (the number of squares)
     *
     * @return the board size
     */
    @Override
    public int boardSize() {
        return boardSize;
    }

    /**
     * Gets the number of coins.
     *
     * @return the number of coins
     */
    @Override
    public int coinCount() {
        return coins.size();
    }

    /**
     * Gets the (zero-based) position of coin number {@code coinIndex}.
     *
     * @param coinIndex which coin to look up
     * @return the coin's position
     * @throws IllegalArgumentException if there is no coin with the requested index
     */
    @Override
    public int getCoinPosition(int coinIndex) {
        if (coinIndex < 0 || coinIndex >= coins.size()) {
            throw new IllegalArgumentException("coin Index " +
                    coinIndex + " is out of bounds");
        } else {
            return coins.get(coinIndex);
        }
    }

    /**
     * Returns whether the current game is over. The game is over if there are no valid moves.
     *
     * @return whether the game is over
     */
    @Override
    public boolean isGameOver() {
        if (coins.size() == 0) {
            return true;
        }
        int pos = coins.get(coins.size() - 1);
        boolean res = true;
        for (int i = 0; i < coins.size(); i++) {
            res = res && isCoinAt(i);
        }
        return res;
    }

    /**
     * determine whether there is a coin in the position provided
     *
     * @param pos a coin's position
     * @return whether there is a coin in that position
     * @throws IllegalArgumentException if the position is out of board
     */
    public boolean isCoinAt(int pos) {
        if (pos < 0 || pos >= boardSize)
            throw new IllegalArgumentException(pos + " is outside the bounds");
        return coins.contains(pos);
    }

    /**
     * make the positions of coins into a String
     *
     * @return A string represents how coins arrange
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < boardSize(); i++) {
            if (isCoinAt(i)) {
                output += "O";
            } else {
                output += "-";
            }
        }
        return output;
    }

    /**
     * shift the coin's position
     *
     * @param coinIndex which coin
     * @param position  the position that the coin is moving to
     */
    protected void playCoins(int coinIndex, int position) {
        coins.remove(coinIndex);
        for (int i = 0; i < boardSize; i++) {
            if (i == coins.size() || coins.get(i) > position) {
                coins.add(i, position);
                break;
            }
        }
    }
}
