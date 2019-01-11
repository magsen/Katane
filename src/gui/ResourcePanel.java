package gui;

import java.awt.*;
import javax.swing.*;
import Katane.Katane;

/**
 * Display and selection menu for raw materials (e.g. monopoly card).
 * 原材料的显示和选择菜单（例如，垄断卡）。
 * @author LIAO Haoyun
 * 
 */
@SuppressWarnings("serial")
public class ResourcePanel extends ImagePanel {

	/**
	 * Width of the <code>ResourcePanel</code>.
	 */
	private int width;
	/**
	 * Height of the <code>ResourcePanels</code>
	 */
	private int height;
	/**
	 * <code> Katane </code> for the action elements of the <code> ResourcePanel </code>.
	 */
	private Katane katane;
	/**
	 * Contains the <code>PlayerButtons</code> of the <code>ResourcePanel</code>.
	 */
	private JPanel contentPanel;
	/**
	 * Contains the number of raw materials already selected.
	 */
	private JPanel discardPanel;
	/**
	 * Contains all parts of the <code>ResourcePanel</code> at different levels.
	 */
	private JLayeredPane layeredPane;
	/**
	 * Placeholder in the <code>GridBagLayout</code> of the <code>contentPanel</code>.
	 */
	private JLabel fillerLabel1;
	/**
	 * Placeholder in the <code>GridBagLayout</code> of the <code>contentPanels</code>.
	 */
	private JLabel fillerLabel2;
	/**
	 * <code>PlayerButton</code> for grain.
	 */
	private PlayerButton grainBtn;
	/**
	 * <code>PlayerButton</code> for ore.
	 */
	private PlayerButton oreBtn;
	/**
	 * <code>PlayerButton</code> for wool.
	 */
	private PlayerButton woolBtn;
	/**
	 * <code>PlayerButton</code> for clay.
	 */
	private PlayerButton brickBtn;
	/**
	 * <code>PlayerButton</code> for wood.
	 */
	private PlayerButton lumberBtn;
	/**
	 * <code>PlayerLabel</code> for the first resource in the invention card.
	 */
	private PlayerLabel resource1Lbl;
	/**
	 * <code>PlayerLabel</code> for the second resource in the invention card.
	 */
	private PlayerLabel resource2Lbl;
	/**
	 * <code>PlayerButton</code> to confirm.
	 */
	private PlayerButton confirmBtn;
	/**
	 * <code>PlayerButton</code> to reset the selection.
	 */
	private PlayerButton cancelBtn;
	/**
	 * <code>PlayerLabel</code> for the raw material at the monopoly card.
	 */
	private PlayerLabel resourceMonoLbl;
	/**
	 * <code>ImagePanel</code> for cereals.
	 */
	private ImagePanel grainLbl;
	/**
	 * <code>ImagePanel</code> for ore.
	 */
	private ImagePanel oreLbl;
	/**
	 * <code>ImagePanel</code> for wool.
	 */
	private ImagePanel woolLbl;
	/**
	 * <code>ImagePanel</code> for clay.
	 */
	private ImagePanel brickLbl;
	/**
	 * <code>ImagePanel</code> for wood.
	 */
	private ImagePanel lumberLbl;
	/**
	 * The first raw material.
	 */
	private String firstResource;
	/**
	 * The second raw material.
	 */
	private String secondResource;
	/**
	 * Size of <code>PlayerButtons</code>
	 */
	private int btnWidth;
	/**
	 * Counters for cereals.
	 */
	private int grain;
	/**
	 * Counters for ore.
	 */
	private int ore;
	/**
	 * Zoller for wool.
	 */
	private int wool;
	/**
	 * Counters for clay.
	 */
	private int brick;
	/**
	 * Counters for wood.
	 */
	private int lumber;
	

	/**
	 * The constructor creates a new ResourcePanel 
	 * that allows it to interact with the resources (for example, the ruler)
	 * 
	 * @param katane
	 *            <code>Katane</code>
	 * @param width
	 *            Width of the <code>ResourcePanels</code>
	 * @param height
	 *            Heigh of the <code>ResourcePanels</code>
	 */
	public ResourcePanel(Katane katane, int width, int height) {
		super(ImportImages.chatBg, width, height);
		this.katane = katane;
		this.width = width;
		this.height = height;
		this.firstResource = ""; //$NON-NLS-1$
		this.secondResource = ""; //$NON-NLS-1$
		btnWidth = (int) (width * 0.2);

		grain = 0;
		ore = 0;
		wool = 0;
		brick = 0;
		lumber = 0;

		init();

		setPreferredSize(new Dimension(width, height));
		setOpaque(false);
		setVisible(false);
	}

