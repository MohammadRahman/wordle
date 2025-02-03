package game;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private static final int MAX_ATTEMPTS = 6;
    private static final int WORD_LENGTH = 5;

    private String secretWord;
    private int attemptsLeft;
    private Set<Character> remainingLetters;
    private Scanner scanner;
    private int attemptsUsed;
    private boolean win;

    public Game(List<String> words, Scanner scanner) {
        Random random = new Random();
        this.secretWord = words.get(random.nextInt(words.size())).toUpperCase();
        this.attemptsLeft = MAX_ATTEMPTS;
        this.remainingLetters = new HashSet<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            remainingLetters.add(c);
        }
        this.scanner = scanner;
        this.attemptsUsed = 0;
        this.win = false;
    }

    public void play() {
        System.out.println("Welcome to Wordle! Guess the 5-letter word. You have " + MAX_ATTEMPTS + " attempts.");

        while (attemptsLeft > 0) {
            System.out.println("\nAttempts left: " + attemptsLeft);
            System.out.println("Remaining letters: " + remainingLetters);
            System.out.print("Enter your guess: ");
            String guess = scanner.nextLine().toUpperCase();

            if (guess.length() != WORD_LENGTH) {
                System.out.println("Please enter a 5-letter word.");
                continue;
            }

            String feedback = getFeedback(guess);
            System.out.println("Feedback: " + feedback);

            if (guess.equals(secretWord)) {
                System.out.println("Congratulations! You guessed the word: " + secretWord);
                win = true;
                break;
            }

            attemptsLeft--;
            attemptsUsed++;
        }

        if (!win) {
            System.out.println("Out of attempts! The secret word was: " + secretWord);
        }
    }

    private String getFeedback(String guess) {
        StringBuilder feedback = new StringBuilder();
        for (int i = 0; i < WORD_LENGTH; i++) {
            char guessChar = guess.charAt(i);
            char secretChar = secretWord.charAt(i);

            if (guessChar == secretChar) {
                feedback.append("\u001B[32m").append(guessChar).append("\u001B[0m"); // Green for correct position
            } else if (secretWord.contains(String.valueOf(guessChar))) {
                feedback.append("\u001B[33m").append(guessChar).append("\u001B[0m"); // Yellow for correct letter in wrong position
            } else {
                feedback.append("\u001B[37m").append(guessChar).append("\u001B[0m"); // White for incorrect letter
                remainingLetters.remove(guessChar);
            }
        }
        return feedback.toString();
    }

    public String getSecretWord() {
        return secretWord;
    }

    public int getAttemptsUsed() {
        return attemptsUsed;
    }

    public boolean isWin() {
        return win;
    }
}
