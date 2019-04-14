import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Model {


	private int frameHeight;
	private int frameWidth;
	private int imgHeight;
	private int imgWidth;
	protected int xloc, yloc;
	protected int xVector, yVector;
	protected int xIncr, yIncr;
	private int direction;
	private int health;
	

    public Model(int w, int h, int iw, int ih) {
    	this.frameWidth = w;
    	this.frameHeight = h;
    	this.imgWidth = iw;
    	this.imgHeight = ih;
    }
	
	//updateLocationAndDirection() will contain the logic that allows the bird to move in the x or y direction based on user input
	public void updateLocationAndDirection() {
		
	}
	
	//detectCollisions() will contain the logic that determines if the bird model has collided with objects such as the ground and other obstacles
	public boolean detectCollisions() {
		return false;
	}
	
	public int getFrameHeight() {
		return frameHeight;
	}
	
	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public int getXloc() {
		return xloc;
	}

	public void setXloc(int xloc) {
		this.xloc = xloc;
	}

	public int getYloc() {
		return yloc;
	}

	public void setYloc(int yloc) {
		this.yloc = yloc;
	}

	public int getxVector() {
		return xVector;
	}

	public void setxVector(int xVector) {
		this.xVector = xVector;
	}

	public int getyVector() {
		return yVector;
	}

	public void setyVector(int yVector) {
		this.yVector = yVector;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}

//-----------------------------------------------------------------------------------------------------
//JUnit Tests

class ModelTest {

	@Test
	public void testUpdateLocationAndDirection() {
		Model test = new Model(5, 5, 10, 10);
		test.setXloc(0);
		test.setxVector(1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getXloc());
		assertNotEquals(1, test.getxVector());
	}
	
	@Test
	public void testDetectCollisions() {
		Model test = new Model(5, 5, 10, 10);
		assertEquals(false, test.detectCollisions());
		assertFalse(test.detectCollisions());
	}

}
