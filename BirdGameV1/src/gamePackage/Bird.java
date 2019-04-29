package gamePackage;

import java.awt.geom.Point2D;

public class Bird extends Point2D{
	private double xPosition;
	private double yPosition;
	private int health = 250;
	private final int BIRD_SPEED = 20;
	
	protected HitBox birdBox;
	public Bird() {
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

}

