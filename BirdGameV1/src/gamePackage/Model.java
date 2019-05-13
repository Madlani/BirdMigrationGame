package gamePackage;

import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public abstract class Model extends Point2D {

	protected static int scaledImageWidth = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
	protected static int scaledImageHeight = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	
	private int imgHeight;
	private int imgWidth;
	protected double xloc, yloc;
	private int direction;
	private int health;
	final int airplaneStartX = 200;
	final int thunderCloudStartX = 450;
	final int questionBlockStartX = 700;
	final int foodStartX = 700;
	private final double screenWidth = scaledImageWidth;
	private final double screenHeight = scaledImageHeight;
	public double startingX = screenWidth/3;
	public double startingY = screenHeight - 200;
	ArrayList<GameObject> gameObjects;
	
	// Objects in our game
	private Bird osprey;
	private GameObject airplane;
	private GameObject food;
	private GameObject cloudQuestionBlock;
	private GameObject thunderCloud;
	short a = 0;
	private boolean healthChangable = false;
	private boolean pauseGameFlag = false;

	public Model() {
		this.osprey = new Bird();
    	this.airplane = new GameObject(screenWidth + airplaneStartX, ObjectType.PLANE, 150, 150);
    	this.food = new GameObject(screenWidth + foodStartX, ObjectType.FOOD, 100, 65);
    	this.thunderCloud = new GameObject(screenWidth + thunderCloudStartX, ObjectType.THUNDER_CLOUD, 200, 200);
    	this.cloudQuestionBlock = new GameObject(screenWidth + questionBlockStartX, ObjectType.CLOUD_QUESTION_BOX, 300, 178);
    	
    	this.osprey.setLocation(startingX, startingY);
    	
    	// Adds all GameObjects to one collection
    	this.gameObjects = new ArrayList<>();
    	gameObjects.add(osprey);
    	gameObjects.add(airplane);
    	gameObjects.add(thunderCloud);
    	gameObjects.add(cloudQuestionBlock);
    	gameObjects.add(food);
    	
    }
	
	/**
	 * updateLocationAndDirection()
	 * Updates the location of the bird based on the bird's position and FlyState.
	 * Contains the logic to move the GameObjects when they start to go off screen.
	 * Updates the location and direction of all of the game objects as well.
	 * @return a boolean, which is whether or not a collision has occurred with the bird during this update.
	 */
	public boolean updateLocationAndDirection() {

		switch (osprey.getFlyState()) {
		case UP:
			if (osprey.getY() > 0) {
				osprey.moveUp();
			}
			break;
		case DOWN:
			if (osprey.getY() < screenHeight - 150) {
				osprey.moveDown();
			}
			break;
		case RIGHT:
			if (osprey.getX() < screenWidth - 150) {
				osprey.moveRight();
			}
			break;
		case LEFT:
			if (osprey.getX() > screenWidth / 4) {
				osprey.moveLeft();
			}
			break;
		default:
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
	
	/**
	 * updateGameObjectLocationAndDirection
	 * Updates the location and direction of the game object passed in.
	 * @param o, the game object that needs to be updated.
	 */
	public void updateGameObjectLocationAndDirection(GameObject o) {

	}

	/**
	 * resetGameObjectLocation() Resets the location and direction of the game
	 * object passed in.
	 * 
	 * @param o, the game object that needs to be reset.
	 */
	public void resetGameObjectLocation(GameObject o) {

	}

	/**
	 * detectCollisions()
	 * Contains the logic that determines if the bird model has collided with objects such as the ground / other GameObjects
	 * @param objectList, a list of all the GameObjects in use during the game.
	 * @return a boolean to indicate if a collision with the bird occurred during this update.
	 */
	public boolean detectCollisions(ArrayList<GameObject> objectList) {
		int i = 0;
		for (GameObject o : objectList) {
			if (i == 0) {
				i++;
			} else {
				if (o.GameObjectBox.intersects(osprey.birdBox)) {

					if ((o.getType() == ObjectType.PLANE || o.getType() == ObjectType.THUNDER_CLOUD)
							&& healthChangable == false) {
						this.osprey.decreaseHealthCount();
						resetGameObjectLocation(o);
					}
					if (o.getType() == ObjectType.FOOD && this.osprey.getHealthCount() < 10 && healthChangable == false) {
						this.osprey.increaseHealthCount();
						resetGameObjectLocation(o);
					}
					if ((o.getType() == ObjectType.CLOUD_QUESTION_BOX) && healthChangable == false) {
						System.out.println("hit question cloud");

						System.out.println("Game Flag before question is displayed: " + this.getPauseGameFlag());
						changePauseGameFlag();

						/*
						Question q = new Question();
						int response = q.displayQuestion();

						if (response == JOptionPane.YES_OPTION || response == JOptionPane.NO_OPTION || response == JOptionPane.CANCEL_OPTION) {
							changePauseGameFlag();
							System.out.println("Game Flag after OK_option picked: " + this.getPauseGameFlag());
							System.out.println("Response: " + response);
							this.updateLocationAndDirection();
							return true;
						}
						*/
						
						//return false; // this was originally true
						resetGameObjectLocation(o);
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

	/**
	 * getImgHeight()
	 * @return the height of the image
	 */
	public int getImgHeight() {
		return imgHeight;
	}

	/**
	 * setImgHeight()
	 * Sets the img height to be the parameter.
	 * @param imgHeight
	 */
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	/**
	 * getImgWidth
	 * @return the width of the image
	 */
	public int getImgWidth() {
		return imgWidth;
	}

	/**
	 * setImgWidth()
	 * Sets the img width to be the parameter.
	 * @param imgWidth
	 */
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	/**
	 * getDirection()
	 * @return the direction of the object calling this method.
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * setDirection()
	 * Sets the direction of the object calling this method.
	 * @param direction
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * getHealth()
	 * @return health, the numerical representation of the bird's health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * setHealth()
	 * Sets the health of the bird to be the parameter.
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * getX()
	 * Overrides the Point2D's getX() method. Returns the x-position of the bird.
	 */
	@Override
	public double getX() {
		return this.xloc;
	}

	/**
	 * getY()
	 * Overrides the Point 2D's getY() method. Returns the y-position of the bird.
	 */
	@Override
	public double getY() {
		return this.yloc;
	}

	/**
	 * Overrides the Point 2D's setLocation() method. Sets the location of the bird to the specified parameters.
	 */
	@Override
	public void setLocation(double x, double y) {
		this.xloc = x;
		this.yloc = y;
	}
	
	/**
	 * getOsprey()
	 * @return the osprey in use.
	 */
	public Bird getOsprey() {
		return osprey;
	}

	/**
	 * getAirplane()
	 * @return the airplane in use.
	 */
	public GameObject getAirplane() {
		return airplane;
	}
	
	/**
	 * getFood()
	 * @return the food object in use.
	 */
	public GameObject getFood() {
		return food;
	}

	/**
	 * getUpdatableGameObjects()
	 * @return an array list containing all the game objects.
	 */
	public ArrayList<GameObject> getUpdatableGameObjects() {
		return this.gameObjects;
	}
	
	/**
	 * changePauseGameFlag()
	 * Toggles the pause game flag to be the opposite of what it currently is.
	 */
	public void changePauseGameFlag() {
		this.pauseGameFlag  = !this.pauseGameFlag;
	}
	
	/**
	 * getPauseGameFlag()
	 * @return a boolean which represents the current state of the flag.
	 */
	public boolean getPauseGameFlag() {
		return this.pauseGameFlag;
	}
}


