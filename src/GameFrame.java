import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {
    private static final long serialVersionUID = 1L;
	private GameState currentState = GameState.MAIN_MENU;
    private JPanel mainMenuPanel;
    private JPanel gamePanel;
    private JPanel gameOverPanel;
    private IController c;
    private ICard card_1;
    private ICard card_2;
    private int selectCardBtnTime = 1;
    private boolean selectCard = false;
    private JLabel PlayerInfo;
    private JLabel AIInfo;
    private static int roundCounter = 0;
    private int round;
	private JLabel lblRoundLabel;
	private JButton btnNewButton_3;
	private JButton btnNewButton_3_1;
	private JButton btnNewButton_3_2;
	private JButton btnNewButton_3_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_4_1;
	private JButton btnNewButton_4_2;
	private JButton btnNewButton_4_3;
	private JButton btnNewButton_5;
	private JButton btnNewButton_5_1;
	private JButton btnNewButton_5_2;
	private JButton btnNewButton_5_3;
	private JButton btnNewButton_6;
	private JButton btnNewButton_6_1;
	private JButton btnNewButton_6_2;
	private JButton btnNewButton_6_3;
	private JButton[] countingButtons;
	private JButton[] calculatingButtons;
	private JButton[] informationButtons;	
	private JButton[] singularityButtons;
    private JPanel panel;
    private JButton gameOverButton;
    private JPanel rulesPanel;
    private boolean isRulesVisible = false;
    
    public GameFrame() {
        setTitle("Game");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        this.setController(new Controller(this));
        btnNewButton_3 = new JButton("Level 1 Machine");
        btnNewButton_3_1 = new JButton("Level 1 Machine");
        btnNewButton_3_2 = new JButton("Level 1 Machine");
        btnNewButton_3_3 = new JButton("Level 1 Machine");
        btnNewButton_4 = new JButton("Level 2 Machine");
        btnNewButton_4_1 = new JButton("Level 2 Machine");
        btnNewButton_4_2 = new JButton("Level 2 Machine");
        btnNewButton_4_3 = new JButton("Level 2 Machine");
        btnNewButton_5 = new JButton("Level 3 Machine");
        btnNewButton_5_1 = new JButton("Level 3 Machine");
        btnNewButton_5_2 = new JButton("Level 3 Machine");
        btnNewButton_5_3 = new JButton("Level 3 Machine");
        btnNewButton_6 = new JButton("Level 4 Machine");
        btnNewButton_6_1 = new JButton("Level 4 Machine");
        btnNewButton_6_2 = new JButton("Level 4 Machine");
        btnNewButton_6_3 = new JButton("Level 4 Machine");
        
        countingButtons = new JButton[] {btnNewButton_3, btnNewButton_3_1, btnNewButton_3_2, btnNewButton_3_3};
        calculatingButtons = new JButton[] {btnNewButton_4, btnNewButton_4_1, btnNewButton_4_2, btnNewButton_4_3};
        informationButtons = new JButton[] {btnNewButton_5, btnNewButton_5_1, btnNewButton_5_2, btnNewButton_5_3};
        singularityButtons = new JButton[] {btnNewButton_6, btnNewButton_6_1, btnNewButton_6_2, btnNewButton_6_3};
        disableButtons(informationButtons);
        disableButtons(singularityButtons);
        
        initializeUI();
        //initialize buttons
        
        setState(GameState.MAIN_MENU);
    }

    private void initializeUI() {
        mainMenuPanel = createMainMenuPanel();
        gamePanel = createGamePanel();
        gameOverPanel = createGameOverPanel();
        // Initially, add the main menu panel
        getContentPane().add(mainMenuPanel);
       //getContentPane().add(gamePanel); //testing
    }

    private JPanel createMainMenuPanel() {
        //JPanel panel = new JPanel();
    	BackgroundPanel backgroundPanel = new BackgroundPanel("/game_background.jpg");
    	backgroundPanel.setLayout(null); // Use null layout to manually position components
    	//setContentPane(backgroundPanel);
        JLabel gameTitle = new JLabel("EVO AI", SwingConstants.CENTER);
        gameTitle.setFont(new Font("Arial", Font.BOLD, 24)); // Set the font size and style as needed
        int titleWidth = 200; // Width of the title label
        int titleHeight = 50; // Height of the title label
        int titleX = (700 - titleWidth) / 2; // Frame width is 700
        int titleY = 100; // Assuming 60 pixels above the start button
        gameTitle.setBounds(titleX, titleY, titleWidth, titleHeight);
        backgroundPanel.add(gameTitle);
        
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(300, 180, 112, 29);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setUIState(GameState.IN_GAME);
            }
        });
        backgroundPanel.add(startButton);
        
        JButton quitButton = new JButton("Quit Game");
        quitButton.setBounds(300, 220, 112, 29);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.quit();
			}
		});
		backgroundPanel.add(quitButton);
		
        JLabel credit = new JLabel("Get VP to " + c.getWinningPoints() + " or get to SINGULARITY! Have fun!", SwingConstants.CENTER);
        credit.setFont(new Font("Arial", Font.PLAIN, 11)); // Set the font size and style as needed
        credit.setForeground(Color.WHITE); // Set the text color to white
        titleWidth = 400; // Width of the title label
        titleHeight = 50; // Height of the title label
        titleX = (700 - titleWidth) / 2; // Frame width is 700
        titleY = 300; // Assuming 60 pixels above the start button
        credit.setBounds(titleX, titleY, titleWidth, titleHeight);
        backgroundPanel.add(credit);
	
        return backgroundPanel;
    }

    private JPanel createGamePanel() {
        //panel = new JPanel();
    	panel = new BackgroundPanel("/game_background.jpg");
    	panel.setLayout(null); // Use null layout to manually position components
       
    	gameOverButton = new JButton("");
        gameOverButton.setForeground(new Color(254, 251, 255));
        gameOverButton.setBounds(187, 120, 367, 133); 
        //gameOverButton.setBackground(Color.YELLOW); // Set the background color to yellow)
        gameOverButton.setVisible(false); // Initially invisible
        gameOverButton.addActionListener(e -> {
            // Define what happens when this button is clicked, for example:
            c.quit();
        });
       panel.add(gameOverButton);
   
        
        JButton quitButton = new JButton("Quit Game");
        quitButton.setBounds(6, 50, 110, 29);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setUIState(GameState.GAME_OVER);
            }
        });
        panel.setLayout(null);
        panel.add(quitButton);
        JButton btnNewButton_1 = new JButton("Rules");
        // Add action listener to the button
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleRulesVisibility();
            }
        });
        btnNewButton_1.setBounds(6, 94, 117, 29);
        panel.add(btnNewButton_1);
        rulesPanel = new JPanel();
        rulesPanel.setBounds(104, 72, 490, 274);
        rulesPanel.setBackground(new Color(211, 211, 211)); // Light gray background
        rulesPanel.setLayout(new BorderLayout());
        rulesPanel.setVisible(false); // Initially hidden
        panel.add(rulesPanel);
        JLabel rulesLabel = new JLabel("<html>Game Rules:<br/>"
        		+ "In the first round, click select card and select a card displayed below;"
        		+ " <br/>Points will be added to you after you end turn;"
        		+ " <br/>After first round for each player, you have the option to select a new card replacing current card;"
        		+ "<br/>You can select a machine to own by clicking the machine directly;"
        		+ "<br/>Machines can only be owned by one player, after you end turn, points will be added to you;"
        		+ "<br/>Reach " + c.getWinningPoints() + " points or own a Singularity machine to win the game!"
        		+ "<br/>VP: Victory Points; SP: Science Points; EP: Economy Points"
        		+ "<br/> click Rules again to close"
        		+ " </html>");
        rulesPanel.add(rulesLabel, BorderLayout.CENTER);
        rulesLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
        rulesLabel.setHorizontalAlignment(JLabel.CENTER);

        // user actions
        JButton btnNewButton = new JButton("Select card");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
				// only when select card button is clicked, the card can be selected
        		selectCard = true;
        		System.out.println(selectCardBtnTime);
        	}
        });
        btnNewButton.setBounds(6, 135, 117, 29);
        panel.add(btnNewButton);
        
        JButton btnNewButton_2 = new JButton("End Turn");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//set the time you can click action btn to 1
        		//update round number
        		round = ++roundCounter;
        		updateRound(round);  //could be put in controller
        		//end turn process
        		c.endTurn();  // update color; update player info; switch player
        		c.isGameOver();
        		selectCardBtnTime = 1;
        	}
        });
        btnNewButton_2.setBounds(6, 176, 117, 29);
        panel.add(btnNewButton_2);
        //end of user actions
        
        //user player panel
        PlayerInfo = new JLabel("");
        PlayerInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        PlayerInfo.setOpaque(true);
        PlayerInfo.setText(c.getPlayer().toString()); 
        PlayerInfo.setHorizontalAlignment(SwingConstants.CENTER);
        PlayerInfo.setForeground(Color.WHITE);
        PlayerInfo.setBackground(Color.BLUE);
        PlayerInfo.setBounds(360, 388, 171, 183);
        panel.add(PlayerInfo);
        
        //AI player panel
        AIInfo = new JLabel("");
        AIInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        AIInfo.setOpaque(true);
        AIInfo.setText(c.getAI().toString()); 
        AIInfo.setHorizontalAlignment(SwingConstants.CENTER);
        AIInfo.setForeground(Color.WHITE);
        AIInfo.setBackground(Color.BLACK);
        AIInfo.setBounds(164, 388, 171, 183);
        panel.add(AIInfo);
        
        //machine display for selection
        // level counting:
     // Initialize buttons for Counting Level
        btnNewButton_3 = initializeLevelButton("l1m", 128, 68, 117, 81, Level.Counting, 0, countingButtons, calculatingButtons);
        btnNewButton_3_1 = initializeLevelButton("l1m", 128, 145, 117, 78, Level.Counting, 1, countingButtons, calculatingButtons);
        btnNewButton_3_2 = initializeLevelButton("l1m", 128, 221, 117, 81, Level.Counting, 2, countingButtons, calculatingButtons);
        btnNewButton_3_3 = initializeLevelButton("l1m", 128, 301, 117, 81, Level.Counting, 3, countingButtons, calculatingButtons);

        // Initialize buttons for Calculating Level
        btnNewButton_4 = initializeLevelButton("l2m", 250, 68, 117, 81, Level.Calculating, 0, calculatingButtons, informationButtons);
        btnNewButton_4_1 = initializeLevelButton("l2m", 250, 145, 117, 81, Level.Calculating, 1, calculatingButtons, informationButtons);
        btnNewButton_4_2 = initializeLevelButton("l2m", 250, 221, 117, 81, Level.Calculating, 2, calculatingButtons, informationButtons);
        btnNewButton_4_3 = initializeLevelButton("l2m", 250, 301, 117, 81, Level.Calculating, 3, calculatingButtons, informationButtons);

        // level information:
     // Initialize buttons for Information Level
        informationButtons[0] = initializeLevelButton("l3m", 385, 68, 117, 81, Level.Information, 0, informationButtons, singularityButtons);
        informationButtons[1] = initializeLevelButton("l3m", 385, 145, 117, 81, Level.Information, 1, informationButtons, singularityButtons);
        informationButtons[2] = initializeLevelButton("l3m", 385, 221, 117, 81, Level.Information, 2, informationButtons, singularityButtons);
        informationButtons[3] = initializeLevelButton("l3m", 385, 301, 117, 81, Level.Information, 3, informationButtons, singularityButtons);

        // Initialize buttons for Singularity Level
        singularityButtons[0] = initializeLevelButton("l4m", 537, 68, 117, 81, Level.Singularity, 0, singularityButtons, null);
        singularityButtons[1] = initializeLevelButton("l4m", 537, 145, 117, 81, Level.Singularity, 1, singularityButtons, null);
        singularityButtons[2] = initializeLevelButton("l4m", 537, 221, 117, 81, Level.Singularity, 2, singularityButtons, null);
        singularityButtons[3] = initializeLevelButton("l4m", 537, 301, 117, 81, Level.Singularity, 3, singularityButtons, null);

     
        
        // deck not show
        JLabel deck= new JLabel("DECK");
        deck.setOpaque(true); // This is needed to show the background color
        deck.setBackground(Color.BLACK); // Set the background color to blue
        deck.setForeground(Color.WHITE); // Set the text color to white
        deck.setHorizontalAlignment(JLabel.CENTER); // Center the text in the label
        deck.setBounds(30, 263, 61, 83);
        panel.add(deck);
        
        //two cards display(lots of code duplication)
        JLabel lblCardinfo = new JLabel("card1info");
        lblCardinfo.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
        card_1 = c.updateCardInfo_1();
        lblCardinfo.setText(card_1.toString());
        lblCardinfo.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (selectCard&&selectCardBtnTime>0) {
        		c.playerSelectCard(card_1);
        		c.updateCard_1_Info();
        		lblCardinfo.setText(card_1.toString());
        		updatePlayerInfo(c.getCurrentPlayer());
        		selectCardBtnTime--;
        		System.out.println(c.getCurrentPlayer().getVp());   //testing
        		}
        	}
        });
        lblCardinfo.setOpaque(true);
        lblCardinfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblCardinfo.setForeground(Color.WHITE);
        lblCardinfo.setBackground(Color.BLACK);
        lblCardinfo.setBounds(30, 358, 61, 83);
        panel.add(lblCardinfo);
        
        JLabel lblCardinfo_1 = new JLabel("card2info");
        lblCardinfo_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
        lblCardinfo_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		// only when select card button is clicked, the card can be selected
        		if (selectCard && selectCardBtnTime>0) {
        		c.playerSelectCard(card_2); //put the card in hand back to the deck, and select a card displayed on the board
        		c.updateCard_2_Info(); //draw a new card from the deck, and update the card info
        		lblCardinfo_1.setText(card_2.toString());
        		//System.out.println(c.getCurrentPlayer().getVp());   //testing
        		updatePlayerInfo(c.getCurrentPlayer()); //update player's hand
        		selectCardBtnTime--;
        		}
        	}
        });

        card_2=c.updateCardInfo_1();
        lblCardinfo_1.setText(card_2.toString());
        lblCardinfo_1.setOpaque(true);
        lblCardinfo_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblCardinfo_1.setForeground(Color.WHITE);
        lblCardinfo_1.setBackground(Color.BLACK);
        lblCardinfo_1.setBounds(30, 463, 61, 83);
        panel.add(lblCardinfo_1);
        
        //machine level info
        JLabel Counting = new JLabel("Counting Era");
        Counting.setBounds(143, 31, 89, 16);
        panel.add(Counting);
        
        JLabel cal = new JLabel("Calculation Era");
        cal.setBounds(256, 31, 111, 16);
        panel.add(cal);
        
        JLabel lblInformationEra = new JLabel("Information Era");
        lblInformationEra.setBounds(385, 31, 117, 16);
        panel.add(lblInformationEra);
        
        JLabel lblSingularity = new JLabel("Singularity");
        lblSingularity.setBounds(555, 31, 89, 16);
        panel.add(lblSingularity);
        
        lblRoundLabel = new JLabel("Round:" + round);
        lblRoundLabel.setBounds(6, 11, 85, 16);
        panel.add(lblRoundLabel);
        
        JLabel lblRemarkLabel = new JLabel("BLUE indicates current player");
        lblRemarkLabel.setFont(new Font("Apple Braille", Font.PLAIN, 6));
        lblRemarkLabel.setForeground(Color.BLUE); 
        lblRemarkLabel.setBounds(555, 507, 99, 39);
        panel.add(lblRemarkLabel);
        
        updateMachineButtons(Level.Counting, countingButtons);
        updateMachineButtons(Level.Calculating, calculatingButtons);
        updateMachineButtons(Level.Information, informationButtons);
        updateMachineButtons(Level.Singularity, singularityButtons);
        return panel;
    }

    private JPanel createGameOverPanel() {
        // Initialize your game over panel here
    	BackgroundPanel backgroundPanel = new BackgroundPanel("/background.jpg");
    	backgroundPanel.setLayout(null); // Use null layout to manually position components
        JButton restartButton = new JButton("Resume Game");
        restartButton.setBounds(145, 64, 112, 29);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setUIState(GameState.IN_GAME);
            }
        });
   
        backgroundPanel.add(restartButton);
        
        JButton quitButton = new JButton("Quit Game");
        quitButton.setBounds(145, 100, 112, 29);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   c.quit();
            }
        });
        backgroundPanel.setLayout(null);
        backgroundPanel.add(quitButton);

        return backgroundPanel;
    }
    
    // switch between panels
    public void setState(GameState newState) {
        currentState = newState;
        getContentPane().removeAll(); // Remove current panel
        
        switch (currentState) {
            case MAIN_MENU:
                getContentPane().add(mainMenuPanel);
                break;
            case IN_GAME:
                getContentPane().add(gamePanel);
                break;
            case GAME_OVER:
                getContentPane().add(gameOverPanel);
                break;
        }
        
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    
	public void setController(IController controller) {
		this.c = controller;
		
	}

	public void updateCard_1(ICard card) {
		this.card_1 = card;
	}

	public void updateCard_2(ICard card) {
		this.card_2 = card;
	}
	
	public void updatePlayerInfo(IPlayer player) {
		if (player == c.getPlayer()) {
			PlayerInfo.setText(player.toString());
		} else {
			AIInfo.setText(player.toString());
		}
	}
	
	public void updateColor() {
		Player viewCurPlayer = (Player) c.getCurrentPlayer();
		if (viewCurPlayer == c.getPlayer()) {
			PlayerInfo.setBackground(Color.BLACK);
			AIInfo.setBackground(Color.BLUE);
		} else {
			AIInfo.setBackground(Color.BLACK);
			PlayerInfo.setBackground(Color.BLUE);
		}
	}
		
	public void updateRound(int newRound){
	        this.round = newRound;
	        lblRoundLabel.setText("Round: " + round);
	  }

	public int getRound() {
		return round;
	}

	public void updateMachineButtons(Level level, JButton[] buttons) {
		 MachineList machines = new MachineList(c.getMachines().getMachineList().filter(x -> x.getLevel() == level));
	  
	    // Iterate over the smaller of the two lengths to avoid IndexOutOfBoundsException
	    int length = Math.min(buttons.length, machines.size());
	    for (int i = 0; i < length; i++) {
	        buttons[i].setText(machines.getMachine(i).toString()); // Update button text with machine info
	    }
	}

	public int setMachineOwnerAndUpdateUI(int machineIndex, Level level, JButton[] buttons) {
		//get the level machine list
		MachineList machines = new MachineList(c.getMachines().getMachineList().filter(x -> x.getLevel() == level));
		//set owner and mark next level enabled
		int boolNextLevel = machines.getMachine(machineIndex).setOwner(c.getCurrentPlayer());
	    updateMachineButtons(level, buttons);
	    updatePlayerInfo(c.getCurrentPlayer());
	    return boolNextLevel;
	}
	
	private void enableButtons(JButton[] buttons) {
		for (JButton button : buttons) {
			button.setEnabled(true);
		}
	}
	
	private void disableButtons(JButton[] buttons) {
		for (JButton button : buttons) {
			button.setEnabled(false);
		}
	}
	
	private JButton initializeLevelButton(String text, int x, int y, int width, int height, Level level, int machineIndex, JButton[] buttonArray, JButton[] nextLevelButtons) {
	    JButton button = buttonArray[machineIndex];
	    button.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
	    button.setBounds(x, y, width, height);
	    button.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int boolNxt = setMachineOwnerAndUpdateUI(machineIndex, level, buttonArray);
				//if no next level pass
	            if (nextLevelButtons != null && boolNxt == 1 ) {
	            	enableButtons(nextLevelButtons);
				} else if (nextLevelButtons == null && boolNxt == 1) {
					c.isGameOver();
				}	
	        }
	    });
	    panel.add(button);
	    return button;
	}
	
	public void displayGameOver(IPlayer winner) {
	    String message = "<html>Game Over - Winner: <b>" + winner.getName() + "</b> wins!<br>Press to Exit Game</html>";
	    gameOverButton.setText(message);
	    //set text color
	    gameOverButton.setForeground(Color.BLUE);
	    gameOverButton.setVisible(true);
	}
	
    private void toggleRulesVisibility() {
        isRulesVisible = !isRulesVisible; // Toggle the state
        rulesPanel.setVisible(isRulesVisible); // Apply the new visibility state to the label
    }

}
