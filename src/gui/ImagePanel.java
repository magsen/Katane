package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The class creates a simple JPanel with background
 * 
 * @author YU Shijia
 */

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	private int width;

	private int height;
	/*
	 * Displayed image
	 */
	private Image img;
	/*
	 * Optional label for possible text
	 */
	private JLabel label;

	/*
	 * The font of numbers
	 */
	private Font font;

	public ImagePanel(Image img, int width, int height) {
		this.img = img.getScaledInstance(width, height,
				java.awt.Image.SCALE_SMOOTH);
		this.width = width;
		this.height = height;
		this.font = new Font("Times New Roman", Font.BOLD, (int) (width * 0.7));
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);//Sets the preferred size of this component.
		setMaximumSize(size);
		setMinimumSize(size);
		setOpaque(false);
		setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}

	/*
	 * Adds a label with text to the ImagePanel.
	
	 */
	public void addLabel(String text) {
		label = new JLabel();
		label.setBackground(Color.YELLOW);
		label.setPreferredSize(new Dimension((int) (width * 0.9),
				(int) (height * 0.6)));
		label.setText(text);
		label.setFont(font);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		add(label);
	}

	/*
	 
	 * @param c
	 *           Color of the text
	 */
	public void addLabel(String text, Color c) {
		Font font = new Font("Times New Roman", Font.BOLD, 
				(int) (height * 0.65));
		label = new JLabel();
		label.setPreferredSize(new Dimension((int) (width * 0.7),
				(int) (height * 0.7)));
		label.setText(text);
		label.setFont(font);
		label.setForeground(c);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		add(label);
	}

	public void setText(String text) {
		label.setText(text);
	}

	public JLabel getLabel() {
		return label;
	}
}
