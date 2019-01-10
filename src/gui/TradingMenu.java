package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import Katane.*;

/**
 * The class provides the frontend for the trading function. 
 * The Klaase partially updates itself. 
 * (Not via the <code>Katane</code>
 * 
 * @author LIAO Haoyun
 */

@SuppressWarnings("serial")
public class TradingMenu extends JPanel implements ChangeListener,
		ActionListener {

	/**
	 * background image
	 */
	private ImagePanel bgpanel;

	/**
	 * DropDown Menu, where the trading partners are located
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox tradepartners;

	/**
	 * Panel, which should point to its own raw materials
	 */
	private JLabel ich;

	/**
	 * Size of the buttons
	 */
	private int size;

	/**
	 * Wool Icon
	 */
	private PlayerLabel wool;

	/**
	 *Ore Icon
	 */
	private PlayerLabel ore;

	/**
	 * Brick icon
	 */
	private PlayerLabel brick;

	/**
	 * Lumber icon
	 */
	private PlayerLabel lumber;

	/**
	 * Grain icon
	 */
	private PlayerLabel grain;

	/**
	 * Wool Icon
	 */
	private PlayerLabel wool2;

	/**
	 * Ore Icon
	 */
	private PlayerLabel ore2;

	/**
	 * Brick icon
	 */
	private PlayerLabel brick2;

	/**
	 * Lumber icon
	 */
	private PlayerLabel lumber2;

	/**
	 * Grain icon
	 */
	private PlayerLabel grain2;

	/**
	 * Button with which you can start trading
	 */
	private PlayerButton trade;

	/**
	 * Wool JSpinner
	 */
	private JSpinner woolspinner;

	/**
	 * Wool Spinner
	 */
	private SpinnerNumberModel woolnum;

	/**
	 * Ore JSpinner
	 */
	private JSpinner orespinner;

	/**
	 * Ore Spinner
	 */
	private SpinnerNumberModel orenum;

	/**
	 * Clay JSpinner
	 */
	private JSpinner brickspinner;

	/**
	 * Clay Spinner
	 */
	private SpinnerNumberModel bricknum;

	/**
	 * Wood JSpinner
	 */
	private JSpinner lumberspinner;

	/**
	 * Wood Spinner
	 */
	private SpinnerNumberModel lumbernum;

	/**
	 * Wheat JSpinner
	 */
	private JSpinner grainspinner;

	/**
	 * Wheat Spinner
	 */
	private SpinnerNumberModel grainnum;

	/**
	 * SpinnerModel array containing the JSpinnerModels of raw materials
	 */
	private SpinnerNumberModel[] numbers;

	/**
	 * Spinner array containing the jspinner of the raw materials
	 */
	private JSpinner[] spinners;

	/**
	 * This array stores the player's exchange rates
	 */
	int[] rates;

	/**
	 * Wool JSpinner
	 */
	private JSpinner woolspinner2;

	/**
	 * Wool Spinner
	 */
	private SpinnerNumberModel woolnum2;

	/**
	 * Ore Spinner
	 */
	private JSpinner orespinner2;

	/**
	 * Ore Spinner
	 */
	private SpinnerNumberModel orenum2;

	/**
	 * Clay Spinner
	 */
	private JSpinner brickspinner2;

	/**
	 * Clay Spinner
	 */
	private SpinnerNumberModel bricknum2;

	/**
	 * Wood Spinner
	 */
	private JSpinner lumberspinner2;

	/**
	 * Wood Spinner
	 */
	private SpinnerNumberModel lumbernum2;

	/**
	 * Wheat Spinner
	 */
	private JSpinner grainspinner2;

	/**
	 * Wheat Spinner
	 */
	private SpinnerNumberModel grainnum2;

	/**
	 * Array for the spinners
	 */
	private JSpinner[] spinners2;

	/**
	 * Array for the NumberModels of Spinners
	 */
	private SpinnerNumberModel[] numbers2;

	/**
	 * This is where the player's hives are cached
	 */
	private ArrayList<Byte> harbors;

	/**
	 * Width of the panel
	 */
	private int width;

	/**
	 * Height of the panel
	 */
	private int height;

	/**
	 * The well-known katane
	 */
	private Katane katane;

	/**
	 * The player currently on the move
	 */
	private Player current;

	/**
	 * Contains whether it is a banking trade
	 */
	private boolean banktrade = true;

	/**
	 * Enhances whether I am changing the left (or right) spinners haeb
	 * 增强我是否正在改变左（或右）旋转器
	 */
	private boolean left;
	
	/**
	 * Counters for the number of raw materials to be received from the player side
	 */
	private int counterLeft;
	
	/**
	 * Counters for the number of high-end commodities on the bank side
	 */
	private int counterRight;
	
	/**
	 * Contains the last state of the left side
	 */
	private int[] lastScoreL;
	
	/**
	 * Contains the last state of the right side
	 */
	private int[] lastScoreR;
	
	/**
	 * Number in the array where the ChangeEvent came from
	 */
	private int source;
	/**
	 * Button to close the panel
	 */
	private PlayerButton exit;
	/**
	 * Label under the close button
	 */
	private JLabel exitLabel;


	/**
	 * The constructor creates an object of the TradingMenus, 
	 * which is the frontend for bank, port and player trading.
	 * 
	 * @param katane
	 *            is the katane
	 * @param width
	 *            is the width of the panel
	 * @param height
	 *            is the height of the panel
	 */
	public TradingMenu(Katane katane, int width, int height) {
		this.katane = katane;
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));
		this.setOpaque(false);
		this.current = katane.getCurrentPlayer();
		this.size = width/10;
		// current rate (bank trading) - depending on the order of the order: 
		//wool, ore, brick, lumber, grain by default, 
		//the exchange rate is initially 4 to 1
		rates = new int[] { 4, 4, 4, 4, 4 };
		lastScoreL = new int[] {0,0,0,0,0};
		lastScoreR = new int[] {0,0,0,0,0};
		counterLeft = 0;
		counterRight = 0;
		source = -1;
		init();
	}

	/**
	 * Initialize that <code>TradingMenu</code>.
	 */
	public void init() {
		createWidgets();
		setupInteraction();
		addWidgets();
	}

	/**
	 * Creates the components of <code>TradingMenus<code>.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createWidgets() {
		/**
		 * Here is the background (still: scroll added)
		 */
		bgpanel = new ImagePanel(ImportImages.chatBg, width, height);
		/**
		 * Here the JSpinners are initialized for the player. It will: 
		 * - The initial value is set to 0 
		 * - The minimum value is set to 0 (no negative JSpinners) 
		 * - The maximum value (always the number of current resources of the player), 
		 * in the beginning all 0 Then the JSpinners are set uneditable, 
		 * so that no letters or weird Entering numbers Afterwards, 
		 * the maximum number of digits is set to a maximum of 2 (at most 99).
		 */
		//for(Ressource ressource : this.player.get(0).getSpecificRessource((ressource)-> ressource instanceof Wood)){
			//	((Wood) ressource).hit();
			//}
		
		
		woolnum = new SpinnerNumberModel(0, 0, current.getSpecificRessource((ressource)-> ressource instanceof Wool).get(0).getQuantity(), 1);
		woolspinner = new JSpinner(woolnum);
		((JSpinner.DefaultEditor) woolspinner.getEditor()).getTextField()
				.setEditable(false);
		((JSpinner.DefaultEditor) woolspinner.getEditor()).getTextField()
				.setColumns(2);
		orenum = new SpinnerNumberModel(0, 0, current.getSpecificRessource((ressource)-> ressource instanceof Ore).get(0).getQuantity(), 1);
		orespinner = new JSpinner(orenum);
		((JSpinner.DefaultEditor) orespinner.getEditor()).getTextField()
				.setEditable(false);
		((JSpinner.DefaultEditor) orespinner.getEditor()).getTextField()
				.setColumns(2);
		bricknum = new SpinnerNumberModel(0, 0, current.getSpecificRessource((ressource)-> ressource instanceof Brick).get(0).getQuantity(), 1);
		brickspinner = new JSpinner(bricknum);
		((JSpinner.DefaultEditor) brickspinner.getEditor()).getTextField()
				.setEditable(false);
		((JSpinner.DefaultEditor) brickspinner.getEditor()).getTextField()
				.setColumns(2);
		lumbernum = new SpinnerNumberModel(0, 0, current.getSpecificRessource((ressource)-> ressource instanceof Wood).get(0).getQuantity(), 1);
		lumberspinner = new JSpinner(lumbernum);
		((JSpinner.DefaultEditor) lumberspinner.getEditor()).getTextField()
				.setEditable(false);
		((JSpinner.DefaultEditor) lumberspinner.getEditor()).getTextField()
				.setColumns(2);
		grainnum = new SpinnerNumberModel(0, 0, current.getSpecificRessource((ressource)-> ressource instanceof Grain).get(0).getQuantity(), 1);
		grainspinner = new JSpinner(grainnum);
		((JSpinner.DefaultEditor) grainspinner.getEditor()).getTextField()
				.setEditable(false);
		((JSpinner.DefaultEditor) grainspinner.getEditor()).getTextField()
				.setColumns(2);
		
		exit = new PlayerButton(ImportImages.cancelBtn, size/2,size/2);
		exitLabel = new JLabel("Menu verlassen");
		exitLabel.setFont(new Font("Times New Roman", Font.ITALIC, size/3));

		/**
		 * Both the JSpinners and the SpinnerNumberModels are packaged in arrays for simplicity
		 */
		numbers = new SpinnerNumberModel[] { woolnum, orenum, bricknum,
				lumbernum, grainnum };
		spinners = new JSpinner[] { woolspinner, orespinner, brickspinner,
				lumberspinner, grainspinner };

		/**
		 * Here, the JSpinners are initialized for the trading partner. It will: 
		 * - The initial value is set to 0 
		 * - The minimum value is set to 0 (no negative JSpinners) 
		 * - The maximum value 99 is set After that the JSpinners are set uneditable, 
		 * so that you can not enter letters or weird numbers 
		 * Then the maximum number of digits is set to maximum 2 (at most 99).
		 */
		woolnum2 = new SpinnerNumberModel(0, 0, 99, 1);
		woolspinner2 = new JSpinner(woolnum2);
		((JSpinner.DefaultEditor) woolspinner2.getEditor()).getTextField()
				.setEditable(false);
		((JSpinner.DefaultEditor) woolspinner2.getEditor()).getTextField()
				.setColumns(2);
		orenum2 = new SpinnerNumberModel(0, 0, 99, 1);
		orespinner2 = new JSpinner(orenum2);
		((JSpinner.DefaultEditor) orespinner2.getEditor()).getTextField()
				.setEditable(false);
		((JSpinner.DefaultEditor) orespinner2.getEditor()).getTextField()
				.setColumns(2);
		bricknum2 = new SpinnerNumberModel(0, 0, 99, 1);
		brickspinner2 = new JSpinner(bricknum2);
		((JSpinner.DefaultEditor) brickspinner2.getEditor()).getTextField()
				.setEditable(false);
		((JSpinner.DefaultEditor) brickspinner2.getEditor()).getTextField()
				.setColumns(2);
		lumbernum2 = new SpinnerNumberModel(0, 0, 99, 1);
		lumberspinner2 = new JSpinner(lumbernum2);
		((JSpinner.DefaultEditor) lumberspinner2.getEditor()).getTextField()
				.setEditable(false);
		((JSpinner.DefaultEditor) lumberspinner2.getEditor()).getTextField()
				.setColumns(2);
		grainnum2 = new SpinnerNumberModel(0, 0, 99, 1);
		grainspinner2 = new JSpinner(grainnum2);
		((JSpinner.DefaultEditor) grainspinner2.getEditor()).getTextField()
				.setEditable(false);
		((JSpinner.DefaultEditor) grainspinner2.getEditor()).getTextField()
				.setColumns(2);

		/**
		 * Both the JSpinners and the SpinnerNumberModels are packaged in arrays for simplicity
		 */
		numbers2 = new SpinnerNumberModel[] { woolnum2, orenum2, bricknum2,
				lumbernum2, grainnum2 };
		spinners2 = new JSpinner[] { woolspinner2, orespinner2, brickspinner2,
				lumberspinner2, grainspinner2 };

		/**
		 * This is the dropdown menu for the selection of the trading partner
		 */
		tradepartners = new JComboBox();
		tradepartners.addItem(Messages.getString("TradingMenu.Hafen")); //$NON-NLS-1$
		tradepartners.addItem(Messages.getString("TradingMenu.Spieler")); //$NON-NLS-1$

		/**
		 * In the following, the font for the labels and JComboBoxes is set
		 */
		Font f = new Font("Times New Roman", Font.ITALIC, size/3); //$NON-NLS-1$
		tradepartners.setFont(f);
		ich = new JLabel("Meine Rohstoffe"); //$NON-NLS-1$
		ich.setFont(f);

		/**
		 * In the following, the associated pictures are uploaded and stored in PlayerLabels
		 */
		wool = new PlayerLabel(ImportImages.woolBtn, size, size);
		ore = new PlayerLabel(ImportImages.oreBtn, size, size);
		brick = new PlayerLabel(ImportImages.brickBtn, size, size);
		lumber = new PlayerLabel(ImportImages.lumberBtn, size, size);
		grain = new PlayerLabel(ImportImages.grainBtn, size, size);

		wool2 = new PlayerLabel(ImportImages.woolBtn, size, size);
		ore2 = new PlayerLabel(ImportImages.oreBtn, size, size);
		brick2 = new PlayerLabel(ImportImages.brickBtn, size, size);
		lumber2 = new PlayerLabel(ImportImages.lumberBtn, size, size);
		grain2 = new PlayerLabel(ImportImages.grainBtn, size, size);

		/**
		 * Here the button for the execution of the trade is initialized
		 */
		trade = new PlayerButton(ImportImages.arrowDouble, (int)(1.5*size), size);
		trade.setToolTipText(Messages.getString("TradingMenu.Handeln")); //$NON-NLS-1$
		trade.setEnabled(false);
	}

	/**
	 * Adds the interaction with the <code>Katane</code>.
	 */
	public void setupInteraction() {
		//addMouseListener(katane);

		trade.addActionListener(new ActionListener() {public void
			actionPerformed(ActionEvent e) { /*NEED TO BE EDIT*/ }});
		trade.setActionCommand("deal.trade"); //$NON-NLS-1$
		trade.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				if(((PlayerButton)me.getSource()).isEnabled()){
					((PlayerButton)me.getSource()).changeIcon(ImportImages.arrowDoubleActive);
				}
			}
			public void mouseExited(MouseEvent me){
				if(((PlayerButton)me.getSource()).isEnabled()){
					((PlayerButton)me.getSource()).changeIcon(ImportImages.arrowDouble);
				}
			}
		});

		woolspinner.addChangeListener(this);
		orespinner.addChangeListener(this);
		brickspinner.addChangeListener(this);
		lumberspinner.addChangeListener(this);
		grainspinner.addChangeListener(this);

		woolspinner2.addChangeListener(this);
		orespinner2.addChangeListener(this);
		brickspinner2.addChangeListener(this);
		lumberspinner2.addChangeListener(this);
		grainspinner2.addChangeListener(this);

		tradepartners.addActionListener(this);
		tradepartners.setActionCommand("choose"); //$NON-NLS-1$
		
		exit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				getThis().setVisible(false);	
			}
			public void mouseEntered(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.cancelBtnActive);
			}
			public void mouseExited(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.cancelBtn);
			}
		});

	}

	/**
	 * Adds the ingredients to the <code>TradingMenu</code>.
	 */
	public void addWidgets() {

		add(bgpanel);
		bgpanel.setLayout(new GridBagLayout());

		/**
		 * First column of the grid
		 */
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(ich, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(wool, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(ore, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(brick, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(lumber, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(grain, c);

		/**
		 * Second column of the grid
		 */
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 0);
		bgpanel.add(woolspinner, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 0);
		bgpanel.add(orespinner, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 0);
		bgpanel.add(brickspinner, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 0);
		bgpanel.add(lumberspinner, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 0);
		bgpanel.add(grainspinner, c);

		/**
		 * Third column of the grid
		 */

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 20, 0, 20);
		// bgpanel.add(new JLabel("<- Trade ->"), c);
		bgpanel.add(trade, c);

		/**
		 * Fourth column of the grid
		 */
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridwidth = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(0, 0, 0, 20);
		bgpanel.add(tradepartners, c);

		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 0);
		bgpanel.add(woolspinner2, c);

		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 0);
		bgpanel.add(orespinner2, c);

		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 0);
		bgpanel.add(brickspinner2, c);

		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 4;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 0);
		bgpanel.add(lumberspinner2, c);

		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 5;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 0, 0, 0);
		bgpanel.add(grainspinner2, c);

		/**
		 * column of the grid
		 */

		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(wool2, c);

		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(ore2, c);

		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(brick2, c);

		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 4;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(lumber2, c);

		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 5;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(grain2, c);
		
		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 6;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10,0,5,0);
		bgpanel.add(exit, c);
		
		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 7;
		c.anchor = GridBagConstraints.CENTER;
		bgpanel.add(exitLabel, c);

	}



	/**
	 * The method sets the values of all JSpinners to 0
	 */
	public void reset() {
		for (int i = 0; i < numbers.length; i++) {
			numbers[i].setValue(0);
			numbers2[i].setValue(0);
			lastScoreL[i] = 0;
			lastScoreR[i] = 0;
		}
		counterLeft = 0;
		counterRight = 0;
	}

	/**
	 * Set all steps to the correct size. 
	 * Turn off the ChangeListeners to not trigger any other ChangeEvents.
	 */
	public void update() {
		killAllChangeListener();
		reset();
		int[] playerRes = getResources();
		current = katane.getCurrentPlayer();
		/*
		harbors = current.getHarbors();
		
		if (banktrade) {
			if (harbors.contains(Constants.HARBOR)) {
				// if there is 3 to 1 port, set the rate to 3 to 1
				for (int i = 0; i < rates.length; i++) {
					rates[i] = 3;
				}
			}
			// if one of the special cases exists, the respective value is set to 2
			if (harbors.contains(Constants.WOOLHARBOR)) {
				rates[0] = 2;
			}
			if (harbors.contains(Constants.OREHARBOR)) {
				rates[1] = 2;
			}
			if (harbors.contains(Constants.BRICKHARBOR)) {
				rates[2] = 2;
			}
			if (harbors.contains(Constants.LUMBERHARBOR)) {
				rates[3] = 2;
			}
			if (harbors.contains(Constants.GRAINHARBOR)) {
				rates[4] = 2;
			}
			for (int i = 0; i < spinners.length; i++) {
				// only sets the spinner active if there are enough resources at all
				spinners[i].setEnabled(playerRes[i] >= rates[i]);
				// sets the step size to the type of port the player owns
				numbers[i].setStepSize(rates[i]);
				// sets the Spinnermaximum on the number of the player
//				numbers [i] .setMaximum (playerRes [i]);
				// sets the harbor moths to the maximum number
//				numbers2[i].setMaximum(1);
				spinners2[i].setEnabled(false);
			}
			
		} else {
			for (int i = 0; i < spinners.length; i++) {
				// When playing the spinner is only disabled 
				//if no resources of the category are available
				spinners[i].setEnabled(playerRes[i] != 0);
				// the step size is reset to 1
				numbers[i].setStepSize(1);
				// the maximum of the player's resources is 99
				numbers2[i].setMaximum(99);
				spinners2[i].setEnabled(true);

			}
		}
		*/

		// is updated independently of Bank or PlayerTrade a player 
		//can only specify the resources that he really 
		//has additionally the minima are reset to 0 from the left side
		for (int i = 0; i < numbers.length; i++) {
			numbers[i].setMaximum(playerRes[i]);
			numbers[i].setMinimum(0);
		}
		trade.setEnabled(false);
		reviveAllChangeListener();
	}

	public void stateChanged(ChangeEvent ce) {			
		if (banktrade) {
			JSpinner src = (JSpinner) ce.getSource();
//			SpinnerNumberModel snm = (SpinnerNumberModel) src.getModel ();
//			int max = (Integer) snm.getNumber();
			left = false;
			int[] currentL = new int[5];
			int[] currentR = new int[5];
			source = -1;
			
			killAllChangeListener();
			
			// checks if the stateChanged event takes place 
			//on the left or right side (buttons enable)
			for (int i = 0; i < spinners.length; i++) {
				currentL[i] = numbers[i].getNumber().intValue();
				if (spinners[i].equals(src)) {
					source = i;
					left = true;
					break;
				}
			}
			
			if (left) {
				if (currentL[source] > lastScoreL[source]) {
					counterLeft++;
				}
				if (currentL[source] < lastScoreL[source]) {
					counterLeft--;
				}
				for (int i = 0; i < currentL.length; i++) {
					lastScoreL[i] = currentL[i];
				}
			} else {
				for (int i = 0; i < spinners2.length; i++) {
					currentR[i] = numbers2[i].getNumber().intValue();
					if (spinners2[i].equals(src)) {
						source = i;						
						break;
					}
				}
				if (currentR[source] > lastScoreR[source]) {
					counterRight++;
				}
				if (currentR[source] < lastScoreR[source]) {
					counterRight--;
					setMinAll(numbers, new int[]{0,0,0,0,0});
				}
				for (int i = 0; i < currentR.length; i++) {
					lastScoreR[i] = currentR[i];
				}
			}
			if (counterLeft > counterRight) {
				setMaxAll(numbers2,new int[]{99,99,99,99,99});
				enableAll(spinners2);
			}
			if (counterLeft == 0) {
				disableAll(spinners2);
			}
			if (counterLeft == counterRight) {	
				setMinAll(numbers,lastScoreL);
				setMaxAll(numbers2,lastScoreR);
			}
			
			reviveAllChangeListener();
			
			trade.setEnabled(counterRight == counterLeft && counterRight != 0);
			
		}
		/*
		 * Causes that one must not give away any more.
		 */
		else{
			JSpinner src = (JSpinner) ce.getSource();
			for (int i = 0; i < spinners.length; i++) {
				if (spinners[i].equals(src)) {
					if (spinners[i].getValue().equals(new Integer(0))) {
						spinners2[i].setEnabled(true);
					}
					else{
						spinners2[i].setValue(new Integer(0));
						spinners2[i].setEnabled(false);
					}
					break;
				}
			}
			// the tradeButton only becomes active 
			//if raw materials have been used on both sides
			trade.setEnabled(hasValue(numbers) && hasValue(numbers2));
		}
	}

	public void actionPerformed(ActionEvent ae) {
		banktrade = tradepartners.getSelectedItem().equals(Messages.getString("TradingMenu.Hafen")); //$NON-NLS-1$
		reset();
		update();
	}

	/**
	 * The method disables all JSpinners sps except the JSpinner sp
	 * 
	 * @param sps
	 *            is an array of J spinners
	 * @param sp
	 *            is a single JSpinner
	 */
	public void disableAllBut(JSpinner[] sps, JSpinner sp) {
		for (int i = 0; i < sps.length; i++) {
			if (!sps[i].equals(sp)) {
				sps[i].setEnabled(false);
			}
		}
	}
	/**Disabled all spinners of the passed array
	 * @param sps to disjoint Jspinner array
	 */
	public void disableAll(JSpinner[] sps) {
		for (int i = 0; i < sps.length; i++) {			
				sps[i].setEnabled(false);			
		}
	}
	
	/**Sets all values of the transferred array to 0
	 * @param numbers SpinnerNumberModel Array
	 */
	public void resetAllOf( SpinnerNumberModel[] numbers) {
		for (int i = 0; i < numbers.length; i++) {	
			numbers[i].setValue(new Integer(0));						
		}
	}

	/**
	 * The method sets all JSpinners enabled After that, however, update () 
	 * is called and the spinners, 
	 * which were previously disabled, stay the same
	 * 
	 * @param sps
	 *            is an array of J spinners
	 */
	public void enableAll(JSpinner[] sps) {

		for (int i = 0; i < sps.length; i++) {
			sps[i].setEnabled(true);
		}
	}

	/**
	 * The method returns whether a spinner has a value other than 0
	 * 
	 * @param snm
	 *            is the array with <code>SpinnerNumberModel</code> 
	 * @return true if a value other than 0 is found.
	 */
	public boolean hasValue(SpinnerNumberModel[] snm) {
		boolean hasValue = false;
		for (int i = 0; i < numbers.length; i++) {
			if (!snm[i].getNumber().equals(new Integer(0))) {
				hasValue = true;
				break;
			}
		}
		return hasValue;
	}

	/**
	 * The method returns all raw materials that the player owns as a beautiful array
	 * 
	 * @return Array of raw materials (wool, ore, loam, wood, wheat)
	 */
	public int[] getResources() {
		Player c = katane.getCurrentPlayer();
		return new int[] { 	c.getSpecificRessource((ressource)-> ressource instanceof Wool).get(0).getQuantity(), 
							c.getSpecificRessource((ressource)-> ressource instanceof Ore).get(0).getQuantity(), 
							c.getSpecificRessource((ressource)-> ressource instanceof Brick).get(0).getQuantity(),
							c.getSpecificRessource((ressource)-> ressource instanceof Wood).get(0).getQuantity(), 
							c.getSpecificRessource((ressource)-> ressource instanceof Grain).get(0).getQuantity() };

	}
	
	/**Sets all minima of the array to the values of the second array
	 * @param snm array with spinners
	 * @param value array with the minima to be set
	 */
	public void setMinAll(SpinnerNumberModel[] snm, int[] value){
		for (int i = 0; i < snm.length; i++) {
			snm[i].setMinimum(value[i]);
		}
	}
	
	/**Sets all maxima of the array to the values of the second array
	 * @param snm array with spinners
	 * @param value Array with the maxima to be set
	 */
	public void setMaxAll(SpinnerNumberModel[] snm, int[] value){
		for (int i = 0; i < snm.length; i++) {
			snm[i].setMaximum(value[i]);
		}
	}
	
	/**
	 * Removes all ChangeListeners
	 */
	public void killAllChangeListener(){
		woolspinner.removeChangeListener(this);
		orespinner.removeChangeListener(this);
		brickspinner.removeChangeListener(this);
		lumberspinner.removeChangeListener(this);
		grainspinner.removeChangeListener(this);

		woolspinner2.removeChangeListener(this);
		orespinner2.removeChangeListener(this);
		brickspinner2.removeChangeListener(this);
		lumberspinner2.removeChangeListener(this);
		grainspinner2.removeChangeListener(this);
	}
	
	/**
	 * Adds the ChangeListener again
	 */
	public void reviveAllChangeListener(){
		woolspinner.addChangeListener(this);
		orespinner.addChangeListener(this);
		brickspinner.addChangeListener(this);
		lumberspinner.addChangeListener(this);
		grainspinner.addChangeListener(this);

		woolspinner2.addChangeListener(this);
		orespinner2.addChangeListener(this);
		brickspinner2.addChangeListener(this);
		lumberspinner2.addChangeListener(this);
		grainspinner2.addChangeListener(this);
	}
	
	public TradingMenu getThis(){
		return this;
	}
}
