import javax.swing.*;
import java.util.Objects;

/**
 * Controller class
 * control flow of the game
 */
public class Controller implements IController{
    private Model model;
    private GameFrame view;
    
    public Controller(Model model, GameFrame view){
        this.model = model;
        this.view = view;
        this.view.setController(this);

    }
    
    public Controller(GameFrame view) {
         this.model = new Model(Constant.PLAYER_NAME);
         this.view = view;
         this.view.setController(this);
         model.load();	         
    }

	@Override
	public void endTurn() {
		view.updateColor();
		model.nextTurn();
		Player prevPlayer = this.getCurrentPlayer();
		model.switchPlayer();
		view.updatePlayerInfo(prevPlayer);
	}
	@Override
	public void setUIState(GameState newState) {
		// Now instruct the GameFrame to update its UI for the new state
		view.setState(newState);
	}
	@Override
	public void quit() {
		System.exit(0);
	}

	@Override
	public void enterGame() {
		view.setState(GameState.MAIN_MENU);
		view.setVisible(true);
	}

	@Override
	public void restart() {
		model = new Model(Constant.PLAYER_NAME);
		model.load();
		view.updatePlayerInfo(this.getPlayer());
		view.updatePlayerInfo(this.getAI());
		view.updateColor();
		view.updateMachineButtons();
		view.resetRoundCounter();
	}

	@Override
	public void isGameOver() {
	    if (model.isGameOver()) {
	        Player winner = model.getWinner();
	        view.displayGameOver(winner);
	    }
	}

	public void playerSelectCard(Card card) {
		//if player hand is null getCard
		if (Objects.equals(model.getTurn().getHand().getTopCard(), new ResourceCard())) {
			model.getTurn().getCard(card);
		}
		model.selectCard(card);// select & put back
	}

	public ICard updateCardInfo_1() {
		return model.getDeck().drawCard();
	}
	
	//turn begins
	public void updateCard_1_Info() {
		Card card = model.getDeck().drawCard();
		view.updateCard_1(card);
	}
	
	public void updateCard_2_Info() {
		Card card =  model.getDeck().drawCard();
		view.updateCard_2(card);
	}
	
	public int getWinningPoints() {
		return  Constant.WINNING_POINTS;
	}

	public void updateMachineButtonsUI(Level level, JButton[] buttons) {
		MachineList machines = new MachineList(model.getMachineList().getMachineList().filter(x -> x.getLevel() == level));
		int length = Math.min(buttons.length, machines.size());
		for (int i = 0; i < length; i++) {
			buttons[i].setText(machines.getMachine(i).toString());
		}
	}

	public int setMachineOwnerAndUpdate(Level level, int machineIndex, JButton[] buttons) {
		MachineList machines = new MachineList(model.getMachineList().getMachineList().filter(x -> x.getLevel() == level));
		int boolNextLevel = machines.getMachine(machineIndex).setOwner(this.getCurrentPlayer());
		updateMachineButtonsUI(level, buttons);
		view.updatePlayerInfo(this.getCurrentPlayer());
		return boolNextLevel;
	}

	public Player getPlayer() {
		return model.getPlayer(0);
	}

	public Player getAI() {
		return model.getPlayer(1);
	}

	public Player getCurrentPlayer() {
		return model.getTurn();
	}
}
