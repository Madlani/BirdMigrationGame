import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

public class FeedingModel extends Model {
	
	//detectCollisions() will contain the logic that determines if the bird model has collided with objects such as the ground and other obstacles
	@Override
	public boolean detectCollisions() {
		return false;
	}
	
	//updateLocationAndDirection() will contain the logic that allows the bird to move in the x or y direction based on user input
	@Override
	public void updateLocationAndDirection() {
		
	}
	
	//dive() will start the dive animation when the bird tries to catch a fish
	public void dive() {
		
	}
	
	//isHoldingFish() returns true if the bird is holding a fish else false
	public boolean isHoldingFish() {
		return false;
	}
}

//-----------------------------------------------------------------------------------------------------
//JUnit Tests
class FeedingModelTest {
	@Test
	public void testDetectCollisions() {
		FeedingModel test = new FeedingModel();
		assertEquals(false, test.detectCollisions());
		assertFalse(test.detectCollisions());
	}
	
	@Test
	public void testUpdateLocationAndDirection() {
		FeedingModel test = new FeedingModel();
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
		FeedingModel test = new FeedingModel();
		assertEquals(false, test.isHoldingFish());
		assertFalse(test.isHoldingFish());
	}
}
