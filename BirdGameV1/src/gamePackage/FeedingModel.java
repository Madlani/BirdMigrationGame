package gamePackage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

public class FeedingModel extends Model {
	
	public FeedingModel() {
		super();
	}
	
	//dive() will start the dive animation when the bird tries to catch a fish
	public void dive() {
		
	}
	
	//isHoldingFish() returns true if the bird is holding a fish else false
	public boolean isHoldingFish() {
		return false;
	}
}

//-----------------------------------------------------------------------------------------------------

