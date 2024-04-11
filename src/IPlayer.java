/**
 * IPlayer interface
 * Player
 */

public interface IPlayer {
    // get the player's name
    String getName();
    // get the player's score
    int getVp();
    IDeck getHand();
    void drawCard(IDeck deck);
    void getCard(ICard card);
    // remove a card from the player's hand
    void putCardBack(IDeck deck, ICard card);
	int getEconomyPoints();
	int getSciencePoints();
	void setEconomyPoints(int i);
	void setSciencePoints(int i);
	void setVp(int i);

}
