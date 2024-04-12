public interface IController {
	void quit();
	void endTurn();
	void isGameOver();
	void setUIState(GameState newState);
	void enterGame();
	//restart a new game
	void restart();
}
