package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private Random random = new Random();

    @Override
    public Move makeMove(Position position) {
        while (true) {
            int row = random.nextInt(position.sizeRow());
            int col = random.nextInt(position.sizeCol());
            Move move = new Move(row, col, position.getTurn());
            if (position.isValid(move)) {
                return move;
            }
        }
    }

    @Override
    public boolean getLog() {
        return false;
    }
}
