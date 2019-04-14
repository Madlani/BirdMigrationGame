import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MigrationModel extends Model {
	
	public MigrationModel(int w, int h, int iW, int iH) {
		super(w, h, iW, iH);
	}
	//detectCollisions() will contain the logic that determines if the bird model has collided with objects such as the ground and other obstacles
	@Override
	public boolean detectCollisions() {
		return false;
	}
	
	//updateLocationAndDirection() will contain the logic that allows the bird to move in the x or y direction based on user input
	@Override
	public void updateLocationAndDirection() {
		
	}
	
	//randomizeObstacles() will contain the logic that randomizes where obstacles such as blocks or enemies will appear in the game
	public void randomizeObstacles() {
		
	}
}


//-----------------------------------------------------------------------------------------------------
//JUnit Tests

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
		test.setXloc(0);
		test.setxVector(1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getXloc());
		assertNotEquals(1, test.getxVector());
	}
	
	@Test
	public void testRandomizeObstacles() {
		//can't test at this time
	}

}
