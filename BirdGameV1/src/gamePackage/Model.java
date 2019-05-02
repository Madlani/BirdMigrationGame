package gamePackage;

import java.awt.AWTKeyStroke;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class Model extends Point2D {

	private int imgHeight;
	private int imgWidth;
	protected double xloc, yloc;
	private int direction;
	private int health;
	final int airplaneStartX = 200;
	final int blockStartX = 450;
	final int questionBlockStartX = 700;
	final int foodStartX = 700;
	ArrayList<GameObject> gameObjects;
	
	// Objects in our game
	public Bird osprey;
	public GameObject airplane;
	public GameObject block;
	public GameObject questionBlock;
	public GameObject food;
	
	String[] options = {"abc", "def", "ghi", "jkl"};


	public Model() {
    	this.osprey = new Bird();
    	this.airplane = new GameObject(airplaneStartX, ObjectType.PLANE);
    	this.block = new GameObject(blockStartX, ObjectType.QUESTION_BOX);
    	this.questionBlock = new GameObject(questionBlockStartX, ObjectType.QUESTION_BOX);
    	this.food = new GameObject(foodStartX, ObjectType.FOOD);
    	
    	// Adds all GameObjects to one collection
    	this.gameObjects = new ArrayList<>();
    	gameObjects.add(airplane);
    	gameObjects.add(block);
    	gameObjects.add(questionBlock);
    	gameObjects.add(food);
    }
	
	//updateLocationAndDirection() will contain the logic to move GameObject when they start to go off screen
	public void updateLocationAndDirection() {
		
//		detectCollisions(this.airplane);
//		detectCollisions(this.block);
		
		detectCollisions(gameObjects);
		
		this.osprey.setLocation(this.osprey.getX(), this.osprey.getY());
		this.osprey.birdBox.setLocation((int)this.osprey.getX(), (int)this.osprey.getY());
		
    	this.airplane.setLocation(this.airplane.getX(), this.airplane.getY());
    	this.airplane.GameObjectBox.setLocation((int)this.airplane.getX(), (int)this.airplane.getY());
    	
    	this.block.setLocation(this.block.getX(), this.block.getY());
    	this.block.GameObjectBox.setLocation((int)this.block.getX(), (int)this.block.getY());   
    	
    	this.food.setLocation(this.food.getX(), this.food.getY());
    	this.food.GameObjectBox.setLocation((int)this.food.getX(), (int)this.food.getY());
    	
    	
    	updateGameObjectLocationAndDirection(airplane);
    	updateGameObjectLocationAndDirection(block);
    	updateGameObjectLocationAndDirection(food);

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
//			System.out.println(o.GameObjectBox.intersects(osprey.birdBox));
//			System.out.println(this.osprey.getHealth());
			//bounds to keep the GameObject from going off the screen in the +/- y direction
			int maxHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - o.GameObjectBox.height;
			int minHeight = 0;
			
			int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			int rand = (int)(Math.random()*(maxHeight - minHeight + 1) + minHeight);
			
			// sets x location to be all the way to the right on the screen (with random y height)
			o.setLocation(width, rand);
	}

	// detectCollisions() will contain the logic that determines if the bird model
	// has collided with objects such as the ground and other GameObjects
	public boolean detectCollisions(ArrayList<GameObject> objectList) {

		for (GameObject o : objectList) {
			if (o.GameObjectBox.intersects(osprey.birdBox)) {
				if (o.getType() == ObjectType.PLANE) {
					this.osprey.setHealth(this.getOsprey().getHealth() - 1);
				}
				
				if (o.getType() == ObjectType.FOOD && this.osprey.getHealth() < 250) {
					this.osprey.setHealth(this.getOsprey().getHealth() + 1);
				}
				
				if (o.getType() == ObjectType.BLOCK || o.getType() == ObjectType.QUESTION_BOX) {
					
				}
				
				return true;
			}
		}
		
		return false;
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
	
	public GameObject getFood() {
		return food;
	}
}


