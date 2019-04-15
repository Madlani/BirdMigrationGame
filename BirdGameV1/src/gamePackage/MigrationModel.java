package gamePackage;
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



