////
//// WARNING: You should only change this file in the places that are
//// marked /* fill in */ and nowhere else.
////
package cs3500.hw03;

import cs3500.hw03.CoinGameModel;
import cs3500.hw03.StrictCoinGameModel;

/**
 * Provides testing scenarios for {@link StrictCoinGameModel}.
 *
 * <p>
 * There are five testing scenarios below. Each one is a public static
 * method that takes an external string representation of a board,
 * initializes a variable 'model' to a CoinGameModel representing that
 * board, and then performs a sequence of operations on the board before
 * returning either the external string representation of the new board
 * (scenarios 1-4) or whether the game is over (scenario 5).
 *
 * <p>
 * Your job is as follows:
 *
 * <p>
 * 1. In each scenario, add parameters to the 'new StrictCoinGameModel'
 * constructor call in order to construct a game model object with the
 * board represented by 'board' and the number of players specified in
 * the comment. (If your game model is constructed using a static
 * factory instead of a constructor, modify the constructor expression
 * to use your static factory.)
 *
 * <p>
 * 2. For each operation in each scenario's sequence, add the code
 * necessary to perform that operation on 'model'. Provide whatever
 * arguments are necessary so that the operation might succeed. (For
 * example, if your move operation requires specifying a player then
 * pass in the right player for that turn.)
 *
 * <p>
 * 3. For scenario 5 ONLY, modify the 'return' statement in order to use your
 * game-over method to return whether the game is over. (For the other
 * four scenarios, the 'return' statement should not need to be
 * changed.)
 *
 * <p>
 * 4. SUGGESTION: Use these scenarios in your tests. For example:
 *
 * <code>
 *      @Test
 *      public void testScenario4_1() {
 *        assertEquals("OOO-O---",
 *                     CoinGameTestScenarios.scenario4("OOO---O-"));
 *      }
 *
 *      @Test(expected = CoinGameModel.IllegalMoveException.class)
 *      public void testScenario4_2() {
 *        CoinGameTestScenarios.scenario4("O-O-O-O-");
 *      }
 * </code>
 */
public final class CoinGameTestScenarios {
  public static String scenario1(String board) {
    // Creates a CoinGameModel with TWO players from board:
    CoinGameModel model = new StrictCoinGameModel(board, "A", "B");

    // Moves coin index 0 to position 0:
    model.move(0, 0);

    // Moves coin index 1 to position 1:
    model.move(1, 1);

    // Moves coin index 2 to position 2:
    model.move(2, 2);

    // Moves coin index 3 to position 3:
    model.move(3, 3);

    // DON'T CHANGE THIS:
    return model.toString();
  }

  public static String scenario2(String board) {
    // Creates a CoinGameModel with THREE players from board:
    StrictCoinGameModel.Factory f = new StrictCoinGameModel.Factory();
    StrictCoinGameModel model = f.setBoard(board).setPlayers("A", "B", "C").build();

    // Moves coin index 2 to position 4:
    model.move(2, 4);

    // Moves coin index 1 to position 2:
    model.move(1, 2);

    // Moves coin index 1 to position 1:
    model.move(1, 1);

    // DON'T CHANGE THIS:
    return model.toString();
  }

  public static String scenario3(String board) {
    // Creates a CoinGameModel with TWO players from board:
    StrictCoinGameModel.Factory f = new StrictCoinGameModel.Factory();
    StrictCoinGameModel model = f.setBoard(board).setPlayers("A", "B").build();

    // Moves coin index 5 to position 8:
    model.move(5, 8);

    // Moves coin index 4 to position 6:
    model.move(4, 6);

    // Adds a player (where in the order is up to you):
    model.addPlayer("C", 1);

    // Moves coin index 0 to position 0:
    model.move(0 ,0);

    // Moves coin index 2 to position 3:
    model.move(2, 3);

    // DON'T CHANGE THIS:
    return model.toString();
  }

  public static String scenario4(String board) {
    // Creates a CoinGameModel with FOUR players from board:
    StrictCoinGameModel.Factory f = new StrictCoinGameModel.Factory();
    StrictCoinGameModel model = f.setBoard(board).setPlayers("A", "B", "C", "D").build();

    // Moves coin index 3 to position 4:
    model.move(3, 4);

    // DON'T CHANGE THIS:
    return model.toString();
  }

  public static boolean scenario5(String board) {
    // Creates a CoinGameModel with THREE players from board:
    StrictCoinGameModel.Factory f = new StrictCoinGameModel.Factory();
    StrictCoinGameModel model = f.setBoard(board).setPlayers("A", "B", "C").build();

    // Moves coin index 1 to position 6:
    model.move(1, 6);

    // Adds a player (where in the order is up to you):
    model.addPlayer("D", 0);

    // Adds another player (where in the order is up to you):
    model.addPlayer("E", 3);

    // Moves coin index 1 to position 1:
    model.move(1, 1);

    // Returns whether the game is over:
    return model.isGameOver();
  }
}
