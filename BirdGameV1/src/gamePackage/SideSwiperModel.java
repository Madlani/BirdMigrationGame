package gamePackage;


public class SideSwiperModel extends Model {
	
	
	protected Bird osprey;
	protected Obstacle airplane;
	
	Bird bird;
	Obstacle obstacle;
	
	//protected HitBox birdBox;
	public SideSwiperModel(int w, int h, int iW, int iH) {
		super(w, h, iW, iH);
		this.bird = new Bird();
		this.obstacle = new Obstacle();
		this.bird.setLocation(15, 15);
		this.obstacle.setLocation(450, 450);
	}
	
	//updateLocationAndDirection() will contain the logic that allows the bird to move in the x or y direction based on user input
	@Override
	public void updateLocationAndDirection() {
    	bird.setLocation(this.bird.getX(), this.bird.getY());
    	obstacle.setLocation(this.obstacle.getX(), this.obstacle.getY());
		detectCollisions(); // check this after the locations have been updated
		
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
		return obstacle.obstacleBox.intersects(bird.birdBox);
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
	
	public Bird getBird()	{
		return bird;
	}
	
}

//-----------------------------------------------------------------------------------------------------



