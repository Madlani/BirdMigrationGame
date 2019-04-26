package gamePackage;

import java.awt.geom.Point2D;

public class Bird extends Point2D{
	private double xPosition;
	private double yPosition;
	private int health;
	//file location of the bird's image
	private String sprite;
	private final int BIRD_SPEED = 20;
	
	public Bird() {
		// TODO Auto-generated constructor stub
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

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return this.xPosition;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return this.yPosition;
	}

	@Override
	public void setLocation(double x, double y) {
		this.xPosition = x;
		this.yPosition = y;
	}

}

//-----------------------------------------------------------------------------------------------------

