package gamePackage;

import java.awt.Toolkit;

public class SideSwiperModel extends Model {
	
	public SideSwiperModel() {
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
		if(o.getX() <= -o.GameObjectBox.width) {
			resetGameObjectLocation(o);
		}
		else {
			o.setLocation(o.getX() - o.getGameObjectSpeed(), o.getY());
		}
	}
	
	@Override
		public void resetGameObjectLocation(GameObject o) {

			int maxHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - o.GameObjectBox.height;
			int minHeight = 0;
			
			int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			int rand = (int)(Math.random()*(maxHeight - minHeight + 1) + minHeight);
			
			o.setLocation(width, rand);
	}
	
}



