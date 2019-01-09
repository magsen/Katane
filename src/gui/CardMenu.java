package gui;

import java.awt.*;
import java.awt.event.MouseAdapter;//It is used to receive mouse events, making it easy to create listener objects.
import java.awt.event.MouseEvent;//Mouse events and mouse movement events.
import java.util.ArrayList;//Provides a resizable array and implements the List interface.
import model.Constants;//A class in which all constants are stored.
import javax.swing.*;

import model.IslandOfCatan;//the state of the island.
import model.Settler;//the state of the settler.
import controller.Controller;

/*聽 
 * The class represents the menu with which to buy development cards and
 * @author YU Shijia
 */

@SuppressWarnings("serial")
public class CardMenu extends JPanel {
	/**
	 * Scroll as a background
	 */
	private ImagePanel bgpanel;//creates a simple JPanel with background

	/**
	 * The Arraylist contains the development maps
	 */
	private ArrayList<Byte> dev;//create an ArrayList of bytes

	/**
	 * Text about the buttons
	 */
	private JLabel choose;//JLabel display text, image

	private JLabel choose2;

	/**
	 * knight card
	 */
	private PlayerButton knight;
	
	/**
	 * Knight cards number
	 */
	private ImagePanel knightAmount;

	/**
	 * invention card
	 */
	private PlayerButton invention;
	
	private ImagePanel inventionAmount;

	/**
	 * 鍨勬柇鍗�
	 */
	private PlayerButton monopoly;

	private ImagePanel monopolyAmount;

	private PlayerButton streets;
	
	private ImagePanel streetsAmount;

	/**
	 * Victory Point Card
	 */
	private PlayerButton victory;
	
	private ImagePanel victoryAmount;

	/**
	 * cards on the stack
	 */
	private PlayerButton backside;

	private ImagePanel backsideAmount;
	
	/**
	 * Exit Button
	 */
	private PlayerButton exit;
	/**
	 * Exit Label
	 */
	private JLabel exitLabel;

	/**
	 * Width of the buttons
	 */
	private int btnwidth;

	private int btnheight;
	/**
	 * Button Size
	 */
	private int sbtn;

	/**
	 * Width of the panel
	 */
	private int width;

	private int height;

	private Controller controller;

	/**
	 * The model of the island
	 */
	private IslandOfCatan island;

	/**
	 * The Current Settler
	 */
	private Settler settler;

	/**
	 * Creates an instance of CardMenus Here you can opt for the
     * Decide play or for the purchase of a development map
	 */
	public CardMenu(Controller controller, int width, int height) {
		this.controller = controller;
		this.width = (int) (width*0.9);
		this.height = (int) (height*0.9);
		this.setPreferredSize(new Dimension(width, height));
		this.setOpaque(false);
		this.island = controller.getIsland();
		this.settler = island.getCurrentPlayer();

		init();
	}

	/**
	 * This initializes the GUI
	 */
	public void init() {
		createWidgets();
		setupInteraction();
		addWidgets();
	}

