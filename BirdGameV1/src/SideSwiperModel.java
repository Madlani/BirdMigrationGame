import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SideSwiperModel extends Model {
	
	//updateLocationAndDirection() will contain the logic that allows the bird to move in the x or y direction based on user input
	@Override
	public void updateLocationAndDirection() {
		
	}
	
	//updateBirdLocation() will update the position of the bird to update the minimap
	public void updateBirdLocation() {
		
	}
	
	//updateObstacleLocation() will update where the obstacles are on screen 
	public void updateObstacleLocation() {
		
	}
	
	//detectCollisions() will contain the logic that determines if the bird model has collided with objects such as the ground and other obstacles
	@Override
	public boolean detectCollisions() {
		return false;
	}
	
	//randomizeBlocks() will randomize where blocks that contain questions will appear on screen
	public void randomizeBlocks() {
		
	}
	
	//randomizeQuestion() will pick a random question to appear if the bird hits a question box
	public void randomizeQuestion() {
		
	}
	
	//moveObstacles() moves the obstacles as the game progresses
	public void moveObstacles() {
		
	}
	
}

//-----------------------------------------------------------------------------------------------------
//JUnit Tests
class SideSwiperModelTest {
	
	@Test
	public void testUpdateLocationAndDirection() {
		SideSwiperModel test = new SideSwiperModel();
		test.setXloc(0);
		test.setxVector(1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getXloc());
		assertNotEquals(1, test.getxVector());
	}
	
	@Test
	public void testUpdateBirdLocation() {
		SideSwiperModel test = new SideSwiperModel();
		test.setXloc(0);
		test.setYloc(1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getXloc());
		assertNotEquals(1, test.getYloc());
	}
	
	@Test
	public void testUpdateObstacleLocation() {
		SideSwiperModel test = new SideSwiperModel();
		test.setXloc(0);
		test.setYloc(1);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getXloc());
		assertNotEquals(1, test.getYloc());
	}
	
	@Test
	public void testDetectCollisions() {
		SideSwiperModel test = new SideSwiperModel();
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
		Model test = new SideSwiperModel();
		test.setXloc(0);
		test.setYloc(0);
		int xloc1 = test.getXloc();
		int yloc1 = test.getYloc();
		test.updateLocationAndDirection();
		assertNotEquals(xloc1, test.getXloc());
		assertNotEquals(yloc1, test.getYloc());
	}
}

