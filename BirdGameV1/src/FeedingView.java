import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

@SuppressWarnings("serial")
public class FeedingView extends View {
	
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

//-----------------------------------------------------------------------------------------------------
//JUnit Tests

class FeedingViewTest {
	
	@Test
	public void testDisplayFish()
	{
		// GUI element - cannot test at this time
	}
	
	@Test
	public void testDrawBackground()
	{
		// GUI element - cannot test at this time
	}
}
