package game;

import java.util.Arrays;
import java.util.Map;

public class PhombusBoard implements Board {
    private final int n;
    private Cell[][] board;
    private Cell turn;
    private int empty = 0;
    private int k;
    private Map<Cell, Character> CELL_TO_SYMBOL = Map.of(
            Cell.N, '#',
            Cell.X, 'X',
            Cell.O, '0',
            Cell.E, '.'
    );

    public PhombusBoard(int n, int k) {
        this.board = new Cell[2 * n - 1][2 * n - 1];
        this.n = n;
        this.k = k;
        for (int i = 0; i < 2 * n - 1; i++) {
            Arrays.fill(board[i], Cell.N);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j + 1) >= n && (i + j + 1) <= 2 * n) {
                    board[i][j] = Cell.E;
                    board[2 * n - i - 2][2 * n - j - 2] = Cell.E;
                    board[2 * n - i - 2][j] = Cell.E;
                    board[i][2 * n - j - 2] = Cell.E;
                }
            }
        }
        turn = Cell.X;
    }


    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        final int col = move.getCol();
        final int row = move.getRow();
        board[row][col] = move.getValue();
        empty--;
        if (checkCol(move) >= k) {
            return Result.WIN;
        }
        if (checkRow(move) >= k) {
            return Result.WIN;
        }
        if (checkDiagRight(move) >= k) {
            return Result.WIN;
        }
        if (checkDiagLeft(move) >= k) {
            return Result.WIN;
        }
        if (turn == Cell.X) {
            turn = Cell.O;
        } else {
            turn = Cell.X;
        }

        if (empty == 0) {
            return Result.DRAW;
        } else {
            return Result.UNKNOWN;
        }
    }

    @Override
    public int getRow() {
        return 2 * n - 1;
    }

    @Override
    public int getCol() {
        return 2 * n - 1;
    }

    @Override // с -> c
    public Board сlone() {
        return new PhombusBoard(n, k);
    }


    // dcol drow
    private int checkCol(Move move) {
        final int col = move.getCol();
        final int row = move.getRow();
        int cnt = 1;
        int r = row - 1;
        while (r >= 0 && board[r][col] == turn) {
            cnt++;
            r--;
        }
        r = row + 1;
        while (r < 2 * n - 1 && board[r][col] == turn) {
            cnt++;
            r++;
        }
        return cnt;
    }

    private int checkRow(Move move) {
        final int col = move.getCol();
        final int row = move.getRow();
        int cnt = 1;
        int c = col - 1;
        while (c >= 0 && board[row][c] == turn) {
            cnt++;
            c--;
        }
        c = col + 1;
        while (c < 2 * n - 1 && board[row][c] == turn) {
            cnt++;
            c++;
        }
        return cnt;
    }

    private int checkDiagRight(Move move) {
        final int col = move.getCol();
        final int row = move.getRow();
        int cnt = 1;
        int r = row - 1;
        int c = col + 1;
        while (r >= 0 && c < 2 * n - 1 && board[r][c] == turn) {
            cnt++;
            r--;
            c++;
        }
        r = row + 1;
        c = col - 1;
        while (c >= 0 && r < 2 * n - 1 && board[r][c] == turn) {
            cnt++;
            r++;
            c--;
        }
        return cnt;
    }

    private int checkDiagLeft(Move move) {
        final int col = move.getCol();
        final int row = move.getRow();
        int cnt = 1;
        int r = row - 1;
        int c = col - 1;
        while (r >= 0 && c >= 0 && board[r][c] == turn) {
            cnt++;
            r--;
            c--;
        }
        r = row + 1;
        c = col + 1;
        while (c < 2 * n - 1 && r < 2 * n - 1 && board[r][c] == turn) {
            cnt++;
            r++;
            c++;
        }
        return cnt;
    }


    @Override
    public Cell getTurn() {
        return turn;
    }

    public boolean isValid(Move move) {
        final int col = move.getCol();
        final int row = move.getRow();
        return 0 <= row && row < 2 * n - 1 && 0 <= col && col < 2 * n - 1 &&
                board[row][col] == Cell.E &&
                move.getValue() == turn;
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    @Override
    public int sizeRow() {
        return 2 * n - 1;
    }

    @Override
    public int sizeCol() {
        return 2 * n - 1;
    }

    @Override
    public Position getPosition() {
        return new AntiCheater(this);
    }


    @Override
    public String toString() {
        StringBuilder form = new StringBuilder();
        form.append("%2s");
        form.append(" %2s".repeat(Math.max(0, 2 * n - 1)));
        form.append("\n");
        StringBuilder sb = new StringBuilder();
        String format = form.toString();
        String[] s = new String[2 * n];
        s[0] = " ";
        for (int i = 0; i < 2 * n - 1; i++) {
            s[i + 1] = String.valueOf(i + 1);
        }
        sb.append(String.format(format, s));
        for (int i = 0; i < 2 * n - 1; i++) {
            s = new String[2 * n];
            s[0] = String.valueOf(i + 1);
            for (int j = 0; j < 2 * n - 1; j++) {
                s[j + 1] = String.valueOf(CELL_TO_SYMBOL.get(board[i][j]));
            }
            sb.append(String.format(format, s));
        }
        return sb.toString();
    }
}
