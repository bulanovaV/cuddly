package game;

public class Game {
    // final
    private static Player player1;
    private static Player player2;
    private static int p;
    private static boolean log1;
    private static boolean log2;
    private static int cntP;


    public Game(Player player1, Player player2, int p) {
        this.player1 = player1;
        this.player2 = player2;
        this.p = p;
        this.cntP = 0;
    }

    public static int makeMove(Board board, Player player, int no) {
        Move move;
        while (true) {
            try {
                move = player.makeMove(board.getPosition());
                if (cntP >= p || (move.getRow() <= board.getRow() / 2 + cntP && move.getRow() >= board.getRow() / 2 - cntP && move.getCol() <= board.getCol() / 2 + cntP && move.getCol() >= board.getCol() / 2 - cntP)) {
                    break;
                } else {
                    if (player.getLog()) {
                        System.out.println("Вы ввели невалидные координаты относительно полухода попробуйте еще раз.");
                    }
                }
            } catch (Exception e) {
                return 3 - no;
            }
        }
        final Result result = board.makeMove(move);
        if (player1.getLog() || player2.getLog()) {
            System.out.println();
            System.out.println("Игрок " + move.getValue() + " ходит на (" + move.getRow() + ", " + move.getCol() + ")");
            System.out.println(board);
        }
        switch (result) {
            case LOSE -> {
                return 3 - no;
            }
            case WIN -> {
                return no;
            }
            case DRAW -> {
                return 0;
            }
            case UNKNOWN -> {
                return -1;
            }
            default -> throw new AssertionError("Unknown Result");
        }
    }

    public int play(Board board) {
        if (player1.getLog() || player2.getLog()) {
            System.out.println(board);
        }
        while (true) {
            int result1 = makeMove(board, player1, 1);
            if (result1 >= 0) {
                return result1;
            }
            cntP++;
            int result2 = makeMove(board, player2, 2);
            if (result2 >= 0) {
                return result2;
            }
            cntP++;
        }
    }
}
