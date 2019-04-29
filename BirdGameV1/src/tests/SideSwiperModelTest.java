package tests;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gamePackage.Model;
import gamePackage.SideSwiperModel;

class SideSwiperModelTest {
	
	@Test
	public void testUpdateLocationAndDirection() {
		SideSwiperModel test = new SideSwiperModel();
		test.setLocation(0, 0);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getX());
	}
	
	@Test
	public void testUpdateBirdLocation() {
		SideSwiperModel test = new SideSwiperModel();
		test.setLocation(0, 1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getX());
		assertNotEquals(1, test.getY());
	}
	
	@Test
	public void testUpdateObstacleLocation() {
		SideSwiperModel test = new SideSwiperModel();
		test.setLocation(0, 1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getX());
		assertNotEquals(1, test.getY());
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
		Model test = new SideSwiperModel();
		test.setLocation(0, 0);
		double xloc1 = test.getX();
		double yloc1 = test.getY();
		test.updateLocationAndDirection();
		assertNotEquals(xloc1, test.getX());
		assertNotEquals(yloc1, test.getY());
	}
}
