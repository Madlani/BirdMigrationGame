package gamePackage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class View extends JPanel {
	private Color background;
	private String movement; 

	public JButton pauseButton;
	public boolean pauseButtonFlag = false;
		
	public View() {
		
		//Allows key presses to work with JPanel
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	/**
	 * drawBackground()
	 * Draws the background for the view.
	 */
	public void drawBackground() {
		
	}
	
	/**
	 * createImage()
	 * Creates the image that is passed into this method.
	 * @param name, the file path for the image to be created
	 * @return a BufferedImage of the image.
	 */
	protected BufferedImage createImage(String name) {
		
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(name));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * paintComponent()
	 * Paints the view component when called.
	 * @param g
	 */
	public void paintComponent(Graphics2D g) {
		
	}
	
	/**
	 * getPreferredSize()
	 * Overrides the JPanel's getPreferredSize()
	 * @return a new Dimension.
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension();
	}

	/**
	 * update()
	 * Abstract method which must be implemented in any view classes extending this View.
	 * @param list, an ArrayList containing the GameObjects
	 */
	public abstract void update(ArrayList<GameObject> list);
	
	/**
	 * getBackground()
	 * @return the color of the background
	 */
	@Override
	public Color getBackground() {
		return background;
	}

	/**
	 * setBackground()
	 * Sets the background to the one provided in the parameter.
	 * @param background, the color of the background
	 */
	@Override
	public void setBackground(Color background) {
		this.background = background;
	}
	
	/**
	 * getMovement()
	 * Returns the string representation of the movement.
	 * @return the movement.
	 */
	public String getMovement() {
		return this.movement;
	}
	
	/**
	 * setMovement()
	 * Sets the string representation of the movement to be the one specified in the parameter.
	 * @param movement
	 */
	public void setMovement(String movement) {
		this.movement = movement;
	}
}


