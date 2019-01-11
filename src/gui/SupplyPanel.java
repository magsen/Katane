package gui;

import java.awt.*;
import javax.swing.*;
import model.*;
import Katane.Brick;
import Katane.Grain;
import Katane.Katane;
import Katane.Ore;
import Katane.Player;
import Katane.Wood;
import Katane.Wool;

/**
 * The class creates the GUI element Supply Panel, 
 * where the available resources of the player are displayed and updated at any time.
 * 该类创建GUI元素“供应面板”，随时显示和更新播放器的可用资源。
 * @author LIAO Haoyun
 */

@SuppressWarnings("serial")
public class SupplyPanel extends JPanel {

	/**
	 * Wool Icon
	 */
	private ImagePanel wool;

	/**
	 * Ore Icon
	 */
	private ImagePanel ore;

	/**
	 * Clay Icon
	 */
	private ImagePanel brick;

	/**
	 * Wood symbol
	 */
	private ImagePanel lumber;

	/**
	 * Cereal icon
	 */
	private ImagePanel grain;

	/**
	 * Number of victory points icon
	 */
	private ImagePanel victoryPoints;

	/**
	 * Number of penalty icon
	 */
	private ImagePanel road;

	/**
	 * Number of settlements icon
	 */
	private ImagePanel settlement;

	/**
	 * 	Number of cities icon
	 */
	private ImagePanel city;

	/**
	 * Number of raw material map icon
	 */
	private ImagePanel cards;

	/**
	 * Number of knights icon
	 */
	private ImagePanel army;

	/**
	 * The good old katane
	 */
	private Katane katane;

	/**
	 * The height of the panel
	 */
	private int height;

	/**
	 * Size of the icons
	 */
	private int size;

