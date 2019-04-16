package gamePackage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.junit.Test;

@SuppressWarnings("serial")
public class SideSwiperView extends View {
	
	private Image backgroundImage, birdImg;
	public int imgVelX = -2;
	
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
		backgroundImage = icon.getImage();
		birdImg = icon2.getImage();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("testing paintComponent");
		
		g.drawImage(backgroundImage, (imgVelX-=10)%736, 0, null); // draws image in the window
		g.drawImage(backgroundImage, (((imgVelX-=10)%736)+736), 0, null); // draws image in the window
		g.drawImage(birdImg, Controller.getBird().getXPosition(), Controller.getBird().getYPosition(), null);
		this.update(Controller.getBird().getXPosition(), Controller.getBird().getYPosition(), imgVelX);
		System.out.println(Controller.getBird().getXPosition() + ", " +Controller.getBird().getYPosition());
	}
	
	
}



//-----------------------------------------------------------------------------------------------------



