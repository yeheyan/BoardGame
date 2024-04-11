public interface IController {

	void quit();

	void endTurn();

	void isGameOver();

	Object getPlayer();

	ICard updateCardInfo_1();

	void updateCard_1_Info();
	void updateCard_2_Info();

	void playerSelectCard(ICard card_1);

	IPlayer getCurrentPlayer();

	MachineList getMachines();

	void setUIState(GameState newState);

	IPlayer getAI();

	int getWinningPoints();
	
}
