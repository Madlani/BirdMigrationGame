package gamePackage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class SideSwiperView extends View implements Serializable {
	
	protected Image g1;
	protected Image g2;
	BufferedImage[] migrationMap;
	
	private boolean isHit = false;

	public int imgVelX = 0;
	protected int scaledImageWidth = Model.scaledImageWidth;
	protected int scaledImageHeight = Model.scaledImageHeight;
	
	protected BufferedImage airplaneImg;
	protected BufferedImage healthImg;
	protected BufferedImage healthIcon;
	protected BufferedImage cloudQuestionBox;
	protected BufferedImage thunderCloud;
	protected BufferedImage caution;
			
	double foxX, foxY, birdX, birdY, planeX, planeY, cloudQuestionX, cloudQuestionY, fishX, fishY, thunderCloudX, thunderCloudY;
	
	protected int imgWidth = 150;
	protected String birdImagePath = "src/images/osprey_frames.png";
	protected int birdFrameCount = 22;
	protected int foxFrameCount = 7;
	protected BufferedImage[] bird_imagesBufferedImage;
	protected BufferedImage[] fishFrames;
	protected BufferedImage[] foxFrames;
	protected int picNum = 0;
	protected short picNumFish = 0;
	protected short picNumFox = 0;
	protected int picNumMap = 0;
	protected int foxTick = 0;
	protected int healthCount;
	
	protected Bird bird;
	protected GameObject plane;
	protected GameObject thunderCloudObj;
	protected GameObject cloudQuestionBoxObj;
	protected GameObject fish;
	protected GameObject fox;
	
	protected final int MIGRATION_MAP_SUBIMAGES = 9;
	protected final int BIRD_IMG_SIZE = 150;
	protected final int FISH_IMG_WIDTH = 100;
	protected final int FISH_IMG_HEIGHT = 65;
	protected final int FOX_IMG_WIDTH = 300;
	protected final int FOX_IMG_HEIGHT = 150;
	protected final int FOX_FRAME_DELAY = 5;
	protected final int NUMBER_FISH_FRAMES = 4;
	protected final int MAP_FRAME_COUNT = 400;
	protected final int HEALTH_BIRD_OFFSET = 30;
	protected final int HEALTH_IMG_X = scaledImageWidth/2 - 50;
	protected final int HEALTH_ICON_X = scaledImageWidth/2;
	protected final int HEALTH_IMG_Y = 20;
	protected final int MAP_X = 0;
	protected final int MAP_Y = 0;
	protected boolean finished = false;
	
	protected BufferedImage warning;
	protected BufferedImage checkMark;
	protected BufferedImage downKeyFlash;
	protected BufferedImage upKeyFlash;
	protected GameState state = GameState.TUTORIAL;

	public SideSwiperView() {
		super();
		this.loadImage();
	}
	
	/**
	 * loadImage()
	 * Loads in the images for the objects used for the SideSwiper mini-game. 
	 */
	private void loadImage() {
		ImageIcon grassyBackground = new ImageIcon("src/images/fullBackground.png");
        ImageIcon background = new ImageIcon("src/images/grass_2.png");
        migrationMap = new BufferedImage[MIGRATION_MAP_SUBIMAGES];
        migrationMap[0] = super.createImage("src/images/migrationMap1.png");
        migrationMap[1] =  super.createImage("src/images/migrationMap2.png");
        migrationMap[2] =  super.createImage("src/images/migrationMap3.png");
        migrationMap[3] =  super.createImage("src/images/migrationMap4.png");
        migrationMap[4] =  super.createImage("src/images/migrationMap5.png");
        migrationMap[5] =  super.createImage("src/images/migrationMap6.png");
        migrationMap[6] = super.createImage("src/images/migrationMap7.png");
        migrationMap[7] =  super.createImage("src/images/migrationMap8.png");
        migrationMap[8] = super.createImage("src/images/migrationMap9.png");
        
		airplaneImg = super.createImage("src/images/airplane.png");
		healthImg = super.createImage("src/images/health.png");
		healthIcon = super.createImage("src/images/birdHealth.png");
		cloudQuestionBox = super.createImage("src/images/cloudQuestionMark.png");
		thunderCloud = super.createImage("src/images/thunderCloud.png");
		caution = super.createImage("src/images/caution.png");
		
		
		g1 = grassyBackground.getImage().getScaledInstance(scaledImageWidth*3, scaledImageHeight, Image.SCALE_DEFAULT*3);	
		g2 = background.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		
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
		
		this.warning = super.createImage("src/images/warning.png");
		this.checkMark = super.createImage("src/images/checkmark.png");
		this.downKeyFlash = super.createImage("src/images/downKeyFlash.png");
		this.upKeyFlash = super.createImage("src/images/upKeyFlash.png");
		
		setDoubleBuffered(true);
	}
	
	/**
	 * paintComponent()
	 * This method overrides the View's paintComponent(). It draws all of our images on the screen and sets them
	 * to be the appropriate starting locations that we have defined using constants.
	 */
	
	int flashCount = 0;
	@Override
	public void paintComponent(Graphics g) {		
		
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

//		if (this.state == GameState.TUTORIAL) {
//			g.drawImage(g2, (imgVelX % (scaledImageWidth)), 0, null);
//			g.drawImage(g2, (imgVelX % (scaledImageWidth)) + scaledImageWidth, 0, null);
//		} else {
		g.drawImage(g2, (imgVelX & scaledImageWidth), -5, null);
		g.drawImage(g1, (imgVelX % (scaledImageWidth * 4)), -5, null);
		g.drawImage(g2, (imgVelX % (scaledImageWidth * 5) + (scaledImageWidth * 3)), -5, null);

		g.drawImage(bird_imagesBufferedImage[picNum], (int)birdX, (int)birdY, null);
		
		g.drawImage(this.thunderCloud, (int)thunderCloudX, (int)thunderCloudY, null);
		g.drawImage(this.cloudQuestionBox, (int)cloudQuestionX, (int)cloudQuestionY, null);

		g.drawImage(airplaneImg, (int)planeX, (int)planeY, null);
		g.drawImage(fishFrames[picNumFish], (int)fishX, (int)fishY, null);
		
		g.drawImage(healthImg, HEALTH_IMG_X, HEALTH_IMG_Y, null);
		
		g.drawImage(foxFrames[picNumFox], (int)foxX, (int)foxY, null);
		
		
		if (this.state == GameState.TUTORIAL) {
			this.flashCount= (this.flashCount+1) % 30;
			if (this.flashCount < 15) {
				g.drawImage(this.warning, (int)thunderCloudX, (int)thunderCloudY, null);
				g.drawImage(this.warning, (int)planeX, (int)planeY, null);
				g.drawImage(this.warning, (int)foxX, (int)foxY, null);
				g.drawImage(this.checkMark, (int)fishX, (int)fishY, null);
				g.drawImage(this.checkMark, (int)cloudQuestionX, (int)cloudQuestionY, null);
				g.drawImage(this.upKeyFlash, this.scaledImageWidth/2 - 175, this.scaledImageHeight - 250, null);
			} else {
				g.drawImage(this.downKeyFlash, this.scaledImageWidth/2 - 175, this.scaledImageHeight - 250, null);
			}
		} else {
			g.drawImage(migrationMap[picNumMap], MAP_X, MAP_Y, null);
		}
		
		
		if (this.bird.getHealthCount() <= 2) {
			g.drawImage(caution, HEALTH_IMG_X - 40, HEALTH_IMG_Y, null);
		}
		
		
//		//-----------------------------------------------------------------------------------------------------------------------------
		//SAVE THIS CODE FOR TESTING PURPOSES - DRAWS THE HIT BOXES ON THE OBJECTS
//		g.drawRect((int)this.bird.getBirdBox().getX(), (int)this.bird.getBirdBox().getY(), (int)this.bird.getBirdBox().getWidth(), (int)this.bird.getBirdBox().getHeight());
//		g.drawRect((int)this.plane.GameObjectBox.getX(), (int)this.plane.GameObjectBox.getY(), (int)this.plane.GameObjectBox.getWidth(), (int)this.plane.GameObjectBox.getHeight());
//		g.drawRect((int)this.thunderCloudObj.GameObjectBox.getX(), (int)this.thunderCloudObj.GameObjectBox.getY(), (int)this.thunderCloudObj.GameObjectBox.getWidth(), (int)this.thunderCloudObj.GameObjectBox.getHeight());
//		g.drawRect((int)this.cloudQuestionBoxObj.GameObjectBox.getX(), (int)this.cloudQuestionBoxObj.GameObjectBox.getY(), (int)this.cloudQuestionBoxObj.GameObjectBox.getWidth(), (int)this.cloudQuestionBoxObj.GameObjectBox.getHeight());
//		//g.drawRect((int)this.food.GameObjectBox.getX(), (int)this.food.GameObjectBox.getY(), (int)this.food.GameObjectBox.getWidth(), (int)this.food.GameObjectBox.getHeight());
//		g.drawRect((int)this.fox.GameObjectBox.getX(), (int)this.fox.GameObjectBox.getY(), (int)this.fox.GameObjectBox.getWidth(), (int)this.fox.GameObjectBox.getHeight());

//		//-----------------------------------------------------------------------------------------------------------------------------
		
		
		for (int i = 0; i < healthCount; i++) {
			g.drawImage(healthIcon, HEALTH_ICON_X + (HEALTH_BIRD_OFFSET * i), HEALTH_IMG_Y, null);
		}
		
		if(super.isTimeForRectangle == true) {
			super.drawRedRectangle(g);
			super.setTimeForRectangle(false);
		}
	}
	
	/**
	 * update()
	 * Updates the x-position and y-position of the game objects using getters.
	 * This is method is called from the Controller.
	 */
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
	
	
	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}
	
	public void setPicNumMap(int p) {
		this.picNumMap = p;
	}
	
	public void setState(GameState s) {
		this.state = s;
	}
}