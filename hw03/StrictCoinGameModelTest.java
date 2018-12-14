package cs3500.hw03;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Claire on 2015/10/18.
 */

/**
 * Test methods in StrictCoinGameModel
 */
public class StrictCoinGameModelTest {
  StrictCoinGameModel.Factory f = new StrictCoinGameModel.Factory();

  @Test
  public void AGameTest() {
    StrictCoinGameModel game1 = f.setBoard("----OO-O0-").setPlayers("A", "B", "C").build();
    game1.addPlayer("D");
    game1.addPlayer("E");
    assertEquals(game1.getMaxPlayers(), 5);
    assertEquals(game1.currentPlayer(), new Player("A"));
    game1.addPlayer("F", 0);
    assertEquals(game1.currentPlayer(), new Player("F"));
    game1.move(0, 0);
    assertEquals(game1.currentPlayer(), new Player("A"));
    game1.move(1, 1);
    assertEquals(game1.currentPlayer(), new Player("B"));
    try {
      game1.getWinner();
    } catch(IllegalStateException e) {
      assertEquals("Game is not over!", e.getMessage());
    }
    game1.addPlayer("G", 4);
    try {
      game1.addPlayer("I", 11);
    } catch (IllegalArgumentException e) {
      assertEquals("Wrong position", e.getMessage());
    }
    game1.move(2, 2);
    assertEquals(game1.currentPlayer(), new Player("C"));
    game1.move(3, 3);
    assertEquals(game1.currentPlayer(), new Player("G"));
    assertEquals(game1.getWinner(), new Player("G"));
    try {
      game1.addPlayer("H");
    } catch(IllegalStateException e) {
      assertEquals("Game over", e.getMessage());
    }
    try {
      game1.addPlayer("H", 3);
    } catch(IllegalStateException e) {
      assertEquals("Game over", e.getMessage());
    }
  }
  @Test
  public void testScenario3() {
    assertEquals(CoinGameTestScenarios.scenario3("-OO-OO---OO"), "O-OO-OO-O--");
  }

  @Test
  public void testScenario3Bad() {
    try {
      CoinGameTestScenarios.scenario3("-OO-OO--O-O");
    } catch (CoinGameModel.IllegalMoveException e) {
      assertEquals("the position is occupied", e.getMessage());
    }
  }

  @Test
  public void testScenario4() {
    assertEquals(CoinGameTestScenarios.scenario4("OOO--O"), "OOO-O-");
  }

  @Test
  public void testScenario4Bad() {
    try {
      CoinGameTestScenarios.scenario5("OOO--O");
    } catch (CoinGameModel.IllegalMoveException e) {
      assertEquals("Can't cross other coin", e.getMessage());
    }
  }

  @Test
  public void testScenario5() {
    assertEquals(CoinGameTestScenarios.scenario5("O------O--"), true);
  }

  @Test
  public void testScenario5Bad() {
    try {
      CoinGameTestScenarios.scenario5("--O----O");
    } catch (CoinGameModel.IllegalMoveException e) {
      assertEquals("Can't cross other coin", e.getMessage());
    }
  }
}