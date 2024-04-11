import listadt.ListImp;
//import java.io.File;
import static java.lang.System.exit;

/**
 * Model class
 * All heavy lifting is done here
 */

public class Model implements IModel {
    private ListImp<IPlayer> players; //two players
    private IDeck deck;  // total cards not being drawn
    private IPlayer currentPlayer;
    private MachineList allMachines; // all machines
    private IPlayer comparePlayer = new AIPlayer("No one");

    //single player mode(or probably two players mode, AI not implemented)
    public Model(String playerName) {
        players = new ListImp<IPlayer>();
        players.add(new HumanPlayer(playerName));
        players.add(new AIPlayer());
        deck = new Deck();
        currentPlayer = players.get(0); // human player goes first
        allMachines = new MachineList();
    }
    
    //Dependency injection version of the constructor
    public Model(IDeck deck, MachineList allMachines, ListImp<IPlayer> players) {
        this.players = players;
        this.deck = deck;
        this.allMachines = allMachines;
        currentPlayer = this.players.get(0); 
    }
    
    //load game data
    @Override
    public void load() {
    	ResourceLoader<ICard> cardLoader = new ResourceLoader<>();
    	ListImp<ICard> cards = cardLoader.load(Constant.CARD_DATA_FILE, Card::parseCard);
    	ResourceLoader<Machine> machineLoader = new ResourceLoader<>();
    	ListImp<Machine> machines = machineLoader.load(Constant.MACHINE_DATA_FILE, Machine::parseMachine);
    	this.deck.setDeck(cards);
    	this.allMachines.setMachines(machines);
        this.shuffleDeck();
    }
    
    //player actions
    @Override
    public void selectCard(ICard card) {
    	//put the card in hand back to the deck
    	 currentPlayer.putCardBack(deck, currentPlayer.getHand().getTopCard());
        //select a card displayed on the board
        currentPlayer.getCard(card);
    }


    public void shuffleDeck() {
        deck.shuffle();
    }

    @Override
    public IPlayer getTurn() {
        return currentPlayer;
    }

    @Override
    public boolean isGameOver() {
        return getWinner()!= comparePlayer || currentPlayer.getVp() >=  Constant.WINNING_POINTS;
    }

    @Override
    public IPlayer getWinner() {
        if (currentPlayer.getVp() >= Constant.WINNING_POINTS) {
            return currentPlayer;
        }
        //own singularity machine
		for (int i = 0; i < allMachines.size(); i++) {
			if (allMachines.getMachine(i).getLevel() == Level.Singularity
					&& allMachines.getMachine(i).getOwner() == currentPlayer) {
				return currentPlayer;
			}
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

    @Override
    public void endGame() {
        exit(0); //for now
    }
    @Override
    public IPlayer getPlayer(int i) {
        return players.get(i);
    }
    @Override
	public IDeck getDeck() {
        return deck;
    }
	@Override
	public MachineList getMachineList() {
		return allMachines;
    }
	
	
}