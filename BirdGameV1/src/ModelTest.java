

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
