package gamePackage;

import java.util.ArrayList;

public class MigrationModel extends Model {
	
	private final double screenWidth = Model.scaledImageWidth;
	private final double screenHeight = Model.scaledImageHeight;
	
	private int maxWidth = Model.scaledImageHeight - 150;
	private int minWidth = 0;
	
	private final int MINI_MAP_WIDTH = (int)screenWidth / 4;
	
	private final int mouseStartY = -200;
	private final int treeStartY = -50;
	private final int bushQuestionBlockStartY = -100;
	private final int owlStartY = -250;
	
	private final int TREE_WIDTH = 150;
	private final int TREE_HEIGHT = 204;
	private final int BUSH_QUESTION_WIDTH = 150;
	private final int BUSH_QUESTION_HEIGHT = 85;
	private final int OWL_WIDTH = 81;
	private final int OWL_HEIGHT = 47;
	private final int NORTHERNHARRIER_WIDTH = 220;
	
	private final int MAP_X = 4;
	private final int MOUSE_HEIGHT = 89;
	private final int MOUSE_WIDTH = 75;
	
	private double northernHarrierStartingX = screenWidth/2;
	private double northernHarrierStartingY = screenHeight - 100;
	
	protected Bird northernHarrier;
	protected GameObject tree;
	protected GameObject mouse;
	protected GameObject bushQuestionBlock;
	protected GameObject owl;
	
	public MigrationModel() {
		super();
		this.northernHarrier = new Bird(BirdType.NORTHERNHARRIER, ObjectType.NORTHERNHARRIER);
    	this.tree = new GameObject(BirdType.NORTHERNHARRIER, treeStartY, ObjectType.TREE, TREE_WIDTH, TREE_HEIGHT);
    	this.mouse = new GameObject(BirdType.NORTHERNHARRIER, mouseStartY, ObjectType.MOUSE, MOUSE_WIDTH, MOUSE_HEIGHT);
    	this.bushQuestionBlock = new GameObject(BirdType.NORTHERNHARRIER, bushQuestionBlockStartY, ObjectType.BUSH_QUESTION_BOX, BUSH_QUESTION_WIDTH, BUSH_QUESTION_HEIGHT);
    	this.owl = new GameObject(BirdType.NORTHERNHARRIER, owlStartY, ObjectType.OWL, OWL_WIDTH, OWL_HEIGHT);
    	
    	this.northernHarrier.setLocation(northernHarrierStartingX, northernHarrierStartingY);
    	
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
		System.out.println(o.getY());
		if (o.getY() >= screenHeight) {
			resetGameObjectLocation(o);
		} else {
			o.setLocation(o.getX(), o.getY() + o.getGameObjectSpeed());
		}
	}
	
	
	public boolean updateLocationAndDirectionForNorthernHarrier() {
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
		
		this.northernHarrier.setLocation(this.northernHarrier.getX(), this.northernHarrier.getY());
		this.northernHarrier.GameObjectBox.setLocation((int)this.northernHarrier.getX(), (int)this.northernHarrier.getY());
		
		this.tree.setLocation(this.tree.getX(), this.tree.getY());
		this.tree.GameObjectBox.setLocation((int)this.tree.getX(), (int)this.tree.getY());
		
		this.mouse.setLocation(this.mouse.getX(), this.mouse.getY());
		this.mouse.GameObjectBox.setLocation((int)this.mouse.getX(), (int)this.mouse.getY());
		
		this.bushQuestionBlock.setLocation(this.bushQuestionBlock.getX(), this.bushQuestionBlock.getY());
		this.bushQuestionBlock.GameObjectBox.setLocation((int)this.bushQuestionBlock.getX(), (int)this.bushQuestionBlock.getY());
		
		this.owl.setLocation(this.owl.getX(), this.owl.getY());
		this.owl.GameObjectBox.setLocation((int)this.owl.getX(), (int)this.owl.getY());
		
    	updateGameObjectLocationAndDirection(northernHarrier);
    	updateGameObjectLocationAndDirection(tree);
    	updateGameObjectLocationAndDirection(mouse);
    	updateGameObjectLocationAndDirection(bushQuestionBlock);
    	updateGameObjectLocationAndDirection(owl);
		
		
		return detectCollisions(this.gameObjectsForNortherHarrier, this.northernHarrier);
	}
	
	
	/**
	 * resetGameObjectLocation()
	 * Moves the object to the right side of the screen once it gets too far left.
	 */
	@Override
	public void resetGameObjectLocation(GameObject o) {
		int maxWidth = (int)screenWidth - o.GameObjectBox.width;
		int minHeight = -150;

		int rand = (int) (Math.random() * (maxWidth - minHeight + 1) - minHeight) + MINI_MAP_WIDTH;
		o.setLocation(rand, minHeight);
	}
	
	public Bird getNorthernHarrier() {
		return this.northernHarrier;
	}
}

