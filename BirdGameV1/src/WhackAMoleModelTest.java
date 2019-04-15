

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WhackAMoleModelTest {

	@Test
	public void testDetectCollisions() {
		WhackAMoleModel test = new WhackAMoleModel(5, 5, 10, 10);
		assertEquals(false, test.detectCollisions());
		assertFalse(test.detectCollisions());
	}
	
	@Test
	public void testRandomizeObjects() {
		//no need for test at this time
	}
	
}
