/*
 * Represents cards in the game,
 * including scientists, institutions, and notable figures,
 * with attributes for science points, economy points, victory points,
 * and any special effects.
 * TODO: Have an abstract class for cards and extend it to have different types of cards
 */
public abstract class Card implements ICard{    // use interface ICard so that we can easily switch to a different card implementation
    private static int idCounter = 0; // Counter for card IDs
    private final int id; // Unique identifier for the card
    private String name; // Name of the scientist, machine, or figure
    private String description; // A brief description or the significance of the card
    private int vp; // Victory points that the card contributes
    private int sciencePoints; // Science points produced by the card
    private int economyPoints; // Economy points produced by the card
    private boolean isPlayed; // Indicates if the card is currently in play(currently not used, probably for future expansion)

    // Constructor
    public Card(String name, String description, int vp, int sciencePoints, int economyPoints) {
        this.id = ++idCounter;
        setName(name);
        setDescription(description);
        setVp(vp);
        setSciencePoints(sciencePoints);
        setEconomyPoints(economyPoints);
        this.isPlayed = false; // Initially, cards are not in play
    }
    
    public Card(){
        this.id = ++idCounter;
        this.isPlayed = false;
        setVp(0);
        setSciencePoints(0);
        setEconomyPoints(0);
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
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setVp(int vp) {
        this.vp = vp;
    }
    public void setSciencePoints(int sciencePoints) {
        this.sciencePoints = sciencePoints;
    }
    public void setEconomyPoints(int economyPoints) {
        this.economyPoints = economyPoints;
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
}
