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
	
	protected Bird northernHarrier;
	protected GameObject tree;
	protected GameObject mouse;
	protected GameObject bushQuestionBlock;
	protected GameObject owl;
	
	protected GameState state;
	protected int tick = 0;
	protected final int MINIMAP_SUBIMAGES = 13;
	protected final int MAP_DELAY = 100;
	protected int picNumMap = 0;
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
		if (this.state != GameState.TUTORIAL) {
			tick = (tick + 1) % this.MAP_DELAY;
			if (tick == 0) {
				picNumMap = (picNumMap + 1) % this.MINIMAP_SUBIMAGES;
			}
		}
		
		
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
		
		if (this.bushQuestionBlock.getY() >= screenHeight && this.state == GameState.TUTORIAL) {
			this.state = GameState.MIGRATION;
		}
		
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
	 * resetGameObjectLocation()
	 * Moves the object to the right side of the screen once it gets too far left.
	 */
	@Override
	public void resetGameObjectLocation(GameObject o) {
		int maxWidth = (int)screenWidth - o.GameObjectBox.width;
		int minHeight = -250;

		int rand = (int) (Math.random() * (maxWidth - 50 + 1) - minHeight) + MINI_MAP_WIDTH;
		o.setLocation(rand, minHeight);
//		int rand;
//		switch(o.getType()) {
//		case BUSH_QUESTION_BOX:
//		case TREE:
//		case OWL:
//			rand = (int)(Math.random() * (this.thirdOfTheScreenY - 80));
//			o.setLocation(scaledImageWidth + offset, rand);
//			break;
//			
//		case MOUSE:		
//		
//		default:
//			break;
//		}
	}
	
	public Bird getNorthernHarrier() {
		return this.northernHarrier;
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
	
}

