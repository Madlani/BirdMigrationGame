package tests;


import gamePackage.Bird;

class BirdTest {

Bird testBird = new Bird();
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMoveLeft() {
		testBird.setLocation(5, 5);
		testBird.moveLeft();
		assertEquals(4, testBird.getX());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMoveRight() {
		testBird.setLocation(8, 8);
		testBird.moveRight();
		assertEquals(9, testBird.getX());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMoveUp() {
		testBird.setLocation(5, 5);
		testBird.moveUp();
		assertEquals(4, testBird.getY());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMoveDown() {
		testBird.setLocation(5, 5);
		testBird.moveDown();
		assertEquals(6, testBird.getY());
	}

}
