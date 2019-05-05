package gamePackage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

public class WhackAMoleModel extends Model {
	private int resourceCount;
	private String patternOne;
	
	ArrayList<Integer> gamePattern = new ArrayList<Integer>();
	

	private boolean isPlaying = true;
	private boolean isWinning = false;
	
	//Game Patterns (logic, belongs in Model but exists in WhackAMoleView for testing purposes
//	Shape upRect = new Rectangle((scaledImageWidth/2) - 175, 0, 350, 224);
//	Shape downRect = new Rectangle((scaledImageWidth/2) - 175, scaledImageHeight - 224, 350, 224);
//	Shape rightRect = new Rectangle(scaledImageWidth - 350, (scaledImageHeight/2) - 112, 350, 224);
//	Shape leftRect = new Rectangle( 0, (scaledImageHeight/2) - 112, 350, 224);
//	
//	ArrayList<Shape> userRect;
	
	public WhackAMoleModel() {
		super();
		//this.randomizeGamePattern(4);
		//this.win();
	}
	
//	public void randomizeGamePattern(int length) {
//		gamePattern = new ArrayList<Integer>();
//		
//		for (int i = 0; i < length; i++) {
//			gamePattern.add((int)(Math.random()*(4) + 1));
//		}
//
//		System.out.println(gamePattern);
//	}
	
//	public void win() {
//		while(isPlaying) {
//			if (Controller.inputs.equals(gamePattern)) {
//				isWinning = true;
//				System.out.println("You won!");
//				
//				
//			}
//			else {
//				isWinning = false;
//				isPlaying=false;
//				System.out.println("You lost!");
//				
//			}
//			
//		}
//	}
	
	
//	public void initRects() {
//		userRect = new ArrayList<Shape>();
//		//1 - up
//		userRect.add(upRect);
//		//2 - down
//		userRect.add(downRect);
//		//3 - left
//		userRect.add(leftRect);
//		//4 - right
//		userRect.add(rightRect);
//}
//

	
	
	//randomizeObjects() will randomize where objects will appear during the whack a mole mini game
	public void randomizeObjects() {
		
	}

	public int getResourceCount() {
		return resourceCount;
	}

	public void setResourceCount(int resourceCount) {
		this.resourceCount = resourceCount;
	}
	
	public ArrayList<Integer> getGamePattern() {
		return gamePattern;
	}

	public void setGamePattern(ArrayList<Integer> gamePattern) {
		this.gamePattern = gamePattern;
	}
}


