package CardGame;

import java.io.IOException;
import java.util.Scanner;

public class WarCard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of players: ");
        int playerCount = scanner.nextInt();

        System.out.print("Enter the number of shuffles: ");
        int shuffleTimes = scanner.nextInt();

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
