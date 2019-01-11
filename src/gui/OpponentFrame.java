package gui;

import java.awt.*;
import Katane.Player;

/**
 * Display of the opponent.
 * 
 * @author YU Shijia
 * 
 */
@SuppressWarnings("serial")
public class OpponentFrame extends PlayerFrame { //Player and status display of the own player as well as the opponent.

	// Display of the number of knight cards played.
	private ImagePanel armyPanel;
	/*
	 * Indication of the length of the longest commercial road
	 */
	private ImagePanel roadsPanel;
	/*
	 * Indicator of the number of development cards in the hand.
	 */
	private ImagePanel cardsPanel;
	/*
	 * Indicator of the number of raw material cards in the hand.
	 */
	private ImagePanel resourcesPanel;
	/*
	 * Display of victory points.
	 */
	private ImagePanel victoryPointsPanel;
	/*
	 * The settler for the OpponentFrame.
	 */
	private Player opponent;

	/*
	 * Produces a Gui representation of the opponent
	 */
	public OpponentFrame(Image img, int width, int height) {
		setWidth(width);
		setHeight(height);
		setPreferredSize(new Dimension(width, height));
		setOpaque(false);
		setAvatarImage(img);
		init();
	}

	public void init() {
		createLabels();
		addLabels();
		createWidgets();
		addWidgets();
	}

	/*
	 * Create the labels for the counters on the individual display icons.
	 */
	public void createLabels() {
		int labelWidth = getComponentWidth();
		int labelHeight = getComponentHeight();
		armyPanel = new ImagePanel(ImportImages.armyLbl, labelWidth,
				labelHeight);
		roadsPanel = new ImagePanel(ImportImages.roadLbl, labelWidth,
				labelHeight);
		cardsPanel = new ImagePanel(ImportImages.cardBtn, labelWidth,
				labelHeight);
		resourcesPanel = new ImagePanel(ImportImages.resourceLbl, labelWidth,
				labelHeight);
		victoryPointsPanel = new ImagePanel(ImportImages.victoryPointBtn,
				labelWidth, labelHeight);
		armyPanel.setToolTipText(Messages.getString("OpponentFrame.Ritter")); 
		roadsPanel.setToolTipText(Messages.getString("OpponentFrame.LaengsteHandelsstrasse")); 
		cardsPanel.setToolTipText(Messages.getString("OpponentFrame.Entwicklungskarten"));
		resourcesPanel.setToolTipText(Messages.getString("OpponentFrame.Rohstoffe")); 
		victoryPointsPanel.setToolTipText(Messages.getString("OpponentFrame.Siegespunkte")); 
	}


	public void addLabels() {
		addComponent(armyPanel);
		addComponent(roadsPanel);
		addComponent(cardsPanel);
		addComponent(resourcesPanel);
		addComponent(victoryPointsPanel);
	}

	/*
	 * The method makes it possible to take the opponent (settler) from the model as the basis for the GUI.
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
		//armyPanel.addLabel("" + this.opponent.getArmyCount());
		//roadsPanel.addLabel("" + this.opponent.getLongestRoadLength()); 
		//cardsPanel.addLabel("" + this.opponent.getAmountOfDevCards()); 
		
		/* to be add */
		//resourcesPanel.addLabel("" + this.opponent.getAmountOfResources()); 
		
		victoryPointsPanel.addLabel("" + this.opponent.getVictoryPoint()); 
		addNameLbl(this.opponent.getPlayerNumber()+"", opponent.getColor());
		addColorLbl(opponent.getColor());
	}

	/*
	 * Updates the display values.
	 */
	public void update() {
		//armyPanel.setText("" + opponent.getArmyCount()); 
		//roadsPanel.setText("" + opponent.getLongestRoadLength()); 
		//cardsPanel.setText("" + opponent.getAmountOfDevCards());
		
		/* to be add */
		//resourcesPanel.setText("" + opponent.getAmountOfResources());
		
		victoryPointsPanel.setText("" + opponent.getVictoryPoint()); 
	}

	
	public Player getOpponent() {
		return opponent;
	}
}


