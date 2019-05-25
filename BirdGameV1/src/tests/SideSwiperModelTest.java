package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import gamePackage.Bird;
import gamePackage.BirdType;
import gamePackage.GameObject;
import gamePackage.GameState;
import gamePackage.Model;
import gamePackage.ObjectType;
import gamePackage.SideSwiperModel;

public class SideSwiperModelTest {

	private final int screenHeight = Model.scaledImageHeight;
	private final int screenWidth = Model.scaledImageWidth;
	
	
	private final int FOX_WIDTH = 150;
	private final int FOX_HEIGHT = 75;
	
	GameObject fox = new GameObject(BirdType.OSPREY, 500, ObjectType.FOX, FOX_WIDTH, FOX_HEIGHT);

	
	SideSwiperModel test = new SideSwiperModel();
	ArrayList<GameObject> gameObjectsForOsprey = new ArrayList<GameObject>(); //Comment out plane, or fish, or question cloud to show coverage for each objecttype
	
	@Test
	public void TestDetectCollisions1() {
		Bird b = new Bird(BirdType.OSPREY, null);
		b.setLocation(0, 0);
		b.setBirdBox(0, 0, 100, 100);
		GameObject plane = new GameObject(BirdType.OSPREY, 0, 0, ObjectType.PLANE, 25, 25);
		plane.setLocation(0, 0);
		
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(b);
		gameObjects.add(plane);

		assertFalse(test.detectCollisions(gameObjects, b, GameState.SIDESWIPER));
	}
	
	@Test
	public void TestDetectCollisions2() {
		Bird b = new Bird(BirdType.OSPREY, null);
		b.setLocation(0, 0);
		b.setBirdBox(0, 0, 100, 100);
		GameObject fish = new GameObject(BirdType.OSPREY, 0, 0, ObjectType.FISH, 25, 25);
		fish.setLocation(0, 0);
		
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(b);
		gameObjects.add(fish);
		
		assertFalse(test.detectCollisions(gameObjects, b, GameState.SIDESWIPER));
	}
	
	@Test
	public void TestDetectCollisions3() {
		Bird b = new Bird(BirdType.OSPREY, null);
		b.setLocation(0, 0);
		b.setBirdBox(0, 0, 100, 100);
		GameObject questionCloud = new GameObject(BirdType.OSPREY, 0, 0, ObjectType.CLOUD_QUESTION_BOX, 25, 25);
		questionCloud.setLocation(0, 0);
		
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(b);
		gameObjects.add(questionCloud);
		
		assertFalse(test.detectCollisions(gameObjects, b, GameState.SIDESWIPER));
	}
	
	@Test
	public void TestDetectCollisions4() {
		Bird b = new Bird(BirdType.NORTHERNHARRIER, null);
		b.setLocation(0, 0);
		b.setBirdBox(0, 0, 100, 100);
		GameObject mouse = new GameObject(BirdType.NORTHERNHARRIER, 0, 0, ObjectType.MOUSE, 25, 25);
		mouse.setLocation(0, 0);
		
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(b);
		gameObjects.add(mouse);
		
		assertFalse(test.detectCollisions(gameObjects, b, GameState.MIGRATION));
	}
	

	@Test
	public void TestDetectCollisions5() {
		Bird b = new Bird(BirdType.NORTHERNHARRIER, null);
		b.setLocation(0, 0);
		b.setBirdBox(0, 0, 100, 100);
		GameObject owl = new GameObject(BirdType.NORTHERNHARRIER, 0, 0, ObjectType.OWL, 25, 25);
		owl.setLocation(0, 0);
		
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(b);
		gameObjects.add(owl);
		
		assertFalse(test.detectCollisions(gameObjects, b, GameState.MIGRATION));
	}
	
	@Test
	public void TestDetectCollisions6() {
		Bird b = new Bird(BirdType.NORTHERNHARRIER, null);
		b.setLocation(0, 0);
		b.setBirdBox(0, 0, 100, 100);
		GameObject bush = new GameObject(BirdType.NORTHERNHARRIER, 0, 0, ObjectType.BUSH_QUESTION_BOX, 25, 25);
		bush.setLocation(0, 0);
		
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(b);
		gameObjects.add(bush);
		
		assertFalse(test.detectCollisions(gameObjects, b, GameState.MIGRATION));
	}
	
	@Test
	public void TestDetectCollisions7() {
		Bird b = new Bird(BirdType.NORTHERNHARRIER, null);
		b.setLocation(0, 0);
		b.setBirdBox(0, 0, 100, 100);
		
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(b);
		
		assertFalse(test.detectCollisions(gameObjects, b, GameState.SIDESWIPER));
	}
	
	//Testing getters and setters
	@Test
	public void TestGetImageHeightAndWidth() {
		 test.setImgHeight(5);
		 test.setImgWidth(6);
		 
		 assertEquals(5, test.getImgHeight());
		 assertEquals(6, test.getImgWidth());
	}
	
	@Test
	public void TestGetDirection() {
		test.setDirection(0);
		assertEquals(0, test.getDirection());
	}
	
	@Test
	public void TestGetHealth() {
		test.setHealth(8);
		assertEquals(8, test.getHealth());
	}
	
	@Test
	public void TestSetLocation() {
		test.setLocation(5, 5);
		assertEquals(5, test.getX());
		assertEquals(5, test.getY());
	}
	
	@Test
	public void TestGetUpdatableGameObjects() {
		assertNotEquals(gameObjectsForOsprey, test.getUpdatableGameObjectsForOsprey());
		assertNotEquals(gameObjectsForOsprey, test.getUpdatableGameObjectsForNorthernHarrier());
	}
	
	@Test
	public void TestChangePauseGameFlag() {
		Boolean flag = test.getPauseGameFlag();
		test.changePauseGameFlag();
		assertNotEquals(flag, test.getPauseGameFlag());
	}
	
	@Test
	public void TestGetIsHit() {
		Boolean flag = test.getIsHit();
		test.setIsHit(flag);
		assertFalse(flag);
		assertFalse(test.getIsHit());
	}
	
	@Test
	public void testUpdateGameObjectLocationAndDirection() {
		
		// Testing when the y-location is >= the screenHeight
		fox.setLocation(fox.getX(), screenHeight);
		test.updateGameObjectLocationAndDirection(fox);
		assertTrue((screenWidth+600)>fox.getX()); //if it puts x val off of the screen
		

	}
	
	@Test 
	public void testupdateLocationAndDirectionForOsprey() {
		
		test.setState(GameState.MIGRATION);
		test.updateLocationAndDirectionForOsprey();
		assertNotEquals(0, test.getTick());
		
		// These cases are irrelevant to the Migration game, but to get complete
		// coverage, it was necessary to include these
		test.setState(GameState.LOSE);
		test.updateLocationAndDirectionForOsprey();

		test.setState(GameState.OSPREYWIN);
		test.updateLocationAndDirectionForOsprey();

		test.setState(GameState.SIDESWIPER);
		test.updateLocationAndDirectionForOsprey();

		test.setState(GameState.START);
		test.updateLocationAndDirectionForOsprey();

		test.setState(GameState.WHACKAMOLE);
		test.updateLocationAndDirectionForOsprey();

		test.setState(GameState.WIN);
		test.updateLocationAndDirectionForOsprey();

		test.setState(GameState.TUTORIAL);
		test.updateLocationAndDirectionForOsprey();
	}
	
	
	
	
}
