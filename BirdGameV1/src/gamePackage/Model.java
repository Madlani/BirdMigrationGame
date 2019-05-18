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
	private final int START_HEALTH_COUNT = 10;
	
	private final double screenWidth = scaledImageWidth;
	private final double screenHeight = scaledImageHeight;

	protected ArrayList<GameObject> gameObjectsForOsprey;
	protected ArrayList<GameObject> gameObjectsForNortherHarrier;
	
	short a = 0;
	private boolean healthChangable = false;
	private boolean pauseGameFlag = false;
	private boolean isHit = false;

	
	
	
	public void updateGameObjectLocationAndDirection(GameObject o) {
		
	}
	
	// resetGameObjectLocation() will update where the GameObjects are on screen
	public void resetGameObjectLocation(GameObject o) {
	}

	// detectCollisions() will contain the logic that determines if the bird model
	// has collided with objects such as the ground and other GameObjects
	public boolean detectCollisions(ArrayList<GameObject> objectList, Bird b) {
		int i = 0;
		for (GameObject o : objectList) {
			if (i == 0) {
				i++;
			} else {
				if (o.GameObjectBox.intersects(b.birdBox)) {
					
					if ((o.getType() == ObjectType.PLANE || o.getType() == ObjectType.THUNDER_CLOUD || o.getType() == ObjectType.FOX) && healthChangable == false) {
						b.decreaseHealthCount();
						resetGameObjectLocation(o);
						isHit = true;
					}
					if ((o.getType() == ObjectType.FISH || o.getType() == ObjectType.MOUSE) && b.getHealthCount() < START_HEALTH_COUNT && healthChangable == false) {
						b.increaseHealthCount();
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


