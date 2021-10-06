import java.util.ArrayList;
import java.util.Scanner;

//Fuck you David.

//But this is the Driver you should use. Don't use Driver02
public class Driver {
    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);

        Dictionary d1 = new Dictionary("dictionary.txt");
        boolean playAgain = false;
        boolean runningTotal = false;
        int guessesRemaining = 0;
        int currentGuess = 0;
        int wordLength;
        ArrayList<String> guessList = new ArrayList<>();
        ArrayList<String> wordList = new ArrayList<>();

        do {
            do {
                System.out.print("Word length? ");
                wordLength = s1.nextInt();
                s1.nextLine();
                if(!d1.hasWordsOfLength(wordLength)) {
                    System.out.println("Error - try again.");
                }
                else {
                    wordList = d1.getWordsOfLength(wordLength);
                }
            } while(!d1.hasWordsOfLength(wordLength));
            do {
                System.out.print("Number of guesses? ");
                guessesRemaining = s1.nextInt();
                s1.nextLine();
                if(guessesRemaining <= 0) {
                    System.out.println("Error - try again.");
                }
            } while(guessesRemaining <= 0);

            String input;
            do { //Still working on
                System.out.print("Running total (y/n)? ");
                input = s1.nextLine();
                if(input.equals("y")) {
                    runningTotal = true;
                }
                else if(input.equals("n")) {
                    runningTotal = false;
                }
                else {
                    System.out.println("Error - try again.");
                }
            } while(!input.equals("y") && !input.equals("n"));

            WordFamilyList wfl1 = new WordFamilyList(wordLength);

            while(true) {
                System.out.println("");
                System.out.println("Remaining guesses: " + guessesRemaining);
                System.out.println("Guesses: " + guessList);

                if(runningTotal) {
                    System.out.println("Running total: " + wordList.size());
                }

                System.out.println(wfl1.getCurrentWordFamily());

                String guess;
                do {
                    System.out.print("Guess? ");
                    guess = s1.nextLine();
                    if(!guess.equals("?")) {
                        if(guessList.contains(guess) || !guess.matches("[a-zA-Z]+") || guess.length() > 1) {
                            System.out.println("Error - try again.");
                        }
                    }
                    else if(guess.equals("?")) {
                        System.out.println("Temporary word: " + wordList.get(0));
                    }
                } while(guessList.contains(guess) || !guess.matches("[a-zA-Z]+") || guess.length() > 1);

                guessList.add(guess);

                for(String word : wordList) {
                    wfl1.evaluate(guess, word);
                }

                wordList = wfl1.getLargestWordFamily();

                if(wordList.get(0).contains(guess)) {
                    System.out.println("Correct");
                }
                else {
                    System.out.println("Incorrect");
                    guessesRemaining--;
                }

                if(wordList.size() == 1 && wfl1.getCurrentWordFamily().equals(wordList.get(0))) {
                    System.out.println("");
                    System.out.println("You won! The word was: " + wordList.get(0));
                    break;
                }
                else if(guessesRemaining <= 0) {
                    System.out.println("");
                    //System.out.println("You lost! The word was: " + wordList.get(0));
                    System.out.println("You lost!");
                    break;
                }

            }
            do { //Still working on
                System.out.print("Play again (y/n)? ");
                input = s1.nextLine();
                if(input.equals("y")) {
                    playAgain = true;
                }
                else if(input.equals("n")) {
                    playAgain = false;
                }
            } while(!input.equals("y") && !input.equals("n"));

        } while(playAgain == true);

    }
}
