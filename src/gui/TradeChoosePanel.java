package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Katane.*;

/**
 * This allows the trading player to choose a trading partner
 * 
 * @author LIAO Haoyun
 *
 */

@SuppressWarnings("serial")
public class TradeChoosePanel extends JPanel{

	/**
	 * The good old one <code>Katane</code>
	 */
	private Katane katane;
	/**
	 * Width of the panel
	 */
	private int width;
	/**
	 * Height of the panel
	 */
	private int height;
	/**
	 * Contains the enemy settlers
	 */
	private Player[] opponents;
	/**
	 * Represents the top part of the GUI
	 */
	private JPanel topPanel;
	/**
	 * Represents the lower part of the GUI
	 */
	private JPanel bottomPanel;
	/**
	 * Every opponent gets his own panel
	 */
	private JLayeredPane[] playerPanes;
	/**
	 * The avatars of the opponents
	 */
	private ImagePanel[] playerPanels;
	/**
	 * The names of the opposing players
	 */
	private ImagePanel[] namePanels;
	/**
	 * The buttons for selecting
	 */
	private PlayerButton[] buttons;
	/**
	 * The "accepted" status of the other players
	 */
	private PlayerLabel[] status;
	/**
	 * Button to close the window
	 */
	private PlayerButton cancelBtn;
	/**
	 * The background panel
	 */
	private JPanel bgpanel;
	/**
	 * temporary width
	 */
	private int tempWidth;
	/**
	 * temporary height
	 */
	private int tempHeight;
	/**
	 * The background
	 */
	private ImagePanel background;
	
	/**
	 * This constructor creates a new lookout window for the trading partners
	 * 
	 * @param katane
	 */
	public TradeChoosePanel(Katane katane) {
		width = (int) (katane.getMainGUI().getWidth() * 0.3);
		height = (int) (katane.getMainGUI().getHeight() * 0.15);

		this.katane = katane;
		initOpponents();
		
		tempWidth = (int) (width * 0.75);
		tempHeight = (int) (height * 0.75);
		createWidgets();
		setupInteraction();
		addWidgets();
		
		height = height * (opponents.length + 1);
		setPreferredSize(new Dimension(width, height));
		setOpaque(false);
		setVisible(true);
	}
	
	/**
	 * Initializes the opponents, for the ads.
	 */
	/*private void initOpponents() {
		opponents = new Player[3];
		for(int i = 0, count = 0; i < 3; i++) {
			if(!(katane.getPlayerN(i+1).getPlayerNumber() == katane.getCurrentPlayer().getPlayerNumber())) {
				opponents[count] = katane.getPlayerN(i+1);
				count++;
			}
		}
	}*/
	private void initOpponents() {
		opponents = new Player[3];
		int i = 0;
		for(Player player : katane.getListPlayer()) {
			if(player.getPlayerNumber() != katane.getCurrentPlayerIndex()) {
			opponents[i] = player;i++;
			}
			
		}
	}
	
