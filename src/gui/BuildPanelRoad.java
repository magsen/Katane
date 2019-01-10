package gui;

import java.awt.*;

import javax.swing.*;

import Katane.Katane;

/**
 * The class is the "menu" called when building a street
 * wants (construction menu)
 *
 * @author YU Shijia
 * 
 */

@SuppressWarnings("serial")
public class BuildPanelRoad extends JPanel {

	private int width;

	private int height;

	private Katane katane;
	
	/*
	* A button for a street
	*/
	private PlayerButton bRoad;

	/**
	* Creates a new Road Construction Menu
	* @param katane
	* is the good old katane
	* @param width
	* is the width of the panel
	* @param height
	* is the height of the panel
	*/
	public BuildPanelRoad(Katane katane, int width, int height) {
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
		bRoad = new PlayerButton(ImportImages.roadBtn, buttonWidth,
				buttonHeight);
	}

	private void setupInteraction() {
		bRoad.setActionCommand("road.road"); 
		bRoad.addActionListener(katane);
	}

	private void addWidgets() {
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(bRoad, gbc);
	}
	
	public void changeIcon(Image img) {
		bRoad.changeIcon(img);
	}
}
