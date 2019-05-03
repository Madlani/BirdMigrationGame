package gamePackage;

import java.awt.Toolkit;
import java.awt.geom.Point2D;

public class Bird extends GameObject {
	private double xPosition;
	private double yPosition;
	private int health = 250;
	private int healthCount = 10;
	private double screenSizeWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private final double STARTING_XPOS = screenSizeWidth/3;
	private final double STARTING_YPOS = 240.0;
	
	private final int BIRD_SPEED = 10;
	private int flyState = 0;
	private int leftRightFlyState = 0;
	
	protected HitBox birdBox;
	
	public Bird() {
		super(0, ObjectType.BIRD);
		this.xPosition = STARTING_XPOS;
		this.yPosition = STARTING_YPOS;
		this.birdBox = new HitBox((int)this.xPosition,(int) this.yPosition, 100,100);
	}
	
	//Decrements the xPosition of the bird (in order for it to move left)
	public void moveLeft() {
		this.setLocation(this.getX() - BIRD_SPEED, this.getY());
	}
	
	//Increments the xPosition of the bird (in order for it to move right)
	public void moveRight() {
		this.setLocation(this.getX() + BIRD_SPEED, this.getY());
	}
	
	//Decrements the yPosition of the bird (in order for it to move up)
	public void moveUp() {
		this.setLocation(this.getX(), this.getY() - BIRD_SPEED);
	}
	
	//Increments the yPosition of the bird (in order for it to move down)
	public void moveDown() {
		this.setLocation(this.getX(), this.getY() + BIRD_SPEED);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealthCount() {
		return this.healthCount;
	}
	
	public void setHealthCount(int h) {
		this.healthCount = h;
	}
	
	public void decreaseHealthCount() {
		if (this.healthCount > 0) 
		this.healthCount = this.healthCount - 1;
	}
	
	public void increaseHealthCount() {
		if (this.healthCount < 9)
		this.healthCount = this.healthCount + 1;
	}
  	
	public Bird getBird() {
		return this;
	}

	@Override
	public double getX() {
		return this.xPosition;
	}

	@Override
	public double getY() {
		return this.yPosition;
	}

	@Override
	public void setLocation(double x, double y) {
		this.xPosition = x;
		this.yPosition = y;
	}

	public int getFlyState() {
		return flyState;
	}

	public void setFlyState(int flyState) {
		this.flyState = flyState;
	}

	public int getLeftRightFlyState() {
		return leftRightFlyState;
	}

	public void setLeftRightFlyState(int leftRightFlyState) {
		this.leftRightFlyState = leftRightFlyState;
	}

}

