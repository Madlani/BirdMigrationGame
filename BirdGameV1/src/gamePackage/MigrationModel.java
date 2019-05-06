package gamePackage;

import java.awt.Toolkit;

public class MigrationModel extends Model {
	
	public MigrationModel() {
		super();
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
				int minHeight = 0;
				
				int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
				int rand = (int)(Math.random()*(maxWidth - minHeight + 1) + minHeight);
				o.setLocation(rand, minHeight);
		}
}