	/**
	 * Initialize that <code>ResourcePanel</code>.
	 */
	public void init() {
		createWidgets();
		setupInteraction();
		addWidgets();
	}

	/**
	 * Creates the components of <code>ResourcePanels</code>.
	 */
	public void createWidgets() {
		int sBtnWidth = (int) (width * 0.13);
		int lblWidth = (int) (width * 0.15);

		discardPanel = new JPanel();
		discardPanel.setPreferredSize(new Dimension(width,
				(int) (btnWidth * 1.2)));
		contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(width, height));
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(width, height));

		fillerLabel1 = new JLabel();
		fillerLabel1.setPreferredSize(new Dimension(sBtnWidth, sBtnWidth));

		fillerLabel2 = new JLabel();
		fillerLabel2.setPreferredSize(new Dimension(sBtnWidth, sBtnWidth));

		grainBtn = new PlayerButton(ImportImages.grainBtn, btnWidth, btnWidth);
		oreBtn = new PlayerButton(ImportImages.oreBtn, btnWidth, btnWidth);
		woolBtn = new PlayerButton(ImportImages.woolBtn, btnWidth, btnWidth);
		lumberBtn = new PlayerButton(ImportImages.lumberBtn, btnWidth, btnWidth);
		brickBtn = new PlayerButton(ImportImages.brickBtn, btnWidth, btnWidth);
		resource1Lbl = new PlayerLabel(ImportImages.confirmBtn, sBtnWidth,
				sBtnWidth);
		resource2Lbl = new PlayerLabel(ImportImages.confirmBtn, sBtnWidth,
				sBtnWidth);
		resourceMonoLbl = new PlayerLabel(ImportImages.confirmBtn, sBtnWidth,
				sBtnWidth);
		confirmBtn = new PlayerButton(ImportImages.confirmBtn,
				(int) (sBtnWidth * 0.7), (int) (sBtnWidth * 0.7));
		confirmBtn.setToolTipText(Messages.getString("ResourcePanel.AuswahlBestaetigen")); //$NON-NLS-1$
		cancelBtn = new PlayerButton(ImportImages.cancelBtn,
				(int) (sBtnWidth * 0.7), (int) (sBtnWidth * 0.7));
		cancelBtn.setToolTipText(Messages.getString("ResourcePanel.AuswahlAufheben")); //$NON-NLS-1$

		grainLbl = new ImagePanel(ImportImages.grainBtn, lblWidth, lblWidth);
		oreLbl = new ImagePanel(ImportImages.oreBtn, lblWidth, lblWidth);
		woolLbl = new ImagePanel(ImportImages.woolBtn, lblWidth, lblWidth);
		lumberLbl = new ImagePanel(ImportImages.lumberBtn, lblWidth, lblWidth);
		brickLbl = new ImagePanel(ImportImages.brickBtn, lblWidth, lblWidth);

		grainLbl.addLabel("" + grain, Color.BLACK); //$NON-NLS-1$
		oreLbl.addLabel("" + ore, Color.BLACK); //$NON-NLS-1$
		woolLbl.addLabel("" + wool, Color.BLACK); //$NON-NLS-1$
		lumberLbl.addLabel("" + lumber, Color.BLACK); //$NON-NLS-1$
		brickLbl.addLabel("" + brick, Color.BLACK); //$NON-NLS-1$

		contentPanel.setBounds(0, - (int) (height * 0.1), width, height);
		discardPanel.setBounds(0, (int) (height * 0.725), width,
				(int) (btnWidth * 1.2));

		resourceMonoLbl.setBounds((int) (width * 0.43), (int) (height * 0.35),
				sBtnWidth, sBtnWidth);
		resource1Lbl.setBounds((int) (width * 0.365), (int) (height * 0.35),
				sBtnWidth, sBtnWidth);
		resource2Lbl.setBounds((int) (width * 0.5), (int) (height * 0.35),
				sBtnWidth, sBtnWidth);

		cancelBtn.setBounds((int) (width * 0.7), (int) (height * 0.675),
				(int) (sBtnWidth * 0.7), (int) (sBtnWidth * 0.7));
		confirmBtn.setBounds((int) (width * 0.8), (int) (height * 0.6),
				(int) (sBtnWidth * 0.7), (int) (sBtnWidth * 0.7));

		contentPanel.setOpaque(false);
		layeredPane.setOpaque(false);
		discardPanel.setOpaque(false);

		discardPanel.setVisible(false);
		resource1Lbl.setVisible(false);
		resource2Lbl.setVisible(false);
		resourceMonoLbl.setVisible(false);
	}

	/**
	 * Adds the <code>ResourcePanel</code> 
	 * interaction with the <code>Katane</code>.
	 */
	public void setupInteraction() {
		grainBtn.setActionCommand("resP.Grain"); //$NON-NLS-1$
		//grainBtn.addActionListener(katane);
		oreBtn.setActionCommand("resP.Ore"); //$NON-NLS-1$
		//oreBtn.addActionListener(katane);
		woolBtn.setActionCommand("resP.Wool"); //$NON-NLS-1$
		//woolBtn.addActionListener(katane);
		lumberBtn.setActionCommand("resP.Lumber"); //$NON-NLS-1$
		//lumberBtn.addActionListener(katane);
		brickBtn.setActionCommand("resP.Brick"); //$NON-NLS-1$
		//brickBtn.addActionListener(katane);
		confirmBtn.setActionCommand("resP.confirm"); //$NON-NLS-1$
		//confirmBtn.addActionListener(katane);
		cancelBtn.setActionCommand("resP.cancel"); //$NON-NLS-1$
		//cancelBtn.addActionListener(katane);
	}

	/**
	 * Adds the components of the <code>ResourcePanels</code> to the
	 * <code>ResourcePanel</code>.
	 */
	public void addWidgets() {

		discardPanel.setLayout(new FlowLayout());
		contentPanel.setLayout(new GridBagLayout());

		discardPanel.add(grainLbl);
		discardPanel.add(oreLbl);
		discardPanel.add(woolLbl);
		discardPanel.add(lumberLbl);
		discardPanel.add(brickLbl);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 6;
		gbc.ipadx = (int) (btnWidth * 0.2);
		gbc.ipady = (int) (btnWidth * 0.2);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		contentPanel.add(grainBtn, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.ipadx = (int) (btnWidth * 0.2);
		gbc.ipady = (int) (btnWidth * 0.2);
		gbc.anchor = GridBagConstraints.WEST;
		contentPanel.add(oreBtn, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.ipadx = (int) (btnWidth * 0.1);
		contentPanel.add(fillerLabel1, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.ipadx = (int) (btnWidth * 0.1);
		contentPanel.add(fillerLabel2, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 6;
		gbc.gridy = 1;
		gbc.ipadx = (int) (btnWidth * 0.2);
		gbc.ipady = (int) (btnWidth * 0.2);
		contentPanel.add(woolBtn, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.gridheight = 2;
		gbc.ipadx = (int) (btnWidth * 0.2);
		gbc.ipady = (int) (btnWidth * 0.2);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(lumberBtn, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.gridheight = 2;
		gbc.ipadx = (int) (btnWidth * 0.2);
		gbc.ipady = (int) (btnWidth * 0.2);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(brickBtn, gbc);

		layeredPane.add(contentPanel, new Integer(1));
		layeredPane.add(resourceMonoLbl, new Integer(2));
		layeredPane.add(resource1Lbl, new Integer(2));
		layeredPane.add(resource2Lbl, new Integer(2));
		layeredPane.add(confirmBtn, new Integer(2));
		layeredPane.add(cancelBtn, new Integer(2));
		layeredPane.add(discardPanel, new Integer(2));
		add(layeredPane);
		

	}

	/**
	 * Sets the first chosen raw material.
	 * 
	 * @param firstResource
	 *            first raw material
	 * @param isMonopoly
	 *            meets Monopoly card
	 */
	public void setFirstResource(String firstResource, boolean isMonopoly) {
		this.firstResource = firstResource;
		if (isMonopoly) {
			resourceMonoLbl.setNewIcon(new ImageIcon(getClass().getResource("graphics/buttonPics/button" //$NON-NLS-1$
					+ firstResource + ".png")).getImage()); //$NON-NLS-1$
			resourceMonoLbl.setVisible(true);
		} else {
			resource1Lbl.setNewIcon(new ImageIcon(getClass().getResource("graphics/buttonPics/button" //$NON-NLS-1$
					+ firstResource + ".png")).getImage()); //$NON-NLS-1$
			resource1Lbl.setVisible(true);
		}
	}

	/**
	 * Sets the second chosen raw material.
	 * 
	 * @param secondResource
	 *            second raw material
	 */
	public void setSecondResource(String secondResource) {
		this.secondResource = secondResource;
		resource2Lbl.setNewIcon(new ImageIcon(getClass().getResource("graphics/buttonPics/button" //$NON-NLS-1$
				+ secondResource + ".png")).getImage()); //$NON-NLS-1$
		resource2Lbl.setVisible(true);
	}

	/**
	 * Returns whether the first raw material was selected.
	 * 
	 * @return first raw material
	 */
	public boolean hasFirstResource() {
		return !firstResource.equals(""); //$NON-NLS-1$
	}

	/**
	 * Returns whether the second resource was selected.
	 * 
	 * @return second raw material chosen
	 */
	public boolean hasSecondResource() {
		return !secondResource.equals(""); //$NON-NLS-1$
	}

	/**
	 * Returns the raw material constant at any position.
	 * 
	 * @param index
	 *            Position
	 * @return Commodity constant
	 */
	public byte getResourceByIndex(int index) {
		if (index == 1 && hasFirstResource()) {
			if (firstResource.equals("Grain")) //$NON-NLS-1$
				return Constants.GRAIN;
			else if (firstResource.equals("Ore")) //$NON-NLS-1$
				return Constants.ORE;
			else if (firstResource.equals("Lumber")) //$NON-NLS-1$
				return Constants.LUMBER;
			else if (firstResource.equals("Wool")) //$NON-NLS-1$
				return Constants.WOOL;
			else if (firstResource.equals("Brick")) //$NON-NLS-1$
				return Constants.BRICK;
			else
				return -1;
		} else if (index == 2 && hasSecondResource()) {
			if (secondResource.equals("Grain")) //$NON-NLS-1$
				return Constants.GRAIN;
			else if (secondResource.equals("Ore")) //$NON-NLS-1$
				return Constants.ORE;
			else if (secondResource.equals("Lumber")) //$NON-NLS-1$
				return Constants.LUMBER;
			else if (secondResource.equals("Wool")) //$NON-NLS-1$
				return Constants.WOOL;
			else if (secondResource.equals("Brick")) //$NON-NLS-1$
				return Constants.BRICK;
			else
				return -1;
		}
		return -1;
	}

	/**
	 * Resets the raw materials.
	 */
	public void resetResources() {
		firstResource = ""; //$NON-NLS-1$
		secondResource = ""; //$NON-NLS-1$
		resource1Lbl.setVisible(false);
		resource2Lbl.setVisible(false);
		resourceMonoLbl.setVisible(false);
	}

	/**
	 *Adds a commodity.
	 * 
	 * @param resource
	 *            Raw material string (eg "Grain")
	 */
	public void addResource(String resource) {
		if (resource.equals("Grain")) //$NON-NLS-1$
			grain++;
		else if (resource.equals("Wool")) //$NON-NLS-1$
			wool++;
		else if (resource.equals("Lumber")) //$NON-NLS-1$
			lumber++;
		else if (resource.equals("Ore")) //$NON-NLS-1$
			ore++;
		else if (resource.equals("Brick")) //$NON-NLS-1$
			brick++;
		update();
	}

	/**
	 * Returns the amount of a commodity.
	 * 
	 * @param resource
	 *            Raw material string (e.g. "Grain")
	 * @return Quantity of a raw material
	 */
	public int getResourceAmount(String resource) {
		if (resource.equals("Grain")) //$NON-NLS-1$
			return grain;
		else if (resource.equals("Wool")) //$NON-NLS-1$
			return wool;
		else if (resource.equals("Lumber")) //$NON-NLS-1$
			return lumber;
		else if (resource.equals("Ore")) //$NON-NLS-1$
			return ore;
		else if (resource.equals("Brick")) //$NON-NLS-1$
			return brick;
		return 0;
	}

	/**
	 * Returns an array of discarded raw materials.
	 * 
	 * @return discarded raw materials (order: grain, wool, lumber, ore, brick)
	 */
	public int[] getDiscardedResources() {
		int[] resources = { grain, wool, lumber, ore, brick };
		return resources;
	}

	/**
	 * Resets selected raw materials to discard
	 */
	public void resetDiscardingResources() {
		grain = 0;
		wool = 0;
		lumber = 0;
		ore = 0;
		brick = 0;
		update();
	}

	/**
	 * Returns the number of selected raw materials.
	 * 
	 * @return Number of selected raw materials
	 */
	public int getResAmount() {
		return (grain + wool + lumber + ore + brick);
	}

	/**
	 * Updates the display of raw materials.
	 */
	public void update() {
		grainLbl.setText("" + grain); //$NON-NLS-1$
		woolLbl.setText("" + wool); //$NON-NLS-1$
		oreLbl.setText("" + ore); //$NON-NLS-1$
		lumberLbl.setText("" + lumber); //$NON-NLS-1$
		brickLbl.setText("" + brick); //$NON-NLS-1$
	}

	/**
	 * Displays the display of the raw materials to be discarded.
	 */
	public void showDiscard() {
		this.setVisible(true);
		discardPanel.setVisible(true);
	}

	/**
	 * Resets the complete <code>ResourcePanel</code>.
	 */
	public void resetAll() {
		resetResources();
		resetDiscardingResources();
		discardPanel.setVisible(false);
	}
}
