package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Round {
    //    private int n;
//    private int m;
//    private int k;
    private List<Player> player;
    //    private int human;
//    private int bot;
    private int p;
    private List<Integer> winners = new ArrayList<>();
    private List<Integer> numbers, result;
    private Board board;

    public Round(int cnt, int n, int m, int k, int p, List<Player> player, List<Integer> numbers, Board board) {
//        this.human = cnt;
//        this.n = n;
//        this.m = m;
//        this.k = k;
        this.p = p;
        this.player = player;
        this.numbers = numbers;
        this.board = board;
    }

    // List<List<...>>
    public List<Integer> play(List<ArrayList<Integer>> tableOfResults) {
        tableOfResults.add(new ArrayList<>());
        Random rn = new Random();
        int player1;
        int player2;
        for (int j = 0; j < numbers.size() - 1 - numbers.size() % 2; j += 2) {
            // choose symbol
            boolean choice = rn.nextBoolean();
            if (choice) {
                player1 = j;
                player2 = j + 1;
            } else {
                player1 = j + 1;
                player2 = j;
            }
            // play game
            System.out.println("Игра между: " + numbers.get(player1) + " - 'X' | " + numbers.get(player2) + " - 'O'");
            Game game = new Game(player.get(numbers.get(player1) - 1), player.get(numbers.get(player2) - 1), p);
            int result = -1;
            if (choice) {
                while (result < 1) {
                    result = game.play(board.сlone());
                }
            } else {
                while (result < 1) {
                    result = 3 - game.play(board.сlone());
                }
            }
            // results
            if ((j + result - 1) == j + 1) {
                tableOfResults.getLast().add(numbers.get(j));
            } else {
                tableOfResults.getLast().add(numbers.get(j + 1));
            }
            System.out.println("Победил: " + numbers.get(j + result - 1));
            System.out.println();
            winners.add(numbers.get(j + result - 1));
        }
        if (numbers.size() % 2 != 0) {
            System.out.println("Автоматическая победа у игрока: " + numbers.getLast());
            winners.add(numbers.getLast());
        }
        return winners;
    }

}
