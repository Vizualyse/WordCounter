import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class WordCounterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @org.junit.Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @org.junit.After
    public void tearDown() throws Exception {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void tokenize() {
        WordCounter wc = new WordCounter("Hello world & good morning. The date is 18/05/2016");
        ArrayList<String> methodReturn = wc.Tokenize();
        ArrayList<String> testArrayList = new ArrayList<>(Arrays.asList("Hello", "world", "&", "good", "morning", "The", "date", "is", "18/05/2016"));
        Assert.assertEquals(testArrayList, methodReturn);
    }

    @Test
    public void count() {
        WordCounter wc = new WordCounter("Hello world & good morning. The date is 18/05/2016");
        int[] methodReturn = wc.Count();
        int[] testArray = new int[]{0, 1, 1, 1, 2, 2, 0, 1, 0, 0, 1};
        Assert.assertArrayEquals(testArray, methodReturn);
    }

    @Test
    public void checkParams() {
        WordCounter wc = new WordCounter("Hello world & good morning. The date is 18/05/2016");
        wc.Tokenize();
        Assert.assertEquals(wc.getWordCount(), 9);
        Assert.assertEquals(wc.getLongestWord(), 10);
    }

    @Test
    public void output() {
        WordCounter wc = new WordCounter("Hello world & good morning. The date is 18/05/2016");
        wc.Output();
        Assert.assertEquals(outContent.toString(), "Word count = 9\n" +
                "Average word length = 4.556\n" +
                "Number of words of length 1 is 1\n" +
                "Number of words of length 2 is 1\n" +
                "Number of words of length 3 is 1\n" +
                "Number of words of length 4 is 2\n" +
                "Number of words of length 5 is 2\n" +
                "Number of words of length 7 is 1\n" +
                "Number of words of length 10 is 1\n" +
                "The most frequently occurring word length is 2, for word lengths of 4 & 5\n");
    }

}