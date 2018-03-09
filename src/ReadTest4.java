// For more information on this performance test, see
// http://www.redgreencode.com/why-is-java-io-slow
//
// ReadTest3 is based on a discussion in the comments.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.util.regex.Pattern;

public class ReadTest4 {
    static long total = 0;

    private static final Pattern DELIMITER_PATTERN = Pattern.compile("\\^\\|\\^");

    private void ReadLines() {
        try {
            File file = new File(this.getClass().getResource("input.txt").getPath());
            FileInputStream fis = new FileInputStream(file);
            FileChannel inputChannel = fis.getChannel();
            int s = -1;
            ByteBuffer byteBuffer = ByteBuffer.allocate(10240);
            StringBuilder recordSB = new StringBuilder();
            while ((inputChannel.read(byteBuffer)) != -1) {
                byte b;
                byteBuffer.flip();
                while (byteBuffer.hasRemaining() && (b = byteBuffer.get()) != -1) {
                    if (b == 10) {
                        total += splitIntoFields(recordSB).length;
                        recordSB = new StringBuilder();
                    } else {
                        recordSB.append((char) b);
                    }
                }
                byteBuffer.clear();
            }
        } catch (Exception e) {
            System.err.println("Error");
        }

    }

    private String[] splitIntoFields(StringBuilder recordSB) {
        return DELIMITER_PATTERN.split(recordSB, 0);
    }

    public static void main(String args[]) {
        long startTime = System.nanoTime();
        new ReadTest4().ReadLines();
        long stopTime = System.nanoTime();
        System.err.println((stopTime - startTime));
        System.err.println(total);
    }
}
