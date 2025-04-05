package game;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("Вы хотите сыграть обычную партию?(Yes/No)");
            String ans = sc.nextLine();
            if (ans.equals("Yes")) {
                new BaseGame();
                break;
            } else if (ans.equals("No")) {
                break;
            } else {
                System.out.println("Неккоректный ввод");
            }
        }
        while (true){
            System.out.println("Вы хотите сыграть турнир?(Yes/No)");
            String ans = sc.nextLine();
            if (ans.equals("Yes")) {
                new Tournament();
                break;
            } else if (ans.equals("No")) {
                break;
            } else {
                System.out.println("Неккоректный ввод");
            }
        }
        sc.close();
    }
}