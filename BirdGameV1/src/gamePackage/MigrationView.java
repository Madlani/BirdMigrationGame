package gamePackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class MigrationView extends View {
	
	private int scaledImageWidth = Model.scaledImageWidth;
	private int scaledImageHeight = Model.scaledImageHeight;
	private int imgVelY = 0;
	private int birdFrameCount = 8;
	private int picNum = 0;
	private int picNumBird = 0;
	private int healthCount;
	private int picNumMap = 0;
	private int tick = 0;
	private int birdTick = 0;
	
	private Image backgroundImage;
	
	private BufferedImage northernHarrierImg, treeImg, mouseImg, bushQuestionBlockImg, owlImg;
	private BufferedImage[] miniMap;
	private BufferedImage[] bird_imagesBufferedImage;
	//private BufferedImage[] fishFrames;
			
	private double birdX, birdY, treeX, treeY, mouseX, mouseY, bushQuestionBlockX, bushQuestionBlockY, owlX, owlY;
	
	private String birdImagePath = "src/images/northernHarrierFrames.png";
	
	private Bird bird;
	private GameObject tree;
	private GameObject mouse;
	private GameObject blockQuestionBlock;
	private GameObject owl;
	
	private final int BIRD_IMG_DELAY = 50;
	private final int BIRD_IMG_WIDTH = 220;
	private final int BIRD_IMG_HEIGHT = 140;
	private final int FISH_IMG_WIDTH = 100;
	private final int FISH_IMG_HEIGHT = 65;
	private final int NUMBER_FISH_FRAMES = 4;
	private final int TOP_LEFT_IMG_Y = 0;
	private final int MINIMAP_SUBIMAGES = 13;
	private final int MAP_X = 0;
	private final int MAP_Y = 0;
	private final int MAP_FRAME_COUNT = 30;
	private final int HEALTH_BIRD_OFFSET = 30;
	private final int HEALTH_IMG_X = scaledImageWidth - 350;
	private final int HEALTH_ICON_X = scaledImageWidth - 300;
	private final int HEALTH_IMG_Y = 20;
	
	public MigrationView() {
		super();
		this.loadImage();
	}
	
	/**
	 * loadImage()
	 * Loads in the images for the objects used for the Migration mini-game. 
	 */
	private void loadImage() {
		ImageIcon grass_1 = new ImageIcon("src/images/grassyField.png");
		
		northernHarrierImg = super.createImage("src/images/northernHarrierFrames.png");
		mouseImg = super.createImage("src/images/mouse.png");
		healthImg = super.createImage("src/images/health.png");
		healthIcon = super.createImage("src/images/birdHealth.png");
		cloudQuestionBox = super.createImage("src/images/cloudQuestionMark.png");
		thunderCloud = super.createImage("src/images/thunderCloud.png");
		
		backgroundImage = grass_1.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		
		BufferedImage birdFrames = super.createImage(birdImagePath);
		BufferedImage fishAnimation = super.createImage("src/images/fishFrames.png");
		bird_imagesBufferedImage = new BufferedImage[birdFrameCount];
		fishFrames = new BufferedImage[NUMBER_FISH_FRAMES];
		
//		for (int i = 0; i < birdFrameCount; i++) {
//			bird_imagesBufferedImage[i] = birdFrames.getSubimage(BIRD_IMG_SIZE * i, TOP_LEFT_IMG_Y, BIRD_IMG_SIZE, BIRD_IMG_SIZE);
//		}
		for (int i = 0; i < NUMBER_FISH_FRAMES; i++)
			fishFrames[i] = fishAnimation.getSubimage(FISH_IMG_WIDTH * i, TOP_LEFT_IMG_Y, FISH_IMG_WIDTH, FISH_IMG_HEIGHT);
		
		this.miniMap = new BufferedImage[MINIMAP_SUBIMAGES];
		this.miniMap[0] = super.createImage("src/images/delawareMiniMap1.png");
		this.miniMap[1] = super.createImage("src/images/delawareMiniMap2.png");
		this.miniMap[2] = super.createImage("src/images/delawareMiniMap3.png");
		this.miniMap[3] = super.createImage("src/images/delawareMiniMap4.png");
		this.miniMap[4] = super.createImage("src/images/delawareMiniMap5.png");
		this.miniMap[5] = super.createImage("src/images/delawareMiniMap7.png");
		this.miniMap[6] = super.createImage("src/images/delawareMiniMap8.png");
		this.miniMap[7] = super.createImage("src/images/delawareMiniMap9.png");
		this.miniMap[8] = super.createImage("src/images/delawareMiniMap10.png");
		this.miniMap[9] = super.createImage("src/images/delawareMiniMap11.png");
		this.miniMap[10] = super.createImage("src/images/delawareMiniMap12.png");
		this.miniMap[11] = super.createImage("src/images/delawareMiniMap13.png");
		this.miniMap[12] = super.createImage("src/images/delawareMiniMap6.png");

			
		bird_imagesBufferedImage = new BufferedImage[birdFrameCount];
		
		for (int i = 0; i < birdFrameCount; i++)
			bird_imagesBufferedImage[i] = birdFrames.getSubimage(BIRD_IMG_WIDTH * i, 0, BIRD_IMG_WIDTH, BIRD_IMG_HEIGHT);
		
		setDoubleBuffered(true); // used to smooth image movement
	}
	
	/**
	 * paintComponent()
	 * This method overrides the View's paintComponent(). It draws all of our images on the screen and sets them
	 * to be the appropriate starting locations that we have defined using constants.
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		g = g.create();
		
		picNum = (picNum + 1) % birdFrameCount;
		imgVelY-=1;
		
		if (imgVelY % scaledImageHeight == 0) {
			imgVelY = 0;
		}
		
		g.drawImage(backgroundImage, 0, (-imgVelY % scaledImageHeight), null); 
		
		// draws image in the window, had to make second image the same as the first for continuity
		g.drawImage(backgroundImage,  0,(-(imgVelY % scaledImageHeight)-scaledImageHeight), null);
		
		g.drawImage(bird_imagesBufferedImage[picNumBird], (int)birdX, (int)birdY, null);
		
		g.drawImage(this.thunderCloud, (int)thunderCloudX, (int)thunderCloudY, null);
		g.drawImage(this.cloudQuestionBox, (int)cloudQuestionX, (int)cloudQuestionY, null);
		
		g.drawImage(mouseImg, (int)mouseX, (int)mouseY, null);

		g.drawImage(healthImg, HEALTH_IMG_X, HEALTH_IMG_Y, null);
		

//		-----------------------------------------------------------------------------------------------------------------------------
//		SAVE THIS CODE FOR TESTING PURPOSES - DRAWS THE HIT BOXES ON THE OBJECTS
//		g.drawImage(backgroundImage, 0, 0, null);
//		g.drawRect((int)this.bird.getBirdBox().getX(), (int)this.bird.getBirdBox().getY(), (int)this.bird.getBirdBox().getWidth(), (int)this.bird.getBirdBox().getHeight());
//		g.drawRect((int)this.mouse.GameObjectBox.getX(), (int)this.mouse.GameObjectBox.getY(), (int)this.mouse.GameObjectBox.getWidth(), (int)this.mouse.GameObjectBox.getHeight());
//		g.drawRect((int)this.thunderCloudObj.GameObjectBox.getX(), (int)this.thunderCloudObj.GameObjectBox.getY(), (int)this.thunderCloudObj.GameObjectBox.getWidth(), (int)this.thunderCloudObj.GameObjectBox.getHeight());
//		g.drawRect((int)this.cloudQuestionBoxObj.GameObjectBox.getX(), (int)this.cloudQuestionBoxObj.GameObjectBox.getY(), (int)this.cloudQuestionBoxObj.GameObjectBox.getWidth(), (int)this.cloudQuestionBoxObj.GameObjectBox.getHeight());
//		g.drawRect((int)this.food.GameObjectBox.getX(), (int)this.food.GameObjectBox.getY(), (int)this.food.GameObjectBox.getWidth(), (int)this.food.GameObjectBox.getHeight());
//		-----------------------------------------------------------------------------------------------------------------------------
		
		// Generates a new map image at a specified interval of time
		tick = (tick + 1) % MAP_FRAME_COUNT;
		birdTick = (birdTick + 1) % 30;
		if (tick == 0) {
			picNumMap = (picNumMap + 1) % MINIMAP_SUBIMAGES;
		}
		if (birdTick == 0) {
			picNumBird = (picNumBird + 1) % birdFrameCount;
		}
		
		g.drawImage(this.miniMap[picNumMap], MAP_X, MAP_Y, null);
		
		for (int i = 0; i < healthCount; i++) {
			g.drawImage(healthIcon, HEALTH_ICON_X + (HEALTH_BIRD_OFFSET * i), HEALTH_IMG_Y, null);
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
		this.tree = list.get(1);
		this.cloudQuestionBoxObj = list.get(2);
		this.mouse = list.get(3);
		this.bushQuestionBlock = list.get(4);
		this.owl = list.get(5);
		
		this.birdX = bird.getX();
		this.birdY = bird.getY();
		
		this.cloudQuestionX = cloudQuestionBoxObj.getX();
		this.cloudQuestionY = cloudQuestionBoxObj.getY();
		
		this.thunderCloudX = thunderCloudObj.getX();
		this.thunderCloudY = thunderCloudObj.getY();
		
		this.mouseX = mouse.getX();
		this.mouseY = mouse.getY();
		this.healthCount = bird.getHealthCount();
	}
}
	




