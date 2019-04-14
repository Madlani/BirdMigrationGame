import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class Bird {
	private int xPosition;
	private int YPosition;
	private int health;
	//file location of the bird's image
	private String sprite;
	
	public Bird() {
		// TODO Auto-generated constructor stub
	}
	
	//Decrements the xPosition of the bird (in order for it to move left)
	public void moveLeft() {
	
	}
	
	//Increments the xPosition of the bird (in order for it to move right)
	public void moveRight() {
		
	}
	
	//Decrements the yPosition of the bird (in order for it to move up)
	public void moveUp() {
		
	}
	
	//Increments the yPosition of the bird (in order for it to move down)
	public void moveDown() {
		
	}
	
	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getYPosition() {
		return YPosition;
	}

	public void setYPosition(int yPosition) {
		YPosition = yPosition;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

}

//-----------------------------------------------------------------------------------------------------
//JUnit Tests

class BirdTest {
	Bird testBird = new Bird();
	
	@Test
	public void testMoveLeft() {
		testBird.setxPosition(5);
		testBird.moveLeft();
		assertEquals(4, testBird.getxPosition());
	}
	
	@Test
	public void testMoveRight() {
		testBird.setxPosition(8);
		testBird.moveRight();
		assertEquals(9, testBird.getxPosition());
	}
	
	@Test
	public void testMoveUp() {
		testBird.setYPosition(5);
		testBird.moveUp();
		assertEquals(4, testBird.getxPosition());
	}
	
	@Test
	public void testMoveDown() {
		testBird.setYPosition(5);
		testBird.moveDown();
		assertEquals(6, testBird.getxPosition());
	}
}
