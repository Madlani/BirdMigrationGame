package gamePackage;

import java.util.ArrayList;

public class WhackAMoleModel extends Model {
	private int resourceCount;
	private String patternOne;
	
	ArrayList<Integer> gamePattern = new ArrayList<Integer>();
	int gamePatternSize = gamePattern.size();

	private boolean isPlaying = true;
	private boolean isWinning = false;
	
	private int keyState = 0;
	
	public WhackAMoleModel() {
		super();
		//this.randomizeGamePattern(4);
		//this.win();
	}

	public int setRandomPattern() {
		
		return 0;
	}
	
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
	
	public void setKeyState(int k) {
		this.keyState = k;
	}
	
	public int getKeyState() {
		return this.keyState;
	}
}