	/**
	 * The constructor creates a new supply panel
	 * 
	 * @param katane
	 *            is the katane
	 * @param width
	 *            is the width of the panel
	 * @param height
	 *            is the height of the panel
	 */
	public SupplyPanel(Katane katane, int width, int height) {
		this.katane = katane;
		this.height = height;
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(width, height));
		init();
	}

	/**
	 * Initialize that <code>SupplyPanel</code>.
	 */
	public void init() {
		createWidgets();
		addWidgets();
	}

	/**
	 * Creates the components of <code>SupplyPanels</code>.
	 */
	public void createWidgets() {
		size = (int) (height * 0.14);

		/**
		 * Buttons are created in the size <code>size</code>
		 */
		wool = new ImagePanel(ImportImages.woolBtn, size, size);
		ore = new ImagePanel(ImportImages.oreBtn, size, size);
		brick = new ImagePanel(ImportImages.brickBtn, size, size);
		lumber = new ImagePanel(ImportImages.lumberBtn, size, size);
		grain = new ImagePanel(ImportImages.grainBtn, size, size);
		victoryPoints = new ImagePanel(ImportImages.victoryPointBtn, size, size);
		road = new ImagePanel(ImportImages.roadBtn, size, size);
		settlement = new ImagePanel(ImportImages.settlementBtn, size, size);
		city = new ImagePanel(ImportImages.cityBtn, size, size);
		cards = new ImagePanel(ImportImages.cardBtn, size, size);
		army = new ImagePanel(ImportImages.armyLbl, size, size);

		/**
		 * To the icons labels with the respective numbers and tool tips are added
		 */
		wool.addLabel("0", Color.BLACK); //$NON-NLS-1$
		wool.setToolTipText(Messages.getString("SupplyPanel.Wolle")); //$NON-NLS-1$
		ore.addLabel("0", Color.BLACK); //$NON-NLS-1$
		ore.setToolTipText(Messages.getString("SupplyPanel.Erz")); //$NON-NLS-1$
		brick.addLabel("0", Color.BLACK); //$NON-NLS-1$
		brick.setToolTipText(Messages.getString("SupplyPanel.Lehm")); //$NON-NLS-1$
		lumber.addLabel("0", Color.BLACK); //$NON-NLS-1$
		lumber.setToolTipText(Messages.getString("SupplyPanel.Holz")); //$NON-NLS-1$
		grain.addLabel("0", Color.BLACK); //$NON-NLS-1$
		grain.setToolTipText(Messages.getString("SupplyPanel.Weizen")); //$NON-NLS-1$
		victoryPoints.addLabel("0", Color.BLACK); //$NON-NLS-1$
		victoryPoints.setToolTipText(Messages.getString("SupplyPanel.Siegpunkte")); //$NON-NLS-1$
		road.addLabel("15", Color.BLACK); //$NON-NLS-1$
		road.setToolTipText(Messages.getString("SupplyPanel.BenoetigenFall1")); //$NON-NLS-1$
		settlement.addLabel("5", Color.BLACK); //$NON-NLS-1$
		settlement
				.setToolTipText(Messages.getString("SupplyPanel.BenoetigenFall2")); //$NON-NLS-1$
		city.addLabel("4", Color.BLACK); //$NON-NLS-1$
		city.setToolTipText(Messages.getString("SupplyPanel.BenoetigenFall3")); //$NON-NLS-1$
		cards.addLabel("0", Color.BLACK); //$NON-NLS-1$
		cards.setToolTipText(Messages.getString("SupplyPanel.BenoetigenFall4")); //$NON-NLS-1$
		army.addLabel("0", Color.BLACK); //$NON-NLS-1$
		army.setToolTipText(Messages.getString("SupplyPanel.Ritter")); //$NON-NLS-1$
	}

	/**
	 * Adds all components to the <code>SupplyPanel</code>.
	 */
	public void addWidgets() {
		/**
		 * Initialization and maintenance of the gridbag with the elements
		 */
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 5, 0);
		add(wool, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 5, 0);
		add(ore, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 5, 0);
		add(brick, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 5, 0);
		add(lumber, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 5, 0);
		add(grain, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 5, 0);
		add(victoryPoints, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(-size / 2, 0, 5, 10);
		add(road, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(-size / 2, 0, 5, 10);
		add(settlement, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(-size / 2, 0, 5, 10);
		add(city, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(-size / 2, 0, 5, 10);
		add(cards, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(-size / 2, 0, 5, 10);
		add(army, c);
	}

	/**
	 * Updates the display of the SupplyPanel.
	 */
	public void update() {
		Player settler = katane.getCurrentPlayer();
		wool.setText("" + settler.getSpecificRessource((ressource)-> ressource instanceof Wool).get(0).getQuantity()); //$NON-NLS-1$
		ore.setText("" + settler.getSpecificRessource((ressource)-> ressource instanceof Ore).get(0).getQuantity()); //$NON-NLS-1$
		brick.setText("" + settler.getSpecificRessource((ressource)-> ressource instanceof Brick).get(0).getQuantity()); //$NON-NLS-1$
		lumber.setText("" + settler.getSpecificRessource((ressource)-> ressource instanceof Wood).get(0).getQuantity()); //$NON-NLS-1$
		grain.setText("" + settler.getSpecificRessource((ressource)-> ressource instanceof Grain).get(0).getQuantity()); //$NON-NLS-1$
		victoryPoints.setText("" //$NON-NLS-1$
				+ (settler.getVictoryPoint()) );//+ settler
					//	.getAmountOfDevCard(Constants.VICTORYPOINTS)));
	/*	
	cards.setText("" //$NON-NLS-1$
				+ (settler.getAmountOfDevCards() + settler
						.getAmountOfTempDevCards()));
		road.setText("" + (Constants.ROADS_MAX - settler.getRoadCount())); //$NON-NLS-1$
		settlement.setText("" //$NON-NLS-1$
				+ (Constants.SETTLEMENTS_MAX - settler.getSettlementCount()));
		city.setText("" + (Constants.CITIES_MAX - settler.getCityCount())); //$NON-NLS-1$
		army.setText("" + settler.getArmyCount()); //$NON-NLS-1$
		*/
	}
	/*
	c.getSpecificRessource((ressource)-> ressource instanceof Wool).get(0).getQuantity(), 
	c.getSpecificRessource((ressource)-> ressource instanceof Ore).get(0).getQuantity(),
	c.getSpecificRessource((ressource)-> ressource instanceof Brick).get(0).getQuantity(),
	c.getSpecificRessource((ressource)-> ressource instanceof Wood).get(0).getQuantity(), 
	c.getSpecificRessource((ressource)-> ressource instanceof Grain).get(0).getQuantity() };
	 */
}
