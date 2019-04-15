

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ControllerTest {
	Controller testController = new Controller();
	
	@Test
	public void testStart() {
		assertEquals(true, testController.getControllerStart());
	}
	
	@Test
	public void testKeyTyped() {
		assertEquals(true, testController.getKeyPressed());
	}
	
	@Test
	public void testKeyReleased() {
		assertEquals(true, testController.getKeyReleased());
	}
	
	@Test
	public void testActionPerformed() {
		assertEquals(true, testController.getActionPerformed());
	}
	
}
