package CardGame;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WarCard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int playerCount = 0;
        int shuffleTimes = 0;

        // Validate player count input
        while (true) {
            System.out.print("Enter the number of players: ");
            try {
                playerCount = scanner.nextInt();
                if (playerCount <= 0) {
                    System.err.println("Error: Number of players must be a positive integer.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }

        // Validate shuffle times input
        while (true) {
            System.out.print("Enter the number of shuffles: ");
            try {
                shuffleTimes = scanner.nextInt();
                if (shuffleTimes < 0) {
                    System.err.println("Error: Number of shuffles cannot be negative.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }

        try {
            Game game = new Game(playerCount, shuffleTimes, "input.txt");
            game.startGame();
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
