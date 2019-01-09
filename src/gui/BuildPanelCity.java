package gui;

import java.awt.*;
import javax.swing.*;
import controller.Controller;

/**
 * The class is the panel that appears when looking at a node
 * clicks (= build settlement)
 * 
 * @ Yu Shijia
 * 
 */

@SuppressWarnings("serial")
public class BuildPanelCity extends JPanel {

	private int width;

	private int height;

	private Controller controller;

	//A button for a settlement
	
	private PlayerButton bSettlement;

	// Creates a new object of the "Construction Menu"
	public BuildPanelCity(Controller controller, int width, int height) {
		this.controller = controller;
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
		bSettlement = new PlayerButton(ImportImages.cityBtn, buttonWidth,
				buttonHeight);
	}

	private void setupInteraction() {
		bSettlement.setActionCommand("node.city");
		bSettlement.addActionListener(controller);
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
