import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Dictionary {
    private ArrayList<String> wordList = new ArrayList<>();

    public Dictionary(String fileName) {
        // create File object associated with the dictionary file
        File myFile = new File(fileName);

        // Create a Scanner associated to the above file
        Scanner in = null;
        try {
            in = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Read all lines from the file
        while(in.hasNextLine()) {

            String line = in.nextLine();

            wordList.add(line);

        }
    }

    public boolean hasWordsOfLength(int wordLength) {
        for(String word : wordList) {
            if (word.length() == wordLength) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getWordsOfLength(int wordLength) {

        ArrayList<String> newList = new ArrayList<>();

        for(String word : wordList) {
            if(word.length() == wordLength) {
                newList.add(word);
            }
        }
        return newList;
    }

}
