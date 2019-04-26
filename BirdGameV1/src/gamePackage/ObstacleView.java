package gamePackage;
import org.junit.Test;

@SuppressWarnings("serial")
public class ObstacleView extends View {
	private String movingDirection;
	
	public ObstacleView(Controller controller)	{
		super(controller);
	}
	
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



