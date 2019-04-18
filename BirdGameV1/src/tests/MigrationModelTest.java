package tests;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gamePackage.MigrationModel;

class MigrationModelTest {

	@Test
	public void testDetectCollisions() {
		MigrationModel test = new MigrationModel(5, 5, 10, 10);
		assertEquals(false, test.detectCollisions());
		assertFalse(test.detectCollisions());
	}
	
	@Test
	public void testUpdateLocationAndDirection() {
		MigrationModel test = new MigrationModel(5, 5, 10, 10);
		test.setLocation(0, 0);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getX());
	}
	
	@Test
	public void testRandomizeObstacles() {
		//can't test at this time
	}

}
