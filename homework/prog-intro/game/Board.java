package game;

public interface Board {
    Position getPosition();

    Result makeMove(Move move);

    int getRow();

    int getCol();

    boolean isValid(Move move);

    Board сlone();

    Cell getCell(int row, int col);

    int sizeRow();

    int sizeCol();

    Cell getTurn();
}

