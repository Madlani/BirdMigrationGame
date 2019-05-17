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
	final int mouseStartX = 200;
	final int thunderCloudStartX = 450;
	final int questionBlockStartX = 700;
	final int foodStartX = 700;
	final int foxStartX = 750;
	private final int START_HEALTH_COUNT = 10;
	private final int MAP_X = 4;
	private final int BIRD_WIDTH = 150;
	private final int BIRD_HEIGHT = 150;
	private final int PLANEBOX_WIDTH = 150;
	private final int PLANEBOX_HEIGHT = 150;
	private final int FOODBOX_WIDTH = 100;
	private final int FOODBOX_HEIGHT = 65;
	private final int THUNDERCLOUD_WIDTH = 150;
	private final int THUNDERCLOUD_HEIGHT = 150;
	private final int QUESTIONCLOUD_WIDTH = 225;
	private final int QUESTIONCLOUD_HEIGHT = 150;
	private final int MOUSE_HEIGHT = 89;
	private final int MOUSE_WIDTH = 75;
	private final int FOX_WIDTH = 150;
	private final int FOX_HEIGHT = 75;
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
	private GameObject mouse;
	private GameObject fox;
	
	short a = 0;
	private boolean healthChangable = false;
	private boolean pauseGameFlag = false;
	private boolean isHit = false;

	public Model() {
		this.osprey = new Bird();
    	this.airplane = new GameObject(screenWidth + airplaneStartX, ObjectType.PLANE, PLANEBOX_WIDTH, PLANEBOX_HEIGHT);
    	this.food = new GameObject(screenWidth + foodStartX, ObjectType.FOOD, FOODBOX_WIDTH, FOODBOX_HEIGHT);
    	this.thunderCloud = new GameObject(screenWidth + thunderCloudStartX, ObjectType.THUNDER_CLOUD, THUNDERCLOUD_WIDTH, THUNDERCLOUD_HEIGHT);
    	this.cloudQuestionBlock = new GameObject(screenWidth + questionBlockStartX, ObjectType.CLOUD_QUESTION_BOX, QUESTIONCLOUD_WIDTH, QUESTIONCLOUD_HEIGHT);
    	this.mouse = new GameObject(screenWidth + mouseStartX, ObjectType.MOUSE, MOUSE_WIDTH, MOUSE_HEIGHT);
    	this.fox = new GameObject(screenWidth + foxStartX, ObjectType.FOX, FOX_WIDTH, FOX_HEIGHT);
    	this.osprey.setLocation(startingX, startingY);
    	
    	// Adds all GameObjects to one collection
    	this.gameObjects = new ArrayList<>();
    	gameObjects.add(osprey);
    	gameObjects.add(airplane);
    	gameObjects.add(thunderCloud);
    	gameObjects.add(cloudQuestionBlock);
    	gameObjects.add(food);
    	gameObjects.add(mouse);
    	gameObjects.add(fox);
    	
    }
	
	//updateLocationAndDirection() will contain the logic to move GameObject when they start to go off screen
	public boolean updateLocationAndDirection() {

		switch (osprey.getFlyState()) {
		case UP:
			if (osprey.getY() > 0) {
				osprey.moveUp();
			}
			break;
		case DOWN:
			if (osprey.getY() < screenHeight - BIRD_HEIGHT) {
				osprey.moveDown();
			}
			break;
		case RIGHT:
			if (osprey.getX() < screenWidth - BIRD_WIDTH) {
				osprey.moveRight();
			}
			break;
		case LEFT:
			if (osprey.getX() > screenWidth / MAP_X) {
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
    	
    	this.mouse.setLocation(this.mouse.getX(), this.mouse.getY());
    	this.mouse.GameObjectBox.setLocation((int)this.mouse.getX(), (int)this.mouse.getY());
    	
    	this.fox.setLocation(this.fox.getX(), this.fox.getY());
    	this.fox.GameObjectBox.setLocation((int)this.fox.getX(), (int)this.fox.getY());
    	
    	updateGameObjectLocationAndDirection(airplane);
    	updateGameObjectLocationAndDirection(food);
    	updateGameObjectLocationAndDirection(thunderCloud);
    	updateGameObjectLocationAndDirection(cloudQuestionBlock);
    	updateGameObjectLocationAndDirection(mouse);
    	updateGameObjectLocationAndDirection(fox);
    	
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
					
					if ((o.getType() == ObjectType.PLANE || o.getType() == ObjectType.THUNDER_CLOUD || o.getType() == ObjectType.FOX) && healthChangable == false) {
						this.osprey.decreaseHealthCount();
						resetGameObjectLocation(o);
						isHit = true;
					}
					if ((o.getType() == ObjectType.FOOD || o.getType() == ObjectType.MOUSE) && this.osprey.getHealthCount() < START_HEALTH_COUNT && healthChangable == false) {
						this.osprey.increaseHealthCount();
						resetGameObjectLocation(o);
					}
					if ((o.getType() == ObjectType.CLOUD_QUESTION_BOX) && healthChangable == false) {
						System.out.println("hit question cloud");

						System.out.println("Game Flag before question is displayed: " + this.getPauseGameFlag());
						changePauseGameFlag();
						resetGameObjectLocation(o);
						isHit = true;
					}

					healthChangable = true;
					return false;
				}
				i++;
			}
		}
		healthChangable = false;
		setIsHit(false);
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
	
	public void changePauseGameFlag() {
		this.pauseGameFlag  = !this.pauseGameFlag;
	}
	
	public boolean getPauseGameFlag() {
		return this.pauseGameFlag;
	}
	
	public boolean getIsHit() {
		return isHit;
	}
	
	public void setIsHit(boolean b) {
		this.isHit = b;
	}
}


