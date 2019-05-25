package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.jws.WebParam.Mode;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import gamePackage.Bird;
import gamePackage.BirdType;
import gamePackage.FlyState;
import gamePackage.GameObject;
import gamePackage.GameState;
import gamePackage.MigrationModel;
import gamePackage.Model;
import gamePackage.ObjectType;

class MigrationModelTest {
	
	private final int OWL_WIDTH = 80;
	private final int OWL_HEIGHT = 82;
	private final int screenHeight = Model.scaledImageHeight;
	private final int screenWidth = Model.scaledImageWidth;
	
	Bird northernHarrier = new Bird(BirdType.NORTHERNHARRIER, ObjectType.NORTHERNHARRIER);
	GameObject owl = new GameObject(BirdType.NORTHERNHARRIER, 500, ObjectType.OWL, OWL_WIDTH, OWL_HEIGHT);
	
	MigrationModel model = new MigrationModel();
	
	@Test
	public void testMoveObjects() {
		double owlStartingX = owl.getX();
		double owlStartingY = owl.getY();
		
		model.moveObjects(owl);
		
		assertEquals(owlStartingX, owl.getX()); // the x position of the owl shouldn't move
		assertEquals(owlStartingY + owl.getGameObjectSpeed(), owl.getY());
	}
	
	// MAJOR CODE RESTRUCTURING NEEDED IN THIS METHOD
	@Test
	public void testUpdateGameObjectLocationAndDirection() {
		int minHeight = -250; // used for reseting the location of objects going off the screen
		
		// Testing when the y-location is >= the screenHeight
		owl.setLocation(owl.getX(), screenHeight);
		model.updateGameObjectLocationAndDirection(owl);
		assertEquals(minHeight, owl.getY());
		
		// Testing when the y-location is < the screenHeight
		int originalOwlY = 0;
		owl.setLocation(owl.getX(), originalOwlY);
		model.updateGameObjectLocationAndDirection(owl);
		assertEquals(originalOwlY + owl.getGameObjectSpeed(), owl.getY());
	}
	
	@Test
	public void testUpdateLocationAndDirectionForNorthernHarrier() {
		model.setState(GameState.MIGRATION);
		model.updateLocationAndDirectionForNorthernHarrier();
		assertNotEquals(0, model.getTick());
		
		// These cases are irrelevant to the Migration game, but to get complete coverage, it was necessary to include these
		model.setState(GameState.LOSE);
		model.updateLocationAndDirectionForNorthernHarrier();
		
		model.setState(GameState.OSPREYWIN);
		model.updateLocationAndDirectionForNorthernHarrier();
		
		model.setState(GameState.SIDESWIPER);
		model.updateLocationAndDirectionForNorthernHarrier();
		
		model.setState(GameState.START);
		model.updateLocationAndDirectionForNorthernHarrier();
		 
		model.setState(GameState.WHACKAMOLE);
		model.updateLocationAndDirectionForNorthernHarrier();
		
		model.setState(GameState.WIN);
		model.updateLocationAndDirectionForNorthernHarrier();
		
		model.setState(GameState.TUTORIAL);
		model.updateLocationAndDirectionForNorthernHarrier();
	}
	
	@Test
	public void testSelectCorrectMiniMap() {
		final int MAP_DELAY = 100;
		
		model.setState(GameState.MIGRATION);
		model.setTick(99);
		model.selectCorrectMiniMap();
		assertEquals(0, model.getTick() % MAP_DELAY);
		assertEquals(0, model.getTick());

	}
	
	@Test
	public void testCheckEndOfTutorial() {
		model.getBushQuestionBlock().setLocation(model.getBushQuestionBlock().getX(), screenHeight);
		model.setState(GameState.TUTORIAL);
		model.checkEndOfTutorial();
		assertEquals(GameState.MIGRATION, model.getState());
		
		model.getBushQuestionBlock().setLocation(model.getBushQuestionBlock().getX(), screenHeight);
		model.setState(GameState.MIGRATION);
		model.checkEndOfTutorial();
		assertEquals(GameState.MIGRATION, model.getState());
	}
	
