import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExampleUsage {
    public static void main(String[] args){
        String exampleText = FileReader("example_text.txt");
        WordCounter wc = new WordCounter(exampleText);
        wc.Output();
        String bible = FileReader("bible_daily.txt");
        WordCounter wcBible = new WordCounter(bible);
        wcBible.Output();
    }
    
    private static String FileReader(String fileName){
        File file = new File("src/main/resources/"+fileName);
        String data = "";
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
}
