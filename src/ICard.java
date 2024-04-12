public interface ICard {
	/**
	 * Load a card data from a string
	 * @param lines
	 */
    void loadCard(String lines);
	/**
	 * Get the card ID
	 * @return the card ID
	 */
    int getCardID();
	/**
	 * Get the card name
	 * @return the card name
	 */
	String getName();
	/**
	 * Get the card description
	 * @return the card description
	 */
	int getEconomyPoints();
	int getSciencePoints();
	int getVp();
}
