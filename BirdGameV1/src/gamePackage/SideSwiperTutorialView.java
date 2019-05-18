package gamePackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class SideSwiperTutorialView extends SideSwiperView {

	public SideSwiperTutorialView() {
		super();
		this.loadImage();
	}
	
	private void loadImage() {
		ImageIcon grassyBackground = new ImageIcon("src/images/fullBackground.png");

		migrationMap = new BufferedImage[MIGRATION_MAP_SUBIMAGES];
		migrationMap[0] = super.createImage("src/images/migrationMiniMap1.png");
		migrationMap[1] = super.createImage("src/images/migrationMiniMap2.png");
		migrationMap[2] = super.createImage("src/images/migrationMiniMap3.png");
		migrationMap[3] = super.createImage("src/images/migrationMiniMap4.png");
		migrationMap[4] = super.createImage("src/images/migrationMiniMap5.png");
		migrationMap[5] = super.createImage("src/images/migrationMiniMap6.png");
		migrationMap[6] = super.createImage("src/images/migrationMiniMap7.png");
		migrationMap[7] = super.createImage("src/images/migrationMiniMap8.png");
		migrationMap[8] = super.createImage("src/images/migrationMiniMap9.png");
        
		airplaneImg = super.createImage("src/images/airplane.png");
		healthImg = super.createImage("src/images/health.png");
		healthIcon = super.createImage("src/images/birdHealth.png");
		cloudQuestionBox = super.createImage("src/images/cloudQuestionMark.png");
		thunderCloud = super.createImage("src/images/thunderCloud.png");
		caution = super.createImage("src/images/caution.png");

		g1 = grassyBackground.getImage().getScaledInstance(scaledImageWidth*4, scaledImageHeight, Image.SCALE_DEFAULT*4);		
		cur = g1;
	

		BufferedImage birdFrames = super.createImage(birdImagePath);
		BufferedImage fishAnimation = super.createImage("src/images/fishFrames.png");
		BufferedImage foxAnimation = super.createImage("src/images/foxFrames.png");
		bird_imagesBufferedImage = new BufferedImage[birdFrameCount];
		fishFrames = new BufferedImage[NUMBER_FISH_FRAMES];
		foxFrames = new BufferedImage[foxFrameCount];
		
		for (int i = 0; i < birdFrameCount; i++)
			bird_imagesBufferedImage[i] = birdFrames.getSubimage(imgWidth * i, 0, imgWidth, BIRD_IMG_SIZE);
		
		for (int i = 0; i < NUMBER_FISH_FRAMES; i++)
			fishFrames[i] = fishAnimation.getSubimage(FISH_IMG_WIDTH * i, 0, FISH_IMG_WIDTH, FISH_IMG_HEIGHT);
		
		for (int i = 0; i < foxFrameCount; i++)
			foxFrames[i] = foxAnimation.getSubimage(FOX_IMG_WIDTH * i, 0, FOX_IMG_WIDTH, FOX_IMG_HEIGHT);
		
		setDoubleBuffered(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g = (Graphics2D) g.create();
		
		
		picNumFish = (short) ((picNumFish + 1) % NUMBER_FISH_FRAMES);
		picNum = (picNum + 1) % birdFrameCount;
		imgVelX-=1;
		
		if (imgVelX % (scaledImageWidth*3) == 0) {
			imgVelX = 0;
			finished = true;
			
		} else {
			finished = false;
		}
		
		foxTick = (foxTick+1) % FOX_FRAME_DELAY;
		
		
		if (foxTick == 0) {
			picNumFox = (short) ((picNumFox + 1) % foxFrameCount);
		}
		
		
		g.drawImage(cur, (imgVelX % (scaledImageWidth*3)), 0, null);

		g.drawImage(bird_imagesBufferedImage[picNum], (int)birdX, (int)birdY, null);
		System.out.println("birdX and birdY: " + birdX + ", " + birdY);
		
		//g.drawImage(this.thunderCloud, (int)thunderCloudX, (int)thunderCloudY, null);
		//g.drawImage(this.cloudQuestionBox, (int)cloudQuestionX, (int)cloudQuestionY, null);

		//g.drawImage(airplaneImg, (int)planeX, (int)planeY, null);
		//g.drawImage(fishFrames[picNumFish], (int)fishX, (int)fishY, null);
		
		g.drawImage(healthImg, HEALTH_IMG_X, HEALTH_IMG_Y, null);
		
		//g.drawImage(foxFrames[picNumFox], (int)foxX, (int)foxY, null);
		
		
//		if (this.bird.getHealthCount() <= 2) {
//			g.drawImage(caution, HEALTH_IMG_X - 40, HEALTH_IMG_Y, null);
//		}
		
		
//		//-----------------------------------------------------------------------------------------------------------------------------
		//SAVE THIS CODE FOR TESTING PURPOSES - DRAWS THE HIT BOXES ON THE OBJECTS
//		g.drawRect((int)this.bird.getBirdBox().getX(), (int)this.bird.getBirdBox().getY(), (int)this.bird.getBirdBox().getWidth(), (int)this.bird.getBirdBox().getHeight());
//		g.drawRect((int)this.plane.GameObjectBox.getX(), (int)this.plane.GameObjectBox.getY(), (int)this.plane.GameObjectBox.getWidth(), (int)this.plane.GameObjectBox.getHeight());
//		g.drawRect((int)this.thunderCloudObj.GameObjectBox.getX(), (int)this.thunderCloudObj.GameObjectBox.getY(), (int)this.thunderCloudObj.GameObjectBox.getWidth(), (int)this.thunderCloudObj.GameObjectBox.getHeight());
//		g.drawRect((int)this.cloudQuestionBoxObj.GameObjectBox.getX(), (int)this.cloudQuestionBoxObj.GameObjectBox.getY(), (int)this.cloudQuestionBoxObj.GameObjectBox.getWidth(), (int)this.cloudQuestionBoxObj.GameObjectBox.getHeight());
//		g.drawRect((int)this.food.GameObjectBox.getX(), (int)this.food.GameObjectBox.getY(), (int)this.food.GameObjectBox.getWidth(), (int)this.food.GameObjectBox.getHeight());
//		g.drawRect((int)this.fox.GameObjectBox.getX(), (int)this.fox.GameObjectBox.getY(), (int)this.fox.GameObjectBox.getWidth(), (int)this.fox.GameObjectBox.getHeight());

//		//-----------------------------------------------------------------------------------------------------------------------------
		
		//g.drawImage(migrationMap[picNumMap],MAP_X, MAP_Y, null);
		for (int i = 0; i < healthCount; i++) {
			g.drawImage(healthIcon, HEALTH_ICON_X + (HEALTH_BIRD_OFFSET * i), HEALTH_IMG_Y, null);
		}
		
		if(super.isTimeForRectangle == true) {
			super.drawRedRectangle(g);
			super.setTimeForRectangle(false);
		}
	}
	
	@Override
	public void update(ArrayList<GameObject> list) {
		
		this.bird = (Bird) list.get(0);
		this.plane = list.get(1);
		this.thunderCloudObj = list.get(2);
		this.cloudQuestionBoxObj = list.get(3);
		this.fish = list.get(4);
		this.fox = list.get(5);
		
		this.birdX = bird.getX();
		this.birdY = bird.getY();
		
		this.planeX = plane.getX();
		this.planeY = plane.getY();
		
		this.cloudQuestionX = cloudQuestionBoxObj.getX();
		this.cloudQuestionY = cloudQuestionBoxObj.getY();
		
		this.thunderCloudX = thunderCloudObj.getX();
		this.thunderCloudY = thunderCloudObj.getY();
		
		this.fishX = fish.getX();
		this.fishY = fish.getY();
		
		this.foxX = fox.getX();
		this.foxY = fox.getY();

		this.healthCount = bird.getHealthCount();
	}
		
	public void setPicNumMap(int p) {
		this.picNumMap = p;
	}
}
