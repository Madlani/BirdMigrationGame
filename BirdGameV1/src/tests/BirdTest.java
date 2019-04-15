package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gamePackage.Bird;

class BirdTest {

Bird testBird = new Bird();
	
	@Test
	public void testMoveLeft() {
		testBird.setXPosition(5);
		testBird.moveLeft();
		assertEquals(4, testBird.getXPosition());
	}
	
	@Test
	public void testMoveRight() {
		testBird.setXPosition(8);
		testBird.moveRight();
		assertEquals(9, testBird.getXPosition());
	}
	
	@Test
	public void testMoveUp() {
		testBird.setYPosition(5);
		testBird.moveUp();
		assertEquals(4, testBird.getYPosition());
	}
	
	@Test
	public void testMoveDown() {
		testBird.setYPosition(5);
		testBird.moveDown();
		assertEquals(6, testBird.getYPosition());
	}

}
