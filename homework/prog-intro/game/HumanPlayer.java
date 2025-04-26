package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    private PrintStream out;

    private final boolean log = false;

    public HumanPlayer(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public HumanPlayer() {
        this(new Scanner(System.in), System.out);
    }

    @Override
    public Move makeMove(Position position) {
        while (true) {
            out.println("Enter row and column: ");
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            Move move = new Move(row, col, position.getTurn());
            if (position.isValid(move)) {
                return move;
            } else {
                System.out.println("The input is not valid");
            }
        }

    }

    @Override
    public boolean getLog() {
        return true;
    }
}
