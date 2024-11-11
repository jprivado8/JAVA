package CardGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
	// Game class for game logic

	    private final List<Player> players = new ArrayList<>();
	    private Deck deck;

	    public Game(int playerCount, int shuffleTimes, String filename) throws IOException {
	        deck = new Deck(filename);

	        System.out.println("\nDECK BEFORE SHUFFLE: " + deck.getCards());

	        deck.shuffle(shuffleTimes);
	        System.out.println("\nDECK AFTER SHUFFLE: " + deck.getCards());

	        for (int i = 0; i < playerCount; i++) {
	            players.add(new Player());
	        }

	        distributeCards();
	    }

	    private void distributeCards() {
	        List<Card> cards = deck.getCards();
	        int playerIndex = 0;
	        for (Card card : cards) {
	            players.get(playerIndex).addCard(card);
	            playerIndex = (playerIndex + 1) % players.size();
	        }
	    }

	    public void startGame() {
	        int round = 1;
	        while (true) {
	            System.out.println("\nRound " + round);

	            List<Card> cardsInPlay = new ArrayList<>();
	            Player roundWinner = null;
	            Card highestCard = null;

	            for (int i = 0; i < players.size(); i++) {
	                Player player = players.get(i);

	                if (player.getDeckSize() == 0)
	                    continue; // Skip players with no cards

	                Card card = player.playCard();
	                System.out.println("Player " + (i + 1) + " Card is: " + card);
	                cardsInPlay.add(card);

	                if (highestCard == null || card.compareTo(highestCard) > 0) {
	                    highestCard = card;
	                    roundWinner = player;
	                }
	            }

	            if (roundWinner != null) {
	                // Add all played cards to the round winner's deck
	                roundWinner.addCards(cardsInPlay);
	                System.out.println("THE WINNER OF THIS ROUND IS PLAYER " + (players.indexOf(roundWinner) + 1));

	                // Display the contents of the winner's deck
	                System.out.println("Cards on hand: " + roundWinner.getDeck());
	            }
	            
	            /*
	            // Delay output
	            try {
	                Thread.sleep(500); // 500 milliseconds delay
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
				*/
				
	            // Check if the game is over
	            if (isGameOver(roundWinner)) {
	                break;
	            }

	            round++;
	        }
	    }

	    private boolean isGameOver(Player roundWinner) {
	        if (roundWinner != null && roundWinner.getDeckSize() == 52) {
	            System.out.println(
	                    "\nGame Over! Player " + (players.indexOf(roundWinner) + 1)
	                            + " has all the (" + roundWinner.getDeckSize() + ") cards and wins!");
	            return true;
	        }
	        return false;
	    }
	}

