

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		test.setXloc(0);
		test.setxVector(1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getXloc());
		assertNotEquals(1, test.getxVector());
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
