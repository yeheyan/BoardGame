
/*
 * Machine
 * kind of fixed version of card
 */
public class Machine {
    //Level,Machine,Description,VP,SciencePoints,EconomyPoints,RequiredSciencePoints,RequiredEconomyPoints
    private Level level;
    private String machine;
    private String description;
    private int vp;
    private int sciencePoints;
    private int economyPoints;
    private int requiredSciencePoints;
    private int requiredEconomyPoints;
    private Player comparePlayer = new HumanPlayer("No owner");
    private Player owner; // Owner of the machine, initially null;
                          // The owner of the machine add sciencePoints and economyPoints to the player each round

    /**
     * Constructor
     * more flexible constructor can be diy by user
     * @param level
     * @param machine
     * @param description
     * @param vp
     * @param sciencePoints
     * @param economyPoints
     * @param requiredSciencePoints
     * @param requiredEconomyPoints
     */
    public Machine(Level level, String machine, String description, int vp, int sciencePoints, int economyPoints, int requiredSciencePoints, int requiredEconomyPoints) {
        this.level = level;
        this.machine = machine;
        this.description = description;
        this.vp = vp;
        this.sciencePoints = sciencePoints;
        this.economyPoints = economyPoints;
        this.requiredSciencePoints = requiredSciencePoints;
        this.requiredEconomyPoints = requiredEconomyPoints;
        this.owner = comparePlayer; // Initially, no owner
    }

    /**
     * Constructor
     * Default constructor, since we are loading the machines from a file
     */
    public Machine(){
    	 this.owner = comparePlayer; // Initially, no owner
    }

    // Getters and setters
    public Level getLevel() {
        return level;
    }
    public String getMachine() {
        return machine;
    }
    public String getDescription() {
        return description;
    }
    public int getVp() {
        return vp;
    }
    public int getSciencePoints() {
        return sciencePoints;
    }
    public int getEconomyPoints() {
        return economyPoints;
    }
    public int getRequiredSciencePoints() {
        return requiredSciencePoints;
    }
    public int getRequiredEconomyPoints() {
        return requiredEconomyPoints;
    }
    public Player getOwner() {
        return owner;
    }

    public int setOwner(Player owner) {  // when player click on machine, set owner to player with conditions, subtract science and economy points
    	//check if owner player has enough science and economy points and no owner
    	if (owner.getSciencePoints() >= requiredSciencePoints && owner.getEconomyPoints() >= requiredEconomyPoints && this.owner==comparePlayer) {
            this.owner = owner;
            owner.setSciencePoints(owner.getSciencePoints() - requiredSciencePoints);
            owner.setEconomyPoints(owner.getEconomyPoints() - requiredEconomyPoints);
            owner.setVp(owner.getVp() + vp);
        return 1;
        }
		return -1;
    }

	public void setEconomyPoints(int economyPoints) {
		this.economyPoints = economyPoints;
	}
	public void setSciencePoints(int sciencePoints) {
		this.sciencePoints = sciencePoints;
	}
	
    public static Machine parseMachine(String line) {
        Machine machine = new Machine();
        machine.loadMachine(line);
        return machine;
    }
    
    public void loadMachine(String machineData) {
        String[] machineInfo = machineData.split(",(?! )"); //split only with"," not ", "
        this.level = Level.valueOf(machineInfo[0]);
        this.machine = machineInfo[1];
        this.description = machineInfo[2];
        this.vp = Integer.parseInt(machineInfo[3]);
        this.sciencePoints = Integer.parseInt(machineInfo[4]);
        this.economyPoints = Integer.parseInt(machineInfo[5]);
        this.requiredSciencePoints = Integer.parseInt(machineInfo[6]);
        this.requiredEconomyPoints = Integer.parseInt(machineInfo[7]);
    }

    public String toString() {
//        return "Machine{" +
//                "level=" + level +
//                ", machine='" + machine + '\'' +
//                ", description='" + description + '\'' +
//                ", vp=" + vp +
//                ", sciencePoints=" + sciencePoints +
//                ", economyPoints=" + economyPoints +
//                ", requiredSciencePoints=" + requiredSciencePoints +
//                ", requiredEconomyPoints=" + requiredEconomyPoints +
//                ", owner='" + owner + '\'' +
//                '}';
    	return "<html>" +
        "<b>" + machine + "</b><br>" +
      //  "<b>" + description + "</b><br>" +
        "VP: <b>" + vp + " </b>" +
        "SP: <b>" + sciencePoints + " </b>" +
        "EP: <b>" + economyPoints + " </b><br>" +
        "Required SP: <b>" + requiredSciencePoints + "</b><br>" +
        "Required EP: <b>" + requiredEconomyPoints + "</b><br>" +
        "Owned by: <b>" + owner.getName() + "</b><br>" +
        "</html>";
    }

}
