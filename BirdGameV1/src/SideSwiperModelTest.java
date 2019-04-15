

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SideSwiperModelTest {
	
	@Test
	public void testUpdateLocationAndDirection() {
		SideSwiperModel test = new SideSwiperModel(5, 5, 10, 10);
		test.setXloc(0);
		test.setxVector(1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getXloc());
		assertNotEquals(1, test.getxVector());
	}
	
	@Test
	public void testUpdateBirdLocation() {
		SideSwiperModel test = new SideSwiperModel(5, 5, 10, 10);
		test.setXloc(0);
		test.setYloc(1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getXloc());
		assertNotEquals(1, test.getYloc());
	}
	
	@Test
	public void testUpdateObstacleLocation() {
		SideSwiperModel test = new SideSwiperModel(5, 5, 10, 10);
		test.setXloc(0);
		test.setYloc(1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getXloc());
		assertNotEquals(1, test.getYloc());
	}
	
	@Test
	public void testDetectCollisions() {
		SideSwiperModel test = new SideSwiperModel(5, 5, 10, 10);
		assertEquals(false, test.detectCollisions());
		assertFalse(test.detectCollisions());
	}
	
	@Test
	public void testRandomizeBlocks() {
		//no need for test at this time
	}
	
	@Test
	public void testRandomizeQuestion() {
		//no need for test at this time
	}
	
	@Test
	public void testMoveObstacles() {
		Model test = new SideSwiperModel(5, 5, 10, 10);
		test.setXloc(0);
		test.setYloc(0);
		int xloc1 = test.getXloc();
		int yloc1 = test.getYloc();
		test.updateLocationAndDirection();
		assertNotEquals(xloc1, test.getXloc());
		assertNotEquals(yloc1, test.getYloc());
	}
}
