package gamePackage;

@SuppressWarnings("serial")
public class GameObjectView extends View {
	private String movingDirection;
	
	public GameObjectView(Controller controller)	{
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



