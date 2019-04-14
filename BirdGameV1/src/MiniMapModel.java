import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MiniMapModel extends Model {
	
	public MiniMapModel(int w, int h, int iW, int iH) {
		super(w, h, iW, iH);
	}
	//updateLocationAndDirection() will contain the logic that allows the bird to move in the x or y direction based on user input
	@Override
	public void updateLocationAndDirection()	{
		
	}
	
	//Will update the minimap image based on the travel path of both birds, and will generate a point where the bird currently is on the map
	public void plotPoint()	{
		
	}
}

//-----------------------------------------------------------------------------------------------------
//JUnit Tests
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
