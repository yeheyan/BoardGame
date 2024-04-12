import listadt.*;

/*
 * manage the deck of cards
 */
public class Deck implements IDeck{
    private ListImp<Card> deck;

    public Deck() {
        deck = new ListImp<Card>();
    }

    @Override
    public void addCard(Card card) {
        deck.add(card);
    }

    @Override
    public void removeCard(Card card) {
        deck.remove(card);
    }

    @Override
    public void shuffle() {
        //convert to list
        Card[] cards = new Card[deck.count(x -> true)];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.get(i);
        }
        // use the Fisher-Yates shuffle algorithm
        for (int i = cards.length - 1; i > 0; i--) {
            int j = (int) Math.floor(Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }

        //convert back to list
        deck = new ListImp<Card>();
        for (int i = 0; i < cards.length; i++) {
            deck.add(cards[i]);
        }
    }

    @Override
    public int size() {
        return deck.count(x -> true);
    }

    @Override
    public Card getTopCard() {
    	if (deck.count(x -> true) == 0) {
    		return new ResourceCard();
    	}
        return deck.get(0);
    }
    
	public void setDeck(ListImp<Card> deck) {
		this.deck = deck;
	}

    /**
     * Draw a card from the deck and remove it from the deck
     * @return the card drawn
     */
    public Card drawCard() {
        Card card = this.getTopCard();
        this.removeCard(card);
        return card;
    }

    public String toString() {
        return deck.toString();
    }
}
