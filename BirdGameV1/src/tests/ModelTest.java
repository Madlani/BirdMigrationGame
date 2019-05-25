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

public class ModelTest {

	
	SideSwiperModel test = new SideSwiperModel();
	ArrayList<GameObject> gameObjectsForOsprey = new ArrayList<GameObject>(); //Comment out plane, or fish, or question cloud to show coverage for each objecttype
	
	@Test
	public void TestDetectCollisions() {
		
		
		Bird b1 = new Bird(BirdType.OSPREY, null);
		Bird b2 = new Bird(BirdType.OSPREY, null);
		Bird b3 = new Bird(BirdType.OSPREY, null);
		GameObject plane = new GameObject(BirdType.OSPREY, 0, 0, ObjectType.PLANE, 25, 25);
		GameObject fish = new GameObject(BirdType.OSPREY, 0, 0, ObjectType.FISH, 25, 25);
		GameObject questionCloud = new GameObject(BirdType.OSPREY, 0, 0, ObjectType.CLOUD_QUESTION_BOX, 25, 25);
		GameObject mouse = new GameObject(BirdType.NORTHERNHARRIER, 0, 0, ObjectType.MOUSE, 25, 25);
		GameObject thunderCloud = new GameObject(BirdType.OSPREY, 0, 0, ObjectType.THUNDER_CLOUD, 25, 25);
		GameObject bush = new GameObject(BirdType.NORTHERNHARRIER, 0, 0, ObjectType.BUSH_QUESTION_BOX, 25, 25);
		
		b1.setLocation(0, 0);
		b2.setLocation(0, 0);
		b3.setLocation(0, 0);
		plane.setLocation(0, 0);
		fish.setLocation(0, 0);
		questionCloud.setLocation(0, 0);
		mouse.setLocation(0, 0);
		thunderCloud.setLocation(0, 0);
		bush.setLocation(0, 0);
		
		b1.setBirdBox(0, 0, 100, 100);
		b2.setBirdBox(0, 0, 100, 100);
		b3.setBirdBox(0, 0, 100, 100);
		
		
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.add(b1);
		gameObjects.add(plane);
		
		ArrayList<GameObject> gameObjects2= new ArrayList<GameObject>();
		gameObjects2.add(b2);
		gameObjects2.add(fish);
		
		ArrayList<GameObject> gameObjects3 = new ArrayList<GameObject>();
		gameObjects3.add(b3);
		gameObjects3.add(questionCloud);
		
		ArrayList<GameObject> gameObjects4 = new ArrayList<GameObject>();
		gameObjects4.add(b1);
		gameObjects4.add(mouse);
		
		ArrayList<GameObject> gameObjects5 = new ArrayList<GameObject>();
		gameObjects5.add(b1);
		gameObjects5.add(thunderCloud);
		
		ArrayList<GameObject> gameObjects6 = new ArrayList<GameObject>();
		gameObjects6.add(b1);
		gameObjects6.add(bush);
		
		assertFalse(test.detectCollisions(gameObjects, b1, GameState.SIDESWIPER));
		assertFalse(test.detectCollisions(gameObjects2, b2, GameState.SIDESWIPER));
		assertFalse(test.detectCollisions(gameObjects3, b3, GameState.SIDESWIPER));
		assertFalse(test.detectCollisions(gameObjects4, b1, GameState.SIDESWIPER));
		assertFalse(test.detectCollisions(gameObjects5, b2, GameState.SIDESWIPER));
		assertFalse(test.detectCollisions(gameObjects6, b3, GameState.SIDESWIPER));
	
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
}
