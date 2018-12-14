package cs3500.hw03;


public class Player {

  private static int playerCount = 0;
  private int ID;
  private String name;

  public Player(String name) {
    playerCount++;
    this.ID = playerCount;
    this.name = name;
  }

  public int getID() {
    return ID;
  }

  public String getName() {
    return name;
  }

  static public int getPlayerCount() {
    return playerCount;
  }
}
