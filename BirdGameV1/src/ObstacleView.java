import org.junit.Test;

@SuppressWarnings("serial")
public class ObstacleView extends View {
	private String movingDirection;
	
	//Displays the correct Obstacles on the ObstacleView
	public void displayObstacle()	{
		
	}

	public String getMovingDirection() {
		return movingDirection;
	}

	public void setMovingDirection(String movingDirection) {
		this.movingDirection = movingDirection;
	}
	
	
}

//-----------------------------------------------------------------------------------------------------
//JUnit Tests

class ObstacleViewTest {
	
	@Test
	public void testDisplayObstacle() {
		// GUI element - cannot test at this time
	}

}
