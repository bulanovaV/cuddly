package search;

public class BinarySearch {
    // P: for i, j in [0, a.length): i < j && a[i] >= a[j]
    // Q: R : (for i in [0, R] : a[i] > x) && (for j in (R, a.length) : a[j] <= x)
    private static int iterativeBS(int x, long[] a) {
        // P1: P
        int left = -1;
        int right = a.length;
        // Q1: (left < right) && (left == -1) && (right == a.length)

        // Inv: (-1 <= left) && (right <= a.length) && (left < right) && (a[left] > x) && (x <= a[right])
        while (left < right - 1) {

            // Not inf:
            // a.length == 0 -> left == -1 && right == 0 -> right - left == 1
            // a.length > 0 -> t times do : (right - left) // 2, while a[right - left].length > 1

            // P2: Inv && left < right - 1
            int mid = (left + right) / 2;
            // Q2: P2 && (mid = (left + right) // 2) && (left < mid < right)
            // P3: Q2
            if (a[mid] > x) {

                // P4: P3 && (a[mid] > x)
                left = mid;
                // Q4: P4 && (a[left] > x)

            } else {

                // P5: P3 && (a[mid] <= x)
                right = mid;
                // Q5: P5 && (a[right] <= x)

            }
        }

        // P6: P && (right - left <= 1) && (a[right] <= x) && (x < a[left])
        return right;
        // Q6: left = right - 1 ==> left <= left < R <= right ==> right - 1 < R <= right ===> R = right -> is ans
    }

    // P : (for i, j in [0, a.length): i < j && a[i] >= a[j]) && -1 <= left && right <= a.length && left < right
    // Q : R : (for i in [0, R] : a[i] > x) && (for j in (R, a.length) : a[j] <= x)
    private static int recursiveBS(long[] a, int left, int right, int x) {

        // P0 : P
        if (left > right) {
            // P1 : P0 && left > right
            return left;
            // Q1 : left > right => right = left - 1  ==> right < R <= left ==> left - 1 < R <= left ==>
            // left = R - is ans
        }

        // P2 : P0 && right >= left
        int mid = (left + right) / 2;
        // Q2 : P2 && mid = (left + right) / 2

        // P3 : Q2
        if (a[mid] <= x) {

            // P4 : P2 && a[mid] <= x
            return recursiveBS(a, left, mid - 1, x);
            // Q4 : Q

        } else {

            // P5 : P2 && a[mid] > x
            return recursiveBS(a, mid + 1, right, x);
            // Q5 : Q
        }
    }

    public static void main(String[] args) {

        // P0 : args.length > 1 && args[0] == int
        int x = Integer.parseInt(args[0]);
        // Q0 : P && x = int(args[i])

        // P1 : args.length > 0
        long[] a = new long[args.length - 1];
        // Q1 : P1 && a = [...] && a.length + 1 == args.length

        // Inv : i < args.length
        for (int i = 1; i < args.length; i++) {

            // P2 : Inv && P1 : args[i] == int
            a[i - 1] = Integer.parseInt(args[i]);
            // Q2 : P2 && a[i - 1] == int(args[i])

        }

        // P3 : for i, j in [0, a.length): i < j && a[i] >= a[j]
        int iterativeR = iterativeBS(x, a);
        // Q3 : iterativeR : (for i in [0, R] : a[i] > x) && (for j in (R, a.length) : a[j] <= x)

        // P4 : (for i, j in [0, a.length): i < j && a[i] >= a[j]) && -1 <= left && right <= a.length && left < right
        int recursiveR = recursiveBS(a, 0, a.length - 1, x);
        // Q4 : recursiveR : (for i in [0, R] : a[i] > x) && (for j in (R, a.length) : a[j] <= x)

        System.out.println(recursiveR);
    }
}