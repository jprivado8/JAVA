package CardGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck(String filename) throws IOException {
        loadCards(filename);
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

    public void shuffle(int times) {
        for (int i = 0; i < times; i++) {
            riffleShuffle();
        }
    }

    private void riffleShuffle() {
        // Cut the deck in half
        int mid = cards.size() / 2;
        List<Card> leftHalf = new ArrayList<>(cards.subList(0, mid));
        List<Card> rightHalf = new ArrayList<>(cards.subList(mid, cards.size()));

        // Clear the original deck and interleave cards from each half
        cards.clear();
        int leftIndex = 0;
        int rightIndex = 0;

        // Interleave the cards alternately from each half
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
