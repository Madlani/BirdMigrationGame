

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MiniMapModelTest {

	@Test
	public void testUpdateLocationAndDirection() {
		MiniMapModel test = new MiniMapModel(5, 5, 10, 10);
		test.setXloc(0);
		test.setxVector(1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getXloc());
		assertNotEquals(1, test.getxVector());
	}
	
	@Test
	public void testPlotPoint() {
		//can't test at this time
	}

}
