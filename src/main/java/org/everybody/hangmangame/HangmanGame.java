package org.everybody.hangmangame;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
// Tells the code to use Map and HashMap

public class HangmanGame {
    private static final String[] WORDS = {"love", "joy", "peace", "patience", "kindness", "faithfulness"};
    //New words that are Fruits of the Spritit to prompt Bible verses

    private static final Map<String, String> VERSES = new HashMap();
    //Links all words to a specific Bible verse
    
    private static String word;
    private static StringBuilder guessedWord;
    private static int attemptsLeft;

    public static void main(String[] args) {
        initializeVerses();
        //Loads Bible verses into the created HashMap so that there is something to pull from
        play();
    }

    public static void play() {
        initializeGame();

        while (attemptsLeft > 0 && guessedWord.indexOf("_") != -1) {
            System.out.println("\nAttempts left: " + attemptsLeft);
            System.out.println("Word: " + guessedWord.toString());

            char guess = getGuessFromPlayer();
            checkGuess(guess);
        }

        if (guessedWord.indexOf("_") == -1) {
            System.out.println("\nCongratulations! You guessed the word: " + word);
            printVerse(word);
                //Calls to also output a Bible verse in addition the word that was successfully guessed
        } else {
            System.out.println("\nGame over! You ran out of attempts. The word was: " + word);
        }
    }

    private static void initializeGame() {
        word = getRandomWord();
        guessedWord = new StringBuilder(word.length());

        for (int i = 0; i < word.length(); i++) {
            guessedWord.append("_");
        }

        attemptsLeft = 6;
    }

    private static String getRandomWord() {
        int index = (int) (Math.random() * WORDS.length);
        return WORDS[index];
    }

    private static char getGuessFromPlayer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your guess: ");
        String input = scanner.nextLine().toLowerCase();

        while (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.println("Invalid guess. Please enter a single letter.");
            System.out.print("Enter your guess: ");
            input = scanner.nextLine().toLowerCase();
        }

        return input.charAt(0);
    }

    private static void checkGuess(char guess) {
        boolean guessedCorrectly = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedWord.setCharAt(i, guess);
                guessedCorrectly = true;
            }
        }

        if (guessedCorrectly) {
            System.out.println("Correct guess!");
        } else {
            System.out.println("Incorrect guess!");
            attemptsLeft--;
        }
    }
    
    private static void initializeVerses() {
        VERSES.put("love","John 3:16 - For God so loved the world that He gave his one and only Son, that whoever believes in Him shall not perish but have eternal life.");
        VERSES.put("joy","1 Thessalonians 5:16-18 - Rejoice always, pray continually, give thanks in all circumstances; for this is God's will for you in Christ Jesus.");
        VERSES.put("peace","Matthew 5:9 - Blessed are the peacemakers, for they will be called children of God.");
        VERSES.put("patience","Ephesians 4:2 - Be completely humble and gentle; be patient, bearing with one another in love.");
        VERSES.put("kindness","Ephesians 4:32 - Be kind to one another, tenderhearted, forgiving one another, as God in Christ forgave you.");       
        VERSES.put("faithfulness","2 Corinthians 5:7 - For we live by faith, not by sight.");   
        //Loads up the Bible verses and the Fruit of the Spirit into the HashMap so they are paired correctly
    
    }
    
     private static void printVerse(String word) {
         String verse = VERSES.get(word);
         if (verse != null) {
             System.out.println("Bible Reference: " + verse);
        //Searches for the verse matching with the word that was guessed in the HashMap and prints it
         } else {
             System.out.println("/nNo verse available");
             //If no verse is found for a word, "No verse available" will be output
             
         }
     }
       
}