	/**
	 * Creates the components of <code>TradeChoosePanel</code>.
	 */
	private void createWidgets() {
		int frameSize = (int) (tempWidth * 0.26);
		int btnSize = (int) (frameSize * 0.5);
		
		background = new ImagePanel(ImportImages.chatBg, width, height * (opponents.length + 1));
		background.setOpaque(false);
		
		bgpanel = new JPanel();
		bgpanel.setPreferredSize(new Dimension(tempWidth, tempHeight * (opponents.length + 1)));
		bgpanel.setLayout(new BorderLayout());
		bgpanel.setOpaque(false);
		
		GridLayout grid = new GridLayout(opponents.length, 3);
		grid.setHgap(5);
		grid.setVgap(5);
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(tempWidth, tempHeight * opponents.length));
		topPanel.setLayout(grid);
		topPanel.setOpaque(false);
		
		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(width, (int) (height)));
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setOpaque(false);
		
		playerPanes = new JLayeredPane[opponents.length];
		playerPanels = new ImagePanel[opponents.length];
		namePanels = new ImagePanel[opponents.length];
		buttons = new PlayerButton[opponents.length];
		status = new PlayerLabel[opponents.length];
		
		for(int i = 0; i < opponents.length; i++) {
			playerPanes[i] = new JLayeredPane();
			playerPanes[i].setPreferredSize(new Dimension(frameSize, frameSize));
			playerPanes[i].setOpaque(false);
			
			playerPanels[i] = new ImagePanel(ImportImages.kerriganAvatar, frameSize, frameSize);
			playerPanels[i].setBounds(0, 0, frameSize, frameSize);
			
			namePanels[i] = new ImagePanel(ImportImages.nameLbl, frameSize,
					frameSize / 2);
			namePanels[i].setBounds(0, (int) (frameSize * 0.63), frameSize,
					frameSize / 2);
			
			
			namePanels[i].addLabel(opponents[i].getPlayerNumber()+"",
					opponents[i].getColor());
			namePanels[i].getLabel().setFont(new Font("Times New Roman", Font.BOLD, (int) (frameSize / 2 * 0.38))); //$NON-NLS-1$
			namePanels[i].getLabel().setVerticalTextPosition(JLabel.CENTER);
			namePanels[i].getLabel().setVerticalAlignment(SwingConstants.CENTER);
			
			playerPanes[i].add(playerPanels[i], new Integer(1));
			playerPanes[i].add(namePanels[i], new Integer(2));
			
			buttons[i] = new PlayerButton(ImportImages.confirmBtn, btnSize, btnSize);
			buttons[i].setVisible(false);
			
			status[i] = new PlayerLabel(ImportImages.statusWait, btnSize, btnSize);
		}
		
		cancelBtn = new PlayerButton(ImportImages.cancelBtn, btnSize, btnSize);
	}
	
	/**
	 * Adds the <code>TradeChoosePanel</code> interaction with the <code>Katane</code>.
	 */
	private void setupInteraction() {
		for(int i = 0; i < opponents.length; i++) {
			buttons[i].addActionListener(new ActionListener() {public void
actionPerformed(ActionEvent e) { /*NEED TO BE EDIT*/ 
				System.out.println("l");
			}});
			buttons[i].setActionCommand("chos." + opponents[i].getPlayerNumber()); //$NON-NLS-1$
		}
		
		cancelBtn.addActionListener(new ActionListener() {public void
			actionPerformed(ActionEvent e) { 
			
			/*NEED TO BE EDIT*/ 
			
			
			katane.getMainGUI().tradeePanel.setVisible(false);
	
		}});
		cancelBtn.setActionCommand("chos.-1"); //$NON-NLS-1$
	}
	
	/**
	 * Adds the components to the <code>TradeChoosePanel</code>.
	 */
	private void addWidgets() {
		this.setLayout(new GridLayout(1, 0));
		for(int i = 0; i < opponents.length; i++) {
			topPanel.add(playerPanes[i]);
			topPanel.add(status[i]);
			topPanel.add(buttons[i]);
		}
		bottomPanel.add(cancelBtn);
		
		bgpanel.add(topPanel, BorderLayout.CENTER);
		bgpanel.add(bottomPanel, BorderLayout.SOUTH);
		background.setLayout(new BorderLayout());
		background.add(bgpanel, BorderLayout.CENTER);
		JPanel fillPanel = new JPanel();
		fillPanel.setPreferredSize(new Dimension((int) (tempWidth * 0.15), (int) (tempHeight * 0.7)));
		fillPanel.setOpaque(false);
		JPanel fillPanel2 = new JPanel();
		fillPanel2.setOpaque(false);
		fillPanel2.setPreferredSize(new Dimension((int) (tempWidth), (int) (tempHeight * 0.7)));
		background.add(fillPanel, BorderLayout.WEST);
		background.add(fillPanel2, BorderLayout.NORTH);
		add(background);
	}
	
	/**
	 * Changes the status of the trading partner.
	 * @param playerID ID of the partner
	 * @param isAccepted accepted or rejected
	 */
	public void switchStatus(int playerID, boolean isAccepted) {
		for(int i = 0; i < opponents.length; i++) {
			if(opponents[i].getPlayerNumber() == playerID) {
				if(isAccepted) {
					status[i].setNewIcon(ImportImages.statusAcc);
					buttons[i].setVisible(true);
				} else {
					status[i].setNewIcon(ImportImages.statusDen);
				}
			}
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}
