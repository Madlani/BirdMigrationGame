package tests;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gamePackage.Model;

class ModelTest {

	@Test
	public void testUpdateLocationAndDirection() {
		Model test = new Model(5, 5, 10, 10);
		test.setLocation(0, 0);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getX());
	}
	
	@Test
	public void testGetXLoc() {
		Model test = new Model (5, 4, 3, 2);
		test.setLocation(20, 0);
		assertEquals(true,  test.getX() == 20);
	}
	
	@Test
	public void testDetectCollisions() {
		Model test = new Model(5, 5, 10, 10);
		assertEquals(false, test.detectCollisions());
		assertFalse(test.detectCollisions());
	}
	
	@Test
	public void testGetFrameHeight() {
		Model test = new Model(5, 4, 3, 2);
		assertEquals(true, test.getFrameHeight() == 4);
	}
	
	@Test
	public void testSetFrameHeight() {
		Model test = new Model(5, 4, 3, 2);
		test.setFrameHeight(10);
		assertEquals(true, test.getFrameHeight() == 10);
	}
	
	@Test
	public void testSetFrameWidth() {
		Model test = new Model(5, 4, 3, 2);
		test.setFrameWidth(20);
		assertEquals(true, test.getFrameWidth() == 20);
	}
	
	@Test
	public void testGetImgHeight() {
		Model test = new Model(5, 4, 3, 2);
		test.setImgHeight(20);
		assertEquals(true, test.getImgHeight() == 20);
	}
	
	@Test
	public void testSetImgHeight() {
		Model test = new Model(5, 4, 3, 2);
		test.setImgHeight(20);
		assertEquals(true, test.getImgHeight() == 20);
	}
	
	@Test
	public void testGetImgWidth() {
		Model test = new Model(5, 4, 3, 2);
		test.setImgWidth(20);
		assertEquals(true, test.getImgWidth() == 20);
	}
	
	@Test
	public void testSetImgWidth() {
		Model test = new Model(5, 4, 3, 2);
		test.setImgWidth(20);
		assertEquals(true, test.getImgWidth() == 20);
	}
	
	
	@Test public void testGetDirection() {
		Model testModel = new Model(5, 4, 3, 2);
		testModel.setDirection(9);
		assertEquals(true, testModel.getDirection() == 9);
	}
	
	@Test public void testSetDirection() {
		Model testModel = new Model(5, 4, 3, 2);
		testModel.setDirection(9);
		assertEquals(true, testModel.getDirection() == 9);
	}
	
	@Test public void testGetHealth() {
		Model testModel = new Model(5, 4, 3, 2);
		testModel.setHealth(9);
		assertEquals(true, testModel.getHealth() == 9);
	}
	
	@Test public void testSetHealth() {
		Model testModel = new Model(5, 4, 3, 2);
		testModel.setHealth(9);
		assertEquals(true, testModel.getHealth() == 9);
	}
}
