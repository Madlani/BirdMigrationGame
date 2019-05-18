package gamePackage;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;

public class SideSwiperModel extends Model {
	
	private int screenWidth = Model.scaledImageWidth;
	private int screenHeight = Model.scaledImageHeight;
	private int offset = 600;
	
	private final int airplaneStartX = 200;
	private final int thunderCloudStartX = 450;
	private final int questionBlockStartX = 700;
	private final int fishStartX = 700;
	private final int foxStartX = 750;
	
	private final int PLANEBOX_WIDTH = 150;
	private final int PLANEBOX_HEIGHT = 150;
	private final int FISHBOX_WIDTH = 100;
	private final int FISHBOX_HEIGHT = 65;
	private final int THUNDERCLOUD_WIDTH = 150;
	private final int THUNDERCLOUD_HEIGHT = 150;
	private final int QUESTIONCLOUD_WIDTH = 225;
	private final int QUESTIONCLOUD_HEIGHT = 150;
	private final int FOX_WIDTH = 150;
	private final int FOX_HEIGHT = 75;
	
	private final int OSPREY_WIDTH = 150;
	private final int OSPREY_HEIGHT = 150;
	
	private double ospreyStartingX = screenWidth/3;
	private double ospreyStartingY = screenHeight - 200;
	
	protected Bird osprey;
	protected GameObject airplane;
	protected GameObject fish;
	protected GameObject cloudQuestionBlock;
	protected GameObject thunderCloud;
	protected GameObject fox;
	
	public SideSwiperModel() {
		super();
		this.osprey = new Bird(BirdType.OSPREY, ObjectType.OSPREY);
    	this.airplane = new GameObject(BirdType.OSPREY, screenWidth + airplaneStartX, ObjectType.PLANE, PLANEBOX_WIDTH, PLANEBOX_HEIGHT);
    	this.fish = new GameObject(BirdType.OSPREY, screenWidth + fishStartX, ObjectType.FISH, FISHBOX_WIDTH, FISHBOX_HEIGHT);
    	this.thunderCloud = new GameObject(BirdType.OSPREY, screenWidth + thunderCloudStartX, ObjectType.THUNDER_CLOUD, THUNDERCLOUD_WIDTH, THUNDERCLOUD_HEIGHT);
    	this.cloudQuestionBlock = new GameObject(BirdType.OSPREY, screenWidth + questionBlockStartX, ObjectType.CLOUD_QUESTION_BOX, QUESTIONCLOUD_WIDTH, QUESTIONCLOUD_HEIGHT);
    	this.fox = new GameObject(BirdType.OSPREY, screenWidth + foxStartX, ObjectType.FOX, FOX_WIDTH, FOX_HEIGHT);
    	
    	this.osprey.setLocation(ospreyStartingX, ospreyStartingY);
    	
    	this.gameObjectsForOsprey = new ArrayList<>();
    	this.gameObjectsForOsprey.add(osprey);
    	this.gameObjectsForOsprey.add(airplane);
    	this.gameObjectsForOsprey.add(thunderCloud);
    	this.gameObjectsForOsprey.add(cloudQuestionBlock);
    	this.gameObjectsForOsprey.add(fish);
    	this.gameObjectsForOsprey.add(fox);
	}
	
	/**
	 * updateGameObjectLocationAndDirection()
	 * This method overrides the Model's updateGameObjectLocationAndDirection(). This will position the GameObject
	 * on the right side of the screen once it moves too far left, otherwise continue to move the GameObject by the 
	 * speed that was pre-determined.
	 */
	@Override
	public void updateGameObjectLocationAndDirection(GameObject o) {
		if(o.getX() <= -o.GameObjectBox.width) {
			resetGameObjectLocation(o);
		}
		else {
			o.setLocation(o.getX() - o.getGameObjectSpeed(), o.getY());
		}
	}
	
	
	public boolean updateLocationAndDirectionForOsprey() {

		switch (osprey.getFlyState()) {
		case UP:
			if (osprey.getY() > 0) {
				osprey.moveUp();
			}
			break;
		case DOWN:
			if (osprey.getY() < screenHeight - OSPREY_HEIGHT) {
				osprey.moveDown();
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
    	
    	this.fish.setLocation(this.fish.getX(), this.fish.getY());
    	this.fish.GameObjectBox.setLocation((int)this.fish.getX(), (int)this.fish.getY());
    	
    	this.fox.setLocation(this.fox.getX(), this.fox.getY());
    	this.fox.GameObjectBox.setLocation((int)this.fox.getX(), (int)this.fox.getY());
    	
    	updateGameObjectLocationAndDirection(airplane);
    	updateGameObjectLocationAndDirection(fish);
    	updateGameObjectLocationAndDirection(thunderCloud);
    	updateGameObjectLocationAndDirection(cloudQuestionBlock);
    	updateGameObjectLocationAndDirection(fox);
    	
    	return detectCollisions(this.gameObjectsForOsprey, this.osprey);
	}
	
	
	/**
	 * resetGameObjectLocation()
	 * Reset's the GameObject at a random y-height. This method is called from 
	 * updateGameObjectLocationAndDirection() in this class.
	 */
	@Override
		public void resetGameObjectLocation(GameObject o) {

			int maxHeight = scaledImageHeight - o.GameObjectBox.height;
			int minHeight = 0;
			
			int width = scaledImageWidth;
			int rand = (int)(Math.random()*(maxHeight - minHeight + 1) + minHeight);
			
			o.setLocation(width + offset, rand);
	}
	public Bird getOsprey() {
		return this.osprey;
	}

	public GameObject getAirplane() {
		return this.airplane;
	}
	
	public GameObject getFish() {
		return this.fish;
	}
}



