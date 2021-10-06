import java.util.ArrayList;

public class WordFamily {
    private String id;
    private ArrayList<String> wordsInFamily;

    public WordFamily(String id, String word) {
        this.id = id;
        wordsInFamily = new ArrayList<String>();
        addToFamily(word);
    }

    public void addToFamily(String word) {
        wordsInFamily.add(word);
    }

    public int getFamilySize() {
        return wordsInFamily.size();
    }

    public String getFamilyID() {
        return id;
    }

    public ArrayList<String> getWordsInFamily() {
        return wordsInFamily;
    }
}
