package gamePackage;

import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.HashSet;


public class Model extends Point2D {

	private int imgHeight;
	private int imgWidth;
	protected double xloc, yloc;
	private int direction;
	private int health;
	final int airplaneStartX = 200;
	final int blockStartX = 450;
	final int questionBlockStartX = 700;
	HashSet<Obstacle> gameObstacles;
	
	// Objects in our game
	public Bird osprey;
	public Obstacle airplane;
	public Obstacle block;
	public Obstacle questionBlock;

	public Model() {
    	this.osprey = new Bird();
    	this.airplane = new Obstacle(airplaneStartX);
    	this.block = new Obstacle(blockStartX);
    	this.questionBlock = new Obstacle(questionBlockStartX);
    	
    	// Adds all obstacles to one collection
    	this.gameObstacles = new HashSet<>();
    	gameObstacles.add(airplane);
    	gameObstacles.add(block);
    	gameObstacles.add(questionBlock);
    }
	
	//updateLocationAndDirection() will contain the logic to move obstacles when they start to go off screen
	public void updateLocationAndDirection() {

		this.osprey.setLocation(this.osprey.getX(), this.osprey.getY());
		this.osprey.birdBox.setLocation((int)this.osprey.getX(), (int)this.osprey.getY());
		
    	this.airplane.setLocation(this.airplane.getX(), this.airplane.getY());
    	this.airplane.obstacleBox.setLocation((int)this.airplane.getX(), (int)this.airplane.getY());
    	
    	this.block.setLocation(this.block.getX(), this.block.getY());
    	this.block.obstacleBox.setLocation((int)this.block.getX(), (int)this.block.getY());    	
    	
    	updateObstacleLocationAndDirection(airplane);
    	updateObstacleLocationAndDirection(block);

	}
	
	public void updateObstacleLocationAndDirection(Obstacle o) {
		if(o.getX() <= -o.obstacleBox.width) {
			resetObstacleLocation(o);
		}
		else {
			o.setLocation(o.getX() - o.getObstacleSpeed(), o.getY());
		}
	}
	
	//resetObstacleLocation() will update where the obstacles are on screen 
		public void resetObstacleLocation(Obstacle o) {
			
			//bounds to keep the obstacle from going off the screen in the +/- y direction
			int maxHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - o.obstacleBox.height;
			int minHeight = 0;
			
			int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			int rand = (int)(Math.random()*(maxHeight - minHeight + 1) + minHeight);
			
			// sets x location to be all the way to the right on the screen (with random y height)
			o.setLocation(width, rand);
		}
	
	//detectCollisions() will contain the logic that determines if the bird model has collided with objects such as the ground and other obstacles
	public boolean detectCollisions(Obstacle o) {
		return o.obstacleBox.intersects(osprey.birdBox);
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public double getX() {
		return this.xloc;
	}

	@Override
	public double getY() {
		return this.yloc;
	}

	@Override
	public void setLocation(double x, double y) {
		this.xloc = x;
		this.yloc = y;
	}
	
	public Bird getOsprey() {
		return osprey;
	}

	public Obstacle getAirplane() {
		return airplane;
	}

	public Obstacle getBlock() {
		return block;
	}
}


