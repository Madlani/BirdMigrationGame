package gamePackage;
import java.awt.Color;
import java.awt.Dimension;
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
	
	private Color background;
	//BufferedImage backgroundImage = createImage("src/bird_images/background.png");
	private BufferedImage[][] birdAnimationArray;
	private BufferedImage[][] obstacleAnimationArray;
	private String movement; 
	
	private int frameWidth = 736;
	private int frameHeight = 581;
	private int imgWidth = 165;
	private int imgHeight = 165;
	public JButton pauseButton;
	public boolean pauseButtonFlag = false;
	
	private double x;
	private double y;
	private int dir;
	
	public View(Controller controller) {
		
		// Creates a pause button
		//pauseButton = new JButton("Pause");
		//pauseButton.setBounds(80, 80, frameHeight, frameWidth);
		//this.add(pauseButton);
		//pauseButton.setActionCommand("Paused"); // action command used to track if button was pressed
		
		// Creates the array to store the bird images
		birdAnimationArray = new BufferedImage[1][1];
		birdAnimationArray[0][0] = createImage("src/bird_images/osprey.png");
		
		//Allows key presses to work with JPanel
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	public void drawBackground() {
		
	}
	
	private BufferedImage createImage(String name) {

		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(name));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void paintComponent() {
		
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
			this.setBackground(Color.gray);
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

	public BufferedImage[][] getBirdAnimationArray() {
		return birdAnimationArray;
	}

	public BufferedImage[][] getObstacleAnimationArray() {
		return obstacleAnimationArray;
	}

	public void setBirdAnimationArray(BufferedImage[][] birdAnimationArray) {
		this.birdAnimationArray = birdAnimationArray;
	}

	public void setObstacleAnimationArray(BufferedImage[][] obstacleAnimationArray) {
		this.obstacleAnimationArray = obstacleAnimationArray;
	}
	
	public String getMovement() {
		return this.movement;
	}
	public void setMovement(String movement) {
		this.movement = movement;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	//public void updateButton(Controller controller) {
	//	pauseButton.addActionListener(controller);
	//}
	
}

//-----------------------------------------------------------------------------------------------------



