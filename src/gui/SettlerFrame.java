package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import Katane.Katane;

/**
 * The class is the information menu of your own player on the GUI 
 * It is based on the class <code>PlayerFrame</code> aus
 * 
 * @author LIAO Haoyun
 * 
 */
@SuppressWarnings("serial")
public class SettlerFrame extends PlayerFrame {

	/**
	 * The obligatory katane
	 */
	private Katane katane;

	/**
	 * A button to the map menu
	 */
	private PlayerButton bCard;

	/**
	 * A button to the Handelsmenum. call
	 */
	private PlayerButton bTrade;

	/**
	 * A button to dodge
	 */
	private PlayerButton bRoll;

	/**
	 * A button to finish his move
	 */
	private PlayerButton bNext;

	/**
	 * The constructor creates a new settler frame, 
	 * which acts as the linchpin of the GUI
	 * 
	 * @param katane
	 *            is the katane
	 * @param img
	 *            is the display image of the player
	 * @param width
	 *            is the width of the panel
	 * @param height
	 *            is the height of the panel
	 */
	public SettlerFrame(Katane katane, Image img, int width, int height) {
		this.katane = katane;
		setWidth(width);
		setHeight(height);
		setPreferredSize(new Dimension(width, height));
		setOpaque(false);
		setAvatarImage(img);
		init();
	}

	/**
	 * Initialize that <code>SettlerFrame</code>
	 */
	public void init() {
		createButtons();
		setupInteraction();
		createWidgets();
		addWidgets();
	}

	/**
	 * Create the <code>PlayerButtons</code> for the <code>SettlerFrame</code>
	 */
	public void createButtons() {
		int buttonWidth = getComponentWidth();
		int buttonHeight = getComponentHeight();
		bCard = new PlayerButton(ImportImages.cardBtn, buttonWidth,
				buttonHeight);
		bCard.setToolTipText(Messages.getString("SettlerFrame.KarteSpielen")); //$NON-NLS-1$
		bTrade = new PlayerButton(ImportImages.tradeBtn, buttonWidth,
				buttonHeight);
		bTrade.setToolTipText(Messages.getString("SettlerFrame.Handeln")); //$NON-NLS-1$
		bRoll = new PlayerButton(ImportImages.rollBtn, buttonWidth,
				buttonHeight);
		bRoll.setToolTipText(Messages.getString("SettlerFrame.Wuerfeln")); //$NON-NLS-1$
		bNext = new PlayerButton(ImportImages.nextBtn, buttonWidth,
				buttonHeight);
		bNext.setToolTipText(Messages.getString("SettlerFrame.ZugBeenden")); //$NON-NLS-1$
		addComponent(bCard);
		addComponent(bTrade);
		addComponent(bRoll);
		addComponent(bNext);
	}

	/**
	 * Adds the interaction with the <code>Katane</code> 
	 * to the <code>SettlerFrame</code>.
	 */
	public void setupInteraction() {
		bCard.setActionCommand("menu.card"); //$NON-NLS-1$
		bCard.addActionListener(katane);
		bCard.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.cardBtnActive);
			}
			public void mouseExited(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.cardBtn);
			}
		});
		bTrade.setActionCommand("menu.trade"); //$NON-NLS-1$
		bTrade.addActionListener(katane);
		bTrade.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.tradeBtnActive);
			}
			public void mouseExited(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.tradeBtn);
			}
		});
		bRoll.setActionCommand("menu.roll"); //$NON-NLS-1$
		bRoll.addActionListener(katane);
		bRoll.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.rollBtnActive);
			}
			public void mouseExited(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.rollBtn);
			}
		});
		bNext.setActionCommand("menu.next"); //$NON-NLS-1$
		bNext.addActionListener(katane);
		bNext.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.nextBtnActive);
			}
			public void mouseExited(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.nextBtn);
			}
		});
	}
}
