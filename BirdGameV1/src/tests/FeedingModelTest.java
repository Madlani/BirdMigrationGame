package tests;

class FeedingModelTest {
	
	@Test
	public void testUpdateLocationAndDirection() {
		FeedingModel test = new FeedingModel();
		test.setLocation(0, 0);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getX());
	}
	
	@Test
	public void testDive() {
		//can't test at this time
	}
	
	@Test
	public void testIsHoldingFish() {
		FeedingModel test = new FeedingModel();
		assertEquals(false, test.isHoldingFish());
		assertFalse(test.isHoldingFish());
	}
}
