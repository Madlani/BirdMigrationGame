package gamePackage;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class GameObjectView extends View {
	private String movingDirection;
	
	public GameObjectView()	{
		super();
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

	@Override
	public void update(ArrayList<GameObject> list) {
		// TODO Auto-generated method stub
		
	}	
}



