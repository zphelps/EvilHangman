import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver02 {
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

                String input = JOptionPane.showInputDialog("Enter a word length: ");
                wordLength = Integer.parseInt(input);
                if(!d1.hasWordsOfLength(wordLength)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid word length.", "Error!", JOptionPane.PLAIN_MESSAGE );
                }
                else {
                    wordList = d1.getWordsOfLength(wordLength);
                }
            } while(!d1.hasWordsOfLength(wordLength));
            do {
                String input = JOptionPane.showInputDialog("How many guesses would you like? ");
                guessesRemaining = Integer.parseInt(input);
                if(guessesRemaining <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a number.", "Error!", JOptionPane.PLAIN_MESSAGE );
                }
            } while(guessesRemaining <= 0);

            String input;
            do {
                input = JOptionPane.showInputDialog("Would you like a running total? (y/n)? ");
                if(input.equals("y")) {
                    runningTotal = true;
                }
                else if(input.equals("n")) {
                    runningTotal = false;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please enter 'y' or 'no'", "Error!", JOptionPane.ERROR_MESSAGE );
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
                    if(guessList.contains(guess) || !guess.matches("[a-zA-Z]+") || guess.length() > 1) {
                        System.out.println("Error - try again.");
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
