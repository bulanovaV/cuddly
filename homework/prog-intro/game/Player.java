package game;

public interface Player {
    Move makeMove(Position position);
    boolean getLog();
}
