import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class ReverseMaxAbsOctDec {

    public static void main(String[] args) {
        MyScanner r = new MyScanner(System.in);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        int lin = 0;
        int col = 0;
        int[] str = new int[10000000];
        Arrays.fill(str, 0);
        int[] stol = new int[10000000];
        Arrays.fill(stol, 0);
        int ind = 0;
        Predicate<Character> isGood = ch -> {
            return Character.isDigit(ch) || (ch == '-') || (ch == 'o') || (ch == 'O');
        };
        try {
            while (r.hasNext(isGood)) {
                int newStr = r.newStr();
                if (newStr > 0) {
                    col = 0;
                    for (int i = 0; i < newStr; i++) {
                        ind++;
                        lin++;
                        ans.add(new ArrayList<>());
                    }
                }
                int res = r.nextInt(isGood);
                ans.get(ind).add(res);
                if (Math.abs(res) > Math.abs(stol[col])) {
                    stol[col] = res;
                }
                if (Math.abs(res) > Math.abs(str[lin])) {
                    str[lin] = res;
                }
                col++;

            }
            int newStr = r.newStr();
            if (newStr > 1) {
                for (int i = 0; i < newStr - 1; i++) {
                    ind++;
                    ans.add(new ArrayList<>());
                }
            }
            for (int i = 0; i < ans.size(); i++) {
                for (int j = 0; j < ans.get(i).size(); j++) {
                    if (Math.abs(stol[j]) > Math.abs(str[i])) {
                        System.out.print(Integer.toOctalString(stol[j]) + "o ");
                    } else {
                        System.out.print(Integer.toOctalString(str[i]) + "o ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Problem: " + e);
        }
    }
}