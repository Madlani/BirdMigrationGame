package gamePackage;

import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;


public abstract class Model extends Point2D implements Serializable {

	public static int scaledImageWidth = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
	public static int scaledImageHeight = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	
	private int imgHeight;
	private int imgWidth;
	protected double xloc, yloc;
	private int direction;
	private int health;
	private final int START_HEALTH_COUNT = 10;

	protected ArrayList<GameObject> gameObjectsForOsprey;
	protected ArrayList<GameObject> gameObjectsForNortherHarrier;
	
	short a = 0;
	private boolean healthChangable = false;
	private boolean pauseGameFlag = false;
	private boolean isHit = false;
	
	/**
	 * will update the Directions of GameObjects on screen
	 * @param o
	 */
	public void updateGameObjectLocationAndDirection(GameObject o) {
	}
	
	/**
	 * will update where the GameObjects are on screen
	 * @param o
	 */
	public void resetGameObjectLocation(GameObject o) {
	}

	/**
	 * will contain the logic that determines if the bird model 
	 * has collied with objects such as the ground and other GameObjects
	 * @param objectList
	 * @param b
	 * @param isTutorial
	 * @return
	 */
	public boolean detectCollisions(ArrayList<GameObject> objectList, Bird b, GameState isTutorial) {
		int i = 0;
		for (GameObject o : objectList) {
			if (i == 0) {
				i++;
			} else {
				if (o.GameObjectBox.intersects(b.birdBox)) {
					
					hitBadGameObjects(b, o, isTutorial);
					hitGoodGameObjects(b, o, isTutorial);
					hitQuestionObject(o, isTutorial);

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
	
	/**
	 * decrease the bird's health if it hit either a plane, thunder cloud, fox, owl, or tree
	 * @param b
	 * @param o
	 * @param isTutorial
	 */
	public void hitBadGameObjects(Bird b, GameObject o, GameState isTutorial) {
		if ((o.getType() == ObjectType.PLANE || o.getType() == ObjectType.THUNDER_CLOUD || o.getType() == ObjectType.FOX
				|| o.getType() == ObjectType.OWL || o.getType() == ObjectType.TREE) && healthChangable == false) {
			b.decreaseHealthCount();
			if (isTutorial != GameState.TUTORIAL)
				resetGameObjectLocation(o);
			isHit = true;
		}
	}
	
	/**
	 * increase the bird's health if it hit either a fish, or mouse
	 * @param b
	 * @param o
	 * @param isTutorial
	 */
	public void hitGoodGameObjects(Bird b, GameObject o, GameState isTutorial) {
		if ((o.getType() == ObjectType.FISH || o.getType() == ObjectType.MOUSE) && b.getHealthCount() < START_HEALTH_COUNT && healthChangable == false) {
			b.increaseHealthCount();
			if (isTutorial != GameState.TUTORIAL)
				resetGameObjectLocation(o);
		}
	}
	
	/**
	 * determines if the bird hit a question block
	 * @param o
	 * @param isTutorial
	 */
	public void hitQuestionObject(GameObject o, GameState isTutorial) {
		if ((o.getType() == ObjectType.CLOUD_QUESTION_BOX || o.getType() == ObjectType.BUSH_QUESTION_BOX) && healthChangable == false) {
			System.out.println("hit question cloud");

			System.out.println("Game Flag before question is displayed: " + this.getPauseGameFlag());
			changePauseGameFlag();
			if (isTutorial != GameState.TUTORIAL)
				resetGameObjectLocation(o);
		}
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


	public ArrayList<GameObject> getUpdatableGameObjectsForOsprey() {
		return this.gameObjectsForOsprey;
	}
	
	public ArrayList<GameObject> getUpdatableGameObjectsForNorthernHarrier() {
		return this.gameObjectsForNortherHarrier;
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


