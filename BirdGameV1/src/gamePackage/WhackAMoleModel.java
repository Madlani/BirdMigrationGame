package gamePackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class WhackAMoleModel extends Model implements Serializable {
	private int resourceCount;
	
	private int keyState = 0;
	private ArrayList<Integer> sequence = new ArrayList<Integer>();

	public WhackAMoleModel() {
		super();
		
		sequence.add(1);
		sequence.add(2);
		sequence.add(3);
		sequence.add(4);
		randomizeSequence();
		
	}

	public int setRandomPattern() {
		return 0;
	}
	
	/**
	 * will randomize where objects will appear during the whack a mole mini game
	 */
	public void randomizeObjects() {
		
	}

	public int getResourceCount() {
		return resourceCount;
	}

	public void setResourceCount(int resourceCount) {
		this.resourceCount = resourceCount;
	}
	
	
	public void setKeyState(int k) {
		this.keyState = k;
	}
	
	public int getKeyState() {
		return this.keyState;
	}
	
	/**
	 * randomize the sequence of the sticks everytime the whack a mole mini game refresh
	 */
	public void randomizeSequence() {
		Collections.shuffle(sequence);
		
		Iterator i = sequence.iterator();
		while (i.hasNext()) {
			System.out.print("Game Sequence: ");
			System.out.println(i.next());
		}
	}
	
	public ArrayList<Integer> getSequence() {
		return this.sequence;
	}
}


