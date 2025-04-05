import java.io.*;
import java.util.function.Predicate;

public class MyScanner {
    private Reader reader;
    private char[] buff = new char[1024];
    private int len = 0;
    private int ind = 0;
    private String res = null;
    private boolean newstr = false;
    private int cnt = 0;
    private boolean oct = false;
    private char[] sep = System.lineSeparator().toCharArray();

    public MyScanner(InputStream s) {
        this.reader = new InputStreamReader(s);
    }

    public MyScanner(String fileName) throws IOException {
        this.reader = new InputStreamReader(new FileInputStream(fileName), "utf8");
    }

    public boolean hasNext(Predicate<Character> condition) throws IOException {
        res = null;
        res = next(condition);
        return res != null;
    }

    public boolean isLineSep(char m) throws IOException {
        if (sep.length == 1 && m == sep[0]){
            return true;
        }
        if (sep.length > 1 && m == sep[1]) {
            for (int i = 2; i < sep.length; i++) {
                update();
                if (buff[ind] != sep[i]) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public String octacal(String str) {
        char[] charArray = str.toCharArray();
        int ans = 0;
        for (int i = 0; i < charArray.length - 1; i++) {
            ans <<= 3;
            ans += charArray[i] - '0';
        }
        return Integer.toString(ans);
    }

    public static boolean check(char number, Predicate<Character> condition) {
        return condition.test(number);
    }

    public String next(Predicate<Character> condition) throws IOException {
        if (res != null) {
            String nres = res;
            res = null;
            return nres;
        }
        StringBuilder sb = new StringBuilder();
        create();
        while (ind < len) {
            while ((ind < len) && check(buff[ind], condition)) {
                if ((buff[ind] == 'o' || buff[ind] == 'O')) {
                    oct = true;
                }
                sb.append(buff[ind]);
                update();
            }
            if (!sb.isEmpty()) {
                return sb.toString();
            }
            if (isLineSep(buff[ind])) {
                newstr = true;
                cnt += 1;
            }
//            if (buff[ind] == '\n'){
//                newstr = true;
//                cnt += 1;
//            }

            update();
        }
        return null;
    }

    public int nextInt(Predicate<Character> condition) throws IOException {
        String result = next(condition);
        if (oct) {
            oct = false;
            result = octacal(result);
        }
        return Integer.parseInt(result);
    }

    public void create() throws IOException {
        if (len == 0) {
            len = reader.read(buff);
        }
    }

    public void update() throws IOException {
        if (ind == len - 1) {
            len = reader.read(buff);
            ind = -1;
        }
        ind++;
    }

    public int newStr() {
        if (newstr) {
            newstr = false;
            int ncnt = cnt;
            cnt = 0;
            return ncnt;
        }
        return 0;
    }

    public void close() throws IOException {
        reader.close();
    }
}