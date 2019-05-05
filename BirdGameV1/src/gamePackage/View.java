package gamePackage;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.junit.jupiter.api.Test;

@SuppressWarnings("serial")
public abstract class View extends JPanel {
	private Color background;
	private String movement;

	public JButton pauseButton;
	public boolean pauseButtonFlag = false;
	
	private double x;
	private double y;
	private int dir;
	
	public View() {
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
	
	public void createButtons() {
        
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension();
	}

	public abstract void update(ArrayList<GameObject> list);
	
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


