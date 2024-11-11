package CardGame;

public class Card implements Comparable<Card> {
	// Card class with fixed comparison based on specified rank and suit order
	
	    private final Suit suit;
	    private final Rank rank;

	    public Card(Suit suit, Rank rank) {
	        this.suit = suit;
	        this.rank = rank;
	    }

	    public Suit getSuit() {
	        return suit;
	    }

	    public Rank getRank() {
	        return rank;
	    }

	    @Override
	    public int compareTo(Card other) {
	        // First, compare ranks in descending order
	        int rankComparison = this.rank.compareTo(other.rank);
	        if (rankComparison != 0) {
	            return -rankComparison; // Higher rank should win
	        }

	        // If ranks are the same, compare suits in the specified order
	        return Integer.compare(getSuitValue(this.suit), getSuitValue(other.suit));
	    }

	    // Helper method to provide custom suit order values for comparison
	    private int getSuitValue(Suit suit) {
	        switch (suit) {
	            case DIAMOND: return 4;
	            case HEART: return 3;
	            case SPADE: return 2;
	            case CLUB: return 1;
	            default: return 0;
	        }
	    }

	    @Override
	    public String toString() {
	        return suit + "-" + rank;
	    }
	}
