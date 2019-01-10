package gui;

import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * Manages imported graphics from the server.
 * 
 * @author YU Shijia
 */
public class ImportServerImages {
	
	public static Image shutDownIcon;
	
	// Ö÷»ú°´Å¥ 
	public static Image hostIcon;
	
	public static Image joinIcon;
	
	/*
	 * Abbruch Button
	 */
	public static Image denyBtn;
	
	public static Image confirmBtn;
	
	/**
	 * background image
	 */
	public static Image logoBackground;
	
	public static Image serverButton;
	
	public static Image serverButton_4;

	public static Image spaceMarineAvatar;

	public static Image kerriganAvatar;
	
	public static Image zergAvatar;
	
	public static Image tossAvatar;
	
	public static Image artasAvatar;

	public static Image daemonAvatar;
	
	public static Image humanAvatar;
	
	public static Image nightelfAvatar;
	
	public static Image orcAvatar;
	
	public static Image undeadAvatar;
	
	public static Image joinKi;
	
	public static Image joinEnglish;
	
	public static Image quitKey;
	
	public void loadServerPics() {
		/*joinEnglish = new ImageIcon(getClass().getResource("graphics/info/buttonAmerican.png")).getImage();
		joinIcon = new ImageIcon(getClass().getResource(
				"graphics/info/join.png")).getImage();
		*/
		denyBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/cancelBtn.png")).getImage();
		
		confirmBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/confirmBtn.png")).getImage(); 
		
		logoBackground  = new ImageIcon(getClass().getResource(
				"graphics/background/Siedler_von_Catan_Logo.png")).getImage(); 
		
		serverButton = new ImageIcon(getClass().getResource(
				"graphics/info/ServerButton.png")).getImage(); 
		
		serverButton_4 = new ImageIcon(getClass().getResource(
				"graphics/info/4Spieler.png")).getImage(); 
		
		shutDownIcon = new ImageIcon(getClass().getResource(
				"graphics/info/Quit.png")).getImage();

		kerriganAvatar = new ImageIcon(getClass().getResource(
				"graphics/avatars/kerrigan.png")).getImage(); 

		zergAvatar = new ImageIcon(getClass().getResource(
				"graphics/avatars/zerg.png")).getImage(); 

		tossAvatar = new ImageIcon(getClass().getResource(
				"graphics/avatars/toss.png")).getImage(); 
		
		artasAvatar = new ImageIcon(getClass().getResource("graphics/avatars/artas.png")).getImage();
		
		daemonAvatar = new ImageIcon(getClass().getResource("graphics/avatars/daemon.png")).getImage();
		
		humanAvatar = new ImageIcon(getClass().getResource("graphics/avatars/human.png")).getImage();
		
		nightelfAvatar = new ImageIcon(getClass().getResource("graphics/avatars/nightelf.png")).getImage();
		
		orcAvatar = new ImageIcon(getClass().getResource("graphics/avatars/orc.png")).getImage();
		
		undeadAvatar = new ImageIcon(getClass().getResource("graphics/avatars/undead.png")).getImage();
		
		joinKi = new ImageIcon(getClass().getResource("graphics/info/JoinKI.png")).getImage();
		
		quitKey = new ImageIcon(getClass().getResource("graphics/info/schlieb.png")).getImage();
	}
}
