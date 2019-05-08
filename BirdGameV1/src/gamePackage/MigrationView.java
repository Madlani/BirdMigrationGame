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
	private int birdFrameCount = 22;
	private int picNum = 0;
	private int picNumFish = 0;
	private int healthCount;
	private int health;
	private int picNumMap = 0;
	private int tick = 0;
	
	private Image backgroundImage;
	
	private BufferedImage airplaneImg, cloudQuestionBox, thunderCloud, healthImg, healthIcon;
	private BufferedImage[] miniMap;
	private BufferedImage[] bird_imagesBufferedImage;
	private BufferedImage[] fishFrames;
			
	private double birdX, birdY, planeX, planeY, cloudQuestionX, cloudQuestionY, fishX, fishY, thunderCloudX, thunderCloudY;
	
	private String birdImagePath = "src/images/osprey_frames.png";
	
	private Bird bird;
	private GameObject plane;
	private GameObject thunderCloudObj;
	private GameObject cloudQuestionBoxObj;
	private GameObject food;
	
	private final int BIRD_IMG_SIZE = 150;
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
		ImageIcon grass_1 = new ImageIcon("src/images/beach.png");
		
		airplaneImg = super.createImage("src/images/airplane.png");
		healthImg = super.createImage("src/images/health.png");
		healthIcon = super.createImage("src/images/birdHealth.png");
		cloudQuestionBox = super.createImage("src/images/cloudQuestionMark.png");
		thunderCloud = super.createImage("src/images/thunderCloud.png");
		
		backgroundImage = grass_1.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		
		BufferedImage birdFrames = super.createImage(birdImagePath);
		BufferedImage fishAnimation = super.createImage("src/images/fishFrames.png");
		bird_imagesBufferedImage = new BufferedImage[birdFrameCount];
		fishFrames = new BufferedImage[NUMBER_FISH_FRAMES];
		
		for (int i = 0; i < birdFrameCount; i++) {
			bird_imagesBufferedImage[i] = birdFrames.getSubimage(BIRD_IMG_SIZE * i, TOP_LEFT_IMG_Y, BIRD_IMG_SIZE, BIRD_IMG_SIZE);
		}
		for (int i = 0; i < NUMBER_FISH_FRAMES; i++)
			fishFrames[i] = fishAnimation.getSubimage(FISH_IMG_WIDTH * i, TOP_LEFT_IMG_Y, FISH_IMG_WIDTH, FISH_IMG_HEIGHT);
		
		this.miniMap = new BufferedImage[MINIMAP_SUBIMAGES];
		this.miniMap[0] = super.createImage("src/images/delawareMiniMap1.png");
		this.miniMap[1] = super.createImage("src/images/delawareMiniMap2.png");
		this.miniMap[2] = super.createImage("src/images/delawareMiniMap3.png");
		this.miniMap[3] = super.createImage("src/images/delawareMiniMap4.png");
		this.miniMap[4] = super.createImage("src/images/delawareMiniMap5.png");
		this.miniMap[5] = super.createImage("src/images/delawareMiniMap6.png");
		this.miniMap[6] = super.createImage("src/images/delawareMiniMap7.png");
		this.miniMap[7] = super.createImage("src/images/delawareMiniMap8.png");
		this.miniMap[8] = super.createImage("src/images/delawareMiniMap9.png");
		this.miniMap[9] = super.createImage("src/images/delawareMiniMap10.png");
		this.miniMap[10] = super.createImage("src/images/delawareMiniMap11.png");
		this.miniMap[11] = super.createImage("src/images/delawareMiniMap12.png");
		this.miniMap[12] = super.createImage("src/images/delawareMiniMap13.png");
		
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
		
		g.drawImage(bird_imagesBufferedImage[picNum], (int)birdX, (int)birdY, null);
		
		g.drawImage(this.thunderCloud, (int)thunderCloudX, (int)thunderCloudY, null);
		g.drawImage(this.cloudQuestionBox, (int)cloudQuestionX, (int)cloudQuestionY, null);
		
		g.drawImage(airplaneImg, (int)planeX, (int)planeY, null);
		g.drawImage(fishFrames[picNumFish], (int)fishX, (int)fishY, null);

		g.drawImage(healthImg, HEALTH_IMG_X, HEALTH_IMG_Y, null);

//		-----------------------------------------------------------------------------------------------------------------------------
//		SAVE THIS CODE FOR TESTING PURPOSES - DRAWS THE HIT BOXES ON THE OBJECTS
//		g2.drawImage(map, 0, 0, null);
//		g2.drawRect((int)this.b.getBirdBox().getX(), (int)this.b.getBirdBox().getY(), (int)this.b.getBirdBox().getWidth(), (int)this.b.getBirdBox().getHeight());
//		g2.drawRect((int)this.plane.GameObjectBox.getX(), (int)this.plane.GameObjectBox.getY(), (int)this.plane.GameObjectBox.getWidth(), (int)this.plane.GameObjectBox.getHeight());
//		g2.drawRect((int)this.thunderCloudObj.GameObjectBox.getX(), (int)this.thunderCloudObj.GameObjectBox.getY(), (int)this.thunderCloudObj.GameObjectBox.getWidth(), (int)this.thunderCloudObj.GameObjectBox.getHeight());
//		g2.drawRect((int)this.cloudQuestionBoxObj.GameObjectBox.getX(), (int)this.cloudQuestionBoxObj.GameObjectBox.getY(), (int)this.cloudQuestionBoxObj.GameObjectBox.getWidth(), (int)this.cloudQuestionBoxObj.GameObjectBox.getHeight());
//		g2.drawRect((int)this.food.GameObjectBox.getX(), (int)this.food.GameObjectBox.getY(), (int)this.food.GameObjectBox.getWidth(), (int)this.food.GameObjectBox.getHeight());
//		-----------------------------------------------------------------------------------------------------------------------------
		
		// Generates a new map image at a specified interval of time
		tick = (tick + 1) % MAP_FRAME_COUNT;
		if (tick == 0) {
			picNumMap = (picNumMap + 1) % MINIMAP_SUBIMAGES;
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
		this.plane = list.get(1);
		this.thunderCloudObj = list.get(2);
		this.cloudQuestionBoxObj = list.get(3);
		this.food = list.get(4);
		
		this.birdX = bird.getX();
		this.birdY = bird.getY();
		
		this.planeX = plane.getX();
		this.planeY = plane.getY();
		
		this.cloudQuestionX = cloudQuestionBoxObj.getX();
		this.cloudQuestionY = cloudQuestionBoxObj.getY();
		
		this.thunderCloudX = thunderCloudObj.getX();
		this.thunderCloudY = thunderCloudObj.getY();
		
		this.fishX = food.getX();
		this.fishY = food.getY();
	}
}
	




