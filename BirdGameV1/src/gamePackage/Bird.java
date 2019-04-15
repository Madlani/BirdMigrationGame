package gamePackage;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class Bird {
	private int xPosition;
	private int yPosition;
	private int health;
	//file location of the bird's image
	private String sprite;
	
	public Bird() {
		// TODO Auto-generated constructor stub
	}
	
	//Decrements the xPosition of the bird (in order for it to move left)
	public void moveLeft() {
		xPosition-= 5;
	}
	
	//Increments the xPosition of the bird (in order for it to move right)
	public void moveRight() {
		xPosition+= 5;
	}
	
	//Decrements the yPosition of the bird (in order for it to move up)
	public void moveUp() {
		yPosition-= 5;
	}
	
	//Increments the yPosition of the bird (in order for it to move down)
	public void moveDown() {
		yPosition+= 5;
	}
	
	public int getXPosition() {
		return xPosition;
	}

	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
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

}

//-----------------------------------------------------------------------------------------------------

