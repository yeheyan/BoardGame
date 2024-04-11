/**
 * Controller class
 * control flow of the game
 */
public class Controller implements IController{
    private IModel model;
    private GameFrame view;
    
    public Controller(IModel model, GameFrame view){
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
     
	public IPlayer getPlayer() {
		return model.getPlayer(0);
	}
	
	public IPlayer getAI() {
		return model.getPlayer(1);
	}
	
	public IPlayer getCurrentPlayer() {
		return model.getTurn();
	}
	
	public void endTurn() {
		view.updateColor();
		model.nextTurn();
		IPlayer prevPlayer = this.getCurrentPlayer();
		model.switchPlayer();
		view.updatePlayerInfo(prevPlayer);
	}
	
	public void isGameOver() {
	    if (model.isGameOver()) {
	        IPlayer winner = model.getWinner();
	        view.displayGameOver(winner);
	    }
	}
	
	public IPlayer getWinner() {
		return model.getWinner();
	}
	
	public void playerSelectCard(ICard card) {
		//if player hand is null getCard
		if (model.getTurn().getHand().getTopCard() == new Card()) {
			model.getTurn().getCard(card);
		}
		model.selectCard(card);// select & put back
		
	}

	public ICard updateCardInfo_1() {
		return model.getDeck().drawCard();
	}
	
	//turn begins
	public void updateCard_1_Info() {
		ICard card = model.getDeck().drawCard();
		view.updateCard_1(card);
	}
	
	public void updateCard_2_Info() {
		ICard card =  model.getDeck().drawCard();
		view.updateCard_2(card);
	}

	public MachineList getMachines() {
		return model.getMachineList();
	}
	
	public int getWinningPoints() {
		return  Constant.WINNING_POINTS;
	}
	
	public void setUIState(GameState newState) {
	    // Now instruct the GameFrame to update its UI for the new state
	    view.setState(newState);
	}

	public void quit() {
		System.exit(0);
	}
	
	public void enterGame() {
	    view.setState(GameState.MAIN_MENU); 
	    view.setVisible(true); 
	}

}
