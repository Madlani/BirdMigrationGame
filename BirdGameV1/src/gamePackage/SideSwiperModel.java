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
	
	private int thirdOfTheScreenY = (screenHeight / 3) * 2;
	
	private boolean isOver = false;
	private boolean isFirstFrame = true;
	private final int MAP_FRAME_COUNT = 400;
	private int tick = 0;
	
	private final int MIGRATION_MAP_SUBIMAGES = 9;
	private int picNumMap = 0;
	
	public SideSwiperModel() {
		super();
		this.osprey = new Bird(BirdType.OSPREY, ObjectType.OSPREY);
    	this.airplane = new GameObject(BirdType.OSPREY, screenWidth + airplaneStartX, ObjectType.PLANE, PLANEBOX_WIDTH, PLANEBOX_HEIGHT, (screenHeight / 3) * 2, 0);
    	this.fish = new GameObject(BirdType.OSPREY, screenWidth + fishStartX, ObjectType.FISH, FISHBOX_WIDTH, FISHBOX_HEIGHT, screenHeight, (screenHeight / 3) * 2);
    	this.thunderCloud = new GameObject(BirdType.OSPREY, screenWidth + thunderCloudStartX, ObjectType.THUNDER_CLOUD, THUNDERCLOUD_WIDTH, THUNDERCLOUD_HEIGHT, (screenHeight / 3) * 2, 0);
    	this.cloudQuestionBlock = new GameObject(BirdType.OSPREY, screenWidth + questionBlockStartX, ObjectType.CLOUD_QUESTION_BOX, QUESTIONCLOUD_WIDTH, QUESTIONCLOUD_HEIGHT, (screenHeight / 3) * 2, 0);
    	this.fox = new GameObject(BirdType.OSPREY, screenWidth + foxStartX, ObjectType.FOX, FOX_WIDTH, FOX_HEIGHT, screenHeight, (screenHeight / 3) * 2);
    	
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
		} else {
			o.setLocation(o.getX() - o.getGameObjectSpeed(), o.getY());
		}
	}
	
	
	public boolean updateLocationAndDirectionForOsprey() {
		tick = (tick+1) % MAP_FRAME_COUNT;
		if (tick == 0) {
			picNumMap = (picNumMap + 1) % MIGRATION_MAP_SUBIMAGES;
		}

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
		int rand;
		switch(o.getType()) {
		case CLOUD_QUESTION_BOX:
		case PLANE:
		case THUNDER_CLOUD:
			rand = (int)(Math.random() * (this.thirdOfTheScreenY - 80));
			o.setLocation(scaledImageWidth + offset, rand);
			break;
			
		case FISH:
		case FOX:
			rand = (int)(Math.random() * ((this.screenHeight - 100) - this.thirdOfTheScreenY)) + this.thirdOfTheScreenY;
			o.setLocation(scaledImageWidth + offset, rand);
			break;		
			
		default:
			break;
		}
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
	
	public boolean getIsOver() {
		return this.isOver;
	}
	
	public void setIsOver(boolean b) {
		this.isOver = b;
	}
	
	public void setIsFirstFrame(boolean b) {
		this.isFirstFrame = b;
	}
	
	public boolean getIsFirstFrame() {
		return this.isFirstFrame;
	}
	
	public int getPicNumMap() {
		return this.picNumMap;
	}
}



