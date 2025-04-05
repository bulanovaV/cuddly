import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class WordStatInput {
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
        Map<String, Integer> ans = new LinkedHashMap<>();
        try {
            MyScanner r = new MyScanner(input);
            try {
                while (r.hasNext(isOk)) {
                    String k = r.next(isOk).toLowerCase();
                    ans.put(k, ans.getOrDefault(k, 0) + 1);
                }
            } finally {
                r.close();
            }
            try {
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(output), "utf8"),
                        1024
                );
                try {
                    for (String key : ans.keySet()) {
                        out.write(key + " " + ans.get(key));
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