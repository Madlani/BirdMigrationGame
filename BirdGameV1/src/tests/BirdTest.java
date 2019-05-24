package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gamePackage.Bird;
import gamePackage.BirdType;
import gamePackage.FlyState;
import gamePackage.HitBox;

class BirdTest {

Bird testOsprey = new Bird(BirdType.OSPREY, null);
Bird testNorthernHarrier = new Bird(BirdType.NORTHERNHARRIER, null);
	
	//Testing movement

	@Test
	public void testMoveLeft() {
		testOsprey.setLocation(5, 5);
		testOsprey.moveLeft();
		
		assertEquals(-5, testOsprey.getX());
	}
	
	@Test
	public void testMoveRight() {
		testOsprey.setLocation(8, 8);
		testOsprey.moveRight();
		
		assertEquals(18, testOsprey.getX());

	}
	
	@Test
	public void testMoveUp() {
		testOsprey.setLocation(5, 5);
		testOsprey.moveUp();
		
		assertEquals(-5, testOsprey.getY());
	}
	
	@Test
	public void testMoveDown() {
		testOsprey.setLocation(5, 5);
		testOsprey.moveDown();
		
		assertEquals(15, testOsprey.getY());
	}
	
	//Testing health
	@Test
	public void testDecreaseHealthCount() {
		testOsprey.decreaseHealthCount();
		assertEquals(4, testOsprey.getHealthCount());
	}
	
	@Test
	public void testIncreaseHealthCount() {
		testOsprey.increaseHealthCount();
		assertEquals(6, testOsprey.getHealthCount());
		
		testOsprey.increaseHealthCount(5);
		assertEquals(9, testOsprey.getHealthCount()); //Can only go up to 9 as defined in increaseHealthCount(int increase)
		
		testOsprey.setHealthCount(1);
		testOsprey.increaseHealthCount(6);
		assertEquals(7, testOsprey.getHealthCount());
		
	}

	//Testing getters and setters
	@Test
	public void testGetBird() {
		assertEquals(this.testOsprey, this.testOsprey.getBird());
		assertEquals(this.testNorthernHarrier, this.testNorthernHarrier.getBird());
	}
	
	@Test
	public void testGetFlyState() {
		this.testOsprey.setFlyState(FlyState.DOWN);
		assertEquals(FlyState.DOWN, this.testOsprey.getFlyState());
		
		this.testNorthernHarrier.setFlyState(FlyState.UP);
		assertEquals(FlyState.UP, this.testNorthernHarrier.getFlyState());
	}
	
	@Test
	public void testGetBirdBox() {
		HitBox b = new HitBox(5, 5, 5, 5);
		this.testOsprey.setBirdBox(b.x, b.y, b.width, b.height);
		assertEquals(b, this.testOsprey.getBirdBox());
		
		HitBox c = new HitBox(5, 5, 5, 5);
		this.testNorthernHarrier.setBirdBox(c.x, c.y, c.width, c.height);
		assertEquals(c, this.testNorthernHarrier.getBirdBox());
	}
	
	@Test
	public void testGetStartingXandY() {
		this.testOsprey.setStartingX(4.0);
		this.testOsprey.setStartingY(4.0);
		
		assertEquals(4.0, this.testOsprey.getStartingX());
		assertEquals(4.0, this.testOsprey.getStartingY());
	}
	
	
}
