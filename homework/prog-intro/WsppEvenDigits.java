import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class WsppEvenDigits {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Error invalid number of arguments.");
            return;
        }
        String input = args[0];
        String output = args[1];
        Predicate<Character> isOk = ch ->
                Character.isDigit(ch) ||
                        Character.getType(ch) == Character.DASH_PUNCTUATION ||
                        ch == '\'' ||
                        Character.isLetter(ch);

        Map<String, IntList> ans = new LinkedHashMap<>();
        try {
            MyScanner r = new MyScanner(input);
            try {
                int ind = 0;
                while (r.hasNext(isOk)) {
                    int newStr = r.newStr();
                    if (newStr > 0) {
                        ind = 0;
                        for (String key : ans.keySet()) {
                            ans.get(key).add(0);
                        }
                    }
                    ind++;
                    String k = r.next(isOk).toLowerCase();
                    IntList a = ans.getOrDefault(k, new IntList());
                    a.add(ind);
                    ans.put(k, a);
                }
            } finally {
                r.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading in file: " + e.getMessage());
        }
        try {
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8),
                    1024 // to const
            );
            try {
                for (Map.Entry<String, IntList> ent : ans.entrySet()) {
                    int ind = 0;
                    out.write(ent.getKey() + " " + (ent.getValue().size() - ent.getValue().cntStr()));
                    for (int i = 0; i < ent.getValue().size(); i++) {
                        if (ent.getValue().get(i) == 0) {
                            ind = 0;
                        } else if ((ind + 1) % 2 == 0) {
                            ind++;
                            out.write(" " + ent.getValue().get(i));
                        } else {
                            ind++;
                        }
                    }
                    out.newLine();
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            System.err.println("Error writing in file: " + e.getMessage());
        }
    }
}
