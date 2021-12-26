import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class WordCounter {
    private String input;
    private int wordCount = 0;
    private int longestWord = 0;
    private int[] wordLengths;
    private ArrayList<String> inputArray;

    public WordCounter(String input){   //construct object with input string
        this.input = input;
        Count();
    }

    /**
     * Breaks full text into array of strings based on assumptions on what constitutes a word
     */
    private void Tokenize(){
        inputArray = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(input, " .");      //break tokens at space or .
        while(st.hasMoreTokens()){
            String token = st.nextToken();
            wordCount++;
            longestWord = token.length()>longestWord?token.length():longestWord;
            inputArray.add(token);
        }
    }

    /**
     * Runs through an ArrayList of Strings and counts the word lengths
     */
    private void Count(){
        Tokenize();
        wordLengths = new int[longestWord+1];
        inputArray.forEach(word -> { wordLengths[word.length()]++; });      //increment counter (array index = word length)
    }

    /**
     * Writes a formatted string to console giving details about this object
     */
    public void Output(){
        NumberFormat formatter = new DecimalFormat(".###");     //3 decimal places

        float averageWordLength = 0;
        for(int i = 1; i < wordLengths.length; i++)
            averageWordLength += i*wordLengths[i];      //count all letters
        averageWordLength /= wordCount;          //divide by word count

        String output = "Word count = " + wordCount + "\n";
        output += "Average word length = " + formatter.format(averageWordLength) + "\n";    //format decimal places

        int mostCommonWordLength = 0;
        ArrayList<Integer> mostCommonWords = new ArrayList<>();
        for(int i = 1; i < wordLengths.length; i++) {
            if (wordLengths[i] > 0)
                output += "Number of words of length " + i + " is " + wordLengths[i] + "\n";
            if(wordLengths[i] == mostCommonWordLength){                 //if matching commonality add it to list
                mostCommonWords.add(i);
            } else if (wordLengths[i] > mostCommonWordLength) {         //if new most common, clear list
                mostCommonWordLength = wordLengths[i];
                mostCommonWords = new ArrayList<>();
                mostCommonWords.add(i);
            }
        }
        output += "The most frequently occurring word length is " + mostCommonWordLength + ", for word lengths of " + mostCommonWords.get(0);
        for(int i = 1; i < mostCommonWords.size(); i++)
            output += " & " + mostCommonWords.get(i);               //add list of word lengths
        output+="\n";                                               //println throws junit error \n + print doesn't
        System.out.print(output);
    }

    /**
     * @return no. of words in text
     */
    public int getWordCount() {
        return wordCount;
    }
    /**
     * @return longest word in text
     */
    public int getLongestWord() {
        return longestWord;
    }
    /**
     * @return arraylist containing each word
     */
    public ArrayList<String> getInputArray() {
        return inputArray;
    }
    /**
     * @return array with no. of occurrences for each word length
     */
    public int[] getWordLengths() {
        return wordLengths;
    }
}
