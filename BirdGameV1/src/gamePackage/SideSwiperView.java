package gamePackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

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
		
		ImageIcon grass_1 = new ImageIcon("src/bird_images/grass_1.png");
		ImageIcon grass_2 = new ImageIcon("src/bird_images/grass_2.png");
		g1 = icon.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		g2 = icon.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		
		backgroundImage = icon.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		birdImg = icon2.getImage();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("testing paintComponent");
		
		Graphics2D twoD = (Graphics2D)g;
		 
        if (backgroundImage == null) {
        	backgroundImage = createImage(getWidth(), getHeight());
        }
 
        // Create a buffer to draw to
        Graphics buffer = backgroundImage.createGraphics();
 
        // Put the two copies of the background image onto the buffer
        g1.draw(buffer);
        g2.draw(buffer);
 
        // Draw the image onto the window
        twoD.drawImage(backgroundImage, null, 0, 0);
		
		g.drawImage(backgroundImage, (imgVelX-=10 % scaledImageWidth), 0, null); // draws image in the window
		g.drawImage(backgroundImage, (((imgVelX-=10)%scaledImageWidth)+scaledImageWidth), 0, null); // draws image in the window
		g.drawImage(birdImg, Controller.getBird().getXPosition(), Controller.getBird().getYPosition(), null);
		this.update(Controller.getBird().getXPosition(), Controller.getBird().getYPosition(), imgVelX);
		System.out.println(Controller.getBird().getXPosition() + ", " +Controller.getBird().getYPosition());
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
