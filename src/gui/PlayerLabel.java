package gui;

import java.awt.*;
import javax.swing.*;

/**
 * The class enables the easy and uncomplicated creation of a label with background. 
 * In addition, the size is freely scalable.
 *  ����������ɣ��򵥵ش����������ı�ǩ�� ���⣬�ߴ��������չ
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