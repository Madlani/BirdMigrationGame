package gamePackage;

public class MiniMapModel extends Model {
	
	public MiniMapModel() {
		super();
	}
	//updateLocationAndDirection() will contain the logic that allows the bird to move in the x or y direction based on user input
	@Override
	public boolean updateLocationAndDirection()	{
		return false;
	}
	
	//Will update the minimap image based on the travel path of both birds, and will generate a point where the bird currently is on the map
	public void plotPoint()	{
		
	}
}


