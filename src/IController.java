/**
 * IController interface
 */
public interface IController {
	/**
	 * quit the game
	 */
	void quit();
	/**
	 * end the current turn
	 */
	void endTurn();
	/**
	 * check if the game is over
	 */
	void isGameOver();
	/**
	 * set the UI state
	 * @param newState
	 */
	void setUIState(GameState newState);
	/**
	 * enter the game
	 */
	void enterGame();
	/**
	 * restart a new game
	 * reset the game
	 */
	void restart();
}
