import listadt.*;

/*
 * manage the deck of cards
 */
public class Deck implements IDeck{
    private ListImp<ICard> deck;

    public Deck() {
        deck = new ListImp<ICard>();
    }

    @Override
    public void addCard(ICard card) {
        deck.add(card);
    }

    @Override
    public void removeCard(ICard card) {
        deck.remove(card);
    }

    @Override
    public void shuffle() {
        //convert to list
        ICard[] cards = new Card[deck.count(x -> true)];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.get(i);
        }
        // use the Fisher-Yates shuffle algorithm
        for (int i = cards.length - 1; i > 0; i--) {
            int j = (int) Math.floor(Math.random() * (i + 1));
            ICard temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }

        //convert back to list
        deck = new ListImp<ICard>();
        for (int i = 0; i < cards.length; i++) {
            deck.add(cards[i]);
        }
    }

    @Override
    public int size() {
        return deck.count(x -> true);
    }

    @Override
    public ICard getTopCard() {
    	if (deck.count(x -> true) == 0) {
    		return new Card();
    	}
        return deck.get(0);
    }

    
	public void setDeck(ListImp<ICard> deck) {
		this.deck = deck;
	}
	
    public ICard drawCard() {
        ICard card = this.getTopCard();
        this.removeCard(card);
        return card;
    }

    public String toString() {
        return deck.toString();
    }
}
