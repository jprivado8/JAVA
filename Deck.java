package CardGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck(String filename) throws IOException {
        loadCards(filename);
        validateDeck();
    }

    private void loadCards(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");

            while (tokenizer.hasMoreTokens()) {
                String[] parts = tokenizer.nextToken().split("-");
                Suit suit = Suit.valueOf(parts[0]);
                Rank rank = Rank.valueOf(parts[1]);
                cards.add(new Card(suit, rank));
            }
        }
    }

    // Validation to ensure the deck is complete and unique
    private void validateDeck() throws IOException {
        Set<Card> uniqueCards = new HashSet<>(cards);

        if (uniqueCards.size() != 52) {
            System.err.println("Error: Deck must contain 52 unique cards without duplicates and no missing card.");
            throw new IOException("Invalid deck configuration in input file.");
        }
    }

    public void shuffle(int times) {
        for (int i = 0; i < times; i++) {
            riffleShuffle();
        }
    }

    private void riffleShuffle() {
        int mid = cards.size() / 2;
        List<Card> leftHalf = new ArrayList<>(cards.subList(0, mid));
        List<Card> rightHalf = new ArrayList<>(cards.subList(mid, cards.size()));

        cards.clear();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < leftHalf.size() || rightIndex < rightHalf.size()) {
            if (leftIndex < leftHalf.size()) {
                cards.add(leftHalf.get(leftIndex++));
            }
            if (rightIndex < rightHalf.size()) {
                cards.add(rightHalf.get(rightIndex++));
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
