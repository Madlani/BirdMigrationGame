package tests;



import gamePackage.MigrationModel;

class MigrationModelTest {

	@Test
	public void testUpdateLocationAndDirection() {
		MigrationModel test = new MigrationModel();
		test.setLocation(0, 0);
		test.updateLocationAndDirection();
		assertNotEquals(0, test.getX());
	}
	
	@Test
	public void testRandomizeObstacles() {
		//can't test at this time
	}

}
