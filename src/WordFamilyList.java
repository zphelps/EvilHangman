import java.util.ArrayList;

public class WordFamilyList {
    private ArrayList<WordFamily> wordFamilies;
    private String currentWordFamily;

    public WordFamilyList(int wordLength) {
        wordFamilies = new ArrayList<WordFamily>();
        currentWordFamily = "";
        for(int i = 0; i < wordLength; i++) {
            currentWordFamily += "-";
        }
    }

    public void evaluate(String letter, String word) {
        String id = "";
        for(int i = 0; i < word.length(); i++) {
            if(word.substring(i, i+1).equals(letter)) {
                id = id + letter;
            }
            else {
                id = id + "-";
            }
        }
        if(doesWordFamilyExist(id)) {
            for(WordFamily wordFamily : wordFamilies) {
                if(wordFamily.getFamilyID().equals(id)) {
                    wordFamily.addToFamily(word);
                }
            }
        }
        else {
            wordFamilies.add(new WordFamily(id, word));
        }
    }

    public boolean doesWordFamilyExist(String id) {
        for(WordFamily wordFamily : wordFamilies) {
            if(wordFamily.getFamilyID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String getCurrentWordFamily() {
        return currentWordFamily;
    }

    public void updateCurrentWordFamily(String newWordFamily) {
        for(int i = 0; i < newWordFamily.length(); i++) {
            if(!newWordFamily.substring(i, i+1).equals("-")) {
                currentWordFamily = currentWordFamily.substring(0, i) + newWordFamily.substring(i, i+1) + currentWordFamily.substring(i + 1);
            }
        }
    }

    public ArrayList<String> getLargestWordFamily() {
        WordFamily max = wordFamilies.get(0);

        for(WordFamily wf : wordFamilies) {
            if (wf.getFamilySize() > max.getFamilySize()) {
                max = wf;
            }
            else if(wf.getFamilySize() == max.getFamilySize()) {
                if(max.getFamilyID().matches(".*[a-z].*")) {
                    max = wf;
                }
            }
        }

        updateCurrentWordFamily(max.getFamilyID());
        //currentWordFamily = max.getFamilyID();

        wordFamilies.clear();
        return max.getWordsInFamily();
    }
}
