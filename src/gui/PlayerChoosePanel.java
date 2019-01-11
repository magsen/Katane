package gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import model.Settler;
import Katane.Katane;

/**
 * Display of individual players.
 *  ��ʾ������Ա��
 * 
 * @author LIAO Haoyun
 * 
 */
@SuppressWarnings("serial")
public class PlayerChoosePanel extends JPanel {

	/**
	 * Panel in which the message is displayed.
	 * ��ʾ��Ϣ����塣
	 */
	private JPanel upperPanel;

	/**
	 * background image.
	 */
	private JPanel choosePanel;

	/**
	 * katane
	 */
	private Katane katane;

	/**
	 * The width of the panel.
	 */
	private int width;

	/**
	 * The height of the panel.
	 */
	private int height;

	/**
	 * The buttons of the opponents should represent as an array
	 * ���ֵİ�ťӦ�ñ�ʾΪ����
	 */
	private PlayerButton[] opponentBtns;

	/**
	 * The opponents from the model as an array.
	 */
	private Settler[] opponents;

	/**
	 * The content panels for each player
	 */
	private JPanel[] contentPanels;

	/**
	 * LayeredPanes for the opponents as an array
	 */
	private JLayeredPane[] layeredPanes;

	/**
	 * The usernames of the opponents in array form.
	 */
	private ImagePanel[] namePanels;

	/**
	 * The identification numbers of the players.
	 */
	@SuppressWarnings("unused")
	private ArrayList<Integer> playerIds;

	/**
	 * Indicates whether it is a trade (depending on the menu looks different.
	 * �������Ƿ��ǽ��ף�ȡ���ڲ˵���������ͬ��
	 */
	private boolean trading;

	/**
	 * The size of the array or the number of players.
	 */
	private int arraySize;

	/**
	 * A button to cancel
	 */
	private PlayerButton cancelBtn;

	/**
	 * Constructor for the <code>PlayerChoosePanel</code>.
	 * <code> PlayerChoosePanel </ code>�Ĺ��캯����
	 * 
	 * @param katane
	 *            Katane for the action items
	 *            ������Ŀ�����
	 * @param playerIds
	 *            <code>ArrayList</code> for the player IDs to be displayed
	 *            Ҫ��ʾ�����ID��<code>ArrayList</code>
	 */
	// public PlayerChoosePanel(Katane katane, int width, int height,
	// ArrayList<Integer> playerIds, boolean trading) {
	public PlayerChoosePanel(Katane katane,
			ArrayList<Integer> playerIds, boolean trading) {
		this.katane = katane;
		this.playerIds = playerIds;
		this.trading = trading;
		arraySize = (trading) ? playerIds.size() + 1 : playerIds.size();
		height = (int) (katane.getMainGUI().getHeight() * 0.25);
		width = height * arraySize;
		// this.width = width;
		// this.height = height;
		opponents = new Settler[playerIds.size()];
		opponentBtns = new PlayerButton[playerIds.size()];
		contentPanels = new JPanel[arraySize];
		layeredPanes = new JLayeredPane[arraySize];
		namePanels = new ImagePanel[playerIds.size()];

		for (int i = 0; i < opponents.length; i++) {
			opponents[i] = katane.getIsland().getSettler(playerIds.get(i));
		}

		init();

		setPreferredSize(new Dimension(width, height));
		setOpaque(false);
		setVisible(true);
	}

	/**
	 * Initialize <code>PlayerChoosePanel</code>.
	 */
	public void init() {
		createWidgets();
		setupInteraction();
		addWidgets();
	}

