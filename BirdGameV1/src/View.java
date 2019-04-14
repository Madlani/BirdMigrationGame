import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.junit.jupiter.api.Test;

@SuppressWarnings("serial")
public class View extends JPanel {
	
	private Color background;
	private BufferedImage[][] birdAnimationArray;
	private BufferedImage[][] obstacleAnimationArray; 
	
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
