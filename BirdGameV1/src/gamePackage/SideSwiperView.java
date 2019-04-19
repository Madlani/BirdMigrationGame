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
	
	private Image backgroundImage, birdImg;
	private Image g1, g2;
	public int imgVelX = -2;
	private int scaledImageWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int scaledImageHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public SideSwiperView() {
		super();
		
		// Creates a JPanel for the background
		this.setPreferredSize(new Dimension(736, 581));
		//this.setLayout(null);
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
		ImageIcon icon = new ImageIcon("src/bird_images/grass.png");
		ImageIcon icon2 = new ImageIcon("src/bird_images/smaller osprey.gif");
		
		ImageIcon grass_1 = new ImageIcon("src/bird_images/grass3.png");
		//ImageIcon grass_2 = new ImageIcon("src/bird_images/grass_2.png");
		g1 = grass_1.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		//g2 = grass_2.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		
		//backgroundImage = grass_1.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		backgroundImage = icon.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		birdImg = icon2.getImage();
	}
	
	@Override
	public void paintComponent(Graphics g) {	
		imgVelX-=5;
		g.drawImage(g1, (imgVelX % scaledImageWidth), 0, null); // draws image in the window
		g.drawImage(g1, ((imgVelX % scaledImageWidth)+scaledImageWidth), 0, null); // draws image in the window, had to make second image the same as the first for continuity
		g.drawImage(birdImg, (int) Controller.getBird().getX(), (int) Controller.getBird().getY(), null);
		this.update(Controller.getBird().getX(), Controller.getBird().getY(), imgVelX);
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
