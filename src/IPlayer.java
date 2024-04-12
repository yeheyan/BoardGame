/**
 * IPlayer interface
 * Player
 */

public interface IPlayer {
    void drawCard(IDeck deck);
    void getCard(ICard card);
    // remove a card from the player's hand
    void putCardBack(IDeck deck, ICard card);
}
