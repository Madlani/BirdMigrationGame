package gamePackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.junit.Test;

@SuppressWarnings("serial")
public class SideSwiperView extends View {
	
	private Image birdImg = Toolkit.getDefaultToolkit().createImage("src/bird_images/smaller osprey.gif");
	private Image g1;
	public int imgVelX = -2;
	private int scaledImageWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int scaledImageHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private Image obstacleImg = Toolkit.getDefaultToolkit().createImage("src/bird_images/tinyplane.png");
	int birdX, birdY, planeX, planeY;
	
	public SideSwiperView(Controller controller) {
		super(controller);
		birdX = (int)controller.getGameModel().osprey.getX();
		birdY = (int)controller.getGameModel().osprey.getY();
		planeX = (int)controller.getGameModel().airplane.getX();
		planeY = (int)controller.getGameModel().airplane.getY();
		// Creates a JPanel for the background
		this.setPreferredSize(new Dimension(736, 581));
		this.loadImage();
	}
	
	//Draws the blocks which must be collided with to answer questions
	public void displayBlocks() {
		
	}
	
	//Draws the background for the SideSwiper minigame
	@Override
	public void drawBackground() {
		
		
	}
	
	private void loadImage() {
		ImageIcon grass_1 = new ImageIcon("src/bird_images/grass3.png");
		g1 = grass_1.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
	}
	
	@Override
	public void paintComponent(Graphics g) {	
		imgVelX-=5;
		g.drawImage(g1, (imgVelX % scaledImageWidth), 0, null); // draws image in the window
		g.drawImage(g1, ((imgVelX % scaledImageWidth)+scaledImageWidth), 0, null); // draws image in the window, had to make second image the same as the first for continuity
		g.drawImage(birdImg, birdX, birdY, null);
		g.drawImage(obstacleImg, planeX, planeY, null);
		this.update(birdX, birdY, imgVelX);
	}
}



//-----------------------------------------------------------------------------------------------------
//JUnit Tests

class SideSwiperViewTest {
	
	@Test
	public void testDisplayBlocks() {
		// GUI element - cannot test at this time
	}
	
	@Test
	public void testDrawBackground() {
		// GUI element - cannot test at this time
	}
}
