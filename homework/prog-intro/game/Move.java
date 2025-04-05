package game;

public class Move {
    private final int row;
    private final int col;
    Cell value;

    public Move(int row, int col, Cell value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell getValue() {
        return value;
    }

    @Override
    public String toString() {
        return (row + 1) + ", " + (col + 1) + ", " + value;
    }
}