	/**
	 * This creates the GUI elements
	 */
	private void createWidgets() {
		/**
		 * Background panel (scroll)
		 */
		bgpanel = new ImagePanel(ImportImages.chatBg, width, height);

		btnwidth = (int) (width * 0.13);
		btnheight = (int) (width * 0.13);
		sbtn = btnheight/3;

		knight = new PlayerButton(ImportImages.knightBtn, btnwidth, btnheight);
		knight.setToolTipText("Knight");
		knightAmount = new ImagePanel(ImportImages.buttonInvert, sbtn, sbtn);
		knightAmount.addLabel("0", Color.WHITE);//Add an existing label to jPanel
		invention = new PlayerButton(ImportImages.inventionBtn, btnwidth, btnheight);
		invention.setToolTipText("Invention");
		inventionAmount = new ImagePanel(ImportImages.buttonInvert, sbtn, sbtn);
		inventionAmount.addLabel("0", Color.WHITE);
		monopoly = new PlayerButton(ImportImages.monopolyBtn, btnwidth, btnheight);
		monopoly.setToolTipText("Monopoly");
		monopolyAmount = new ImagePanel(ImportImages.buttonInvert, sbtn, sbtn);
		monopolyAmount.addLabel("0", Color.WHITE);
		streets = new PlayerButton(ImportImages.buildStreetBtn, btnwidth, btnheight);
		streets.setToolTipText("Road");
		streetsAmount = new ImagePanel(ImportImages.buttonInvert, sbtn, sbtn);
		streetsAmount.addLabel("0", Color.WHITE);
		victory = new PlayerButton(ImportImages.victoryPointBtn, btnwidth, btnheight);
		victory.setToolTipText("Victory");
		victoryAmount = new ImagePanel(ImportImages.buttonInvert, sbtn, sbtn);
		victoryAmount.addLabel("0", Color.WHITE);
		backside = new PlayerButton(ImportImages.buyCardBtn, btnwidth, btnheight);
		backside.setToolTipText("Draw a development card!");
		backsideAmount = new ImagePanel(ImportImages.buttonInvert, sbtn, sbtn);
		backsideAmount.addLabel(""+Constants.DEVCARDS_MAX, Color.WHITE); //constant ： A class in which all constants are stored . DEVCARDS_MAX: max number of development cards
		
		exit = new PlayerButton(ImportImages.cancelBtn, sbtn, sbtn);
		exitLabel = new JLabel("Leave the menu");
		exitLabel.setFont(new Font("Times New Roman", Font.ITALIC, sbtn/2));//鏂滀綋瀛�

		Font f = new Font("Times New Roman", Font.ITALIC, (int)(sbtn * 0.7)); 
		choose = new JLabel(Messages.getString("CardMenu.Ausspielen")); 
		choose.setFont(f);
		choose2 = new JLabel(
				Messages.getString("CardMenu.Kaufen")); //$NON-NLS-1$
		choose2.setFont(f);

	}

	/**
	 *  Adds the CardMenu interaction with the Controller.
	 */
	public void setupInteraction() {
		addMouseListener(controller);

		knight.addActionListener(controller);
		knight.setActionCommand("card.knight"); 
		knight.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){ // Invoked when the mouse enters a component
				if(((PlayerButton)me.getSource()).isEnabled()){
				//getSource : The object that the event originally occurred.
				//isEnabled : Determine if the component is available.
					((PlayerButton)me.getSource()).changeIcon(ImportImages.knightBtnActive);//changeIcon : Changes the image of a button to img.
				}
			}
			public void mouseExited(MouseEvent me){ // Invoked when the mouse exits a component.
				if(((PlayerButton)me.getSource()).isEnabled()){
					((PlayerButton)me.getSource()).changeIcon(ImportImages.knightBtn);
				}
			}
		});
		invention.addActionListener(controller);
		invention.setActionCommand("card.invention");
		invention.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				if(((PlayerButton)me.getSource()).isEnabled()){
					((PlayerButton)me.getSource()).changeIcon(ImportImages.inventionBtnActive);
				}
			}
			public void mouseExited(MouseEvent me){
				if(((PlayerButton)me.getSource()).isEnabled()){
					((PlayerButton)me.getSource()).changeIcon(ImportImages.inventionBtn);
				}
			}
		});
		monopoly.addActionListener(controller);
		monopoly.setActionCommand("card.monopoly"); 
		monopoly.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				if(((PlayerButton)me.getSource()).isEnabled()){
					((PlayerButton)me.getSource()).changeIcon(ImportImages.monopolyBtnActive);
				}
			}
			public void mouseExited(MouseEvent me){
				if(((PlayerButton)me.getSource()).isEnabled()){
					((PlayerButton)me.getSource()).changeIcon(ImportImages.monopolyBtn);
				}
			}
		});
		streets.addActionListener(controller);
		streets.setActionCommand("card.streets"); 
		streets.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				if(((PlayerButton)me.getSource()).isEnabled()){
					((PlayerButton)me.getSource()).changeIcon(ImportImages.buildStreetBtnActive);
				}
			}
			public void mouseExited(MouseEvent me){
				if(((PlayerButton)me.getSource()).isEnabled()){
					((PlayerButton)me.getSource()).changeIcon(ImportImages.buildStreetBtn);
				}
			}
		});
		victory.addActionListener(controller);
		victory.setActionCommand("card.victory"); 
		backside.addActionListener(controller);
		backside.setActionCommand("card.draw");
		backside.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				if(((PlayerButton)me.getSource()).isEnabled()){
					((PlayerButton)me.getSource()).changeIcon(ImportImages.buyCardBtnActive);
				}
			}
			public void mouseExited(MouseEvent me){
				if(((PlayerButton)me.getSource()).isEnabled()){
					((PlayerButton)me.getSource()).changeIcon(ImportImages.buyCardBtn);
				}
			}
		});
		exit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){ // Invoked when the mouse button has been clicked (pressedand released) on a component.
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
	 * 灏嗗悇涓粍浠舵坊鍔犲埌 Cardmenu銆� 
	 */
	public void addWidgets() {

		add(bgpanel);
		bgpanel.setLayout(new GridBagLayout());

		/**
		 * top row
		 */

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 7;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 10, 0);
		bgpanel.add(choose, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 7;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 20, 0);
		bgpanel.add(choose2, c);

		/**
		 * second row
		 */
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		//Place the component at the corner of its display area, and the first line of text on the page usually begins with the ComponentOrientation.
		bgpanel.add(inventionAmount, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 10, 10, 40);
		bgpanel.add(invention, c);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(streetsAmount, c);
		
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 10, 10, 40);
		bgpanel.add(streets, c);

		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(monopolyAmount, c);
		
		c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
