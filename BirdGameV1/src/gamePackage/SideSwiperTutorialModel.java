package gamePackage;

import java.util.ArrayList;

public class SideSwiperTutorialModel extends SideSwiperModel {
	
	public SideSwiperTutorialModel() {
		super();
//		this.osprey = new Bird(BirdType.OSPREY, ObjectType.OSPREY);
//    	this.airplane = new GameObject(BirdType.OSPREY, screenWidth + airplaneStartX, ObjectType.PLANE, PLANEBOX_WIDTH, PLANEBOX_HEIGHT, (screenHeight / 3) * 2, 0);
//    	this.fish = new GameObject(BirdType.OSPREY, screenWidth + fishStartX, ObjectType.FISH, FISHBOX_WIDTH, FISHBOX_HEIGHT, screenHeight, (screenHeight / 3) * 2);
//    	this.thunderCloud = new GameObject(BirdType.OSPREY, screenWidth + thunderCloudStartX, ObjectType.THUNDER_CLOUD, THUNDERCLOUD_WIDTH, THUNDERCLOUD_HEIGHT, (screenHeight / 3) * 2, 0);
//    	this.cloudQuestionBlock = new GameObject(BirdType.OSPREY, screenWidth + questionBlockStartX, ObjectType.CLOUD_QUESTION_BOX, QUESTIONCLOUD_WIDTH, QUESTIONCLOUD_HEIGHT, (screenHeight / 3) * 2, 0);
//    	this.fox = new GameObject(BirdType.OSPREY, screenWidth + foxStartX, ObjectType.FOX, FOX_WIDTH, FOX_HEIGHT, screenHeight, (screenHeight / 3) * 2);
//    	
//    	this.osprey.setLocation(ospreyStartingX, ospreyStartingY);
//    	
//    	this.gameObjectsForOsprey = new ArrayList<>();
//    	this.gameObjectsForOsprey.add(osprey);
//    	this.gameObjectsForOsprey.add(airplane);
//    	this.gameObjectsForOsprey.add(thunderCloud);
//    	this.gameObjectsForOsprey.add(cloudQuestionBlock);
//    	this.gameObjectsForOsprey.add(fish);
//    	this.gameObjectsForOsprey.add(fox);
	}
	
	@Override
	public void updateGameObjectLocationAndDirection(GameObject o) {
		if(o.getX() <= -o.GameObjectBox.width) {
			resetGameObjectLocation(o);
		} else {
			o.setLocation(o.getX() - o.getGameObjectSpeed(), o.getY());
		}
	}
	
	
//	public boolean updateLocationAndDirectionForOsprey() {
//		tick = (tick+1) % MAP_FRAME_COUNT;
//		if (tick == 0) {
//			picNumMap = (picNumMap + 1) % MIGRATION_MAP_SUBIMAGES;
//		}
//
//		switch (osprey.getFlyState()) {
//		case UP:
//			if (osprey.getY() > 0) {
//				osprey.moveUp();
//			}
//			break;
//		case DOWN:
//			if (osprey.getY() < screenHeight - OSPREY_HEIGHT) {
//				osprey.moveDown();
//			}
//			break;
//		default:
//			break;
//		}
//
//		this.osprey.setLocation(this.osprey.getX(), this.osprey.getY());
//		this.osprey.birdBox.setLocation((int)this.osprey.getX(), (int)this.osprey.getY());
//		System.out.println("tutorial model X and Y: " + this.osprey.getX() + ", " + this.osprey.getY());
//		
//    	this.airplane.setLocation(this.airplane.getX(), this.airplane.getY());
//    	this.airplane.GameObjectBox.setLocation((int)this.airplane.getX(), (int)this.airplane.getY());
//    	
//    	this.thunderCloud.setLocation(this.thunderCloud.getX(), this.thunderCloud.getY());
//    	this.thunderCloud.GameObjectBox.setLocation((int)this.thunderCloud.getX(), (int)this.thunderCloud.getY());
//    	
//    	this.cloudQuestionBlock.setLocation(this.cloudQuestionBlock.getX(), this.cloudQuestionBlock.getY());
//    	this.cloudQuestionBlock.GameObjectBox.setLocation((int)this.cloudQuestionBlock.getX(), (int)this.cloudQuestionBlock.getY());
//    	
//    	this.fish.setLocation(this.fish.getX(), this.fish.getY());
//    	this.fish.GameObjectBox.setLocation((int)this.fish.getX(), (int)this.fish.getY());
//    	
//    	this.fox.setLocation(this.fox.getX(), this.fox.getY());
//    	this.fox.GameObjectBox.setLocation((int)this.fox.getX(), (int)this.fox.getY());
//    	
//    	updateGameObjectLocationAndDirection(airplane);
//    	updateGameObjectLocationAndDirection(fish);
//    	updateGameObjectLocationAndDirection(thunderCloud);
//    	updateGameObjectLocationAndDirection(cloudQuestionBlock);
//    	updateGameObjectLocationAndDirection(fox);
//    	
//    	return detectCollisions(this.gameObjectsForOsprey, this.osprey);
//	}
	
	
	/**
	 * resetGameObjectLocation()
	 * Reset's the GameObject at a random y-height. This method is called from 
	 * updateGameObjectLocationAndDirection() in this class.
	 */
	@Override
	public void resetGameObjectLocation(GameObject o) {
		int rand;
		switch(o.getType()) {
		case CLOUD_QUESTION_BOX:
		case PLANE:
		case THUNDER_CLOUD:
			rand = (int)(Math.random() * (this.thirdOfTheScreenY - 80));
			o.setLocation(scaledImageWidth + offset, rand);
			break;
			
		case FISH:
		case FOX:
			rand = (int)(Math.random() * ((this.screenHeight - 100) - this.thirdOfTheScreenY)) + this.thirdOfTheScreenY;
			o.setLocation(scaledImageWidth + offset, rand);
			break;		
			
		default:
			break;
		}
	}

}
