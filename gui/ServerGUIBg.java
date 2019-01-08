package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * Panel that serves as the content panel for the <code>ServerGUI</code>
 * 
 * @author LIAO Haoyun
 * 
 */
@SuppressWarnings("serial")
public class ServerGUIBg extends JPanel {
	/**
	 * The height of the screen
	 */
	private int screenHeight;
	/**
	 * The width of the screen
	 */
	private int screenWidth;
	/**
	 * Displays the server status
	 */
	@SuppressWarnings("unused")
	private JLabel serverState;
	/**
	 * The panel to which all elements are added
	 */
	private JPanel content;
	/**
	 * The exit panel of the server gui - object of the class
	 * <code>ServerExitButton</code>
	 */
	private JPanel rect_0;
	/**
	 * The panel that contains the <i>Settler of Catan</i> logo - object of the class <code>ServerGUILogo</code>
	 */
	private ServerGUILogo rect_1;
	/**
	 * The host panel of the server gui - object of the class <code>ServerHost</code>
	 */
	private ServerHost rect_2;
	/**
	 * The client panel of the server gui - object of the class
	 * <code>ServerGUIClient</code>
	 */
	private ServerGUIClient rect_3;
	/**
	 * The connection panel between the server status panel 
	 * and host panel object of the class <code>GuiConnection</code>
	 */
	/**
	 * The title panel for the host panel object of the class
	 * <code>ServerGUITitle_1</code>
	 */
	private JPanel hostTitle;
	/**
	 * The title panel for the client panel object of the class
	 * <code>ServerGUITitle_2</code>
	 */
	private JPanel joinTitle;
	/**
	 * The ServerGUI window - object of the class <code>ServerGUI</code>
	 */
	private ServerGUI serverGUI;
	/**
	 * The screen width
	 */
	private int width;
	/**
	 * The screen height
	 */
	private int height;
	/**
	 * The width of a section of the server gui
	 */
	private int rectWidth;
	/**
	 * The height of a section of the server gui
	 */
	private int rectHeight;
	/**
	 * The log window for the server
	 */
	private JTextArea serverLog;
	/**
	 * The panel for the status display of the client
	 */
	private JPanel clientLog;
	/**
	 * The status of the client
	 */
	private JLabel clientState;
	/**
	 * Heading of server gui host area
	 */
	private RichJLabel hostLabel;
	/**
	 * Heading of server gui host area
	 */
	private RichJLabel joinLabel;
	/**
	 * Exit button of the server gui - object of the class <code>PlayerButton</code>
	 */
	private PlayerButton exit;
	
