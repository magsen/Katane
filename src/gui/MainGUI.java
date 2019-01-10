package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import Katane.Katane;
import Katane.Player;
import Katane.World;

/**
 * Contains all visual components of the game.
 * 
 * @author YU Shijia
 * 
 */
@SuppressWarnings("serial")
public class MainGUI extends JFrame{ //容器

	/*
	 * Boolean variable that tells if the GUI has already been started
	 */
	private static boolean isStarted;

	/*
	 * The playermenu - object of class SettlerFrame.
	 */
	private SettlerFrame playerFrame;

	private OpponentFrame[] opponentFrames;

	/**
	 * Panel that contains the OpponentFrame s - object of class JPanel
	 */
	private JPanel opponentsPanel;

	private PolygonMap polygonMap;

	//Construction cost with the cost of building
	private PlayerButton buildingcosts;

	private int width;

	private int height;

	/**
	 * The size of the screen 
	 */
	private Dimension screenSize;

	/**
	 * The world from the model 
	 */
	private ArrayList<World> world;

	/**
	 * X coordinate of the point where the first hexagon is drawn.
	 */
	@SuppressWarnings("unused")
	private int x;
	
	@SuppressWarnings("unused")
	private int y;
	/**
	 * Half the width of a hexagon.
	 */
	private int radius;

	private Katane katane;

	/**
	 * Besonderes Panel, auf dem man die GUI-Elemente bequemer und
	 * &uuml;bersichtlicher anordnen kann.
	 */
	private JLayeredPane layeredPane; //Allow components to overlap each other when needed

	/**
	 * GUI element that allows trading partners (= opponents) to accept or reject a trade.
	 */
	private ConfirmTradePanel confirmTradePanel;

	/**
	 * Is used, for example, for the selection of raw materials after a rollover
	 */
	private ResourcePanel resourcePanel;

	/**
	 * Main panel to which all GUI elements are added
	 */
	private JPanel contentPanel;
	
	/**
	 * Specifies the x-coordinate of the PolygonMap
	 */
	private int polygonMapPos;

	/**
	 * Panel that is displayed when you want to build a street
	 */
	private BuildPanelRoad buildPanelRoad;

	/**
	 *Is the menu that allows trading with the bank, with the hawks?
	 */
	private TradingMenu tradePanel;

	/**
	 * Panel showing the diced pips (in the PlayerFrame)
	 */
	private DicePanel rollDicePanel;

	/**
	 * Menu, which allows the player to buy and play cards.
	 */
	private CardMenu cardMenu;

	// Here the available resources, knight cards, victory points and further information about the own player are displayed
	private SupplyPanel supplyPanel;

	/**
	 * The BuildingCostMenu , which displays the required raw materials
	 */
	private BuildingCostsMenu buildingCostsMenuPanel;

	/**
	 * Panel called, if you want to choose, from whom you want to steal raw materials
	 */
	private PlayerChoosePanel robberPanel;

	/**
	 *  if you want to choose which player you want to trade with
	 */
	private TradeChoosePanel tradeePanel;

	private Graphics g;
	
	/**
	 * Class to import the images
	 */
	private ImportImages importImages;
	
	/**
	 * Class to import the images of the server
	 */
	private ImportServerImages importServerImages;
	
	/**
	 *The image of the player
	 */
	private static int playerImage;
	
	/**
	 * Button for urban and rural development
	 */
	private PlayerButton buildButton;
	
	public MainGUI(Katane katane) {
		this.katane = katane;
		this.world = katane.getWorld();
		init();
		g = super.getGraphics();
	}

	public void init() {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		/*
		 * setDefaultCloseOperation ： Sets the operation that will happen by default when the user initiates a "close" on this frame.
		 * DO_NOTHING_ON_CLOSE ： The do-nothing default window close operation
		 */
		this.setTitle("The Settlers of Catan -All Rights Reversed");
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) (screenSize.getWidth());
		height = (int) (screenSize.getHeight());
		
		if(width > 2000) {
			width /= 2;
		}
		
