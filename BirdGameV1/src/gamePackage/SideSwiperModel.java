package gamePackage;

import java.io.Serializable;
import java.util.ArrayList;

public class SideSwiperModel extends Model implements Serializable {
	
	protected int screenWidth = Model.scaledImageWidth;
	protected int screenHeight = Model.scaledImageHeight;
	protected int offset = 600;
	
	protected final int PLANEBOX_WIDTH = 150;
	protected final int PLANEBOX_HEIGHT = 150;
	protected final int FISHBOX_WIDTH = 100;
	protected final int FISHBOX_HEIGHT = 65;
	protected final int THUNDERCLOUD_WIDTH = 150;
	protected final int THUNDERCLOUD_HEIGHT = 150;
	protected final int QUESTIONCLOUD_WIDTH = 225;
	protected final int QUESTIONCLOUD_HEIGHT = 150;
	protected final int FOX_WIDTH = 150;
	protected final int FOX_HEIGHT = 75;
	
	protected final int OSPREY_WIDTH = 150;
	protected final int OSPREY_HEIGHT = 150;
	
	protected double ospreyStartingX = screenWidth/3;
	protected double ospreyStartingY = screenHeight/2;
	
	protected Bird osprey;
	protected GameObject airplane;
	protected GameObject fish;
	protected GameObject cloudQuestionBlock;
	protected GameObject thunderCloud;
	protected GameObject fox;
	
	protected int thirdOfTheScreenY = (screenHeight / 3) * 2;
	
	
	
	protected boolean isOver = false;
	protected boolean isFirstFrame = true;
	protected final int MAP_FRAME_COUNT = 250;
	protected int tick = 0;
	protected final int MIGRATION_MAP_SUBIMAGES = 9;
	protected int picNumMap = 0;
	
	protected final int airplaneStartX = 1000;
	protected final int thunderCloudStartX = 1500;
	protected final int questionBlockStartX = 2000;
	protected final int fishStartX = 0;
	protected final int foxStartX = 500;
	
	protected GameState state;
	protected final int TUTORIAL_SPEED = 3;
	protected final int NORMAL_SPEED = 7;
	protected final int QUICK_SPEED = 15;
	protected final int HEALTH_BOUNDARY = 5;
	
	protected final int CLOUD_PORTION_SCREEN = 80;
	protected final int GRASS_PORTION_SCREEN = 100;

	
	private final int startingCoordDivider = 2;

	
	public SideSwiperModel() {
		super();
		init();
    	
    	
	}
	
	public void init() {
		this.state = GameState.TUTORIAL;
		this.osprey = new Bird(BirdType.OSPREY, ObjectType.OSPREY);
    	this.airplane = new GameObject(BirdType.OSPREY, screenWidth + airplaneStartX, this.screenHeight / startingCoordDivider, ObjectType.PLANE, PLANEBOX_WIDTH, PLANEBOX_HEIGHT);
    	this.fish = new GameObject(BirdType.OSPREY, screenWidth + fishStartX, this.screenHeight / startingCoordDivider, ObjectType.FISH, FISHBOX_WIDTH, FISHBOX_HEIGHT);
    	this.thunderCloud = new GameObject(BirdType.OSPREY, screenWidth + thunderCloudStartX, this.screenHeight / startingCoordDivider, ObjectType.THUNDER_CLOUD, THUNDERCLOUD_WIDTH, THUNDERCLOUD_HEIGHT);
    	this.cloudQuestionBlock = new GameObject(BirdType.OSPREY, screenWidth + questionBlockStartX, this.screenHeight / startingCoordDivider, ObjectType.CLOUD_QUESTION_BOX, QUESTIONCLOUD_WIDTH, QUESTIONCLOUD_HEIGHT);
    	this.fox = new GameObject(BirdType.OSPREY, screenWidth + foxStartX, this.screenHeight / startingCoordDivider, ObjectType.FOX, FOX_WIDTH, FOX_HEIGHT);
    	
    	this.osprey.setLocation(ospreyStartingX, ospreyStartingY);
    	
    	this.airplane.setSpeed(this.TUTORIAL_SPEED);
    	this.fish.setSpeed(this.TUTORIAL_SPEED);
    	this.thunderCloud.setSpeed(this.TUTORIAL_SPEED);
    	this.cloudQuestionBlock.setSpeed(this.TUTORIAL_SPEED);
    	this.fox.setSpeed(this.TUTORIAL_SPEED);
    	
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
		if(o.getX() <= -o.GameObjectBox.width) 
			resetGameObjectLocation(o);
	}
	
