package gamePackage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WhackAMoleModel extends Model {
	private int resourceCount;
	private String patternOne;
	
	public WhackAMoleModel() {
		super();
	}
	
	public void randomizeGameString() {
		int rand1 = (int)(Math.random()*(4) + 1);
		patternOne = "" + rand1;
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
}


