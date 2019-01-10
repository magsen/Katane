package gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Player and status display of the own player as well as the opponent.
 * 玩家和状态显示自己的玩家以及对手。
 * 
 * @author LIAO Haoyun
 * 
 */
@SuppressWarnings("serial")
public class PlayerFrame extends JPanel {

	/**
	 * Own ImagePanel for the halo (indicating who's turn)
	 */
	private ImagePanel myTurnPanel;
	/**
	 * Display image (avatar) of the <code>PlayerFrames</code>.
	 */
	private Image img;
	/**
	 * Width of the <code>PlayerFrames</code>.
	 */
	private int width;
	/**
	 * The height of the <code>PlayerFrames</code>.
	 */
	private int height;
	/**
	 * Width of the avatar.
	 */
	private int panelWidth;
	/**
	 * Listen to the avatar.
	 */
	private int panelHeight;
	/**
	 * Contains the avatar.
	 */
	private ImagePanel avatarPanel;
	/**
	 * Vector with the individual <code> JComponents </ code> 
	 * that can be added to the <code> PlayerFrame </ code>.
	 * 使用可以添加到<code> PlayerFrame </ code>的单个<code> JComponents </ code>的向量。
	 */
	private Vector<JComponent> components = new Vector<JComponent>();
	/**
	 * Display of the longest commercial road.
	 * 显示最长路
	 */
	private PlayerLabel lblRoad;
	/**
	 * Display of the largest knight power.
	 * 显示最大的骑士力量。
	 */
	private PlayerLabel lblArmy;
	/**
	 * Contains the components of <code> PlayerFrame </ code> 
	 * at different levels.
	 * 包含不同级别的<code> PlayerFrame </ code>的组件。
	 */
	private JLayeredPane bgPane;
	
	/**
	 * Width of the Buttons.
	 */
	private int buttonWidth;
	/**
	 * Height of the Buttons.
	 */
	private int buttonHeight;
	/**
	 * Width of the Labels.
	 */
	private int labelWidth;
	/**
	 * Height of the Labels
	 */
	private int labelHeight;
	/**
	 * Panel distance.
	 */
	private int panelDistance;
	/**
	 * Display of the color of the player.
	 */
	private ImagePanel colorPanel;


	/**
	 * Creates the components of the <code> PlayerFrame </ code>.
	 */
	public void createWidgets() {

		panelWidth = (int) (width * 0.8);
		panelHeight = (int) (height * 0.8);
		buttonWidth = (int) (width * 0.2);
		buttonHeight = (int) (height * 0.2);
		labelWidth = (int) (width * 0.13);
		labelHeight = (int) (height * 0.13);
		panelDistance = width - panelWidth;

		bgPane = new JLayeredPane();
		bgPane.setPreferredSize(new Dimension(width, height));

		myTurnPanel = new ImagePanel(ImportImages.myturn, panelWidth,
				panelHeight);
		myTurnPanel.setBounds(panelDistance, buttonHeight / 2, panelWidth,
				panelHeight);
		myTurnPanel.setVisible(false);

		/*
		avatarPanel = new ImagePanel(img, panelWidth, panelHeight);
		avatarPanel.setBounds(panelDistance, buttonHeight / 2, panelWidth,
				panelHeight);
		*/

		lblRoad = new PlayerLabel(ImportImages.roadLbl, labelWidth, labelHeight);
		lblArmy = new PlayerLabel(ImportImages.armyLbl, labelWidth, labelHeight);
		lblRoad.setBounds(width - (int) (panelWidth * 0.3),
				(int) (panelHeight * 0.15), labelWidth, labelHeight);
		lblArmy.setBounds(width - (int) (panelWidth * 0.18),
				(int) (panelHeight * 0.3), labelWidth, labelHeight);

		lblRoad.setVisible(false);
		lblArmy.setVisible(false);

		lblRoad.setToolTipText(Messages.getString("PlayerFrame.LaengsteHandelsstrasse")); //$NON-NLS-1$
		lblArmy.setToolTipText(Messages.getString("PlayerFrame.GroessteRittermacht")); //$NON-NLS-1$

		components.elementAt(0).setBounds(
				panelDistance + (int) (buttonWidth * 0.28), buttonHeight / 2,
				buttonWidth, buttonHeight);
		components.elementAt(1).setBounds(
				panelDistance - (int) (buttonWidth * 0.3),
				(int) (panelHeight * 0.35), buttonWidth, buttonHeight);
		components.elementAt(2).setBounds(
				panelDistance - (int) (buttonWidth * 0.3),
				(int) (panelHeight * 0.62), buttonWidth, buttonHeight);
		components.elementAt(3).setBounds(
				panelDistance + (int) (buttonWidth * 0.3),
				(int) (panelHeight * 0.85), buttonWidth, buttonHeight);

		if (components.size() == 5) {
			components.elementAt(4).setBounds(
					panelDistance + (int) (buttonWidth * 1.3),
					(int) (panelHeight * 0.02), buttonWidth, buttonHeight);
		}
	}

	/**
	 * Adds the components of the <code> PlayerFrame </ code> 
	 * to the <code> PlayerFrames </ code>.
	 * 将<code> PlayerFrame </ code>的组件添加到<code> PlayerFrames </ code>。
	 */
	public void addWidgets() {

		for (int i = 0; i < components.size(); i++) {
			bgPane.add(components.elementAt(i), new Integer(2));
		}
		bgPane.add(lblRoad, new Integer(3));
		bgPane.add(lblArmy, new Integer(3));
		bgPane.add(myTurnPanel, new Integer(1));
		//bgPane.add(avatarPanel, new Integer(0));

		add(bgPane);
	}

