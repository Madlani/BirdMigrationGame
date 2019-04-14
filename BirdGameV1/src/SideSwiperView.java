import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.junit.Test;

@SuppressWarnings("serial")
public class SideSwiperView extends View {
	
	private Image backgroundImage;
	
	public SideSwiperView() {
		super();
		
		// Creates a JPanel for the background
		loadImage();
		this.setPreferredSize(new Dimension(736, 581));
		this.setLayout(null);
	}
	
	//Draws the blocks which must be collided with to answer questions
	public void displayBlocks() {
		
	}
	
	//Draws the background for the SideSwiper minigame
	@Override
	public void drawBackground() {
		
	}
	
	private void loadImage() {
		ImageIcon icon = new ImageIcon("src/bird_images/osprey.png");
		backgroundImage = icon.getImage();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, null); // draws image in the window
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
