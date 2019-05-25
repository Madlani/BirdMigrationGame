package gamePackage;

import java.util.ArrayList;

public class MigrationModel extends Model {
	
	private final int TUTORIAL_SPEED = 2;
	private final int NORMAL_SPEED = 3;
	private final double screenWidth = Model.scaledImageWidth;
	private final double screenHeight = Model.scaledImageHeight;
	protected int offset = 600;
	
	private int maxWidth = Model.scaledImageHeight - 150;
	private int minWidth = 0;
	
	private final int MINI_MAP_WIDTH = (int)screenWidth / 4;
	
	private final int mouseStartY = -100;
	private final int treeStartY = -600;
	private final int bushQuestionBlockStartY = -1600;
	private final int owlStartY = -1100;
	
	private final int TREE_WIDTH = 200;
	private final int TREE_HEIGHT = 274;
	private final int BUSH_QUESTION_WIDTH = 150;
	private final int BUSH_QUESTION_HEIGHT = 85;
	private final int OWL_WIDTH = 80;
	private final int OWL_HEIGHT = 82;
	private final int NORTHERNHARRIER_WIDTH = 220;
	private final int NORTHERNHARRIER_HEIGHT = 140;
	
	private final int MAP_X = 4;
	private final int MOUSE_HEIGHT = 89;
	private final int MOUSE_WIDTH = 75;
	
	private double northernHarrierStartingX = screenWidth/2;
	private double northernHarrierStartingY = screenHeight - 250;
	
	private Bird northernHarrier;
	private GameObject tree;
	private GameObject mouse;
	private GameObject bushQuestionBlock;
	private GameObject owl;
	
	private GameState state;
	private int tick = 0;
	private final int MINIMAP_SUBIMAGES = 13;
	private final int MAP_DELAY = 100;
	private int picNumMap = 0;
	protected boolean isFirstFrame = true;
	protected int thirdOfTheScreenY = (int) ((screenHeight / 3) * 2);
	
	public MigrationModel() {
		super();
		init();
	}
	
	public void init() {
		this.northernHarrier = new Bird(BirdType.NORTHERNHARRIER, ObjectType.NORTHERNHARRIER);
    	this.tree = new GameObject(BirdType.NORTHERNHARRIER, this.screenWidth / 2, treeStartY, ObjectType.TREE, TREE_WIDTH, TREE_HEIGHT);
    	this.mouse = new GameObject(BirdType.NORTHERNHARRIER, this.screenWidth / 2, mouseStartY, ObjectType.MOUSE, MOUSE_WIDTH, MOUSE_HEIGHT);
    	this.bushQuestionBlock = new GameObject(BirdType.NORTHERNHARRIER, this.screenWidth / 2, bushQuestionBlockStartY, ObjectType.BUSH_QUESTION_BOX, BUSH_QUESTION_WIDTH, BUSH_QUESTION_HEIGHT);
    	this.owl = new GameObject(BirdType.NORTHERNHARRIER, this.screenWidth / 2, owlStartY, ObjectType.OWL, OWL_WIDTH, OWL_HEIGHT);
    	
    	this.northernHarrier.setLocation(northernHarrierStartingX, northernHarrierStartingY);
    	this.state = GameState.TUTORIAL;
    	this.tree.setSpeed(this.TUTORIAL_SPEED);
    	this.mouse.setSpeed(this.TUTORIAL_SPEED);
    	this.owl.setSpeed(this.TUTORIAL_SPEED);
    	this.bushQuestionBlock.setSpeed(this.TUTORIAL_SPEED);
    	
    	this.gameObjectsForNortherHarrier = new ArrayList<>();
    	this.gameObjectsForNortherHarrier.add(northernHarrier);
    	this.gameObjectsForNortherHarrier.add(tree);
    	this.gameObjectsForNortherHarrier.add(mouse);
    	this.gameObjectsForNortherHarrier.add(bushQuestionBlock);
    	this.gameObjectsForNortherHarrier.add(owl);
	}

	/**
	 * updateGameObjectLocationAndDirection
	 * Updates the location of the game object and direction. Calls a method to move the object to the right 
	 * side of the screen when it gets too far left.
	 */
	@Override
	public void updateGameObjectLocationAndDirection(GameObject o) {
		if (o.getY() >= screenHeight) {
			resetGameObjectLocation(o);
		} else {
			o.setLocation(o.getX(), o.getY() + o.getGameObjectSpeed());
		}
	}
	
	public void moveObjects(GameObject o) {
		o.setLocation(o.getX(), o.getY() +  o.getGameObjectSpeed());
	}
	
