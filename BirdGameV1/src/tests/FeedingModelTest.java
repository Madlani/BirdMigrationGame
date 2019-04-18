package tests;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gamePackage.FeedingModel;

class FeedingModelTest {
	@Test
	public void testDetectCollisions() {
		FeedingModel test = new FeedingModel(5, 5, 10, 10);
		assertEquals(false, test.detectCollisions());
		assertFalse(test.detectCollisions());
	}
	
	@Test
	public void testUpdateLocationAndDirection() {
		FeedingModel test = new FeedingModel(5, 5, 10, 10);
		test.setLocation(0, 0);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getX());
	}
	
	@Test
	public void testDive() {
		//can't test at this time
	}
	
	@Test
	public void testIsHoldingFish() {
		FeedingModel test = new FeedingModel(5, 5, 10, 10);
		assertEquals(false, test.isHoldingFish());
		assertFalse(test.isHoldingFish());
	}
}
