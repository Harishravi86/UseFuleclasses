// For more information on this performance test, see
// http://www.redgreencode.com/why-is-java-io-slow
//
// ReadTest3 is based on a discussion in the comments.

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.util.regex.Pattern;

public class ReadTest3 {
    static long total = 0;

    private static final Pattern DELIMITER_PATTERN = Pattern.compile("\\^|\\^");

    private void ReadLines() {
        try {
            File file = new File(this.getClass().getResource("input.txt").getPath());
            FileInputStream fis = new FileInputStream(file);
            FileChannel inputChannel = fis.getChannel();
            try (CustomBufferedReader stdin = new CustomBufferedReader(Channels.newReader(inputChannel,"UTF-8"))) {
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
        new ReadTest3().ReadLines();
        long stopTime = System.nanoTime();
        System.err.println((stopTime - startTime) / 1000000000);
        System.err.println(total);
    }
}
