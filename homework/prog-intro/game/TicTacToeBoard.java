package game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board {
    private final Cell[][] field;
    private Cell turn;
    private final int n;
    private final int m;
    private final int k;
    private int Empty;
    private Map<Cell, Character> CELL_TO_SYMBOL = Map.of(
            Cell.X, 'X',
            Cell.O, '0',
            Cell.E, '.'
    );

    public int sizeRow() {
        return n;
    }

    public int sizeCol() {
        return m;
    }

    public TicTacToeBoard(int n, int m, int k) {
        field = new Cell[n][m];
        this.m = m;
        this.n = n;
        this.k = k;
        for (int i = 0; i < n; i++) {
            Arrays.fill(field[i], Cell.E);
        }
        turn = Cell.X;
        Empty = n * m;
    }

    @Override
    public Position getPosition() {
        return new AntiCheater(this);
    }

    @Override
    public Result makeMove(Move move) {
        try {
            final int col = move.getCol();
            final int row = move.getRow();
            if (!isValid(move)) {
                return Result.LOSE;
            }
            field[row][col] = move.getValue();
            Empty--;
            int cnt = 1;
            if (cnt >= k) {
                return Result.WIN;
            }
            if (checkLineUp(row, col, n) == Result.WIN) {
                return Result.WIN;
            }
            if (checkLineLeft(col, row, m) == Result.WIN) {
                return Result.WIN;
            }
            if (checkDiagRight(row, col) == Result.WIN) {
                return Result.WIN;
            }
            if (checkDiagLeft(row, col) == Result.WIN) {
                return Result.WIN;
            }
            if (turn == Cell.X) {
                turn = Cell.O;
            } else {
                turn = Cell.X;
            }
            if (Empty == 0) {
                return Result.DRAW;
            } else {
                return Result.UNKNOWN;
            }
        } catch (Exception e) {
            System.out.println(e);
            return Result.LOSE;
        }
    }

    private Result checkLineLeft(int col, int row, int m) {
        int cnt = 1;
        int c = col - 1;
        while (c >= 0 && field[row][c] == turn) {
            cnt++;
            c--;
        }
        c = col + 1;
        while (c < m && field[row][c] == turn) {
            cnt++;
            c++;
        }
        if (cnt >= k) {
            return Result.WIN;
        }
        return Result.UNKNOWN;
    }

    @Override
    public int getRow() {
        return n - 1;
    }

    @Override
    public int getCol() {
        return m - 1;
    }

    @Override
    public Board сlone() {
        return new TicTacToeBoard(n, m, k);
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    public boolean isValid(Move move) {
        final int col = move.getCol();
        final int row = move.getRow();
        return 0 <= row && row < n &&
                0 <= col && col < m &&
                field[row][col] == Cell.E &&
                move.getValue() == turn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" |");
        for (int c = 0; c < m; c++) {
            sb.append((char) c + 1);
        }
        sb.append("\n +");
        for (int c = 0; c < m; c++) {
            sb.append('-');
        }
        sb.append("\n");
        for (int r = 0; r < n; r++) {
            sb.append(r + 1).append("|");
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_SYMBOL.get(cell));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Cell getCell(int row, int col) {
        return field[row][col];
    }

    public Result checkLineUp(int x, int y, int len) {
        int cnt = 1;
        for (int i = x - 1; i >= 0; i--) {
            if (field[i][y] == turn) {
                cnt++;
            } else {
                break;
            }
            if (cnt >= k) {
                return Result.WIN;
            }
        }
        for (int j = x + 1; j < len; j++) {
            if (field[j][y] == turn) {
                cnt++;
            } else {
                break;
            }
            if (cnt >= k) {
                return Result.WIN;
            }
        }
        return Result.UNKNOWN;
    }

    public Result checkDiagRight(int row, int col) {
        int cnt = 1;
        int c = col + 1;
        int r = row - 1;
        while (r >= 0 && c < m) {
            if (field[r][c] == turn) {
                cnt += 1;
            }
            r--;
            c++;
            if (cnt >= k) {
                return Result.WIN;
            }
        }
        c = col - 1;
        r = row + 1;
        while (c >= 0 && r < n) {
            if (field[r][c] == turn) {
                cnt++;
            } else {
                break;
            }
            r++;
            c--;
            if (cnt >= k) {
                return Result.WIN;
            }
        }
        return Result.UNKNOWN;
    }

    public Result checkDiagLeft(int row, int col) {
        int cnt = 1;
        int c = col - 1;
        int r = row - 1;
        while (c >= 0 && r >= 0) {
            if (field[r][c] == turn) {
                cnt++;
            } else {
                break;
            }
            r--;
            c--;
            if (cnt >= k) {
                return Result.WIN;
            }
        }
        c = col + 1;
        r = row + 1;
        while (c < m && r < n) {
            if (field[r][c] == turn) {
                cnt++;
            } else {
                break;
            }
            r++;
            c++;
            if (cnt >= k) {
                return Result.WIN;
            }
        }
        return Result.UNKNOWN;
    }
}