//		c.insets = new Insets(10, 10, 10, 10);
		bgpanel.add(monopoly, c);

		/**
		 * third row
		 */
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(knightAmount, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 10, 10, 40);
		bgpanel.add(knight, c);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(victoryAmount, c);
		
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 10, 10, 40);
		bgpanel.add(victory, c);

		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		bgpanel.add(backsideAmount, c);

		c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
//		c.insets = new Insets(10, 10, 10, 10);
		bgpanel.add(backside, c);
		
		/**
		 * fourth row
		 */
		
		c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 4;
		c.anchor = GridBagConstraints.SOUTHEAST;
		c.insets = new Insets(10, 20, 5, 20);
		bgpanel.add(exit, c);
		
		/**
		 * fifth row
		 */
		c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 5;
		c.anchor = GridBagConstraints.SOUTHEAST;
		bgpanel.add(exitLabel, c);
	}

	/**
	 * Updating the map menu
	 */
	public void update() {
		settler = island.getCurrentPlayer();
		dev = settler.getDevelopmentCards();
		invention.setEnabled(dev.contains(Constants.GETRESOURCES));//Enables (or disables) the button.
		/*boolean java.util.ArrayList.contains(Object o) : 
		  Returns true if this list contains the specified element.More formally, returns true if and only if this list containsat least one element e 
		*/
		inventionAmount.getLabel().setText(settler.getAmountOfDevCard(Constants.GETRESOURCES) + "");
		streets.setEnabled(dev.contains(Constants.BUILDSTREETS));
		streetsAmount.getLabel().setText(settler.getAmountOfDevCard(Constants.BUILDSTREETS) + "");
		monopoly.setEnabled(dev.contains(Constants.MONOPOLY));
		monopolyAmount.getLabel().setText(settler.getAmountOfDevCard(Constants.MONOPOLY) + "");
		knight.setEnabled(dev.contains(Constants.KNIGHT));
		knightAmount.getLabel().setText(settler.getAmountOfDevCard(Constants.KNIGHT) + "");
		victory.setEnabled(dev.contains(Constants.VICTORYPOINTS));
		victoryAmount.getLabel().setText(settler.getAmountOfDevCard(Constants.VICTORYPOINTS) + "");
		backside.setEnabled(settler.getWool() >= 1 && settler.getOre() >= 1 && settler.getGrain() >= 1);
		backsideAmount.getLabel().setText((Constants.DEVCARDS_MAX - controller.getIsland().getDrawnDevCard())+ "");
	}
	
	public CardMenu getThis(){
		return this;
	}
}
