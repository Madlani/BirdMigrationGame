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
	
<<<<<<< HEAD
	private Image birdImg = Toolkit.getDefaultToolkit().createImage("src/images/smaller osprey.gif");
=======
	private Image birdImg = Toolkit.getDefaultToolkit().createImage("src/bird_images/smaller osprey.gif");
>>>>>>> 1974baf4edad70ef94db8ecb63e1560156bbdc23
	private Image g1;
	public int imgVelX = -2;
	private int scaledImageWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int scaledImageHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
<<<<<<< HEAD
	private Image obstacleImg = Toolkit.getDefaultToolkit().createImage("src/images/airplane.gif");
	private Image blockImg = Toolkit.getDefaultToolkit().createImage("src/images/block.png");
	double birdX, birdY, planeX, planeY, blockX, blockY;
=======
	private Image obstacleImg = Toolkit.getDefaultToolkit().createImage("src/bird_images/tinyplane.png");
	double birdX, birdY, planeX, planeY;
>>>>>>> 1974baf4edad70ef94db8ecb63e1560156bbdc23
	
	public SideSwiperView(Controller controller) {
		super(controller);
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
<<<<<<< HEAD
		ImageIcon grass_1 = new ImageIcon("src/images/grass3.png");
=======
		ImageIcon grass_1 = new ImageIcon("src/bird_images/grass3.png");
>>>>>>> 1974baf4edad70ef94db8ecb63e1560156bbdc23
		g1 = grass_1.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
	}
	
	@Override
	public void paintComponent(Graphics g) {	
		imgVelX-=5;
		birdX = (int)controller.getGameModel().getOsprey().getX();
		birdY = (int)controller.getGameModel().getOsprey().getY();
		planeX = (int)controller.getGameModel().getAirplane().getX();
		planeY = (int)controller.getGameModel().getAirplane().getY();
<<<<<<< HEAD
		blockX = (int)controller.getGameModel().getBlock().getX();
		blockY = (int)controller.getGameModel().getBlock().getY();
		g.drawImage(g1, (imgVelX % scaledImageWidth), 0, null); // draws image in the window
		g.drawImage(g1, ((imgVelX % scaledImageWidth)+scaledImageWidth), 0, null); // draws image in the window, had to make second image the same as the first for continuity
		g.drawImage(birdImg, (int)birdX, (int)birdY, null);
		g.drawImage(blockImg, (int)blockX, (int)blockY, null);

=======
		g.drawImage(g1, (imgVelX % scaledImageWidth), 0, null); // draws image in the window
		g.drawImage(g1, ((imgVelX % scaledImageWidth)+scaledImageWidth), 0, null); // draws image in the window, had to make second image the same as the first for continuity
		g.drawImage(birdImg, (int)birdX, (int)birdY, null);
>>>>>>> 1974baf4edad70ef94db8ecb63e1560156bbdc23
		g.drawImage(obstacleImg, (int)planeX, (int)planeY, null);
		System.out.println("Printing from SideSwiperView BIRDX, BIRDY: " + birdX + ", " + birdY);
		this.update(birdX, birdY, imgVelX);
	}
}