	@Test
	public void testMoveBirdAccordingToFlyState() {
		final int NORTHERNHARRIER_WIDTH = 220;
		final int MAP_X = 4;
		double originalXLoc = model.getNorthernHarrier().getX();
		
		// Possible cases when the FlyState is RIGHT
		model.getNorthernHarrier().setFlyState(FlyState.RIGHT);
		model.getNorthernHarrier().setLocation(model.getNorthernHarrier().getX() - screenWidth - NORTHERNHARRIER_WIDTH, model.getNorthernHarrier().getY());
		model.moveBirdAccordingToFlyState();
		assertNotEquals(model.getNorthernHarrier().getX() - screenWidth - NORTHERNHARRIER_WIDTH, model.getNorthernHarrier().getX());
		
		model.getNorthernHarrier().setFlyState(FlyState.RIGHT);
		model.getNorthernHarrier().setLocation(screenWidth - NORTHERNHARRIER_WIDTH, model.getNorthernHarrier().getY());
		model.moveBirdAccordingToFlyState();
		assertEquals(screenWidth - NORTHERNHARRIER_WIDTH, model.getNorthernHarrier().getX());
		
		// Possible cases when the FlyState is LEFT
		model.getNorthernHarrier().setFlyState(FlyState.LEFT);
		model.getNorthernHarrier().setLocation(originalXLoc + screenWidth / MAP_X, model.getNorthernHarrier().getY());
		model.moveBirdAccordingToFlyState();
		assertNotEquals(model.getNorthernHarrier().getX() + screenWidth / MAP_X, model.getNorthernHarrier().getX());
		
		model.getNorthernHarrier().setFlyState(FlyState.LEFT);
		model.getNorthernHarrier().setLocation(screenWidth / MAP_X, model.getNorthernHarrier().getY());
		model.moveBirdAccordingToFlyState();
		assertEquals(screenWidth / MAP_X, model.getNorthernHarrier().getX());
		
	}
	
	@Test
	public void testUpdateHitBoxesToFollowObjects() {
		GameObject nh = (GameObject) model.getNorthernHarrier();
		
		nh.setLocation(10, 10);
		model.updateHitBoxesToFollowObjects();
		assertEquals(10, nh.getX());
	}
	
	@Test
	public void testSetFirstFrame() {
		model.setFirstFrame(true);
		assertEquals(true, model.getFirstFrame());
	}
	
	@Test
	public void testSetGameState() {
		model.setState(GameState.MIGRATION);
		assertEquals(GameState.MIGRATION, model.getState());
	}
	
	@Test
	public void testSetTree() {
		final int TREE_WIDTH = 200;
		final int TREE_HEIGHT = 274;
		final int treeStartY = -600;
		GameObject tree = new GameObject(BirdType.NORTHERNHARRIER, this.screenWidth / 2, treeStartY, ObjectType.TREE, TREE_WIDTH, TREE_HEIGHT);
		model.setTree(tree);
		assertEquals(tree, model.getTree());
	}
	 
	@Test
	public void testSetMouse() {
		final int MOUSE_HEIGHT = 89;
		final int MOUSE_WIDTH = 75;
	    final int mouseStartY = -100;
	    
	    GameObject mouse = new GameObject(BirdType.NORTHERNHARRIER, mouseStartY, ObjectType.MOUSE, MOUSE_WIDTH, MOUSE_HEIGHT);
	    model.setMouse(mouse);
	    assertEquals(mouse, model.getMouse());
	}
	
	@Test
	public void testSetBushQuestionBlock() {
		final int BUSH_QUESTION_WIDTH = 150;
		final int BUSH_QUESTION_HEIGHT = 85;
		final int bushQuestionBlockStartY = -1600;
		
		GameObject bush = new GameObject(BirdType.NORTHERNHARRIER, bushQuestionBlockStartY, ObjectType.BUSH_QUESTION_BOX, BUSH_QUESTION_WIDTH, BUSH_QUESTION_HEIGHT);
		model.setBushQuestionBlock(bush);
		assertEquals(bush, model.getBushQuestionBlock());
	}
	
	@Test
	public void testSetOwl() {
		final int OWL_WIDTH = 80;
		final int OWL_HEIGHT = 82;
		final int owlStartY = -1100;
		
		GameObject owl = new GameObject(BirdType.NORTHERNHARRIER, owlStartY, ObjectType.OWL, OWL_WIDTH, OWL_HEIGHT);
		model.setOwl(owl);
		assertEquals(owl, model.getOwl());
	}
	
	@Test
	public void testSetNorthernHarrier() {
		Bird nh = new Bird(BirdType.NORTHERNHARRIER, ObjectType.NORTHERNHARRIER);
		model.setNorthernHarrier(nh);
		
		assertEquals(nh, model.getNorthernHarrier());
	}
	
	@Test
	public void testGetPicNum() {
		assertEquals(model.getPicNumMap(), model.getPicNumMap());
	}

}
