package wordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import game.Game;
import io.GameStats;

public class WordleGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

          // Load words from file
        List<String> words = loadWords("wordle-words.txt");
        if (words.isEmpty()) {
            System.out.println("Error: Could not load words from file.");
            return;
        }

         // Get username
         System.out.print("Enter your username: ");
         String username = scanner.nextLine();
 
         // Initialize and play the game
         Game game = new Game(words, scanner);
         game.play();

            // Save game stats
        GameStats.saveStats(username, game.getSecretWord(), game.getAttemptsUsed(), game.isWin());

        scanner.close();
    }

        private static List<String> loadWords(String filename) {
        List<String> words = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim().toUpperCase();
                if (word.length() == 5) {
                    words.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File " + filename + " not found.");
        }
        return words;
    }
}   