package tests;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gamePackage.MiniMapModel;

class MiniMapModelTest {

	@Test
	public void testUpdateLocationAndDirection() {
		MiniMapModel test = new MiniMapModel(5, 5, 10, 10);
		test.setLocation(0, 0);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getX());
	}
	
	@Test
	public void testPlotPoint() {
		//can't test at this time
	}

}
