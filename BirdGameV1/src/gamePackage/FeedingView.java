package gamePackage;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class FeedingView extends View {
	
	public FeedingView(Controller controller) {
		super(controller);
	}

	private BufferedImage[][] fishingAnimationArray;
	
	//Draws the fish that will be caught as part of the Feeding minigame
	public void displayFish() 
	{
		
	}
	
	//Draws the background for the Feeding minigame
	@Override
	public void drawBackground()
	{
		
	}
	
	//Returns the array that holds images which show the bird diving down and catching the fish
	public BufferedImage[][] getFishingAnimationArray() {
		return fishingAnimationArray;
	}
	
	//Sets the array that holds images which show the bird diving down and catching the fish
	public void setFishingAnimationArray(BufferedImage[][] fishingAnimationArray) {
		this.fishingAnimationArray = fishingAnimationArray;
	}
}



