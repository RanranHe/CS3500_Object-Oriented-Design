package cs3500.hw03;

import java.util.ArrayList;

/**
 * You don't need to implement this class or any concrete subclasses for pset03.
 */
public final class StrictCoinGameModel implements CoinGameModel {
  // (Exercise 2) Declare the fields needed to support the methods in
  // the interface you’ve designed:

  private boolean[] board; // represents the current game board
  private ArrayList<Player> players;
  private int currentPlayer; // whose turn is it

  // (Exercise 3) Describe, as precisely as you can, your
  // representation’s class invariants:

  /**
   * INVARIANT: currentPlayer is always less than
   * maxPlayers and greater than or equal to zero
   *
   * maxPlayers is greater than zero
   */

  // (Exercise 4) Describe your constructor API here by filling in
  // whatever arguments you need and writing good Javadoc. (You may
  // declare any combination of constructors and static factory
  // methods that you like, but you need not get fancy.)

  /**
   * Constructs a game setting the current board sets the max number of players starts the game on
   * the first player
   *
   * @param board      the starting game board
   * @throws IllegalArgumentException improperly formed boards
   * @throws IllegalArgumentException maxPlayers <= 0
   */
  protected StrictCoinGameModel(String board, String... players) {
    // You don't need to implement this constructor.
    // board make
    this.players = new ArrayList<Player>();
    for(String p: players) {
      addPlayer(p);
    }
    throw new UnsupportedOperationException("no need to implement this");
  }

  protected StrictCoinGameModel(String board) {
    // board make
    players = new ArrayList<Player>();
  }

  @Override
  public int boardSize() {
    return 0;
  }

  @Override
  public int coinCount() {
    return 0;
  }

  @Override
  public int getCoinPosition(int coinIndex) {
    return 0;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public void move(int coinIndex, int newPosition) {
  }

  public void addPlayer(String name) {
    players.add(new Player(name));
  }

  public void addPlayer(String name, int pos) {
    if(isGameOver()) {
      throw new IllegalStateException("game is over");
    }
    if(pos <= currentPlayer) {
      endTurn();
    }
    players.add(pos, new Player(name));
  }

  @Override
  public int getMaxPlayers() {
    return Player.getPlayerCount();
  }

  @Override
  public Player currentPlayer() {
    return players.get(currentPlayer);
  }

  @Override
  public void endTurn() {
    if(!isGameOver()) {
      currentPlayer++;
      currentPlayer %= getMaxPlayers();
    }
    throw new IllegalStateException("Game is over");
  }

  @Override
  public Player getWinner() {
    if(!isGameOver()) {
      throw new IllegalStateException("Winner can only be determine if the game is over");
    }
    return currentPlayer();
  }

  // You don't need to implement any methods or constructors. However,
  // if you want to make sure your code compiles, you could have your
  // IDE generate stubs for all the missing methods. This would also
  // allow you to make sure that your tests in StrictCoinGameModelTest
  // actually type check and compile against this class (though you
  // don’t need to make them pass, because you don’t need to implement
  // StrictCoinGameModel’s methods).


  public static class Factory {
    String board;
    int maxPlayers;
    StrictCoinGameModel game;

    /**
     * makes a game with: an empty board, 1 player
     */
    public Factory() {

    }

    /**
     * @param board      starting board
     * @param maxPlayers max number of players
     */
    public Factory(String board, int maxPlayers) {

    }

    /**
     * sets the board
     *
     * @param board starting board
     * @return this factory with modified board
     */
    public Factory setBoard(String board) {
      return null;
    }

    /**
     * sets the max number of players
     *
     * @param max max number of players
     * @return this factory with modified
     */
    public Factory setMaxPlayers(int max) {
      return null;
    }

    /**
     * builds a StrictCoinGameModel with the current parameters
     *
     * @return a game with the current factory settings
     */
    public StrictCoinGameModel build() {
      return null;
    }
  }
}
