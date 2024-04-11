/**
 * This class contains the constant values used in the game. It is used to store
 * the winning conditions, resource paths, and gameplay settings. It is a final
 * class and cannot be instantiated. Modified as necessary.
 */

public final class Constant {
	// Prevent instantiation
	private Constant() {}
	
	// Winning conditions
    public static final int WINNING_POINTS = 18; //SUBJECT TO CHANGE
    
    // Resource paths
    public static final String CARD_DATA_FILE = "/card_data.csv";
    public static final String MACHINE_DATA_FILE = "/machine_data.csv";

    // Gameplay settings(not finish yet, currently not used)
    public static final double ECONOMY_MULTIPLIER = 0.8;
    public static final double SCIENCE_MULTIPLIER = 0.8;
    
    //player name
    public static final String PLAYER_NAME = "The Terminator";
    
}
