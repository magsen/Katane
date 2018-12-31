package gui;

import java.awt.*;
import javax.swing.*;
import model.*;
import controller.Controller;

/**
 * The class provides the frontend for accepting the trade function
 * 
 * @author YU Shijia
 */

@SuppressWarnings("serial")
public class ConfirmTradePanel extends JPanel {

	private ImagePanel bgpanel;
	
	/*
	 * the label for the headline
	 */
	private JLabel heading;
	
	private JLabel receive;
	
	private JLabel giveaway;

	private PlayerButton confirm;
	
	private PlayerButton cancel;
	
	/*
	 *The maximum number of commodity buttons
	 */
	private int maximumButtons;

	/*
	 * The icons of the offered raw materials as an array
	 */
	private ImagePanel[] offResPanels;//The class creates a simple JPanel with background
	
	/*
	 * The icons of the expected raw materials as an array
	 */
	private ImagePanel[] expResPanels;

	/*
	 * The size of the buttons
	 */
	private int size;
	
	private Font font;

	/*
	 * The respective number of offered as an array
	 */
	private int[] offR;
	
	int[] expR;
	
	private int width;

	private int height;

	private Controller controller;
	
	/*
	 * The settler who is currently at the turn
	 */
	private Settler current;
	
	/*
	 * A panel with the buttons on it
	 */
	private JPanel buttons;

	/*
	 * @param offR are the raw materials offered
	 * @param expR are the expected rust materials
	 */
	public ConfirmTradePanel(Controller controller, int width, int height,
			int[] offR, int[] expR) {
		this.controller = controller;
		this.offR = offR;
		this.expR = expR;
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));
		this.setOpaque(false);
		this.current = controller.getIsland().getCurrentPlayer();
		font = new Font("Times New Roman", Font.ITALIC, 20); 

		init();
	}

	public void init() {
		createWidgets();
		setupInteraction();
		addWidgets();
	}

	/*
	 * The method creates the necessary objects for the TradingMenu
	 */
	private void createWidgets() {
		int offNum = 0;
		int expNum = 0;
		for (int i = 0; i < offR.length; i++) {
			if (offR[i] != 0) {
				offNum++;
			}
			if (expR[i] != 0) {
				expNum++;
			}
		}
		maximumButtons = Math.max(offNum, expNum);
		
		/*
		 * Here are the sizes set
		 */
		this.size = (int) (width * 0.07);
		/**
		 * Here is the background (still: scroll added)
		 */
		bgpanel = new ImagePanel(ImportImages.chatBg, width, height);

		/*
		 * These are the labels for the headline
		 */
		heading = new JLabel(current.getUsername() + Messages.getString("ConfirmTradePanel.AnfrageHandeln"));
		heading.setFont(font);
		receive = new JLabel(Messages.getString("ConfirmTradePanel.DuBekommst")); 
		receive.setFont(font);
		giveaway = new JLabel(Messages.getString("ConfirmTradePanel.DuGibstAb")); 
		giveaway.setFont(font);

		/*
		 *In the following, the accompanying pictures are loaded and in
		 * PlayerLabels saved
		 */
		offResPanels = new ImagePanel[offNum];
		expResPanels = new ImagePanel[expNum];

		int c = 0;
		for (int i = 0; i < offR.length; i++) {
			if (offR[i] != 0) {
				switch (i) {
				case 0:
					offResPanels[c] = new ImagePanel(ImportImages.woolBtn,
							size, size);
					break;
				case 1:
					offResPanels[c] = new ImagePanel(ImportImages.oreBtn, size,
							size);
					break;
				case 2:
					offResPanels[c] = new ImagePanel(ImportImages.brickBtn,
							size, size);
					break;
				case 3:
					offResPanels[c] = new ImagePanel(ImportImages.lumberBtn,
							size, size);
					break;
				case 4:
					offResPanels[c] = new ImagePanel(ImportImages.grainBtn,
							size, size);
					break;
				}
				offResPanels[c].addLabel("" + offR[i]); 
				offResPanels[c].getLabel().setFont(
						new Font("Times New Roman", Font.BOLD, 26)); 
				c++;
			}
		}

		c = 0;
		for (int i = 0; i < expR.length; i++) {
			if (expR[i] != 0) {
				switch (i) {
				case 0:
					expResPanels[c] = new ImagePanel(ImportImages.woolBtn,
							size, size);
					break;
				case 1:
					expResPanels[c] = new ImagePanel(ImportImages.oreBtn, size,
							size);
					break;
				case 2:
					expResPanels[c] = new ImagePanel(ImportImages.brickBtn,
							size, size);
					break;
				case 3:
					expResPanels[c] = new ImagePanel(ImportImages.lumberBtn,
							size, size);
					break;
				case 4:
					expResPanels[c] = new ImagePanel(ImportImages.grainBtn,
							size, size);
					break;
				}
				expResPanels[c].addLabel("" + expR[i]); 
				expResPanels[c].getLabel().setFont(
						new Font("Times New Roman", Font.BOLD, 26)); 
				c++;
			}
		}

		/*
		 * Cancel and Confirm Buttons
		 */
		buttons = new JPanel();
		buttons.setOpaque(false);
		confirm = new PlayerButton(ImportImages.confirmBtn, size, size);
		cancel = new PlayerButton(ImportImages.cancelBtn, size, size);
	}

	/*
	 * The method sets the Action, Change, and MouseListeners for the
	 * Interaction with the TradingMenu The TradeButton is handled by the controller
	 * intercepted, the other internal elements are processed internally
	 */
	public void setupInteraction() {
		confirm.addActionListener(controller);
		confirm.setActionCommand("conf.confirm"); 
		cancel.addActionListener(controller);
		cancel.setActionCommand("conf.cancel"); 
	}


	public void addWidgets() {

		add(bgpanel);
		bgpanel.setLayout(new GridBagLayout());

		buttons.setLayout(new FlowLayout());
		buttons.add(confirm);
		buttons.add(cancel);

		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 20, 0);
		bgpanel.add(heading, c);

		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 10, 0);
		bgpanel.add(receive, c);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 10, 0);
		bgpanel.add(giveaway, c);

		for (int i = 0; i < offResPanels.length; i++) {
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = i + 2;
			c.anchor = GridBagConstraints.CENTER;
			bgpanel.add(offResPanels[i], c);
		}


		for (int j = 0; j < expResPanels.length; j++) {
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = j + 2;
			c.anchor = GridBagConstraints.CENTER;
			bgpanel.add(expResPanels[j], c);
		}

		/**
		 * buttons
		 */
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = maximumButtons + 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(20, 0, 0, 0);
		bgpanel.add(buttons, c);

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getOffR() {
		return offR;
	}

	public int[] getExpR() {
		return expR;
	}
}
