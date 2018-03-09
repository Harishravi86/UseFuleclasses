// For more information on this performance test, see
// http://www.redgreencode.com/why-is-java-io-slow

import java.io.*;
import java.util.regex.Pattern;

public class ReadTest2 {
    static long total = 0;
    private static final Pattern DELIMITER_PATTERN = Pattern.compile("\\^|\\^");

    private void ReadLines() {
        try {
            File file = new File(this.getClass().getResource("input.txt").getPath());
            try (CustomBufferedReader stdin = new CustomBufferedReader(new FileReader(file))) {
                String s = null;
                while ((s = stdin.readLine()) != null) {
                    String[] fields = DELIMITER_PATTERN.split(s, 0);
                    total += fields.length;
                }

            } catch (Exception e) {
                System.err.println("Error");
            }
        } catch (
                Exception e)

        {
            System.err.println("Error");
        }

    }

    public static void main(String args[]) {
        long startTime = System.nanoTime();
        new ReadTest2().ReadLines();
        long stopTime = System.nanoTime();
        System.err.println((stopTime - startTime)/1000000000);
        System.err.println(total);
    }
}
