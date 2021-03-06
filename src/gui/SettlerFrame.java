package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;

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

	public MainGUI mainGUI;

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
	public SettlerFrame(MainGUI mainGUI, Katane katane, Image img, int width, int height) {
		this.mainGUI = mainGUI;
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
		bCard.addActionListener(new ActionListener() {public void
			actionPerformed(ActionEvent e) { 
			
			/*NEED TO BE EDIT*/ 
			
			
			katane.getMainGUI().getTradingMenu().setVisible(true);
			System.out.println("TradedMenu with bank(1) with opponent (2)");
			String s ="";
			int quant[] = new int[5];
			try{
		        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		        s = bufferRead.readLine();

		        //System.out.println(s);
		    }
		    catch(IOException e1)
		    {
		        e1.printStackTrace();
		    }
			//System.out.println(s);
			if(Integer.parseInt(s)==1) { //BANK
				System.out.println("TradedMenu [Brick,Grain,Ore,Wood,Wool]");
				s ="";
				try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();

			        //System.out.println(s);
			    }
			    catch(IOException e1)
			    {
			        e1.printStackTrace();
			    }
				quant[0]= Integer.parseInt(s);
				System.out.println(quant[0]+" brick");
				try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();

			        //System.out.println(s);
			    }
			    catch(IOException e1)
			    {
			        e1.printStackTrace();
			    }
				quant[1]= Integer.parseInt(s);
				System.out.println(quant[1]+" grain");
				try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();

			        //System.out.println(s);
			    }
			    catch(IOException e1)
			    {
			        e1.printStackTrace();
			    }
				quant[2]= Integer.parseInt(s);
				System.out.println(quant[2]+" ore");
				try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();

			        //System.out.println(s);
			    }
			    catch(IOException e1)
			    {
			        e1.printStackTrace();
			    }
				quant[3]= Integer.parseInt(s);
				System.out.println(quant[3]+" wood");
				try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();

			        //System.out.println(s);
			    }
			    catch(IOException e1)
			    {
			        e1.printStackTrace();
			    }
				quant[4]= Integer.parseInt(s);
				System.out.println(quant[4]+" wool");
				System.out.println("exchange");
				katane.getTM().tradeB(katane.getCurrentPlayer(), quant);
				
			}else {// other
				System.out.println("TradedMenu [Brick,Grain,Ore,Wood,Wool] opponent:");
				s ="";
				int opp = 1;
				try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();

			        //System.out.println(s);
			    }
			    catch(IOException e1)
			    {
			        e1.printStackTrace();
			    }
				opp= Integer.parseInt(s);
				System.out.println(opp+" opponent");
				try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();

			        //System.out.println(s);
			    }
			    catch(IOException e1)
			    {
			        e1.printStackTrace();
			    }
				quant[0]= Integer.parseInt(s);
				System.out.println(quant[0]+" brick");
				try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();

			        //System.out.println(s);
			    }
			    catch(IOException e1)
			    {
			        e1.printStackTrace();
			    }
				quant[1]= Integer.parseInt(s);
				System.out.println(quant[1]+" grain");
				try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();

			        //System.out.println(s);
			    }
			    catch(IOException e1)
			    {
			        e1.printStackTrace();
			    }
				quant[2]= Integer.parseInt(s);
				System.out.println(quant[2]+" ore");
				try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();

			        //System.out.println(s);
			    }
			    catch(IOException e1)
			    {
			        e1.printStackTrace();
			    }
				quant[3]= Integer.parseInt(s);
				System.out.println(quant[3]+" wood");
				try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();

			        //System.out.println(s);
			    }
			    catch(IOException e1)
			    {
			        e1.printStackTrace();
			    }
				quant[4]= Integer.parseInt(s);
				System.out.println(quant[4]+" wool");
				System.out.println("exchange");
				katane.getTM().trade(katane.getCurrentPlayer().getPlayerNumber(),opp, quant);
				
			}

			
			
		}});
		bCard.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.cardBtnActive);
			}
			public void mouseExited(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.cardBtn);
			}
		});
		bTrade.setActionCommand("menu.trade"); //$NON-NLS-1$
		bTrade.addActionListener(new ActionListener() {public void
			actionPerformed(ActionEvent e) { 
			
			/*NEED TO BE EDIT*/ 
			
			mainGUI.showTradeesPanel();
			
		}});
		bTrade.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.tradeBtnActive);
			}
			public void mouseExited(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.tradeBtn);
			}
		});
		bRoll.setActionCommand("menu.roll"); //$NON-NLS-1$
		bRoll.addActionListener(new ActionListener() {public void
			actionPerformed(ActionEvent e) { 
			
			/*NEED TO BE EDIT*/ 
			
			katane.getMainGUI().getDicePanel().setVisible(true);
			katane.getMainGUI().getDicePanel().setPips(katane.dice1.getResult(),katane.dice2.getResult());
			
			
			
		}});
		bRoll.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.rollBtnActive);
			}
			public void mouseExited(MouseEvent me){
				((PlayerButton)me.getSource()).changeIcon(ImportImages.rollBtn);
			}
		});
		bNext.setActionCommand("menu.next"); //$NON-NLS-1$
		bNext.addActionListener(new ActionListener() {public void
			actionPerformed(ActionEvent e) { 
			
			/*NEED TO BE EDIT*/
			
			
			
			katane.endTurn();
			katane.getMainGUI().getDicePanel().setVisible(false);
			katane.getMainGUI().contentPanel.setVisible(false);
			katane.getMainGUI().init();
			katane.getMainGUI().getSupplyPanel().update();
			
			
			
			
		}});
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
