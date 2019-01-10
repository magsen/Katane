package gui;

import java.awt.*;

import javax.swing.*;

import controller.Controller;

/**
 * GUI element that displays the numbers of the dice
 * 
 * @author YU Shijia
 */

@SuppressWarnings("serial")
public class DicePanel extends JPanel {

	private PlayerLabel diceOne;
	
	private PlayerLabel diceTwo;
	
	private Controller controller;
	
	private int width;

	// Constructor for the DicePanel.
	public DicePanel(int width, int height) {
		this.width = width;


		this.setOpaque(false);
		this.setPreferredSize(new Dimension(width, height));

	}

	// Sets the dice display to the corresponding numbers.
	
	public void setPips(int dice1, int dice2) {
		if (!(diceOne == null) || !(diceTwo == null)) {
			remove(diceOne);//Removes the specified component from this container.
			remove(diceTwo);
		}

		diceOne = new PlayerLabel(new ImageIcon(getClass().getResource("graphics/dice/dice" + dice1 
				+ ".png")).getImage(), width / 3, width / 3); 
		diceTwo = new PlayerLabel(new ImageIcon(getClass().getResource("graphics/dice/dice" + dice2 
				+ ".png")).getImage(), width / 3, width / 3); 
		addMouseListener(controller);
		setLayout(new FlowLayout());
		add(diceOne);
		add(diceTwo);
	}
}
