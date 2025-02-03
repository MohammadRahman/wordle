package io;

import java.io.FileWriter;
import java.io.IOException;

public class GameStats {
        public static void saveStats(String username, String secretWord, int attemptsUsed, boolean win) {
        try (FileWriter writer = new FileWriter("stats.csv", true)) {
            String result = win ? "win" : "loss";
            writer.write(username + "," + secretWord + "," + attemptsUsed + "," + result + "\n");
        } catch (IOException e) {
            System.out.println("Error: Could not save stats to file.");
        }
    }
}
