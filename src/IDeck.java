import listadt.ListImp;

/**
 * Interface for a deck of cards
 * A deck is a collection of cards
 */
public interface IDeck {
    /**
     * Add a card to the deck
     * @param card
     */
    void addCard(Card card);

    /**
     * Remove a card from the deck
     * @param card
     */
    void removeCard(Card card);
    /**
     * Shuffle the deck
     */
    void shuffle();

    /**
     * Get the size of the deck
     * @return the size of the deck
     */
    int size();

    /**
     * Get the top card of the deck
     * @return
     */
    Card getTopCard();
    /**
     * Draw a card from the deck
     * @return the card drawn
     */
    Card drawCard();

    /**
     * Set the deck
     * @param cards
     */
	void setDeck(ListImp<Card> cards);
	
}
