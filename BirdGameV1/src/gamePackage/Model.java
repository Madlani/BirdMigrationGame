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


public abstract class Model extends Point2D {

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
	private Bird osprey;
	private GameObject airplane;
	private GameObject food;
	private GameObject cloudQuestionBlock;
	private GameObject thunderCloud;
	short a = 0;
	private boolean healthChangable = false;

	public Model() {
		this.osprey = new Bird();
    	this.airplane = new GameObject(airplaneStartX, ObjectType.PLANE, 150, 150);
    	this.food = new GameObject(foodStartX, ObjectType.FOOD, 100, 65);
    	this.thunderCloud = new GameObject(blockStartX, ObjectType.THUNDER_CLOUD, 200, 200);
    	this.cloudQuestionBlock = new GameObject(questionBlockStartX, ObjectType.CLOUD_QUESTION_BOX, 300, 178);
    	
    	// Adds all GameObjects to one collection
    	this.gameObjects = new ArrayList<>();
    	gameObjects.add(osprey);
    	gameObjects.add(airplane);
    	gameObjects.add(thunderCloud);
    	gameObjects.add(cloudQuestionBlock);
    	gameObjects.add(food);
    	
    }
	
	//updateLocationAndDirection() will contain the logic to move GameObject when they start to go off screen
	public boolean updateLocationAndDirection() {
		
		switch(osprey.getFlyState()){
		case 1 : 
			osprey.moveUp();
			break;
		case -1: 
			osprey.moveDown();
			break;
		}
		
		switch(osprey.getLeftRightFlyState()){
		case 1 : 
			osprey.moveRight();
			break;
		case -1: 
			osprey.moveLeft();
			break;
		}

		this.osprey.setLocation(this.osprey.getX(), this.osprey.getY());
		this.osprey.birdBox.setLocation((int)this.osprey.getX(), (int)this.osprey.getY());
		
    	this.airplane.setLocation(this.airplane.getX(), this.airplane.getY());
    	this.airplane.GameObjectBox.setLocation((int)this.airplane.getX(), (int)this.airplane.getY());
    	
    	this.thunderCloud.setLocation(this.thunderCloud.getX(), this.thunderCloud.getY());
    	this.thunderCloud.GameObjectBox.setLocation((int)this.thunderCloud.getX(), (int)this.thunderCloud.getY());
    	
    	this.cloudQuestionBlock.setLocation(this.cloudQuestionBlock.getX(), this.cloudQuestionBlock.getY());
    	this.cloudQuestionBlock.GameObjectBox.setLocation((int)this.cloudQuestionBlock.getX(), (int)this.cloudQuestionBlock.getY());
    	
    	this.food.setLocation(this.food.getX(), this.food.getY());
    	this.food.GameObjectBox.setLocation((int)this.food.getX(), (int)this.food.getY());
    	
    	updateGameObjectLocationAndDirection(airplane);
    	updateGameObjectLocationAndDirection(food);
    	updateGameObjectLocationAndDirection(thunderCloud);
    	updateGameObjectLocationAndDirection(cloudQuestionBlock);
    	
    	return detectCollisions(gameObjects);

	}
	
	public void updateGameObjectLocationAndDirection(GameObject o) {
	}
	
	//resetGameObjectLocation() will update where the GameObjects are on screen 
		public void resetGameObjectLocation(GameObject o) {
	}

	// detectCollisions() will contain the logic that determines if the bird model
	// has collided with objects such as the ground and other GameObjects
	public boolean detectCollisions(ArrayList<GameObject> objectList) {
		int i = 0;
		for (GameObject o : objectList) {
			if (i == 0) {
				i++;
			} else {
				if (o.GameObjectBox.intersects(osprey.birdBox)) {
					
					if ((o.getType() == ObjectType.PLANE || o.getType() == ObjectType.THUNDER_CLOUD) && healthChangable == false) 
						this.osprey.decreaseHealthCount();

					if (o.getType() == ObjectType.FOOD && this.osprey.getHealthCount() < 10 && healthChangable == false) 
						this.osprey.increaseHealthCount();

					if ((o.getType() == ObjectType.CLOUD_QUESTION_BOX) && healthChangable == false) {
						return true;
					}
					
					healthChangable = true;
					return false;
				}
				i++;
			}
		}
		healthChangable = false;
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
	
	public GameObject getFood() {
		return food;
	}

	public ArrayList<GameObject> getUpdatableGameObjects() {
		return this.gameObjects;
	}
}


