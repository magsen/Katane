package gui;

import gui.ImportServerImages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;


/**
 * Panel that draws the <i>Settler of Catan</i> logo
 * 
 * @author LIAO Haoyun
 * 
 */

@SuppressWarnings("serial")
public class ServerGUILogo extends JPanel {
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
	
	public ServerGUILogo() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		width = d.width;
		height = d.height;
		rectWidth = width/4+width/16;
		rectHeight = height/5;
		createWidgets();
		addWidgets();
	}
	public void createWidgets(){

	}
	public void addWidgets(){
		
	}
	/**
	 * draws the <i>Settler of Catan</i> logo
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphic = (Graphics2D) g;
		graphic.setColor(new Color(221, 48, 60));
		graphic.fillRect(0, 0, rectWidth, rectHeight);
		Image img = ImportServerImages.logoBackground;
		graphic.drawImage(img, rectWidth/6, rectHeight/6, rectWidth-rectWidth/3, rectHeight-rectHeight/3, this);
			
		}
}
