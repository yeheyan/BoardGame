public interface IModel {
    /**
     * This method is used to get the current player's turn.
     * @return IPlayer
     */
    IPlayer getTurn();

    /**
     * Check if the game is over.
     * @return boolean game over = true, game ongoing = false
     */
    boolean isGameOver();
    /**
     * Get the winner of the game.
     * @return IPlayer
     */
    IPlayer getWinner();

    /**
     * This method is used to switch to the next player's turn.
     */
    void nextTurn();

    /**
     * This method is used to end the game.
     */
    void endGame();

	/**
	 * This method is used to load game data
	 */
	void load();

	/**
	 * This method is used to get a player by index
	 * @param i
	 * @return IPlayer
	 */
	IPlayer getPlayer(int i);

	/**
	 * This method is used to switch the player's turn.
	 */
	void switchPlayer();

	/**
	 * This method is used to add a specific card from revealed cards to the player's hand
	 */
	void selectCard(ICard card);
	
	/**
	 * This method is used to get the deck in game
	 * @return Object
	 */
	IDeck getDeck();
	
	/**
	 * This method is used to get the list of machines in game
	 * @return MachineList
	 */
	MachineList getMachineList();

}
