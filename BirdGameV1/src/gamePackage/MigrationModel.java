package gamePackage;

public class MigrationModel extends Model {
	
	private Bird osprey;
	private GameObject airplane;
	private GameObject food;
	private GameObject cloudQuestionBlock;
	private GameObject thunderCloud;
	
	private final double screenWidth = Model.scaledImageWidth;
	private final double screenHeight = Model.scaledImageHeight;
	
	private int maxWidth = Model.scaledImageHeight - 150;
	private int minWidth = 0;
	private int randX = (int)(Math.random() * (maxWidth - minWidth + 1) + minWidth);
	
	private final int AIRPLANE_START_Y = -200;
	private final int THUNDERCLOUD_STARTING_Y = -450;
	private final int QUESTIONBLOCK_STARTING_Y = -700;
	private final int FOOD_STARTING_Y = -700;
	
	private final int AIRPLANE_WIDTH = 150;
	private final int AIRPLANE_HEIGHT = 150;
	
	private final int FISH_WIDTH = 100;
	private final int FISH_HEIGHT = 65;
	
	private final int THUNDERCLOUD_WIDTH = 200;
	private final int THUNDERCLOUD_HEIGHT = 200;
	
	private final int CLOUD_WIDTH = 300;
	private final int CLOUD_HEIGHT = 178;
	
	private final int BIRD_STARTING_X = (int)screenWidth / 2;
	private final int BIRD_STARTING_Y = (int)screenHeight - 200;
	
	private final int MINI_MAP_WIDTH = (int)screenWidth / 4;
	
	
	public MigrationModel() {
		super();
		
		// Creates the objects for use in the Migration Model
		this.osprey = new Bird();
    	this.airplane = new GameObject(randX, ObjectType.PLANE, AIRPLANE_WIDTH, AIRPLANE_HEIGHT);
    	this.food = new GameObject(randX, ObjectType.FOOD, FISH_WIDTH, FISH_HEIGHT);
    	this.thunderCloud = new GameObject(randX, ObjectType.THUNDER_CLOUD, THUNDERCLOUD_WIDTH, THUNDERCLOUD_HEIGHT);
    	this.cloudQuestionBlock = new GameObject(randX, ObjectType.CLOUD_QUESTION_BOX, CLOUD_WIDTH, CLOUD_HEIGHT);

		// Sets the location for the objects in the Migration Model
		this.osprey.setLocation(BIRD_STARTING_X, BIRD_STARTING_Y);
		this.airplane.setLocation(randX, AIRPLANE_START_Y);
		this.food.setLocation(randX, FOOD_STARTING_Y);
		this.thunderCloud.setLocation(randX, THUNDERCLOUD_STARTING_Y);
		this.cloudQuestionBlock.setLocation(randX, QUESTIONBLOCK_STARTING_Y);

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
}
