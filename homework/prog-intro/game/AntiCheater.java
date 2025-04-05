package game;

public class AntiCheater implements Position{
    private static Board board; // final

    public AntiCheater(Board board) {
        this.board = board;
    }

    @Override
    public Cell getCell(int row, int col) {
        return board.getCell(row, col);
    }

    @Override
    public Cell getTurn() {
        return board.getTurn();
    }

    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public int sizeRow() {
        return board.sizeRow();
    }

    @Override
    public int sizeCol() {
        return board.sizeCol();
    }
}