	/**
	 * Add a <code>JComponent</code> to the <code>Vector</code>, 
	 * which contains the additional components of the <code>PlayerFrame</code>. 
	 * (Maximum 5 m)
	 * 
	 * @param component
	 *            any <code> JComponent </ code> 
	 *            to be added to the <code> PlayerFrame </ code>
	 */
	protected void addComponent(JComponent component) {
		if (components.size() != 5) {
			components.add(component);
		}
	}

	/**
	 * Adds a <code> JLabel </ code> with any text to <code> PlayerFrame </ code>.
	 * 
	 * @param username
	 *            display Text (Username)
	 */
	public void addNameLbl(String username) {
		int lblWidth = (int) (width * 0.6);
		int lblHeight = (int) (height * 0.3);
		ImagePanel namePanel = new ImagePanel(ImportImages.nameLbl, lblWidth,
				lblHeight);
		namePanel.setBounds((int) (panelWidth * 0.4),
				(int) (panelHeight * 0.85), lblWidth, lblHeight);
		namePanel.addLabel(username);
		namePanel.getLabel()
				.setFont(
						new Font("Times New Roman", Font.BOLD, //$NON-NLS-1$
								(int) (lblHeight * 0.3)));
		namePanel.getLabel().setForeground(Color.WHITE);
		namePanel.getLabel().setVerticalTextPosition(JLabel.TOP);
		namePanel.getLabel().setVerticalAlignment(SwingConstants.BOTTOM);
		bgPane.add(namePanel, new Integer(2));
	}

	public void addNameLbl(String username, Color c) {
		int lblWidth = (int) (width * 0.6);
		int lblHeight = (int) (height * 0.3);
		ImagePanel namePanel = new ImagePanel(ImportImages.nameLbl, lblWidth,
				lblHeight);
		namePanel.setBounds((int) (panelWidth * 0.4),
				(int) (panelHeight * 0.95), lblWidth, lblHeight);
		namePanel.addLabel(username);
		namePanel.getLabel()
				.setFont(
						new Font("Times New Roman", Font.BOLD, //$NON-NLS-1$
								(int) (lblHeight * 0.3)));
		namePanel.getLabel().setForeground(c);
		namePanel.getLabel().setVerticalTextPosition(JLabel.TOP);
		namePanel.getLabel().setVerticalAlignment(SwingConstants.BOTTOM);
		bgPane.add(namePanel, new Integer(2));
	}

	/**
	 * The method sets a gradient over the player frames 
	 * to make the player's color more easily recognizable
	 * 该方法在玩家框架上设置渐变，以使玩家的颜色更容易识别
	 * 
	 * @param c is the desired color for the gradient
	 * 
	 * 
	 */
	public void addColorLbl(Color c) {
		if(c.equals(Color.YELLOW)){
			colorPanel = new ImagePanel(ImportImages.playerYellow, panelWidth, panelHeight);
		}
		else if(c.equals(Color.BLUE)){
			colorPanel = new ImagePanel(ImportImages.playerBlue, panelWidth, panelHeight);
		}
		else if(c.equals(Color.RED)){
			colorPanel = new ImagePanel(ImportImages.playerRed, panelWidth, panelHeight);
		}
		else if(c.equals(Color.GREEN)){
			colorPanel = new ImagePanel(ImportImages.playerGreen, panelWidth, panelHeight);
		}
		colorPanel.setBounds(panelDistance, buttonHeight / 2, panelWidth, panelHeight);
		bgPane.add(colorPanel, new Integer(1));
		colorPanel.setVisible(true);
	}
	
	/**
	 * Shows or hides the <code> PlayerLabel </ code> for the largest knight power.
	 * 显示或隐藏<code> PlayerLabel </ code>以获得最大的骑士权力。
	 * @param isLargestArmy
	 *            <code>true</code> - displays the <code>PlayerLabel</code>
	 */
	public void setLargestArmy(boolean isLargestArmy) {
		lblArmy.setVisible(isLargestArmy);
	}

	/**
	 * Shows or hides the <code> PlayerLabel </ code> for the longest trade road.
	 * 
	 * @param isLongestRoad
	 *            <code>true</code> - displays the <code>PlayerLabel</code>
	 */
	public void setLongestRoad(boolean isLongestRoad) {
		lblRoad.setVisible(isLongestRoad);
	}

	/**
	 * Sets the display image for the  <code>PlayerFrame</code>.
	 * 
	 * @param img
	 *            the image to be displayed
	 */
	protected void setAvatarImage(Image img) {
		this.img = img;
	}

	/**
	 * Sets the width of the <code>PlayerFrames</code>.
	 * 
	 * @param width
	 */
	protected void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Sets the height of the <code>PlayerFrames</code>.
	 * 
	 * @param height
	 */
	protected void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Returns the width of the additional <code> JComponents </ code>.
	 * 
	 * @return Width of the additional <code>JComponents</code>
	 */
	protected int getComponentWidth() {
		return (int) (width * 0.2);
	}

	/**
	 * Returns the height of the additional <code> JComponents </ code>.
	 * 
	 * @return Add the additional <code> JComponents </ code>
	 */
	protected int getComponentHeight() {
		return (int) (height * 0.2);
	}

	public void setMyTurn(boolean myturn) {
		myTurnPanel.setVisible(myturn);
	}
}
