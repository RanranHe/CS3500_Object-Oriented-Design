package cs3500.hw02;

/**
 * Created by Claire on 2015/10/7.
 */

/**
 * a strict Coin Game Model
 */
public class StrictCoinGameModel extends AbstractCoinGame {
    public StrictCoinGameModel(String board) {
       super(board);
    }

    @Override
    public void move(int coinIndex, int newPosition) {
        if (newPosition < 0 || newPosition >= boardSize()) {
            throw new CoinGameModel.IllegalMoveException(
                    "New position is out of bounds: " + newPosition);
        }
        if (coinIndex < 0 || coinIndex >= coins.size()) {
            throw new CoinGameModel.IllegalMoveException(
                    "Incorrect Coin: " + coinIndex);
        }
        if (isCoinAt(newPosition)) {
            throw new CoinGameModel.IllegalMoveException(
                    "Position " + newPosition +
                            " is already occupied by another coin");
        }

        if (coins.get(coinIndex) < newPosition) {
            throw new CoinGameModel.IllegalMoveException(
                    "Can't move coins to the right");
        }
        for (int i = coins.get(coinIndex) - 1; i > newPosition; i--) {
            if (isCoinAt(i)) {
                throw new CoinGameModel.IllegalMoveException(
                        "Can't move coin past other coin");
            }
        }
        playCoins(coinIndex, newPosition);
    }
}
