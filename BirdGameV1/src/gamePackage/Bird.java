package gamePackage;

import java.awt.Rectangle;

public class Bird extends GameObject {
	private double xPosition;
	private double yPosition;
	
	//private int health = 250;
	private int healthCount = 5;
	private FlyState flyState = FlyState.STILL;
	
	private final double screenSizeWidth = Model.scaledImageWidth;
	private double startingX = screenSizeWidth/3;
	private double startingY = 300;
	
	private final int BIRD_SPEED = 10;
	private static int BIRD_IMG_WIDTH = 150;
	private static int BIRD_IMG_HEIGHT = 150;
	private final int BIRD_BOX_SIZE = 100;
	
	private final int LOWEST_HEALTH_COUNT = 0;
	private final int HIGHEST_HEALTH_COUNT = 9;
	
	protected HitBox birdBox;
	
	public Bird() {
		super(0, ObjectType.BIRD, Bird.BIRD_IMG_WIDTH, Bird.BIRD_IMG_HEIGHT);
		this.birdBox = new HitBox((int)this.xPosition,(int) this.yPosition, BIRD_BOX_SIZE, BIRD_BOX_SIZE);
	}
	
	/**
	 * moveLeft()
	 * Decrements the xPosition of the bird by the constant value BIRD_SPEED
	 */
	public void moveLeft() {
		this.setLocation(this.getX() - BIRD_SPEED, this.getY());
	}
	
	/**
	 * moveRight()
	 * Increments the xPosition of the bird by the constant value BIRD_SPEED
	 */
	public void moveRight() {
		this.setLocation(this.getX() + BIRD_SPEED, this.getY());
	}
	
	/**
	 * moveUp()
	 * Decrements the yPosition of the bird by the constant value BIRD_SPEED
	 */
	public void moveUp() {
		this.setLocation(this.getX(), this.getY() - BIRD_SPEED);
	}
	
	/**
	 * moveDown()
	 * Increments the yPosition of the bird by the constant value BIRD_SPEED
	 */
	public void moveDown() {
		this.setLocation(this.getX(), this.getY() + BIRD_SPEED);
	}

//	/**
//	 * getHealth()
//	 * @return health, the health of the bird in its current state
//	 */
//	public int getHealth() {
//		return health;
//	}
//
//	/**
//	 * setHealth()
//	 * Increases or decreases the health to a specific value based on the game state. This method is called
//	 * whenever the bird comes in contact with other game objects
//	 * @param health, the health of the bird in its current state
//	 */
//	public void setHealth(int health) {
//		this.health = health;
//	}
	
	/**
	 * getHealthCount()
	 * Returns the health of the bird at its current state
	 * @return healthCount, the numerical value of the bird's health
	 */
	public int getHealthCount() {
		return this.healthCount;
	}
	
	/**
	 * setHealthCount()
	 * Sets the numerical value of the bird's health to the parameter h passed to this method
	 * @param h, a numerical value which will change the bird's health state
	 */
	public void setHealthCount(int h) {
		this.healthCount = h;
	}
	
	/**
	 * decreaseHealthCount()
	 * Decrements the numerical value for the bird's health by 1. This method is called when the bird 
	 * hits a GameObject that it shouldn't hit
	 */
	public void decreaseHealthCount() {
		if (this.healthCount > LOWEST_HEALTH_COUNT) 
		this.healthCount = this.healthCount - 1;
	}
	
	/**
	 * increaseHealthCount()
	 * Increments the numerical value for the bird's health by 1. This method is called when the bird
	 * hits a fish GameObject.
	 */
	public void increaseHealthCount() {
		if (this.healthCount < HIGHEST_HEALTH_COUNT)
		this.healthCount = this.healthCount + 1;
	}
	
	/**
	 * increaseHealthCount()
	 * Increments the numerical value for the bird's health by 1. This method is called when the bird
	 * hits a fish GameObject.
	 * @param increase
	 */
	public void increaseHealthCount(int increase) {
		if (this.healthCount + increase < HIGHEST_HEALTH_COUNT) {
			this.healthCount = this.healthCount + increase;
		} else {
			this.healthCount = HIGHEST_HEALTH_COUNT;
		}
	}
  	
	/**
	 * getX()
	 * This method overrides the GameObject getX() method.
	 * It returns the bird's x-position at its current state.
	 */
	@Override
	public double getX() {
		return this.xPosition;
	}

	/**
	 * getY()
	 * This method overrides the GameObject getX() method.
	 * It returns the bird's y-position at its current state.
	 */
	@Override
	public double getY() {
		return this.yPosition;
	}

	/**
	 * setLocation()
	 * This method overrides the GameObject setLocation() method.
	 * It sets the x-position and y-position of the Bird object.
	 */
	@Override
	public void setLocation(double x, double y) {
		this.xPosition = x;
		this.yPosition = y;
	}
	
	/**
	 * getBird()
	 * @return the Bird object.
	 */
	public Bird getBird() {
		return this;
	}

	/**
	 * getFlyState()
	 * @return FlyState enumeration to represent the current fly state
	 */
	public FlyState getFlyState() {
		return flyState;
	}

	/**
	 * setFlyState()
	 * Sets the FlyState to another enumeration type.
	 * @param flyState
	 */
	public void setFlyState(FlyState flyState) {
		this.flyState = flyState;
	}
	
	/**
	 * getBirdBox()
	 * Returns the rectangle corresponding to the bird's HitBox.
	 * @return birdBox
	 */
	public Rectangle getBirdBox() {
		return birdBox;
	}
	
	public void setBirdBox(int x, int y, int width, int height) {
		this.birdBox = new HitBox(x, y, width, height);
	}
	
	/**
	 * getStartingX()
	 * @return startingX, the starting x-position of the bird.
	 */
	public double getStartingX() {
		return startingX;
	}

	/**
	 * setStartingX()
	 * Sets the starting x-position of the bird.
	 * @param startingX
	 */
	public void setStartingX(double startingX) {
		this.startingX = startingX;
	}

	/**
	 * getStartingY()
	 * @return startingY, the starting y-position of the bird.
	 */
	public double getStartingY() {
		return startingY;
	}

	/**
	 * startingY()
	 * Sets the starting y-position of the bird.
	 * @param startingY
	 */
	public void setStartingY(double startingY) {
		this.startingY = startingY;
	}
}

