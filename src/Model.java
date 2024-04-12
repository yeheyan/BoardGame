import listadt.ListImp;
//import java.io.File;
import static java.lang.System.exit;

/**
 * Model class
 * All heavy lifting is done here
 */
public class Model implements IModel {
    private ListImp<Player> players; //two players
    private IDeck deck;  // total cards not being drawn
    private Player currentPlayer;
    private MachineList allMachines; // all machines
    private Player comparePlayer = new HumanPlayer("No one"); //we could make another dummy player class for this just to represent no owner

    /**
     * Constructor
     * Current game uses this constructor setup
     * @param playerName
     */
    public Model(String playerName) {
        players = new ListImp<Player>();
        players.add(new HumanPlayer(playerName));
        players.add(new AIPlayer());
        deck = new Deck();
        currentPlayer = players.get(0); // first in the list goes first
        allMachines = new MachineList();
    }

    /**
     * Constructor
     * This constructor is more flexible for setting up the game
     * @param deck
     * @param allMachines
     * @param players
     */
    public Model(IDeck deck, MachineList allMachines, ListImp<Player> players) {
        this.players = players;
        this.deck = deck;
        this.allMachines = allMachines;
        currentPlayer = this.players.get(0); 
    }

    @Override
    public void load() {
    	ResourceLoader<Card> cardLoader = new ResourceLoader<>();
    	ListImp<Card> cards = cardLoader.load(Constant.CARD_DATA_FILE, ResourceCard::parseCard);
    	ResourceLoader<Machine> machineLoader = new ResourceLoader<>();
    	ListImp<Machine> machines = machineLoader.load(Constant.MACHINE_DATA_FILE, Machine::parseMachine);
    	this.deck.setDeck(cards);
    	this.allMachines.setMachines(machines);
        deck.shuffle();
    }

    @Override
    public void selectCard(Card card) {
    	//put the card in hand back to the deck
        currentPlayer.putCardBack(deck, currentPlayer.getHand().getTopCard());
        //select a card displayed on the board
        currentPlayer.getCard(card);
    }

    @Override
    public Player getTurn() {
        return currentPlayer;
    }

    @Override
    public boolean isGameOver() {
        return getWinner()!= comparePlayer || currentPlayer.getVp() >=  Constant.WINNING_POINTS;
    }

    @Override
    public Player getWinner() {
        if (currentPlayer.getVp() >= Constant.WINNING_POINTS) {
            return currentPlayer;
        }
        //own singularity machine
        //use count to check if the player has a singularity machine
        int numOwnedSingularity = allMachines.getMachineList().filter(x ->
                x.getLevel() == Level.Singularity && x.getOwner() == currentPlayer)
                .count(x-> true);
        if (numOwnedSingularity > 0) {
            return currentPlayer;
        }
        return comparePlayer;
    }

    @Override
    public void nextTurn() {
        //increase point to current player
        // first check the hand card, add points to player
        // then check the machines, add points to player
        currentPlayer.setEconomyPoints(currentPlayer.getHand().getTopCard().getEconomyPoints()+currentPlayer.getEconomyPoints());
        currentPlayer.setSciencePoints(currentPlayer.getHand().getTopCard().getSciencePoints()+currentPlayer.getSciencePoints());
        //add points from machines
        for (int i = 0; i < allMachines.size(); i++) {
            if (allMachines.getMachine(i).getOwner() == currentPlayer) {
                currentPlayer.setEconomyPoints(allMachines.getMachine(i).getEconomyPoints() + currentPlayer.getEconomyPoints());
                currentPlayer.setSciencePoints(allMachines.getMachine(i).getSciencePoints() + currentPlayer.getSciencePoints());
            }
        }
      }
    @Override
	public void switchPlayer() {
		if (currentPlayer == players.get(0)) {
			currentPlayer = players.get(1);
		} else {
			currentPlayer = players.get(0);
		}
	}

    /**
     * End the game
     */
    public void endGame() {
        exit(0); //for now
    }

    /**
     * Get the player
     * @param i
     * @return Player
     */
    public Player getPlayer(int i) {
        return players.get(i);
    }

    /**
     * Get the deck
     * @return IDeck
     */
	public IDeck getDeck() {
        return deck;
    }

    /**
     * Get the machine list
     * @return MachineList
     */
	public MachineList getMachineList() {
		return allMachines;
    }
}