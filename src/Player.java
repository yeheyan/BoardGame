/*
 * Abstract class for a player(human/AI) in the game.
 * containing attributes like science points,
 * economy points, victory points,
 * current level, and owned cards/machines.
 */
public abstract class Player implements IPlayer{
    private final String name;
    private int sciencePoints;
    private int economyPoints;
    private int vp;
    private IDeck hand; // Player's hand of cards, size 1, ea version player has one card the whole game

    // Constructor
    public Player(String name) {
        this.name = name;
        this.sciencePoints = 0;
        this.economyPoints = 0;
        this.vp = 0;
        this.hand = new Deck();
    }

    public Player() {
        this.name = "The Mighty Brain";
        this.sciencePoints = 0;
        this.economyPoints = 0;
        this.vp = 0;
        this.hand = new Deck();
    }

    @Override
    public void drawCard(IDeck deck) {
        Card card = deck.getTopCard();
        hand.addCard(card);
        deck.removeCard(card);
    }
    
    @Override
	public void getCard(Card card) {
		hand.addCard(card);
		this.setVp(this.getVp() + card.getVp());
	}

    @Override
    public void putCardBack(IDeck deck, Card card) {
        hand.removeCard(card);
        deck.addCard(card);
        this.setVp(this.getVp() - card.getVp());
    }

    // Getters and setters
    public String getName() {
        return name;
    }
    public int getSciencePoints() {
        return sciencePoints;
    }

    public int getVp() {
        return vp;
    }
    public int getEconomyPoints() {
        return economyPoints;
    }

    public IDeck getHand() {
        return hand;
    }

    public void setSciencePoints(int sciencePoints) {
        this.sciencePoints = sciencePoints;
    }

    public void setVp(int vp) {
        this.vp = vp;
    }
    
    public void setEconomyPoints(int economyPoints) {
        this.economyPoints = economyPoints;
    }

    // Override toString method for player information
    @Override
    public String toString() {
//        return "Player \n" +
//                "name='" + name + '\n' +
//                "sciencePoints=" + sciencePoints + '\n' +
//                "economyPoints=" + economyPoints + '\n' +
//                "vp=" + vp + '\n' +
//                "hand=" + hand + '\n';
    	return "<html>Player:<br>" +
        "Name: <b>" + name + "</b><br>" +
        "Science Points: <b>" + sciencePoints + "</b><br>" +
        "Economy Points: <b>" + economyPoints + "</b><br>" +
        "VP: <b>" + vp + "</b><br>" +
        "Hand: <b>" + hand + "</b><br>" +
        "</html>";
    }
}
