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
	HashSet<GameObject> gameObjects;
	
	// Objects in our game
	public Bird osprey;
	public GameObject airplane;
	public GameObject block;
	public GameObject questionBlock;
	public GameObject food;

	public Model() {
    	this.osprey = new Bird();
    	this.airplane = new GameObject(airplaneStartX);
    	this.block = new GameObject(blockStartX);
    	this.questionBlock = new GameObject(questionBlockStartX);
    	
    	// Adds all GameObjects to one collection
    	this.gameObjects = new HashSet<>();
    	gameObjects.add(airplane);
    	gameObjects.add(block);
    	gameObjects.add(questionBlock);
    }
	
	//updateLocationAndDirection() will contain the logic to move GameObject when they start to go off screen
	public void updateLocationAndDirection() {

		this.osprey.setLocation(this.osprey.getX(), this.osprey.getY());
		this.osprey.birdBox.setLocation((int)this.osprey.getX(), (int)this.osprey.getY());
		
    	this.airplane.setLocation(this.airplane.getX(), this.airplane.getY());
    	this.airplane.GameObjectBox.setLocation((int)this.airplane.getX(), (int)this.airplane.getY());
    	
    	this.block.setLocation(this.block.getX(), this.block.getY());
    	this.block.GameObjectBox.setLocation((int)this.block.getX(), (int)this.block.getY());    	
    	
    	updateGameObjectLocationAndDirection(airplane);
    	updateGameObjectLocationAndDirection(block);

	}
	
	public void updateGameObjectLocationAndDirection(GameObject o) {
		if(o.getX() <= -o.GameObjectBox.width) {
			resetGameObjectLocation(o);
		}
		else {
			o.setLocation(o.getX() - o.getGameObjectSpeed(), o.getY());
		}
	}
	
	//resetGameObjectLocation() will update where the GameObjects are on screen 
		public void resetGameObjectLocation(GameObject o) {
			
			//bounds to keep the GameObject from going off the screen in the +/- y direction
			int maxHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - o.GameObjectBox.height;
			int minHeight = 0;
			
			int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			int rand = (int)(Math.random()*(maxHeight - minHeight + 1) + minHeight);
			
			// sets x location to be all the way to the right on the screen (with random y height)
			o.setLocation(width, rand);
		}
	
	//detectCollisions() will contain the logic that determines if the bird model has collided with objects such as the ground and other GameObjects
	public boolean detectCollisions(GameObject o) {
		return o.GameObjectBox.intersects(osprey.birdBox);
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

	public GameObject getAirplane() {
		return airplane;
	}

	public GameObject getBlock() {
		return block;
	}
}


