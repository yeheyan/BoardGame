/*
 * Represents cards in the game,
 * including scientists, institutions, and notable figures,
 * with attributes for science points, economy points, victory points,
 * and any special effects.
 * TODO: Have an abstract class for cards and extend it to have different types of cards
 */
public class Card implements ICard{    // use interface ICard so that we can easily switch to a different card implementation
    private static int idCounter = 0; // Counter for card IDs
    private final int id; // Unique identifier for the card
    private String name; // Name of the scientist, machine, or figure
    private String description; // A brief description or the significance of the card
    private int vp; // Victory points that the card contributes
    private int sciencePoints; // Science points produced by the card
    private int economyPoints; // Economy points produced by the card
    private boolean isPlayed; // Indicates if the card is currently in play(currently not used, probably for future expansion)

    // Constructor
    public Card(int id, String name, String description, int vp, int sciencePoints, int economyPoints) {
        this.id = ++idCounter;
        this.name = name;
        this.description = description;
        this.vp = vp;
        this.sciencePoints = sciencePoints;
        this.economyPoints = economyPoints;
        this.isPlayed = false; // Initially, cards are not in play
    }
    
    public Card(){
        this.id = ++idCounter;
        this.isPlayed = false;
        this.vp = 0;
        this.sciencePoints = 0;
        this.economyPoints = 0;
    }
    // Getters and setters
    public int getCardID(){
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getVp() {
        return vp;
    }
    public int getSciencePoints() {
        return sciencePoints;
    }
    public int getEconomyPoints() {
        return economyPoints;
    }
    public boolean isPlayed() {
        return isPlayed;
    }
    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    @Override
    public String toString() {
    	// terminal output
//        return "Card{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", vp=" + vp +
//                ", sciencePoints=" + sciencePoints +
//                ", economyPoints=" + economyPoints +
//                ", isPlayed=" + isPlayed +
//                '}';
    	// GUI output
        return "<html>" +
        "<b>" + name + "</b><br>" +
        //"<b>" + description + "</b><br>" +    //description is too long, future expansion: only show description when mouse hovers over card
        "VP: <b>" + vp + "</b><br>" +
        "SP: <b>" + sciencePoints + "</b><br>" +
        "EP: <b>" + economyPoints + "</b><br>" +
        "</html>";
    }
    
    public static Card parseCard(String line) {
        Card card = new Card();
        card.loadCard(line);
        return card;
    }

    @Override
    public void loadCard(String lines) {
    	String[] cardInfo = lines.split(",(?! )");
        this.name = cardInfo[1];
        this.description = cardInfo[2];
        this.vp = Integer.parseInt(cardInfo[3]);
        this.sciencePoints = Integer.parseInt(cardInfo[4]);
        this.economyPoints = Integer.parseInt(cardInfo[5]);
    }

}
