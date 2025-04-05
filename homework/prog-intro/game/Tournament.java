package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tournament {
    private int m;
    private int n;
    private int k;
    private int p;
    private Board board;

    // to main
    public Tournament() {
        Scanner sc;
        while (true) {
            try {
                sc = new Scanner(System.in);
                System.out.println("Выберите доску: (1, 2) \n 1. MNK board \n 2. Доска в форме ромба NxN (N)");
                int f = sc.nextInt();
                if (f == 1) {
                    System.out.println("Введите размер вашего игрового поля MxN. (M, N)");
                    this.m = sc.nextInt();
                    this.n = sc.nextInt();
                    System.out.println("Введите кол-во элементов K для выйгрыша. (K)");
                    this.k = sc.nextInt();
                    System.out.println("Введите количество полуходов, которые необходимо сделать в центральном прямоугольнике, \n размера  (a + 2 i)×(b + 2 i), где i — номер полухода, a = − M % 2, b = − N % 2. (P)");
                    this.p = sc.nextInt();
                    this.board = new TicTacToeBoard(n, m, k);
                    break;
                } else if (f == 2) {
                    System.out.println("Введите размер вашего игрового поля NxN. (N)");
                    this.n = sc.nextInt();
                    System.out.println("Введите кол-во элементов K для выйгрыша. (K)");
                    this.k = sc.nextInt();
                    System.out.println("Введите количество полуходов, которые необходимо сделать в центральном прямоугольнике, \n размера  (a + 2 i)×(b + 2 i), где i — номер полухода, a = − M % 2, b = − N % 2. (P)");
                    this.p = sc.nextInt();
                    this.board = new PhombusBoard(n, k);
                    break;
                } else {
                    System.out.println("Невалидные данные, попробуйте еще раз");
                }
            } catch (Exception e) {
                System.out.println("Невалидные данные, попробуйте еще раз");
            }
        }
        play();
        sc.close();
    }

    public void play() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                int human = 0;
                int bot = 0;
                while (true) {
                    try {
                        System.out.println("Введите кол-во игроков");
                        human = sc.nextInt();
                        System.out.println("Введите количество ботов");
                        bot = sc.nextInt();
                        break;
                    } catch (Exception e) {
                        System.out.println("Невалидные данные попробуйте еще раз");
                        sc.nextLine();
                    }
                }
                List<Player> players = new ArrayList<>();
                List<Integer> numbers = new ArrayList<>();
                for (int i = 0; i < human; i++) {
                    players.add(new HumanPlayer());
                    numbers.add(i + 1);
                }
                for (int j = 0; j < bot; j++) {
                    players.add(new RandomPlayer());
                    numbers.add(human + j + 1);
                }
                List<ArrayList<Integer>> results = new ArrayList<>();
                while (numbers.size() > 1) {
                    Round round = new Round(human, n, m, k, p, players, numbers, board);
                    if (numbers.size() == 2) {
                        System.out.println();
                        System.out.println("!!!!!!!!!Финальный раунд!!!!!!!!!");
                        System.out.println();
                    } else if (numbers.size() == human + bot) {
                        System.out.println();
                        System.out.println("!!!!!!!!!Первый раунд!!!!!!!!!");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("!!!!!!!!!Следующий раунд!!!!!!!!!");
                        System.out.println();
                    }
                    numbers = round.play(results);
                }
                System.out.println("!!!!!!!!!РЕЗУЛЬТАТЫ!!!!!!!!!");
                System.out.println();
                results.add(new ArrayList<>());
                results.getLast().add(numbers.getLast());
                for (int i = 0; i < results.size(); i++) {
                    System.out.print("Место " + (results.size() - i) + ":");
                    for (Integer num : results.get(i)) {
                        System.out.print(" " + num);
                    }
                    System.out.println();
                }
                sc.close();
                break;
            } catch (Exception e) {
                System.out.println("Невалидные данные, попробуйте еще раз.");
            }
        }
    }

}
