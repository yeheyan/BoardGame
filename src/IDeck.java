import listadt.ListImp;

public interface IDeck {
    // add a card to the deck
    void addCard(ICard card);
    // remove a card from the deck
    void removeCard(ICard card);
    // shuffle the deck
    void shuffle();
    // get the size of the deck
    int size();
    // get the card at the top of the deck
    ICard getTopCard();
    ICard drawCard();
	void setDeck(ListImp<ICard> cards);
	
}