	/**
	 * move the Objects' X coordinate according to their speed
	 * @param o
	 */
	public void moveObjects(GameObject o) {
//This code enables dynamic dificulty, if we want it
//		if (this.osprey.getHealthCount() >HEALTH_BOUNDARY) {
//			o.setSpeed(QUICK_SPEED);
//		}
//		else {
//			o.setSpeed(NORMAL_SPEED);
//		}
		o.setLocation(o.getX() - o.getGameObjectSpeed(), o.getY());
	}
	
	/**
	 * logic for updating the miniMap
	 */
	public void selectCorrectMiniMap() {
		if (this.state != GameState.TUTORIAL) {
			tick = (tick+1) % MAP_FRAME_COUNT;
			if (tick == 0) {
				picNumMap = (picNumMap + 1) % MIGRATION_MAP_SUBIMAGES;
			}
		}	
	}
	
	/**
	 * updating the bird's coordinate according to it's fly state
	 */
	public void moveBirdAccordingToFlyState() {
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
		
		if (this.cloudQuestionBlock.getX() <= 0 && this.state == GameState.TUTORIAL) {
			this.state = GameState.SIDESWIPER;
		}
	}
	
	/**
	 * update all of the coordinates for GameObjects on screen for the osprey game and 
	 * detect their collisions
	 * @return
	 */
	public boolean updateLocationAndDirectionForOsprey() {
		selectCorrectMiniMap();
		moveBirdAccordingToFlyState();
    	updateHitBoxesToFollowObjects();
    	
    	moveObjects(airplane);
    	moveObjects(fish);
    	moveObjects(thunderCloud);
    	moveObjects(cloudQuestionBlock);
    	moveObjects(fox);
    	
    	if (this.state != GameState.TUTORIAL) {
    		this.airplane.setSpeed(this.NORMAL_SPEED);
        	this.fish.setSpeed(this.NORMAL_SPEED);
        	this.thunderCloud.setSpeed(this.NORMAL_SPEED);
        	this.cloudQuestionBlock.setSpeed(this.NORMAL_SPEED);
        	this.fox.setSpeed(this.NORMAL_SPEED);
    		
			updateGameObjectLocationAndDirection(airplane);
			updateGameObjectLocationAndDirection(fish);
			updateGameObjectLocationAndDirection(thunderCloud);
			updateGameObjectLocationAndDirection(cloudQuestionBlock);
			updateGameObjectLocationAndDirection(fox);
		}
    	
    	return detectCollisions(this.gameObjectsForOsprey, this.osprey, this.state);
	}
	
	/**
	 * update the GameObjects' hitboxes to follow the objects on screen
	 */
	public void updateHitBoxesToFollowObjects() {
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
			rand = (int)(Math.random() * (this.thirdOfTheScreenY - CLOUD_PORTION_SCREEN));
			o.setLocation(scaledImageWidth + offset, rand);
			break;
			
		case FISH:
		case FOX:
			rand = (int)(Math.random() * ((this.screenHeight - GRASS_PORTION_SCREEN) - this.thirdOfTheScreenY)) + this.thirdOfTheScreenY;
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
	
	public void setPicNumMap(int p) {
		this.picNumMap = p;
	}
	
	public GameState getState() {
		return this.state;
	}
	
	public void setState(GameState state) {
		this.state = state;
	}
	
	public int getTick() {
		return this.tick;
	}
	
	public void setTick(int tick) {
		this.tick = tick;
	}
}



