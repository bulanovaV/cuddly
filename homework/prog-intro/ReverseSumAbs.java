import java.util.Scanner;
import java.util.Arrays;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class ReverseSumAbs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] ans = new int[1][1];
        int cntStr = 0;
        while (sc.hasNextLine()) {
            if (cntStr >= ans.length) {
                ans = Arrays.copyOf(ans, ans.length * 2);
            }
            ans[cntStr] = strSum(new Scanner(sc.nextLine()));
            cntStr++;
        }
        ans = Arrays.copyOf(ans, cntStr);
        int[] row = new int[ans.length];
        int mx = 0;
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                row[i] += abs(ans[i][j]);
            }
            mx = max(ans[i].length, mx);
        }
        int[] col = new int[mx];
        for (int i = 0; i < mx; i++) {
            for (int j = 0; j < ans.length; j++) {
                if (ans[j].length > i) {
                    col[i] += abs(ans[j][i]);
                }
            }
        }
        for (int i = 0; i < ans.length; i++) {
            if (ans[i].length > 0) {
                for (int j = 0; j < ans[i].length; j++) {
                    System.out.print(row[i] + col[j] - abs(ans[i][j]) + " ");
                }
            }
            System.out.println();
        }
    }

    private static int[] strSum(Scanner sc) {
        int[] a = new int[2];
        int len = 0;
        while (sc.hasNextInt()) {
            if (len >= a.length) {
                a = Arrays.copyOf(a, a.length * 2);
            }
            a[len] = sc.nextInt();
            len++;
        }
        return Arrays.copyOf(a, len);
    }
}