import java.util.ArrayList;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<String>> n = new ArrayList<>();
        while (scanner.hasNextLine()) {
            ArrayList<String> res = new ArrayList<>();
            String line = scanner.nextLine();
            int i = 0;
            while (i < line.length()) {
                if (line.charAt(i) == ' ') {
                    i++;
                    continue;
                }
                int start = i;
                while (i < line.length() && line.charAt(i) != ' ') {
                    i++;
                }
                res.add(line.substring(start, i));
            }
            n.add(res);
        }
        for (int i = n.size() - 1; i >= 0; i--) {
            for (int j = n.get(i).size() - 1; j > -1; j--) {
                System.out.print(n.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}