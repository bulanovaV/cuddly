import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class Wspp {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Error invalid number of arguments.");
        }

        String input = args[0];
        String output = args[1];
        Predicate<Character> isOk = ch -> {
            return (Character.getType(ch) == (Character.DASH_PUNCTUATION) ||
                    ch == '\'' ||
                    Character.isLetter(ch));
        };
        Map<String, IntList> ans = new LinkedHashMap<>();
        try {
            MyScanner r = new MyScanner(input);
            try {
                int ind = 0;
                while (r.hasNext(isOk)) {
                    ind++;
                    String k = r.next(isOk).toLowerCase();
                    IntList a = ans.getOrDefault(k, new IntList());
                    a.add(ind);
                    ans.put(k, a);
                }
            } catch (NullPointerException e) {
                System.err.println("NullPointerException: " + e.getMessage());
            } finally {
                r.close();
            }
            try {
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(output), "utf8"),
                        1024
                );
                try {
                    for (Map.Entry<String, IntList> ent : ans.entrySet()) {
                        out.write(ent.getKey() + " " + ent.getValue().size());
                        for (int i = 0; i < ent.getValue().size(); i++) {
                            out.write(" " + ent.getValue().get(i));
                        }
                        out.newLine();
                    }
                } finally {
                    out.close();
                }
            } catch (IOException e) {
                System.err.println("Error writing in file.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (IOException e) {
            System.err.println("Error reading in file.");
        }
    }
}