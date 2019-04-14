import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;
import org.junit.jupiter.api.Test;

@SuppressWarnings("serial")
public class View extends JPanel {
	
	private Color background;
	private BufferedImage[][] birdAnimationArray;
	private BufferedImage[][] obstacleAnimationArray;
	private String movement; 
	
	private int frameWidth = 800;
	private int frameHeight = 800;
	private int imgWidth = 165;
	private int imgHeight = 165;
	public JButton pauseButton;
	public boolean pauseButtonFlag = false;
	
	public View() {
		pauseButton = new JButton("Pause");
		this.add(pauseButton);
		pauseButton.setActionCommand("Paused"); // action command used to track if button was pressed

	}
	
	public void drawBackground() {
		
	}
	
	public void createImage() {
		
	}
	
	public void paintComponent() {
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension();
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

	public void updateButton(Controller controller) {
		pauseButton.addActionListener(controller);
	}
	
}

//-----------------------------------------------------------------------------------------------------
//JUnit Tests

class ViewTest {

	@Test
	public void testDrawBackground() {
		// GUI element - cannot test at this time
	}
	
	@Test
	public void testCreateImage() {
		// GUI element - cannot test at this time
	}
	
	@Test
	public void testPaintComponent() {
		// GUI element - cannot test at this time
	}
	
	@Test
	public void testGetPreferredSize() {
		// GUI element - cannot test at this time
	}
	
}
