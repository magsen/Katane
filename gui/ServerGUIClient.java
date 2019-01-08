package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import networking.Client;
import networking.ExceptionLogger;
import networking.Message;
import networking.ServerThread;
import controller.Controller;

/**
 * The client panel of the server gui
 * 
 * @author LIAO Haoyun
 * 
 */
@SuppressWarnings("serial")
public class ServerGUIClient extends JPanel implements CaretListener {
	/**
	 * The desired username
	 */
	private String username;
	/**
	 * The IP of the server as a string
	 */
	private String serverIP;
	/**
	 * the variable is true if the server is available
	 */
	private boolean serverIsAvailable;
	/**
	 * The variable is true if the join button has been pressed
	 */
	private boolean joinPressed;
	/**
	 * The variable returns the number of players who have already joined the game
	 */
	private int playerCounter;
	/**
	 * The socket to the server
	 */
	private Socket socket;
	/**
	 * The text field in which the user name can be entered
	 */
	private JTextField userName;
	/**
	 * The text field in which the IP of the server can be entered
	 */
	private JTextField ip;
	/**
	 * The caption of the text field for the user name
	 */
	private RichJLabel userNameLabel;
	/**
	 * The caption of the text field for the server ip
	 */
	private RichJLabel ipLabel;
	/**
	 * The button of entering a server - object of the class <code>PlayerButton</code>
	 */
	private PlayerButton joinButton;
	/**
	 * <code>ServerGUI</code>
	 */
	private ServerGUI servGUI;
	/**
	 * The information of the host object of the class <code>ServerHost</code>
	 */
	private static ServerHost hostInfo;
	/**
	 * Variable that determines the number of players who can enter the server
	 */
	private int maximumPlayers;
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
	private int rectHeight;
	/**
	 * The height of a section of the server gui
	 */
	private int rectWidth;
	/**
	 * Variable indicating if the server is full
	 */
	private static boolean isFull;
	/**
	 * Variable indicating if the server is active
	 */
	private static boolean serverDown;
	/**
	 * Thread representing the client status
	 */
	private static Thread actionthread;
	/**
	 * Button, to change the language to German - Object of the 
	 * class <code>PlayerButton</code>
	 */
	private PlayerButton german;
	/**
	 * Button, for language change in English - object of the 
	 * class <code>PlayerButton</code>
	 */
	private PlayerButton english;
	/**
	 * The avatar selection window - object of the class <code>ServerGUIAvatar</code>
	 */
	private ServerGUIAvatar frame;
	/**
	 * Thread that manages the countdown in the avatar selection window
	 */
	private Thread countDownThread;
	/**
	 * Logger for the errors
	 */
	private ExceptionLogger exceptionLogger;
	
