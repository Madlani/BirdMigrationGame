package gamePackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class WhackAMoleModel extends Model {
	private int resourceCount;
	private String patternOne;

	
	private int keyState = 0;
	private ArrayList<Integer> sequence = new ArrayList<Integer>();
	
	

	public WhackAMoleModel() {
		super();
		//this.randomizeGamePattern(4);
		//this.win();
		
		sequence.add(1);
		sequence.add(2);
		sequence.add (3);
		sequence.add(4);
		randomizeSequence();
		
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
	
	
	public void setKeyState(int k) {
		this.keyState = k;
	}
	
	public int getKeyState() {
		return this.keyState;
	}
	
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


