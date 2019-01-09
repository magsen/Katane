package gui;

import java.awt.*;
import javax.swing.*;

/**
 * The class enables the easy and uncomplicated creation of a label with background. 
 * In addition, the size is freely scalable.
 *  该类可以轻松，简单地创建带背景的标签。 此外，尺寸可自由扩展
 * @author LIAO Haoyun
 */

@SuppressWarnings("serial")
public class PlayerLabel extends JLabel {

	/**
	 * Width of the Labels
	 */
	private int width;

	/**
	 * Heigh of the Labels
	 */
	private int height;

	/**
	 * Constructor allows you to easily create a <code> label </ code> 
	 * with background image.
	 * 
	 * @param img
	 *            is the background picture
	 * @param width
	 *            is the width of the panel
	 * @param height
	 *            is the width of the panel
	 */
	public PlayerLabel(Image img, int width, int height) {
		this.width = width;
		this.height = height;
		img = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		setPreferredSize(new Dimension(width, height));
		setIcon(new ImageIcon(img));
		setVisible(true);
		setDoubleBuffered(true);
	}

	/**
	 * The method resets the background image of the label.
	 * 
	 * @param newImg
	 */
	public void setNewIcon(Image newImg) {
		newImg = newImg.getScaledInstance(width, height,
				java.awt.Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(newImg));
	}
}