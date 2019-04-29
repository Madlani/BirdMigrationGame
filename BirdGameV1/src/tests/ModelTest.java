package tests;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gamePackage.Model;
import gamePackage.Obstacle;

class ModelTest {

	@Test
	public void testUpdateLocationAndDirection() {
		Model test = new Model();
		test.setLocation(0, 0);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getX());
	}
	
	@Test
	public void testGetXLoc() {
		Model test = new Model ();
		test.setLocation(20, 0);
		assertEquals(true,  test.getX() == 20);
	}
	
	@Test
	public void testDetectCollisions() {
		Model test = new Model();
		Obstacle obs = new Obstacle(20.0);
		assertEquals(false, test.detectCollisions(obs));
		assertFalse(test.detectCollisions(obs));
	}
	
	@Test
	public void testSetImgHeight() {
		Model test = new Model();
		test.setImgHeight(20);
		assertEquals(true, test.getImgHeight() == 20);
	}
	
	@Test
	public void testGetImgWidth() {
		Model test = new Model();
		test.setImgWidth(20);
		assertEquals(true, test.getImgWidth() == 20);
	}
	
	@Test
	public void testSetImgWidth() {
		Model test = new Model();
		test.setImgWidth(20);
		assertEquals(true, test.getImgWidth() == 20);
	}
	
	
	@Test public void testGetDirection() {
		Model testModel = new Model();
		testModel.setDirection(9);
		assertEquals(true, testModel.getDirection() == 9);
	}
	
	@Test public void testSetDirection() {
		Model testModel = new Model();
		testModel.setDirection(9);
		assertEquals(true, testModel.getDirection() == 9);
	}
	
	@Test public void testGetHealth() {
		Model testModel = new Model();
		testModel.setHealth(9);
		assertEquals(true, testModel.getHealth() == 9);
	}
	
	@Test public void testSetHealth() {
		Model testModel = new Model();
		testModel.setHealth(9);
		assertEquals(true, testModel.getHealth() == 9);
	}
}
