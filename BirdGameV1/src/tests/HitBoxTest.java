package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import gamePackage.HitBox;

public class HitBoxTest {

	//HitBox(x,y,width,height)
	HitBox testBox = new HitBox(5, 5, 5, 5);
	HitBox testBox2 = new HitBox(5, 5, 0, 5);
	HitBox testBox3 = new HitBox(5, 5, 5, 0);


	
	@SuppressWarnings("deprecation")
	@Test
	public void testChangeWidth() {
		testBox2.changeWidth(10);
		assertEquals(10,testBox2.getWidth());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testChangeHeight() {
		testBox3.changeHeight(5);
		assertEquals(5,testBox3.getHeight());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSetSize() {
		testBox.setSize(10,27);
		assertEquals(10,testBox.getWidth());
		assertEquals(27,testBox.getHeight());
	}
}
