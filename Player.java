package CardGame;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private final LinkedList<Card> deck = new LinkedList<>();

    public void addCard(Card card) {
        deck.offer(card);
    }

    public void addCards(Collection<Card> cards) {
        List<Card> sortedCards = new ArrayList<>(cards);
        sortedCards.sort(Collections.reverseOrder());
        deck.addAll(sortedCards);
    }

    public Card playCard() {
        return deck.poll();
    }

    public int getDeckSize() {
        return deck.size();
    }

    public Queue<Card> getDeck() {
        return deck;
    }
}
