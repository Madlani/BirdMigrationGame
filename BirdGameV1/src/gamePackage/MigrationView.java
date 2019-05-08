package gamePackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class MigrationView extends View {
	private Image g1, map;
	public int imgVelY = 0;
	private int scaledImageWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int scaledImageHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	private BufferedImage airplaneImg;
	private BufferedImage cloudQuestionBox;
	private BufferedImage thunderCloud;
	private BufferedImage fishImg;
	private BufferedImage healthImg;
	private BufferedImage healthIcon;
			
	double birdX, birdY, planeX, planeY, cloudQuestionX, cloudQuestionY, fishX, fishY, thunderCloudX, thunderCloudY;
	
	private int imgWidth = 150;
	private String birdImagePath = "src/images/osprey_frames.png";
	private int birdFrameCount = 22;
	private BufferedImage[] bird_imagesBufferedImage;
	private BufferedImage[] fishFrames;
	private int picNum = 0;
	private int picNumFish = 0;
	
	private int health;
	private int healthCount;
	
	private Bird b;
	private GameObject plane;
	private GameObject thunderCloudObj;
	private GameObject cloudQuestionBoxObj;
	private GameObject food;
	
	private BufferedImage[] miniMap;
	
	public MigrationView() {
		super();
		this.loadImage();

	}

	//Shows the GameObjects that the bird must avoid
	public void displayGameObjects() 
	{
		
	}
	
	//Draws the background for the Migration minigame
	@Override
	public void drawBackground() 
	{
		
	}
	
	private void loadImage() {
		ImageIcon grass_1 = new ImageIcon("src/images/beach.png");
		//ImageIcon miniMap = new ImageIcon("src/images/minimap.png");
		airplaneImg = super.createImage("src/images/airplane.png");
		healthImg = super.createImage("src/images/health.png");
		healthIcon = super.createImage("src/images/birdHealth.png");
		cloudQuestionBox = super.createImage("src/images/cloudQuestionMark.png");
		thunderCloud = super.createImage("src/images/thunderCloud.png");
		
		g1 = grass_1.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		//map = miniMap.getImage().getScaledInstance(scaledImageWidth/4, scaledImageHeight, Image.SCALE_DEFAULT);
		
		BufferedImage birdFrames = super.createImage(birdImagePath);
		BufferedImage fishAnimation = super.createImage("src/images/fishFrames.png");
		bird_imagesBufferedImage = new BufferedImage[birdFrameCount];
		fishFrames = new BufferedImage[4];
		
		for (int i = 0; i < birdFrameCount; i++) {
			bird_imagesBufferedImage[i] = birdFrames.getSubimage(imgWidth * i, 0, imgWidth, 150);
		}
		for (int i = 0; i < 4; i++) //100 = fish image pixel width
			fishFrames[i] = fishAnimation.getSubimage(100 * i, 0, 100, 65);
		
		this.miniMap = new BufferedImage[13];
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
		
		setDoubleBuffered(true);
	}
	
	private int picNumMap = 0;
	private int tick = 0;
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		
		picNum = (picNum + 1) % birdFrameCount;
		imgVelY-=1;
		
		
		if (imgVelY % scaledImageHeight == 0) {
			imgVelY = 0;
		}
		
		g2.drawImage(g1, 0, (-imgVelY % scaledImageHeight), null); // draws image in the window
		g2.drawImage(g1,  0,(-(imgVelY % scaledImageHeight)-scaledImageHeight), null); // draws image in the window, had to make second image the same as the first for continuity
		
		g2.drawImage(bird_imagesBufferedImage[picNum], (int)birdX, (int)birdY, null);
		
		g2.drawImage(this.thunderCloud, (int)thunderCloudX, (int)thunderCloudY, null);
		g2.drawImage(this.cloudQuestionBox, (int)cloudQuestionX, (int)cloudQuestionY, null);
		
		
		g2.drawImage(airplaneImg, (int)planeX, (int)planeY, null);
		g2.drawImage(fishFrames[picNumFish], (int)fishX, (int)fishY, null);

		g2.drawImage(healthImg, scaledImageWidth - 390, 20, null);
		//g2.drawImage(map, 0, 0, null);
//		g2.drawRect((int)this.b.getBirdBox().getX(), (int)this.b.getBirdBox().getY(), (int)this.b.getBirdBox().getWidth(), (int)this.b.getBirdBox().getHeight());
//		g2.drawRect((int)this.plane.GameObjectBox.getX(), (int)this.plane.GameObjectBox.getY(), (int)this.plane.GameObjectBox.getWidth(), (int)this.plane.GameObjectBox.getHeight());
//		g2.drawRect((int)this.thunderCloudObj.GameObjectBox.getX(), (int)this.thunderCloudObj.GameObjectBox.getY(), (int)this.thunderCloudObj.GameObjectBox.getWidth(), (int)this.thunderCloudObj.GameObjectBox.getHeight());
//		g2.drawRect((int)this.cloudQuestionBoxObj.GameObjectBox.getX(), (int)this.cloudQuestionBoxObj.GameObjectBox.getY(), (int)this.cloudQuestionBoxObj.GameObjectBox.getWidth(), (int)this.cloudQuestionBoxObj.GameObjectBox.getHeight());
//		g2.drawRect((int)this.food.GameObjectBox.getX(), (int)this.food.GameObjectBox.getY(), (int)this.food.GameObjectBox.getWidth(), (int)this.food.GameObjectBox.getHeight());
		
		tick = (tick + 1) % 30;
		if (tick == 0) {
			picNumMap = (picNumMap + 1) % 13;
		}
		g2.drawImage(this.miniMap[picNumMap], 0, 0, null);
		
		for (int i = 0; i < healthCount; i++) {
			g2.drawImage(healthIcon, (scaledImageWidth - 350) + (30 * i), 20, null);
		}
		
		
	}

	@Override
	public void update(ArrayList<GameObject> list) {
		
		this.b = (Bird) list.get(0);
		this.plane = list.get(1);
		this.thunderCloudObj = list.get(2);
		this.cloudQuestionBoxObj = list.get(3);
		this.food = list.get(4);
		
		birdX = b.getX();
		birdY = b.getY();
		planeX = plane.getX();
		planeY = plane.getY();
		cloudQuestionX = cloudQuestionBoxObj.getX();
		cloudQuestionY = cloudQuestionBoxObj.getY();
		this.thunderCloudX = thunderCloudObj.getX();
		this.thunderCloudY = thunderCloudObj.getY();
		fishX = food.getX();
		fishY = food.getY();
		//health = b.getHealth();
		healthCount = b.getHealthCount();
	}
}
	




