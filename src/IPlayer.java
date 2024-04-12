/**
 * IPlayer interface
 * Player
 */
public interface IPlayer {
    void drawCard(IDeck deck);
    void getCard(Card card);
    // remove a card from the player's hand
    void putCardBack(IDeck deck, Card card);
}
