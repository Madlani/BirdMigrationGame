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

	Bird b = new Bird(BirdType.OSPREY, null);
	SideSwiperModel test = new SideSwiperModel();
	ArrayList<GameObject> gameObjectsForOsprey = new ArrayList<GameObject>(); //Comment out plane, or fish, or question cloud to show coverage for each objecttype
	@Test
	public void TestDetectCollisions() {
		
		
		
		GameObject plane = new GameObject(null, 0, ObjectType.PLANE, 25, 25);
		GameObject fish = new GameObject(null, 0, ObjectType.FISH, 25, 25);
		GameObject questionCloud = new GameObject(null, 0, ObjectType.CLOUD_QUESTION_BOX, 25, 25);
		b.setBirdBox(10, 10, 100, 100);

		b.setLocation(10, 10);
		plane.setLocation(10, 10);
		fish.setLocation(20,  20);
		questionCloud.setLocation(30, 30);
		
	 
		gameObjectsForOsprey.add(plane);
		gameObjectsForOsprey.add(fish);
		gameObjectsForOsprey.add(questionCloud);
		
		assertFalse(test.detectCollisions(gameObjectsForOsprey, b, GameState.SIDESWIPER));
	
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
