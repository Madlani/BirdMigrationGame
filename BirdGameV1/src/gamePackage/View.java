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
	
	protected JPanel mainPanel;
	public CardLayout cardLayout;
	
	protected JButton leftArrowKey;
	protected JButton rightArrowKey;
	protected JButton upArrowKey;
	protected JButton downArrowKey;

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
		this.upArrowKey = new JButton("up");
		this.downArrowKey = new JButton("down");
		this.leftArrowKey = new JButton("left");
		this.rightArrowKey = new JButton("right");
		
		mainPanel.getInputMap().put(KeyStroke.getKeyStroke("UP"), "up");
		mainPanel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "down");
		mainPanel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "left");
		mainPanel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "right");
		
		
		@SuppressWarnings("serial")
		Action upArrowPressed = new AbstractAction() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("up arrow pressed (SideSwiperView.java)");
        	}
        };
        
        @SuppressWarnings("serial")
		Action downArrowPressed = new AbstractAction() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("down arrow pressed (SideSwiperView.java)");
        	}
        };
        
        @SuppressWarnings("serial")
		Action leftArrowPressed = new AbstractAction() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("left arrow pressed (SideSwiperView.java)");
        	}
        };
        
        @SuppressWarnings("serial")
		Action rightArrowPressed = new AbstractAction() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("right arrow pressed (SideSwiperView.java)");
        	}
        };
        
        
        
        mainPanel.getActionMap().put("up", upArrowPressed);
        this.upArrowKey.addActionListener(upArrowPressed);
        this.upArrowKey.setMnemonic(KeyEvent.VK_UP);
        
        
        mainPanel.getActionMap().put("down", downArrowPressed);
        this.downArrowKey.addActionListener(downArrowPressed);
        this.downArrowKey.setMnemonic(KeyEvent.VK_DOWN);
        
        mainPanel.getActionMap().put("left", leftArrowPressed);
        this.leftArrowKey.addActionListener(leftArrowPressed);
        this.leftArrowKey.setMnemonic(KeyEvent.VK_LEFT);
        
        mainPanel.getActionMap().put("right", rightArrowPressed);
        this.rightArrowKey.addActionListener(rightArrowPressed);
        this.rightArrowKey.setMnemonic(KeyEvent.VK_RIGHT);
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


