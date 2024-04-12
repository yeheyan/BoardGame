public interface IModel {
    /**
     * This method is used to get the current player's turn.
     * @return IPlayer
     */
    Player getTurn();

    /**
     * Check if the game is over.
     * @return boolean game over = true, game ongoing = false
     */
    boolean isGameOver();
    /**
     * Get the winner of the game.
     * @return IPlayer
     */
    Player getWinner();

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
	 * This method is used to switch the player's turn.
	 */
	void switchPlayer();

	/**
	 * This method is used to add a specific card from revealed cards to the player's hand
	 */
	void selectCard(ICard card);

}