	public ServerGUIClient(ServerHost hostInfo, ServerGUI servGUI) {
		ServerGUIClient.hostInfo = hostInfo;
		this.servGUI = servGUI;
		super.setLayout(new GridBagLayout());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		width = d.width;
		height = d.height;
		rectWidth = width/4+width/16;
		rectHeight = height/5;
		maximumPlayers = hostInfo.getPlayerCount();
		playerCounter = 0;
		joinPressed = false;
		serverDown = false;
		socket = null;
		exceptionLogger = ExceptionLogger.getInstance();
		createWidgets();
		addWidgets();
		setupInteraction();
	}
	/**
	 * Creates the individual panel elements
	 */
	public void createWidgets() {
		frame = new ServerGUIAvatar(width/4,width/4);
		actionthread = new Thread();
		german = new PlayerButton(ImportServerImages.joinGerman,width/15,height/15); //$NON-NLS-1$
		english = new PlayerButton(ImportServerImages.joinEnglish,width/15,height/15); //$NON-NLS-1$
		joinButton = new PlayerButton(ImportServerImages.joinIcon,
				width/10, height/16);
		userName = new JTextField(20);
		userName.setEditable(true);
		ip = new JTextField(20);
		ip.setEditable(true);
		userNameLabel = new RichJLabel("Username:",1); //$NON-NLS-1$
		userNameLabel.setFont(new Font("Impact", Font.BOLD, height/40)); //$NON-NLS-1$
		userNameLabel.setLeftShadow(1, 1, Color.black);
	    userNameLabel.setRightShadow(4, 4, Color.black);
	    userNameLabel.setForeground(Color.WHITE);
	    userNameLabel.setFont(userNameLabel.getFont().deriveFont(width/65f));
		ipLabel = new RichJLabel("IP:",1); //$NON-NLS-1$
		ipLabel.setFont(new Font("Impact", Font.BOLD, height/40)); //$NON-NLS-1$
		ipLabel.setLeftShadow(1, 1, Color.black);
	    ipLabel.setRightShadow(4, 4, Color.black);
	    ipLabel.setForeground(Color.WHITE);
	    ipLabel.setFont(ipLabel.getFont().deriveFont(width/65f));
		
	}
	/**
	 * Adds the elements to the panel
	 */
	public void addWidgets() {
		userName.addCaretListener(this);
		ip.addCaretListener(this);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.ipadx = 1;
		gbc.ipady = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		super.add(userName, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.ipadx = 1;
		gbc.ipady = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		super.add(userNameLabel, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.ipadx = 2;
		gbc.ipady = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		super.add(ipLabel, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		gbc.ipadx = 2;
		gbc.ipady = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		super.add(ip, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.ipadx = 1;
		gbc.ipady = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		super.add(english, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 5;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.ipadx = 1;
		gbc.ipady = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		super.add(german, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 5;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.ipadx = 1;
		gbc.ipady = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		super.add(joinButton, gbc);
		joinButton.setVisible(false);
	}
	/**
	 * Adds the interaction with the buttons
	 */
	public void setupInteraction() {
		english.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.Messages.initEN();
				networking.Messages.initEN();
				gui.Messages.initEN();
				joinButton.doClick();
			}	
		});
		german.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Messages.initDE();
				controller.Messages.initDE();
				networking.Messages.initDE();
				gui.Messages.initDE();	
				joinButton.doClick();
			}
		});
		frame.getConfirm().addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				countDownThread.stop();
				frame.getKerrigan().setEnabled(true);
				frame.getArtas().setEnabled(true);
				frame.getDaemon().setEnabled(false);
				frame.getToss().setEnabled(true);
				frame.getHuman().setEnabled(true);
				frame.getUndead().setEnabled(true);
				frame.getZerg().setEnabled(true);
				frame.getOrc().setEnabled(true);
				frame.getNightelf().setEnabled(true);
				frame.dispose();
				actionthread = null;
				if(ServerThread.getIsFull()){
					joinButton.setEnabled(false);
				}

				ObjectOutputStream out = null;
				ObjectInputStream in = null;
				if (username != null && !username.contains(" ") && !(username.length() > 7) //$NON-NLS-1$
						&& !(username.equals("")) //$NON-NLS-1$
						&& playerCounter < maximumPlayers) {
					joinPressed = true;
					try {
						socket = new Socket(serverIP, 1337);
						out = new ObjectOutputStream(socket.getOutputStream());
						in = new ObjectInputStream(socket.getInputStream());
						serverIsAvailable = true;
					} catch (UnknownHostException e1) {
						ip.setText(Messages.getString("ServerGUIClient.IPNichtVerfuegbar")); //$NON-NLS-1$
						exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.IPNichtVerfuegbar")); //$NON-NLS-1$

					} catch (IOException e1) {
						exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.IOException.NoInOut")); //$NON-NLS-1$

					}
					
					if (serverIsAvailable && playerCounter < maximumPlayers) {
						
						try {
							String[] strings = { username,
									""+ frame.getAvatarNumber(), "blue" }; //$NON-NLS-1$ //$NON-NLS-2$
							Message message = new Message(strings);
							out.writeObject(message);
							out.flush();
						} catch (IOException e2) {
							exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.IOException.passName")); //$NON-NLS-1$

						}
						String message = null;
						do {
							try {
								message = (String) in.readObject();
							} catch (IOException e2) {
								exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.IOException.readMessage")); //$NON-NLS-1$

							} catch (ClassNotFoundException e1) {
								exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.ClassNotFountException")); //$NON-NLS-1$

							}
						} while (!message.equals("accepted") //$NON-NLS-1$
								&& !message.equals("denied") && !message.equals("full")); //$NON-NLS-1$ //$NON-NLS-2$
						if (message.equals("accepted")) { //$NON-NLS-1$
							playerCounter++;
							try {
								if(!hostInfo.getServerGuiBg().getServerLog().isVisible()
										&& !hostInfo.getServerGuiBg().getClientLog().isVisible()){
								hostInfo.getServerGuiBg().getClientLog().setVisible(true);
								actionthread = new Thread(new Runnable() {
									public void run() {
										while(!MainGUI.isStarted()){
									
									hostInfo.getServerGuiBg().getClientState().setText(Messages.getString("ServerGUIClient.Waiting")); //$NON-NLS-1$
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										exceptionLogger.writeException(Level.SEVERE, "InterruptedException im ServerGUIClient: Fehler beim Schlafenlegen des Threads"); //$NON-NLS-1$

									}	
									hostInfo.getServerGuiBg().getClientState().setText(""); //$NON-NLS-1$
										try {
											Thread.sleep(500);
										} catch (InterruptedException e) {
											exceptionLogger.writeException(Level.SEVERE, "InterruptedException im ServerGUIClient: Fehler beim Schlafenlegen des Threads"); //$NON-NLS-1$

										}
									}
										Thread.interrupted();
										hostInfo.getServerGuiBg().getClientState().setText(Messages.getString("ServerGUIClient.GameStarted")); //$NON-NLS-1$
								}});
								actionthread.start();
								}
								
								new Controller(new Client(socket, userName
										.getText()), servGUI);
								userName.setText(""); //$NON-NLS-1$
								username = ""; //$NON-NLS-1$
								ip.setText(""); //$NON-NLS-1$
								
							} catch (IOException e1) {
							}
						} else if (message.equals("denied")){ //$NON-NLS-1$
							userName.setText(Messages.getString("ServerGUIClient.NameBenutzt")); //$NON-NLS-1$
							playerCounter--;
						} else if (message.equals("full")) { //$NON-NLS-1$
							userName.setText(Messages.getString("ServerGUIClient.GenuegendSpieler")); //$NON-NLS-1$
						}
					} else {
						if (!serverIsAvailable) {
							ip.setText(Messages.getString("ServerGUIClient.ServerNichtVerfuegbar")); //$NON-NLS-1$
						} else if (playerCounter >= maximumPlayers) {
							ip.setText(Messages.getString("ServerGUIClient.MaxErreicht")); //$NON-NLS-1$
						} else {
							joinPressed = false;
						}
					}
				} else {
					if (username == null) {
						userName.setText(Messages.getString("ServerGUIClient.Leer")); //$NON-NLS-1$
					} else if ((username.length() > 7)) {
						userName.setText(Messages.getString("ServerGUIClient.ZuLang")); //$NON-NLS-1$
					} else if (username.contains(" ")) { //$NON-NLS-1$
						userName.setText(Messages.getString("ServerGUIClient.Leerzeichen")); //$NON-NLS-1$
					} else if ((username.equals(""))) { //$NON-NLS-1$
						userName.setText(Messages.getString("ServerGUIClient.Leer")); //$NON-NLS-1$
					}
				}
				
			}
			
		});
		joinButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(true);
				countDownThread = new Thread(new Runnable() {
					public void run() {
						for(int i = 0; i<11;i++){
							frame.getCountDown().setText(""+(10-i)); //$NON-NLS-1$
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.InterruptedException.ThreadSleep")); //$NON-NLS-1$
							}
							if(i == 10){
								frame.dispose();
								actionthread = null;
								if(ServerThread.getIsFull()){
									joinButton.setEnabled(false);
								}

								ObjectOutputStream out = null;
								ObjectInputStream in = null;
								if (username != null && !username.contains(" ") && !(username.length() > 7) //$NON-NLS-1$
										&& !(username.equals("")) //$NON-NLS-1$
										&& playerCounter < maximumPlayers) {
									joinPressed = true;
									try {
										socket = new Socket(serverIP, 1337);
										out = new ObjectOutputStream(socket.getOutputStream());
										in = new ObjectInputStream(socket.getInputStream());
										serverIsAvailable = true;
									} catch (UnknownHostException e1) {
										ip.setText(Messages.getString("ServerGUIClient.IPNichtVerfuegbar")); //$NON-NLS-1$
										exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.IPNichtVerfuegbar")); //$NON-NLS-1$

									} catch (IOException e1) {
										exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.IOException.NoInOut")); //$NON-NLS-1$

									}
									
									if (serverIsAvailable && playerCounter < maximumPlayers) {
										
										try {
											String[] strings = { username,
													"Chuck Norris", "blue" }; //$NON-NLS-1$ //$NON-NLS-2$
											Message message = new Message(strings);
											out.writeObject(message);
											out.flush();
										} catch (IOException e2) {
											exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.IOException.passName")); //$NON-NLS-1$

										}
										String message = null;
										do {
											try {
												message = (String) in.readObject();
											} catch (IOException e2) {
												exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.IOException.readMessage")); //$NON-NLS-1$

											} catch (ClassNotFoundException e1) {
												exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.ClassNotFountException")); //$NON-NLS-1$

											}
										} while (!message.equals("accepted") //$NON-NLS-1$
												&& !message.equals("denied") && !message.equals("full")); //$NON-NLS-1$ //$NON-NLS-2$
										if (message.equals("accepted")) { //$NON-NLS-1$
											playerCounter++;
											try {
												if(!hostInfo.getServerGuiBg().getServerLog().isVisible()
														&& !hostInfo.getServerGuiBg().getClientLog().isVisible()){
												hostInfo.getServerGuiBg().getClientLog().setVisible(true);
												actionthread = new Thread(new Runnable() {
													public void run() {
														while(!MainGUI.isStarted()){
													
													hostInfo.getServerGuiBg().getClientState().setText(Messages.getString("ServerGUIClient.Waiting")); //$NON-NLS-1$
													try {
														Thread.sleep(1000);
													} catch (InterruptedException e) {
														exceptionLogger.writeException(Level.SEVERE, "InterruptedException im ServerGUIClient: Fehler beim Schlafenlegen des Threads"); //$NON-NLS-1$

													}	
													hostInfo.getServerGuiBg().getClientState().setText(""); //$NON-NLS-1$
														try {
															Thread.sleep(500);
														} catch (InterruptedException e) {
															exceptionLogger.writeException(Level.SEVERE, "InterruptedException im ServerGUIClient: Fehler beim Schlafenlegen des Threads"); //$NON-NLS-1$

														}
													}
														Thread.interrupted();
														hostInfo.getServerGuiBg().getClientState().setText(Messages.getString("ServerGUIClient.GameStarted")); //$NON-NLS-1$
												}});
												actionthread.start();
												}
												
												new Controller(new Client(socket, userName
														.getText()), servGUI);
												userName.setText(""); //$NON-NLS-1$
												username = ""; //$NON-NLS-1$
												ip.setText(""); //$NON-NLS-1$

											} catch (IOException e1) {
												exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.IOException.newThread")); //$NON-NLS-1$
											}
										} else if (message.equals("denied")){ //$NON-NLS-1$
											userName.setText(Messages.getString("ServerGUIClient.NameBenutzt")); //$NON-NLS-1$
											playerCounter--;
										} else if (message.equals("full")) { //$NON-NLS-1$
											userName.setText(Messages.getString("ServerGUIClient.GenuegendSpieler")); //$NON-NLS-1$
										}
									} else {
										if (!serverIsAvailable) {
											ip.setText(Messages.getString("ServerGUIClient.ServerNichtVerfuegbar")); //$NON-NLS-1$
										} else if (playerCounter >= maximumPlayers) {
											ip.setText(Messages.getString("ServerGUIClient.MaxErreicht")); //$NON-NLS-1$
										} else {
											joinPressed = false;
										}
									}
								} else {
									if (username == null) {
										userName.setText(Messages.getString("ServerGUIClient.Leer")); //$NON-NLS-1$
									} else if ((username.length() > 7)) {
										userName.setText(Messages.getString("ServerGUIClient.ZuLang")); //$NON-NLS-1$
									} else if (username.contains(" ")) { //$NON-NLS-1$
										userName.setText(Messages.getString("ServerGUIClient.Leerzeichen")); //$NON-NLS-1$
									} else if ((username.equals(""))) { //$NON-NLS-1$
										userName.setText(Messages.getString("ServerGUIClient.Leer")); //$NON-NLS-1$
									}
								}
							}
							}
						}
					
				});
				countDownThread.start();
			}});
		}
	/**
	 * Updates the text boxes
	 */
	public void caretUpdate(CaretEvent e) {
		if (joinPressed) {
			joinPressed = false;
		} else {
			if (e.getSource() == userName) {
				username = userName.getText();
			} else {
				serverIP = ip.getText();
			}
		}
	}
	/**
	 * Draws the background of the panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphic = (Graphics2D) g;
		Rectangle rect = new Rectangle(0, 0, rectWidth, rectHeight);
		graphic.setColor(new Color(221, 48, 60));
		graphic.fill(rect);
	}
	public static void setIsFull(boolean b) {
		isFull = b;		
	}
	@SuppressWarnings("deprecation")
	public static void serverClosed(){
		 final ExceptionLogger exceptionLogger = ExceptionLogger.getInstance();
		Thread actionThread;
		try{
		actionthread.stop();
		}catch (Exception e) {
			exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.Exception.ThreadStop")); //$NON-NLS-1$
		}
		actionThread = new Thread(new Runnable() {
			public void run() {
				for(int i = 0; i<3;i++){
					try{
				hostInfo.getServerGuiBg().getClientState().setText("Server Closed"); //$NON-NLS-1$
					}
					catch(Exception e){
						
						exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.Exception.CloseServer")); //$NON-NLS-1$
					}
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.InterruptedException.ThreadSleep")); //$NON-NLS-1$

				}
				try{
					hostInfo.getServerGuiBg().getClientState().setText(""); //$NON-NLS-1$
				}catch(Exception e){
					exceptionLogger.writeException(Level.SEVERE, "Exception beim beim Schließen des Servers"); //$NON-NLS-1$

				}

				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					exceptionLogger.writeException(Level.SEVERE, Messages.getString("ServerGUIClient.InterruptedException.ThreadSleep")); //$NON-NLS-1$

				}
			}
				hostInfo.getServerGuiBg().getClientLog().setVisible(false);
				hostInfo.getServerGuiBg().getRect_1().setVisible(true);
				}
		});
		actionThread.start();

		
	}
	public Thread getActionthread() {
		return actionthread;
	}
	public static boolean isServerDown() {
		return serverDown;
	}
	public static void setServerDown(boolean serverDown) {
		ServerGUIClient.serverDown = serverDown;
	}
	protected boolean getIsFull() {
		return isFull;
	}
	
}
