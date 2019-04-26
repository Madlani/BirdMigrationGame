package gamePackage;

import java.awt.geom.Point2D;


public class Model extends Point2D{

	private int frameHeight;
	private int frameWidth;
	private int imgHeight;
	private int imgWidth;
	protected double xloc, yloc;
	private int direction;
	private int health;
	

	public Bird osprey;
	public Obstacle airplane;
	

    public Model(int w, int h, int iw, int ih) {
    	this.frameWidth = w;
    	this.frameHeight = h;
    	this.imgWidth = iw;
    	this.imgHeight = ih;
    	this.osprey = new Bird();
    	this.airplane = new Obstacle();
    	
    }
	
	//updateLocationAndDirection() will contain the logic that allows the bird to move in the x or y direction based on user input
	public void updateLocationAndDirection() {
		System.out.println("entering Model updateLoc&Dir");
		this.osprey.setLocation(this.osprey.getX(), this.osprey.getY());
		this.osprey.birdBox.setLocation((int)this.osprey.getX(), (int)this.osprey.getY());
    	this.airplane.setLocation(this.airplane.getX(), this.airplane.getY());
    	System.out.println("Model Osprey " + this.osprey.getX() + ", " + this.osprey.getY());
    	System.out.println(detectCollisions());
	}
	
	//detectCollisions() will contain the logic that determines if the bird model has collided with objects such as the ground and other obstacles
	public boolean detectCollisions() {
		return airplane.obstacleBox.intersects(osprey.birdBox);
	}
	
	public int getFrameHeight() {
		return frameHeight;
	}
	
	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
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
}

//-----------------------------------------------------------------------------------------------------


