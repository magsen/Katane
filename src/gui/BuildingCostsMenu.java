package gui;

import java.awt.*;
import javax.swing.*;

/*
 * This class represents the construction cost from the GUI
 * @ auther Yu Shijia
 */

@SuppressWarnings("serial")
public class BuildingCostsMenu extends JPanel {
	//Can place components such as buttons and text boxes in JPanel
	//可在JPanel中放置按，文本框等
	
	/**
	 * The width of the panel 面板宽度
	 */
	private int width;

	/**
	 * The height
	 */
	private int height;

	/**
	 * The background panel
	 */
	private ImagePanel bgpanel;

	/**
	 * A small house icon 结算图标
	 */
	private ImagePanel settlementpanel;

	/**
	 * A road icon
	 */
	private ImagePanel roadpanel;

	/**
	 * A city icon
	 */
	private ImagePanel citypanel;

	/**
	 * A development map icon
	 */
	private ImagePanel cardpanel;

	/**
	 * equals sign
	 */
	private JLabel costs1;

	private JLabel costs2;

	private JLabel costs3;

	private JLabel costs4;

	/**
	 * Wool Icon
	 */
	private ImagePanel wool;

	private ImagePanel wool1;

	
	/**
	 * Ore
	 */
	private ImagePanel ore;

	private ImagePanel ore1;

	private ImagePanel ore2;

	private ImagePanel ore3;
	
	/**
	 * Clay
	 */
	private ImagePanel brick;

	private ImagePanel brick1;

	/**
	 * Wood
	 */
	private ImagePanel lumber;

	private ImagePanel lumber1;

	/**
	 * Celery
	 */
	private ImagePanel grain;

	private ImagePanel grain1;

	private ImagePanel grain2;

	private ImagePanel grain3;

	//Constructor of the display of the construction cost menu(With and height)
	
	public BuildingCostsMenu(int width, int height) {
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));//
		this.setOpaque(false);//Transparent effect
		init();//Specify the init() method as the first execution.

	}

	/**
	 * initialization
	 */
	public void init() {
		createWidgets();//小部件
		setupInteraction();
		addWidgets();
	}

	private void createWidgets() {
		int size = (int) (height / 13);

		bgpanel = new ImagePanel(ImportImages.chatBg, width, height);

		roadpanel = new ImagePanel(ImportImages.roadBtn, size, size);
		settlementpanel = new ImagePanel(ImportImages.settlementBtn, size, size);
		citypanel = new ImagePanel(ImportImages.cityBtn, size, size);
		cardpanel = new ImagePanel(ImportImages.cardBtn, size, size);

		wool = new ImagePanel(ImportImages.woolBtn, size, size);
		wool1 = new ImagePanel(ImportImages.woolBtn, size, size);
		ore = new ImagePanel(ImportImages.oreBtn, size, size);
		ore1 = new ImagePanel(ImportImages.oreBtn, size, size);
		ore2 = new ImagePanel(ImportImages.oreBtn, size, size);
		ore3 = new ImagePanel(ImportImages.oreBtn, size, size);
		brick = new ImagePanel(ImportImages.brickBtn, size, size);
		brick1 = new ImagePanel(ImportImages.brickBtn, size, size);
		lumber = new ImagePanel(ImportImages.lumberBtn, size, size);
		lumber1 = new ImagePanel(ImportImages.lumberBtn, size, size);
		grain = new ImagePanel(ImportImages.grainBtn, size, size);
		grain1 = new ImagePanel(ImportImages.grainBtn, size, size);
		grain2 = new ImagePanel(ImportImages.grainBtn, size, size);
		grain3 = new ImagePanel(ImportImages.grainBtn, size, size);

		Font f = new Font("Times New Roman", Font.BOLD, 50); //粗体字

		costs1 = new JLabel("="); //Create an instance with the specified text
		costs1.setFont(f);
		costs2 = new JLabel("="); 
		costs2.setFont(f);
		costs3 = new JLabel("=");
		costs3.setFont(f);
		costs4 = new JLabel("=");
		costs4.setFont(f);
	}

	private void setupInteraction() {

		wool.setToolTipText(Messages.getString("BuildingCostsMenu.Wolle")); //When the mouse pauses on the button, a message will be displayed.
		wool1.setToolTipText(Messages.getString("BuildingCostsMenu.Wolle")); 
		ore.setToolTipText(Messages.getString("BuildingCostsMenu.Eisen")); 
		ore1.setToolTipText(Messages.getString("BuildingCostsMenu.Eisen")); 
		ore2.setToolTipText(Messages.getString("BuildingCostsMenu.Eisen"));
		ore3.setToolTipText(Messages.getString("BuildingCostsMenu.Eisen")); 
		brick.setToolTipText(Messages.getString("BuildingCostsMenu.Lehm"));
		brick1.setToolTipText(Messages.getString("BuildingCostsMenu.Lehm"));
		lumber.setToolTipText(Messages.getString("BuildingCostsMenu.Holz")); 
		lumber1.setToolTipText(Messages.getString("BuildingCostsMenu.Holz"));
		grain.setToolTipText(Messages.getString("BuildingCostsMenu.Weizen"));
		grain1.setToolTipText(Messages.getString("BuildingCostsMenu.Weizen")); 
		grain2.setToolTipText(Messages.getString("BuildingCostsMenu.Weizen")); 
		grain3.setToolTipText(Messages.getString("BuildingCostsMenu.Weizen")); 

		roadpanel.setToolTipText(Messages.getString("BuildingCostsMenu.Strasse"));
		settlementpanel.setToolTipText(Messages.getString("BuildingCostsMenu.Siedlung"));
		citypanel.setToolTipText(Messages.getString("BuildingCostsMenu.Stadt")); 
		cardpanel.setToolTipText(Messages.getString("BuildingCostsMenu.Entwicklungskarte"));

	}

	private void addWidgets() {
		add(bgpanel);
		bgpanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);//Specify the space left around (ie, up, down, left, and right)
		bgpanel.add(roadpanel, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 20, 0, 20);
		bgpanel.add(costs1, c);

		c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(brick, c);

		c = new GridBagConstraints();
		c.gridx = 6;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(lumber, c);

		/**
		 * small house
		 */
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(settlementpanel, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 20, 0, 20);
		bgpanel.add(costs2, c);

		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(grain, c);

		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(wool, c);

		c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(brick1, c);

		c = new GridBagConstraints();
		c.gridx = 6;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(lumber1, c);

		/**
		 * city
		 */
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(citypanel, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 20, 0, 20);
		bgpanel.add(costs3, c);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(grain2, c);

		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(grain1, c);

		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(ore1, c);

		c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(ore2, c);

		c = new GridBagConstraints();
		c.gridx = 6;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(ore, c);

		/**
		 * Special card
		 */
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(cardpanel, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 20, 0, 20);
		bgpanel.add(costs4, c);

		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(grain3, c);

		c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(wool1, c);

		c = new GridBagConstraints();
		c.gridx = 6;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 5);
		bgpanel.add(ore3, c);

	}
}
