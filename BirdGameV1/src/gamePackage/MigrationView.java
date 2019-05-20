package gamePackage;

import java.awt.Graphics;
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
	private int picNumOwl = 0;
	private int healthCount;
	private int picNumMap = 0;
	private int tick = 0;
	private int birdTick = 0;
	private int owlTick = 0;
	
	private Image backgroundImage;
	
	private BufferedImage treeImg, mouseImg, bushQuestionBlockImg, healthImg, healthIcon;
	private BufferedImage[] miniMap;
	private BufferedImage[] bird_imagesBufferedImage;
	private BufferedImage[] owlBufferedImageFrames;

			
	private double birdX, birdY, treeX, treeY, mouseX, mouseY, bushQuestionBlockX, bushQuestionBlockY, owlX, owlY;
	
	private String birdImagePath = "src/images/northernHarrierFrames.png";
	private String owlImagePath = "src/images/owlFrontFrames.png";
	
	private Bird bird;
	private GameObject tree;
	private GameObject mouse;
	private GameObject bushQuestionBlock;
	private GameObject owl;
	
	private final int OWL_IMG_DELAY = 10;
	private final int OWL_IMG_WIDTH = 80;
	private final int OWL_IMG_HEIGHT = 82;
	private final int OWL_FRAME_COUNT = 8;
	
	private final int BIRD_IMG_DELAY = 50;
	private final int BIRD_IMG_WIDTH = 220;
	private final int BIRD_IMG_HEIGHT = 140;

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
		
		treeImg = super.createImage("src/images/treeImg.png");
		mouseImg = super.createImage("src/images/mouse.png");
		bushQuestionBlockImg = super.createImage("src/images/bushQuestionBlock.png");
		healthImg = super.createImage("src/images/health.png");
		healthIcon = super.createImage("src/images/birdHealth.png");
		
		backgroundImage = grass_1.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		
		BufferedImage birdFrames = super.createImage(birdImagePath);
		bird_imagesBufferedImage = new BufferedImage[birdFrameCount];
		owlBufferedImageFrames = new BufferedImage[OWL_FRAME_COUNT];
		BufferedImage owlFrames = super.createImage(owlImagePath);
		
		for (int i = 0; i < birdFrameCount; i++) {
			bird_imagesBufferedImage[i] = birdFrames.getSubimage(BIRD_IMG_WIDTH * i, TOP_LEFT_IMG_Y, BIRD_IMG_WIDTH, BIRD_IMG_HEIGHT);
		}
	
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
		
		for(int i = 0; i < OWL_FRAME_COUNT; i++) {
			owlBufferedImageFrames[i] = owlFrames.getSubimage(OWL_IMG_WIDTH * i, 0, OWL_IMG_WIDTH, OWL_IMG_HEIGHT);
		}
		
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
		g.drawImage(owlBufferedImageFrames[picNumOwl], (int)owlX, (int)owlY, null);
		//System.out.println("bird view y: " + birdY);
		
		g.drawImage(treeImg, (int)treeX, (int)treeY, null);
		g.drawImage(bushQuestionBlockImg, (int)bushQuestionBlockX, (int)bushQuestionBlockY, null);
		
		g.drawImage(mouseImg, (int)mouseX, (int)mouseY, null);

		g.drawImage(healthImg, HEALTH_IMG_X, HEALTH_IMG_Y, null);
		
		//g.drawImage(owlImg, (int)owlX, (int)owlY, null);
		

//		-----------------------------------------------------------------------------------------------------------------------------
//		SAVE THIS CODE FOR TESTING PURPOSES - DRAWS THE HIT BOXES ON THE OBJECTS
//		g.drawImage(backgroundImage, 0, 0, null);
//		g.drawRect((int)this.bird.getBirdBox().getX(), (int)this.bird.getBirdBox().getY(), (int)this.bird.getBirdBox().getWidth(), (int)this.bird.getBirdBox().getHeight());
//		g.drawRect((int)this.mouse.GameObjectBox.getX(), (int)this.mouse.GameObjectBox.getY(), (int)this.mouse.GameObjectBox.getWidth(), (int)this.mouse.GameObjectBox.getHeight());
//		g.drawRect((int)this.tree.GameObjectBox.getX(), (int)this.tree.GameObjectBox.getY(), (int)this.tree.GameObjectBox.getWidth(), (int)this.tree.GameObjectBox.getHeight());
//		g.drawRect((int)this.owl.GameObjectBox.getX(), (int)this.owl.GameObjectBox.getY(), (int)this.owl.GameObjectBox.getWidth(), (int)this.owl.GameObjectBox.getHeight());
//		g.drawRect((int)this.bushQuestionBlock.GameObjectBox.getX(), (int)this.bushQuestionBlock.GameObjectBox.getY(), (int)this.bushQuestionBlock.GameObjectBox.getWidth(), (int)this.bushQuestionBlock.GameObjectBox.getHeight());
//		-----------------------------------------------------------------------------------------------------------------------------
		
		// Generates a new map image at a specified interval of time
		//tick = (tick + 1) % MAP_FRAME_COUNT;
		birdTick = (birdTick + 1) % BIRD_IMG_DELAY;
		owlTick = (owlTick + 1) % OWL_IMG_DELAY;
		
		if (tick == 0) {
			picNumMap = (picNumMap + 1) % MINIMAP_SUBIMAGES;
		}
		
		if (birdTick == 0) {
			picNumBird = (picNumBird + 1) % birdFrameCount;
		}
		
		if (owlTick == 0) {
			picNumOwl = (picNumOwl + 1) % OWL_FRAME_COUNT;
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
		this.mouse = list.get(2);
		this.bushQuestionBlock = list.get(3);
		this.owl = list.get(4);
		
		this.birdX = bird.getX();
		this.birdY = bird.getY();
		
		this.bushQuestionBlockX = bushQuestionBlock.getX();
		this.bushQuestionBlockY = bushQuestionBlock.getY();
		
		this.owlX = owl.getX();
		this.owlY = owl.getY();
		
		this.mouseX = mouse.getX();
		this.mouseY = mouse.getY();
		
		this.treeX = tree.getX();
		this.treeY = tree.getY();
		
		this.healthCount = bird.getHealthCount();
	}
	
	public void setPicNumMap(int m) {
		this.picNumMap = m;
	}
}
	




