public class Sum {
    public static boolean isGood(String s, int i, int j){
        return Character.isDigit(s.charAt(j)) || s.charAt(j) == '-';
    }

    public static void main(String[] args) {
        int ans = 0;
        for (int i = 0; i < args.length; i++) {
            int l = -1;
            for (int j = 0; j < args[i].length(); j++) {
                if (isGood(args[i], i, j)) {
                    if (l == -1) {
                        l = j;
                    }
                } else {
                    if (l != -1) {
                        ans +=  Integer.parseInt(args[i].substring(l, j));
                        l = -1;
                    }
                }
            }
            if (l != -1) {
                ans += Integer.parseInt(args[i].substring(l));
            }
        }
        System.out.println(ans);
    }
}