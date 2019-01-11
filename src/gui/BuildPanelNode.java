package gui;

import java.awt.*;
import javax.swing.*;
import Katane.Katane;

/**
 * The class is the panel that appears when looking at a node
 * clicks (= build settlement)
 * @ YU Shijia
 * 
 */

@SuppressWarnings("serial")
public class BuildPanelNode extends JPanel {

	private int width;

	private int height;

	private Katane katane;
	
	//A button for a settlement
	private PlayerButton bSettlement;

	//Creates a new object of the "Construction Menu"
	public BuildPanelNode(Katane katane, int width, int height) {
		this.katane = katane;
		this.width = width;
		this.height = height;

		createWidgets();
		setupInteraction();
		addWidgets();

		this.setOpaque(false);
		this.setPreferredSize(new Dimension(width, height));
	}

	private void createWidgets() {
		int buttonWidth = (int) (width * 0.3);
		int buttonHeight = (int) (height * 0.3);
		bSettlement = new PlayerButton(ImportImages.settlementBtn, buttonWidth,
				buttonHeight);
	}

	private void setupInteraction() {
		bSettlement.setActionCommand("node.settlement"); //Set a string of attributes for each button to determine which button you chose.
		bSettlement.addActionListener(katane);//Add event listener interface
	}

	private void addWidgets() {
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(bSettlement, gbc);

	}

	public PlayerButton getbSettlement() {
		return bSettlement;
	}

	public void setbSettlement(PlayerButton bSettlement) {
		this.bSettlement = bSettlement;
	}
	
	public void changeBSettlement(){
		int buttonWidth = (int) (width * 0.3);
		int buttonHeight = (int) (height * 0.3);
		bSettlement = new PlayerButton(ImportImages.cityBtn, buttonWidth,
				buttonHeight);
	}
	
	public void resetBSettlement(){
		int buttonWidth = (int) (width * 0.3);
		int buttonHeight = (int) (height * 0.3);
		bSettlement = new PlayerButton(ImportImages.settlementBtn, buttonWidth,
				buttonHeight);
	}
}
