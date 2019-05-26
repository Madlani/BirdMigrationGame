package gamePackage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class View extends JPanel implements Serializable {
	private Color background;
	private String movement; 

	public JButton pauseButton;
	public boolean pauseButtonFlag = false;
	
	public final int BORDER_X = 0;
	public final int BORDER_Y = 0;
	
	public boolean isTimeForRectangle = false;
		
	public boolean isTimeForRectangle() {
		return isTimeForRectangle;
	}

	public void setTimeForRectangle(boolean isTimeForRectangle) {
		this.isTimeForRectangle = isTimeForRectangle;
	}

	public View() {
		
		//Allows key presses to work with JPanel
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	public void drawRedRectangle(Graphics g) {
		Color OPAQUE_RED = new Color(.75f, 0f, 0f, .75f);
		g.setColor(OPAQUE_RED);
		g.fillRect(BORDER_X, BORDER_Y, Model.scaledImageWidth, Model.scaledImageHeight);
	}
	
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
	
	public void paintComponent(Graphics2D g) {
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension();
	}

	public abstract void update(ArrayList<GameObject> list);
	
	@Override
	public Color getBackground() {
		return background;
	}

	@Override
	public void setBackground(Color background) {
		this.background = background;
	}
	
	public String getMovement() {
		return this.movement;
	}
	public void setMovement(String movement) {
		this.movement = movement;
	}
}