		double value = (double) width / (double) height;
		polygonMapPos = 0;
		if(value < 1.5) {
			height = (int) (width / 1.77);
			if(screenSize.getWidth() > 2000)
				polygonMapPos = - (int) (width * 0.9 / 5.5);
		}
		
		width *= 0.9;// width = width*0.9
		height *= 0.9;

		screenSize = new Dimension(width, height);

		this.setPreferredSize(screenSize);
		this.setMinimumSize(screenSize);
		this.setVisible(true);
		this.setLocationRelativeTo(null); //This window will be placed in the center of the screen
		this.setResizable(false);//Sets whether this frame is resizable by the user.
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){//Invoked when a window is in the process of being closed.The close operation can be overridden at this point.
				int n = JOptionPane.showConfirmDialog(
					    getThis(),
					    "Do you really want to finish the game??\n",
					    "Exit game",
					    JOptionPane.YES_NO_OPTION,
					    JOptionPane.INFORMATION_MESSAGE);
				if(n==0){
					System.exit(0);
				}
			}
		});

		importImages = new ImportImages();
		importImages.loadPics();
		
		importServerImages = new ImportServerImages();
		importServerImages.loadServerPics();
		
		//playerImage = katane.getClient().getSettler().getAvatarNumber();
		playerImage = katane.getCurrentPlayer().getPlayerNumber();
		
		
		createWidgets();
		setupInteraction();
		addWidgets();
	}

	public void drawImage() {
		super.setVisible(true);
	}

	public void paintComponent(Graphics g, int centerX, int centerY) {
		Image img = ImportImages.testvillage;
		g.drawImage(img, centerX, centerY, null); 
	}

	public void paintComponent(Graphics g, Polygon polygon) {
		g = super.getGraphics();
		Graphics2D graphic = (Graphics2D) g;
		graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		graphic.setColor(new Color(139, 69, 19));
		graphic.draw(polygon);
		graphic.fill(polygon);
	}

	private void createWidgets() {

		int opponentsFrameWidth = (int) (width * 0.12);
		@SuppressWarnings("unused")
		int opponentsFrameHeight = (int) (height * 0.15);

		/*
		int chatFrameWidth = (int) (width * 0.28);
		int chatFrameHeight = (int) (height * 0.50);
		*/
		
		@SuppressWarnings("unused")
		int menuFrameWidth = (int) (width * (3.0 / 8.0));
		int menuFrameHeight = (int) (height * (1.5 / 8.0));

		radius = height / 11;

		contentPanel = new JPanel();
		opponentsPanel = new JPanel();
		opponentsPanel.setOpaque(false);
		
			playerFrame = new SettlerFrame(katane,
				ImportImages.avatarArray[playerImage], menuFrameHeight * 2,
				menuFrameHeight * 2);
			
		//chatFrame = new ChatGUI(katane, chatFrameWidth, chatFrameHeight);
		polygonMap = new PolygonMap(world.get(0),  polygonMapPos, 0, radius);
		/* unique world*/

		contentPanel.setOpaque(false);

		//opponentFrames = new OpponentFrame[world.getSettlers().length - 1];
		opponentFrames = new OpponentFrame[3];
		int c = 0;

		//for (int i = 0; i < world.getSettlers().length; i++) {
			
		for (Player player : katane.getListPlayer()) {
			if (player.getPlayerNumber() == katane.getCurrentPlayerIndex()) {
				playerFrame.addNameLbl(katane.getCurrentPlayer().getPlayerNumber()+"",
						katane.getCurrentPlayer().getColor());
				playerFrame.addColorLbl(katane.getCurrentPlayer().getColor());
				playerFrame.repaint();
			} else {
				opponentFrames[c] = new OpponentFrame(
						ImportImages.avatarArray[player.getPlayerNumber()], opponentsFrameWidth,
						opponentsFrameWidth);
				opponentFrames[c].setOpponent(player);
				c++;
			}
		}
		
		buildButton = new PlayerButton(ImportImages.settlementBtn,(int) ((double)width / 10.0 *0.3),(int) ((double)width / 10.0 *0.3));
		buildButton.setBounds(150, 150, width / 10, width / 10);
		buildButton.setVisible(false);

		buildPanelRoad = new BuildPanelRoad(katane, width / 10, width / 10);
		buildPanelRoad.setBounds(150, 150, width / 10, width / 10);
		buildPanelRoad.setVisible(false);

		rollDicePanel = new DicePanel(width / 10, height / 10);
		rollDicePanel.setBounds((int) ((width / 10) * 8.5),
				(int) ((height / 10) * 7.25), width / 10, height / 10);
		rollDicePanel.setVisible(false);

		tradePanel = new TradingMenu(katane, (int) (width / 2.5),
				(int) ((height / 5) * 3.2));
		tradePanel.setBounds((int) ((width / 3) * 1.10), (height / 6),
				(int) (width / 2.5), (int) ((height / 5) * 3.2));
		tradePanel.setVisible(false);

		cardMenu = new CardMenu(katane, (int) (width / 2),
				(int) ((height / 5) * 4));
		cardMenu.setBounds((int) ((width / 3.5)), (height / 8),
				(int) (width / 2), (int) ((height / 5) * 5));
		cardMenu.setVisible(false);

		buildingcosts = new PlayerButton(ImportImages.buildingcostsmenuBtn,
				radius, radius);
		buildingcosts.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){		
				((PlayerButton)me.getSource()).changeIcon(ImportImages.buildingcostsmenuBtnActive);
			}
			public void mouseExited(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.buildingcostsmenuBtn);
			}
		});
		
		buildingcosts.setToolTipText(Messages.getString("MainGUI.Baukosten")); 
		buildingcosts.setVisible(true);

		buildingCostsMenuPanel = new BuildingCostsMenu((int) (width / 2.5),
				(int) ((height / 5) * 3));
		buildingCostsMenuPanel.setBounds((int) ((width / 3) * 1.10),
				(height / 6), (int) (width / 2.5), (int) ((height / 5) * 4));
		buildingCostsMenuPanel.setVisible(false);

		resourcePanel = new ResourcePanel(katane, (int) (width * 0.3), (int) (height * 0.65));
		resourcePanel.setBounds((int) ((width / 3) * 1.10), (height / 6),
				(int) (width * 0.3), (int) (height * 0.65));

		supplyPanel = new SupplyPanel(katane, (int) (width * 0.1),
				(int) (height * 0.35));
		supplyPanel.setVisible(true);

		contentPanel.setBounds(0, 0, width, height);
		polygonMap.setBounds(0, 0, width, height);

		contentPanel.setOpaque(false);
		polygonMap.setOpaque(false);

		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(width, height));
	}

	private void setupInteraction() {

		buildingcosts.setActionCommand("menu.buildingcosts"); 
		//buildingcosts.addActionListener(katane);
		
		buildButton.setActionCommand("node.settlement");
		//buildButton.addActionListener(katane);

		//polygonMap.addMouseListener(katane);
		//this.addMouseListener(katane);
	}

	private void addWidgets() {
		Container c = this.getContentPane();//Returns the contentPane object for this frame.
		c.setBackground(new Color(30, 144, 255));

		contentPanel.setLayout(new GridBagLayout());
		opponentsPanel.setLayout(new FlowLayout());

		for (int i = 0; i < opponentFrames.length; i++) {
			opponentsPanel.add(opponentFrames[i]);
		}

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 0);
		contentPanel.add(opponentsPanel, gbc);

		/*
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = GridBagConstraints.LAST_LINE_START;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.insets = new Insets(5, 5, 40, 5);
		contentPanel.add(chatFrame, gbc);
	*/

		gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		gbc.insets = new Insets(0, 0, 0, (int) (width * 0.01));
		contentPanel.add(buildingcosts, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		gbc.insets = new Insets(0, 0, 0, (int) (width * 0.01));
		contentPanel.add(supplyPanel, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.weighty = GridBagConstraints.LAST_LINE_END;
		gbc.insets = new Insets(5, 5, 35, 20);
		contentPanel.add(playerFrame, gbc);
		
		layeredPane.add(polygonMap, new Integer(1));
		layeredPane.add(contentPanel, new Integer(2));
		layeredPane.add(buildButton, new Integer(3));
		layeredPane.add(buildPanelRoad, new Integer(3));
		layeredPane.add(rollDicePanel, new Integer(4));
		layeredPane.add(tradePanel, new Integer(5));
		layeredPane.add(resourcePanel, new Integer(5));
		layeredPane.add(cardMenu, new Integer(5));
		layeredPane.add(buildingCostsMenuPanel, new Integer(5));
	

		add(layeredPane);

		this.pack();
		setIsStarted(true);
	}

	private void setIsStarted(boolean b) {
		isStarted = b ;
	}

	/**
	 * Creates a new  PlayerChoosePanel  with the playerIDs generated. Here you can choose who you want to steal from.
	 * 
	 * @param playerIds
	 *            are the IDs of the Settlers
	 */
	public void showRobberPanel(ArrayList<Integer> playerIds) {
		robberPanel = new PlayerChoosePanel(katane, playerIds, false);
		robberPanel.setBounds((int) ((width / 3) * 1.10), (height / 6),
				robberPanel.getWidth(), robberPanel.getHeight());
		robberPanel.setVisible(true);
		layeredPane.add(robberPanel, new Integer(5));
	}

	/**
	 * Here you can choose which player you want to trade with.
	 */
	public void showTradeesPanel() {
		tradeePanel = new TradeChoosePanel(katane);
		tradeePanel.setBounds((int) ((width / 3) * 1.10), (height / 6),
				tradeePanel.getWidth(), tradeePanel.getHeight());
		tradeePanel.setVisible(true);
		layeredPane.add(tradeePanel, new Integer(5));
	}

	/**
	 *根据  offR（=提供的原材料）和  expR （预期的原材料）创建一个新的 ConfirmTradePanel 。 在这里，您可以选择是否接受交易（作为贸易伙伴）。
	 * 
	 * @param offR
	 *            (= offered Resources)
	 * @param expR
	 *            (= expected Resources)
	 */
	public void showConfirmTradePanel(int[] offR, int[] expR) {
		confirmTradePanel = new ConfirmTradePanel(katane,
				(int) (width / 2.5), (int) ((height / 5) * 3.2), offR, expR);
		confirmTradePanel.setBounds((int) ((width / 3) * 1.10), (height / 6),
				(int) (width / 2.5), (int) ((height / 5) * 3.2));
		confirmTradePanel.setVisible(true);
		layeredPane.add(confirmTradePanel, new Integer(5));
	}

	public PlayerChoosePanel getRobberPanel() {
		return robberPanel;
	}



	public PolygonMap getPolygonMap() {
		return polygonMap;
	}

	public SettlerFrame getPlayerFrame() {
		return playerFrame;
	}

	public TradingMenu getTradingMenu() {
		return tradePanel;
	}

	public BuildPanelRoad getBuildPanelRoad() {
		return buildPanelRoad;
	}

	public DicePanel getDicePanel() {
		return rollDicePanel;
	}

	public ResourcePanel getResourcePanel() {
		return resourcePanel;
	}

	public CardMenu getCardMenu() {
		return cardMenu;
	}

	public OpponentFrame[] getOpponentFrames() {
		return opponentFrames;
	}

	public SupplyPanel getSupplyPanel() {
		return supplyPanel;
	}

	public BuildingCostsMenu getBuildingCostsMenuPanel() {
		return buildingCostsMenuPanel;
	}

	public ConfirmTradePanel getConfirmTradePanel() {
		return confirmTradePanel;
	}

	public TradeChoosePanel getTradeePanel() {
		return tradeePanel;
	}

	public static boolean isStarted() {
		return isStarted;
	}


	public Graphics getG() {
		return g;
	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public JFrame getThis(){
		return this;
	}

	public static void setFrameCount(int i) {
		playerImage = i;
	}

	public PlayerButton getBuildButton() {		
		return buildButton;
	}


}
