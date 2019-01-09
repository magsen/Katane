package gui;

import java.awt.*;
import javax.swing.*;

/**
 * <code>JButton</code> with a display image.
 * 
 * @author YU Shijia
 *
 */
@SuppressWarnings("serial")
public class PlayerButton extends JButton{ //Create a button
	
	private int width;
	
	private int height;

	private boolean active;
	/**
	 * Creates a PlayerButton with wallpaper.
	 * @param img
	 *            Background image
	 */
	public PlayerButton(Image img, int width, int height) { // java.awt.Image provides various classes for creating and modifying images
		this.width = width;
		this.height = height;
		this.active = false;
		img = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); //Produces a smoother image with a width of "width" and a height of "height".
		setPreferredSize(new Dimension(width, height));
		setIcon(new ImageIcon(img));//getIcon() Returns the graphic image (glyph, icon) displayed by this label.
		                            //Create an ImageIcon that targets this image
		setVisible(true);
		setContentAreaFilled(false);//The button is set to be transparent so that it does not block the back background
		setBorderPainted(false);//Remove the border
		setDoubleBuffered(true);//Set this component to draw using a buffer
	}
	
	/**
	*Changes the image of a button to img
	*
	* @param img is the new picture
	*/
	public void changeIcon(Image img) {
		img = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(img));
	}
	
	/**
	* Changes the image of a button to img 
	* In addition, a Boolean variable is set, which is swiped when the button status changes
	*
	* @param img is the new picture
	*/
	public void changeIconActivity(Image img) {
		this.active = !active;
		img = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(img));
	}
	
	public boolean isActive(){
		return active;
	}

}
