package gui;

import controller.ServerController;
import java.awt.*;
import javax.swing.*;
/**
 * The window of the server gui
 * @author LIAO Haoyun
 *
 */
@SuppressWarnings("serial")
public class ServerGUI extends JFrame {
	/**
	 * Access the the class <code>ServerController</code>
	 */
	private ServerController serverController;
	/**
	 * Panel content of the server gui
	 */
	@SuppressWarnings("unused")
	private JPanel contentPane;
	/**
	 * The screen width
	 */
	private int width;
	/**
	 * The screen height
	 */
	private int height;
	
	public ServerGUI(final ServerController serverController)
			throws HeadlessException {
		this.serverController = serverController;
		super.setBackground(new Color(221, 48, 60));
		super.setBackground(Color.darkGray);
		setPreferredSize(new Dimension(width/2-width/6, 7*height/8));
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		width = d.width;
		height = d.height;
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(500, 500, width/2-width/6, 7*height/8);
		setLocationRelativeTo(null);
		addWidgets();

	}
	/**
	 * Adds the corresponding panels to the window
	 */
	public void addWidgets() {
		add(new ServerGUIBg(this));
	}

	public ServerController getServerController() {
		return serverController;
	}
	
	public ServerGUI getThis() {
		return this;
	}
}
