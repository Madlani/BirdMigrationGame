package gamePackage;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class StartView extends View {

	private boolean tutorialClicked;
	private boolean startClicked;
	

	public StartView() {
		super();
	}
	
	//Displays the bird image
	public void createBirdObject() {
		
	}
	
	//Draws the background that the game will display at the start
	@Override
	public void drawBackground() {
		
	}
	
	//Returns a boolean that detects if the tutorial button was clicked 
	public boolean getTutorialClicked() {
		return this.tutorialClicked;
	}
	
	//Returns a boolean that detects if the start game button was clicked
	public boolean getStartClicked() {
		return this.startClicked;
	}

	@Override
	public void update(ArrayList<GameObject> list) {
		// TODO Auto-generated method stub
		
	}
}



