public interface IGUI {
    /**
     * Set the controller for the GUI
     * @param controller
     */
    void setController(Controller controller);

    /**
     * Set the state of the GUI
     * @param state
     */
    void setState(GameState state);

}
