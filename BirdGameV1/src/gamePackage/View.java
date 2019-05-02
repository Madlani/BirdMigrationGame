package gamePackage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.junit.jupiter.api.Test;

@SuppressWarnings("serial")
public class View extends JPanel {
	
	protected Controller controller;
	private Color background;
	private String movement; 

	public JButton pauseButton;
	public boolean pauseButtonFlag = false;
	
	private double x;
	private double y;
	private int dir;
	
	public View(Controller controller) {
		this.controller = controller;
		
		//Allows key presses to work with JPanel
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	public void drawBackground() {
		
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

	public void update(double x, double y, int d) {
		
		this.x = x;
		this.y = y;
		this.dir = d;
		
		try {
			this.repaint();
			Thread.sleep(15);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Color getBackground() {
		return background;
	}

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


