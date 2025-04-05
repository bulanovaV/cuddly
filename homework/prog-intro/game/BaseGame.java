package game;

import java.util.Scanner;

public class BaseGame {
    // final
    private static Scanner sc = new Scanner(System.in);

    public BaseGame() {
        int f; // gameType
        // choose desk
        while (true) {
            try {
                System.out.println("Выберите доску для игры: (1, 2) \n 1. MNK board \n 2. Ромб NxN");
                f = sc.nextInt();
                if (f == 1 || f == 2) {
                    break;
                } else {
                    System.out.println("Вы ввели невалидные данные, попробуйте еще раз.");
                }
            } catch (Exception e) {
                System.out.println("Вы ввели невалидные данные, попробуйте еще раз.");
            }
        }
        // choose size
        int n = 0;
        int m = 0;
        if (f == 1) {
            while (true) {
                System.out.println("Введите размер вашего поля.(N M)");
                try {
                    Scanner sc = new Scanner(System.in);
                    n = sc.nextInt();
                    m = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Вы ввели невалидные данные, попробуйте еще раз.");
                }
            }
        } else if (f == 2) {
            while (true) {
                System.out.println("Введите размер вашего поля.(N)");
                try {
                    Scanner sc = new Scanner(System.in);
                    n = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Вы ввели невалидные данные, попробуйте еще раз.");
                }
            }
        }
        // choose k
        int k;
        while (true) {
            System.out.println("Введите значения необходимые для победы.");
            try {
                k = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Вы ввели невалидные данные, попробуйте еще раз.");
            }
        }
        // choose p
        int p;
        while (true) {
            System.out.println("Введите количество полуходов, которые необходимо сделать в центральном прямоугольнике, \n размера  (a + 2 i)×(b + 2 i), где i — номер полухода, a = − m % 2, b = − n % 2.");
            try {
                p = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Вы ввели невалидные данные, попробуйте еще раз.");
            }
        }
        // choose players
        Player player1 = null;
        Player player2 = null;
        while (true) {
            System.out.println("Выберите игроков");
            while (true) {
                System.out.println("Игрок 1: \n 1. HumanPlayer \n 2. RandomPlayer");
                int pl = sc.nextInt();
                if (pl == 1) {
                    player1 = new HumanPlayer();
                    break;
                } else if (pl == 2) {
                    player1 = new RandomPlayer();
                    break;
                } else {
                    System.out.println("Вы ввели невалидные данные, попробуйте еще раз.");
                }
            }
            while (true) {
                System.out.println("Игрок 2: \n 1. HumanPlayer \n 2. RandomPlayer");
                int pl = sc.nextInt();
                if (pl == 1) {
                    player2 = new HumanPlayer();
                    break;
                } else if (pl == 2) {
                    player2 = new RandomPlayer();
                    break;
                } else {
                    System.out.println("Вы ввели невалидные данные, попробуйте еще раз.");
                }
            }
            break;
        }
        Board b = null;
        if (f == 1) {
            b = new TicTacToeBoard(n, m, k);
        } else if (f == 2) {
            b = new PhombusBoard(n, k);
        }
        System.out.println("Result: " + new Game(player1, player2, p).play(b));
    }

}