	public boolean updateLocationAndDirectionForNorthernHarrier() {
		selectCorrectMiniMap();
		moveBirdAccordingToFlyState();
		checkEndOfTutorial();
		updateHitBoxesToFollowObjects();

		
	   	moveObjects(tree);
    	moveObjects(mouse);
    	moveObjects(owl);
    	moveObjects(bushQuestionBlock);
		
		if (this.state != GameState.TUTORIAL) {
			this.tree.setSpeed(3);
			this.mouse.setSpeed(3);
			this.owl.setSpeed(3);
			this.bushQuestionBlock.setSpeed(3);

			updateGameObjectLocationAndDirection(tree);
			updateGameObjectLocationAndDirection(mouse);
			updateGameObjectLocationAndDirection(bushQuestionBlock);
			updateGameObjectLocationAndDirection(owl);
		}
		
		return detectCollisions(this.gameObjectsForNortherHarrier, this.northernHarrier, this.state);
	}
	
	/**
	 * selectCorrectMiniMap()
	 * Chooses the correct mini map based on the tick
	 */
	public void selectCorrectMiniMap() {
		if (this.state != GameState.TUTORIAL) {
			tick = (tick + 1) % this.MAP_DELAY;
			if (tick == 0) {
				picNumMap = (picNumMap + 1) % this.MINIMAP_SUBIMAGES;
			}
		}
	}
	
	/**
	 * checkEndOfTutorial()
	 * Checks to see if it is time to transition to the Migration Game
	 * Once the bush question block gets to a specific y location, the game starts as the migration game.
	 */
	public void checkEndOfTutorial() {
		if (this.bushQuestionBlock.getY() >= screenHeight && this.state == GameState.TUTORIAL) {
			this.state = GameState.MIGRATION;
		}
	}
	
	/**
	 * moveBirdAccordingToFlyState()
	 * Moves the bird based on the FlyState and constraints on the x-location of the bird to stay within the game area.
	 */
	public void moveBirdAccordingToFlyState() {
		switch (this.northernHarrier.getFlyState()) {	
		case RIGHT:
			if (this.northernHarrier.getX() < screenWidth - NORTHERNHARRIER_WIDTH) {
				this.northernHarrier.moveRight();
			}
			break;
		case LEFT:
			if (this.northernHarrier.getX() > screenWidth / MAP_X) {
				this.northernHarrier.moveLeft();
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * updateHitBoxesToFollowObjects()
	 * Moves the HitBox associated with each object to be the current position of the object after it was updated in the Model
	 */
	public void updateHitBoxesToFollowObjects() {
		this.northernHarrier.setLocation(this.northernHarrier.getX(), this.northernHarrier.getY());
		this.northernHarrier.birdBox.setLocation((int)this.northernHarrier.getX(), (int)this.northernHarrier.getY());
		System.out.println("bird in migration model: " + this.northernHarrier.getX() + ", " + this.northernHarrier.getY());
		
		this.tree.setLocation(this.tree.getX(), this.tree.getY());
		this.tree.GameObjectBox.setLocation((int)this.tree.getX(), (int)this.tree.getY());
		
		this.mouse.setLocation(this.mouse.getX(), this.mouse.getY());
		this.mouse.GameObjectBox.setLocation((int)this.mouse.getX(), (int)this.mouse.getY());
		
		this.bushQuestionBlock.setLocation(this.bushQuestionBlock.getX(), this.bushQuestionBlock.getY());
		this.bushQuestionBlock.GameObjectBox.setLocation((int)this.bushQuestionBlock.getX(), (int)this.bushQuestionBlock.getY());
		
		this.owl.setLocation(this.owl.getX(), this.owl.getY());
		this.owl.GameObjectBox.setLocation((int)this.owl.getX(), (int)this.owl.getY());
	}
	
	
	/**
	 * resetGameObjectLocation()
	 * Moves the object to the right side of the screen once it gets too far left.
	 */
	@Override
	public void resetGameObjectLocation(GameObject o) {
		int maxWidth = (int)screenWidth - o.GameObjectBox.width;
		int minHeight = -250;

		int rand = (int) (Math.random() * (maxWidth - 50 + 1) - minHeight) + MINI_MAP_WIDTH;
		o.setLocation(rand, minHeight);
	}
	
	public int getPicNumMap() {
		return this.picNumMap;
	}
	
	public boolean getFirstFrame() {
		return this.isFirstFrame;
	}
	
	public void setFirstFrame(boolean b) {
		this.isFirstFrame = b;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}
	
	public GameObject getTree() {
		return tree;
	}

	public void setTree(GameObject tree) {
		this.tree = tree;
	}

	public GameObject getMouse() {
		return mouse;
	}

	public void setMouse(GameObject mouse) {
		this.mouse = mouse;
	}

	public GameObject getBushQuestionBlock() {
		return bushQuestionBlock;
	}

	public void setBushQuestionBlock(GameObject bushQuestionBlock) {
		this.bushQuestionBlock = bushQuestionBlock;
	}

	public GameObject getOwl() {
		return owl;
	}

	public void setOwl(GameObject owl) {
		this.owl = owl;
	}
	
	public Bird getNorthernHarrier() {
		return this.northernHarrier;
	}

	public void setNorthernHarrier(Bird northernHarrier) {
		this.northernHarrier = northernHarrier;
	}
	
	public int getTick() {
		return this.tick;
	}
	
	public void setTick(int tick) {
		this.tick = tick;
	}
}