	public ServerGUIBg(ServerGUI serverGUI) {
		this.serverGUI = serverGUI;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		width = d.width;
		height = d.height;
		screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		rectWidth = width/4+width/16;
		rectHeight = height/5;
		setSize(screenWidth / 2, screenHeight / 4);
		super.setLayout(null);
		createWidgets();
		addWidgets();
		super.add(content);
	}
	/**
	 * Creates the individual panel elements
	 */
	public void createWidgets() {
		
		content = new JPanel();
		content.setSize(width/2-width/6, 7*height/8);
		content.setBackground(new Color(221, 48, 60,0));
		content.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		
		rect_0 = new JPanel();
		rect_0.setOpaque(false);
		exit = new PlayerButton(ImportServerImages.quitKey,width/42,height/30);
		rect_0.setSize(width/12, height/12);
		rect_0.setLocation(width/4+width/42, 0);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		rect_0.add(exit);
		
		
		rect_1 = new ServerGUILogo();
		rect_1.setSize(rectWidth, rectHeight);
		rect_1.setLocation((content.getWidth()-rectWidth)/2, rect_0.getHeight());
		rect_1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		serverLog = new JTextArea();
		serverLog.setSize(rectWidth-rectWidth/12, rectHeight-rectHeight/6);
		serverLog.setPreferredSize(new Dimension(rectWidth-rectWidth/12, rectHeight-rectHeight/6));
		serverLog.setVisible(false);
		serverLog.setLocation((content.getWidth()-rectWidth)/2+(rect_1.getWidth()-serverLog.getWidth())/2, rect_1.getY()+(rect_1.getHeight()-serverLog.getHeight())/2);
		serverLog.setBorder(BorderFactory.createLineBorder(Color.black,2));
		serverLog.setEditable(false);
		
		clientState = new JLabel(""); //$NON-NLS-1$
		clientState.setForeground(Color.WHITE);
		clientState.setFont(new Font("Impact", Font.BOLD, height/30)); //$NON-NLS-1$
		
		clientLog = new JPanel();
		clientLog.setSize(rectWidth-rectWidth/12, rectHeight-rectHeight/6);
		clientLog.setPreferredSize(new Dimension(rectWidth-rectWidth/12, rectHeight-rectHeight/6));
		clientLog.setBackground(new Color(221, 48, 60));
		clientLog.setVisible(false);
		clientLog.setLocation((content.getWidth()-rectWidth)/2+(rect_1.getWidth()-serverLog.getWidth())/2, rect_1.getY()+(rect_1.getHeight()-serverLog.getHeight())/2);
		
		rect_2 = new ServerHost(this);
		rect_2.setSize(rectWidth, rectHeight);
		rect_2.setLocation((content.getWidth()-rectWidth)/2, rect_1.getY()+rect_1.getHeight()+rectHeight/3);
		rect_2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	
		rect_3 = new ServerGUIClient(rect_2, serverGUI);
		rect_3.setSize(rectWidth, rectHeight);
		rect_3.setLocation((content.getWidth()-rectWidth)/2, rect_2.getY()+rect_2.getHeight()+rectHeight/3);
		rect_3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		hostLabel = new RichJLabel("Host",2); //$NON-NLS-1$
		hostLabel.setFont(new Font("Impact", Font.BOLD, height/20)); //$NON-NLS-1$
		hostLabel.setSize(width/20, height/20);
		hostLabel.setLeftShadow(1, 1, Color.black);
	    hostLabel.setRightShadow(4, 4, Color.black);
	  
	    hostLabel.setForeground(Color.WHITE);
	    hostLabel.setFont(hostLabel.getFont().deriveFont(width/30f));
		hostTitle = new JPanel();
		hostTitle.setOpaque(false);
		hostTitle.setSize(rectWidth/4, rectHeight/3);
		hostTitle.setLocation((content.getWidth()-rectWidth)/2, rect_1.getY()+rect_3.getHeight());
		hostTitle.add(hostLabel);
		
		joinTitle = new JPanel();
		joinTitle.setOpaque(false);
		joinTitle.setSize(7*rectWidth/20, rectHeight/3);
		joinTitle.setLocation((content.getWidth()-rectWidth)/2, rect_2.getY()+rect_3.getHeight());
		joinLabel = new RichJLabel("Client",2); //$NON-NLS-1$
		joinLabel.setFont(new Font("Impact", Font.BOLD, height/20)); //$NON-NLS-1$
		joinLabel.setSize(width/20, height/20);
		joinLabel.setLeftShadow(1, 1, Color.black);
	    joinLabel.setRightShadow(4, 4, Color.black);
	  
	    joinLabel.setForeground(Color.WHITE);
	    joinLabel.setFont(hostLabel.getFont().deriveFont(width/30f));
		joinTitle.add(joinLabel);
	}
	/**
	 * Adds the elements to the panel
	 */
	public void addWidgets() {
		rect_1.add(serverLog);
		clientState.setLocation(rect_1.getX()+(rect_1.getWidth()-clientLog.getWidth())/2,
				rect_1.getY()+(rect_1.getHeight()-clientLog.getHeight())/2);
		clientLog.add(clientState);
		rect_1.add(clientLog);
		super.add(rect_0);
		super.add(rect_2);
		super.add(rect_1);
		super.add(rect_3);
		super.add(hostTitle);
		super.add(joinTitle);	
	}
	
	public ServerGUI getServerGUI() {
		return serverGUI;
	}
	public ServerGUILogo getRect_1() {
		return rect_1;
	}
	public JLabel getClientState() {
		return clientState;
	}
	public JPanel getClientLog() {
		return clientLog;
	}
	public ServerHost getRect_2() {
		return rect_2;
	}
	public JTextArea getServerLog() {
		return serverLog;
	}

}