	/**
	 * Creates the components of <code>PlayerChoosePanels</code>.
	 * ����<code> PlayerChoosePanels </ code>�������
	 */
	public void createWidgets() {

		upperPanel = new JPanel();
		upperPanel.setPreferredSize(new Dimension(width, height));
		choosePanel = new JPanel();
		choosePanel.setPreferredSize(new Dimension(width + width/10, height + height/10));
		choosePanel.setOpaque(false);

		int btnSize = (int) (height * 0.8);
		int lblHeight = (int) (btnSize * 0.4);
		int panelHeight = (int) (btnSize * 1.1);
		for (int i = 0; i < opponents.length; i++) {
			contentPanels[i] = new JPanel();
			contentPanels[i].setOpaque(false);
			contentPanels[i].setVisible(true);
			contentPanels[i].setPreferredSize(new Dimension(btnSize,
					panelHeight));

			layeredPanes[i] = new JLayeredPane();
			layeredPanes[i].setOpaque(false);
			layeredPanes[i]
					.setPreferredSize(new Dimension(btnSize, panelHeight));

			opponentBtns[i] = new PlayerButton(ImportImages.kerriganAvatar,
					btnSize, btnSize);
			opponentBtns[i].setBounds(0, 0, btnSize, btnSize);

			namePanels[i] = new ImagePanel(ImportImages.nameLbl, btnSize,
					lblHeight);
			namePanels[i].setBounds(0, (int) (btnSize * 0.7), btnSize,
					lblHeight);
			namePanels[i].addLabel(opponents[i].getUsername(),
					opponents[i].getColor());
			namePanels[i].getLabel().setFont(
					new Font("Times New Roman", Font.BOLD, //$NON-NLS-1$
							(int) (lblHeight * 0.38)));
			namePanels[i].getLabel().setVerticalTextPosition(JLabel.CENTER);
			namePanels[i].getLabel()
					.setVerticalAlignment(SwingConstants.BOTTOM);
		}

		if (trading) {
			cancelBtn = new PlayerButton(ImportImages.cancelBtn, btnSize,
					btnSize);
			cancelBtn.setBounds(0, 0, btnSize, btnSize);
			layeredPanes[arraySize - 1] = new JLayeredPane();
			layeredPanes[arraySize - 1].setOpaque(false);
			layeredPanes[arraySize - 1].setVisible(true);
			layeredPanes[arraySize - 1].setPreferredSize(new Dimension(btnSize,
					panelHeight));
			contentPanels[arraySize - 1] = new JPanel();
			contentPanels[arraySize - 1].setOpaque(false);
			contentPanels[arraySize - 1].setVisible(true);
			contentPanels[arraySize - 1].setPreferredSize(new Dimension(
					btnSize, panelHeight));
		}

	}

	/**
	 * Does the interaction with the <code>Katane</code> in addition.
	 * �Ƿ���<code> Katane </ code>���н�����
	 */
	public void setupInteraction() {
		if (trading) {//�ж��Ƿ���н���
			for (int i = 0; i < opponents.length; i++) {
				opponentBtns[i].addActionListener(katane);
				opponentBtns[i]
						.setActionCommand("chos." + opponents[i].getID()); 
			}
			cancelBtn.addActionListener(katane);
			cancelBtn.setActionCommand("chos.-1"); 
		} else {
			for (int i = 0; i < opponents.length; i++) {
				opponentBtns[i].addActionListener(katane);
				opponentBtns[i]
						.setActionCommand("robb." + opponents[i].getID()); 
			}
		}
	}

	/**
	 * Does the components belong to the<code>PlayerChoosePanel</code> in addition.
	 * ���⣬�������<code> PlayerChoosePanel </ code>��
	 */
	public void addWidgets() {
		add(choosePanel);
		choosePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		for (int i = 0; i < opponentBtns.length; i++) {
			layeredPanes[i].add(opponentBtns[i], new Integer(1));
			layeredPanes[i].add(namePanels[i], new Integer(2));
			contentPanels[i].setLayout(new BorderLayout());
			contentPanels[i].add(layeredPanes[i], BorderLayout.CENTER);
			choosePanel.add(contentPanels[i]);
		}

		if (trading) {
			layeredPanes[arraySize - 1].add(cancelBtn, new Integer(2));
			contentPanels[arraySize - 1].add(layeredPanes[arraySize - 1],
					BorderLayout.CENTER);
			choosePanel.add(contentPanels[arraySize - 1]);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}

