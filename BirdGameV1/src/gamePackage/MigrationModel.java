package gamePackage;

import java.awt.Toolkit;

public class MigrationModel extends Model {
	
	private Bird osprey;
	private GameObject airplane;
	private GameObject food;
	private GameObject cloudQuestionBlock;
	private GameObject thunderCloud;
	
	private final double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int maxWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 150;
	int minWidth = 0;
	int randX = (int)(Math.random() * (maxWidth - minWidth + 1) + minWidth);
	
	final int airplaneStartY = -200;
	final int thunderCloudStartY = -450;
	final int questionBlockStartY = -700;
	final int foodStartY = -700;
	
	public MigrationModel() {
		super();
		
		this.osprey = new Bird();
    	this.airplane = new GameObject(randX, ObjectType.PLANE, 150, 150);
    	this.food = new GameObject(randX, ObjectType.FOOD, 100, 65);
    	this.thunderCloud = new GameObject(randX, ObjectType.THUNDER_CLOUD, 200, 200);
    	this.cloudQuestionBlock = new GameObject(randX, ObjectType.CLOUD_QUESTION_BOX, 300, 178);
    
    	
    	this.osprey.setLocation(screenWidth/2, screenHeight - 200);
    	this.airplane.setLocation(randX, airplaneStartY);
    	this.food.setLocation(randX, foodStartY);
    	this.thunderCloud.setLocation(randX, thunderCloudStartY);
    	this.cloudQuestionBlock.setLocation(randX, questionBlockStartY);
    	
	}
	//updateBirdLocation() will update the position of the bird to update the minimap
		public void updateBirdLocation() {
			
		}
		
		//randomizeBlocks() will randomize where blocks that contain questions will appear on screen
		public void randomizeBlocks() {
			
		}
		
		//randomizeQuestion() will pick a random question to appear if the bird hits a question box
		public void randomizeQuestion() {
			
		}
		
		//moveGameObjects() moves the GameObject as the game progresses
		public void moveGameObjects() {
			
		}
		
		@Override
		public void updateGameObjectLocationAndDirection(GameObject o) {
			System.out.println(o.getY());
			if(o.getY() >= (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()){
				resetGameObjectLocation(o);
			}
			else {
				o.setLocation(o.getX() , o.getY()+ o.getGameObjectSpeed());
			}
		}
		
		@Override
			public void resetGameObjectLocation(GameObject o) {
				int maxWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - o.GameObjectBox.width;
				int minHeight = -150;
				
				int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
				int rand = (int)(Math.random()*(maxWidth - minHeight + 1) - minHeight) +((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth())/4 ;
				o.setLocation(rand, minHeight);
		}
}



