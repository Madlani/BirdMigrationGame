package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import gamePackage.BirdType;
import gamePackage.GameObject;
import gamePackage.ObjectType;

class GameObjectTest {
	
	// The number on the end of the game object represents the type of GameObject constructor that was created
	// ex. 1 is the first constructor in the GameObject file
	
	GameObject ospreyGameObject1 = new GameObject(BirdType.OSPREY, 100, ObjectType.FISH, 100, 65, 500, 400);
	GameObject northernHarrierGameObject1 = new GameObject(BirdType.NORTHERNHARRIER, 100, ObjectType.MOUSE, 75, 89, 500, 400);
	
	GameObject ospreyGameObject2 = new GameObject(BirdType.OSPREY, 100, ObjectType.FISH, 100, 65);
	GameObject northernHarrierGameObject2 = new GameObject(BirdType.NORTHERNHARRIER, 100, ObjectType.MOUSE, 75, 89);
	
	GameObject ospreyGameObject3 = new GameObject(BirdType.OSPREY, 300, 300, ObjectType.FISH, 100, 65);
	GameObject northernHarrierGameObject3 = new GameObject(BirdType.NORTHERNHARRIER, 300, 300, ObjectType.MOUSE, 75, 89);
	
	@Test
	public void testSetLocation() {
		int x = 100;
		int y = 100;
		
		// Checking the osprey game objects
		ospreyGameObject1.setLocation(x, y);
		assertEquals(x, ospreyGameObject1.getX());
		assertEquals(y, ospreyGameObject1.getY());
		
		ospreyGameObject2.setLocation(x, y);
		assertEquals(x, ospreyGameObject2.getX());
		assertEquals(y, ospreyGameObject2.getY());
		
		ospreyGameObject3.setLocation(x, y);
		assertEquals(x, ospreyGameObject3.getX());
		assertEquals(y, ospreyGameObject3.getY());
		
		// Checking the northern game objects
		northernHarrierGameObject1.setLocation(x, y);
		assertEquals(x, northernHarrierGameObject1.getX());
		assertEquals(y, northernHarrierGameObject1.getY());
		
		northernHarrierGameObject2.setLocation(x, y);
		assertEquals(x, northernHarrierGameObject2.getX());
		assertEquals(y, northernHarrierGameObject2.getY());
		
		northernHarrierGameObject3.setLocation(x, y);
		assertEquals(x, northernHarrierGameObject3.getX());
		assertEquals(y, northernHarrierGameObject3.getY());
	}
	
	@Test
	public void testGetGameObjectSpeed() {
		assertEquals(10, ospreyGameObject1.getGameObjectSpeed());
		assertEquals(10, northernHarrierGameObject1.getGameObjectSpeed());
	}
	
	@Test
	public void testGetType() {
		assertEquals(ObjectType.FISH, ospreyGameObject1.getType());
		assertEquals(ObjectType.MOUSE, northernHarrierGameObject1.getType());
	}
	
	@Test
	public void testSetSpeed() {
		ospreyGameObject1.setSpeed(15);
		assertEquals(15, ospreyGameObject1.getSpeed());
	}

}
