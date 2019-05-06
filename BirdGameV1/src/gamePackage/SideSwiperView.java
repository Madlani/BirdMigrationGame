package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SideSwiperView extends View {
	
	private Image g1;
	private Image map;
	
	ImageIcon[] migrationMap;
	
	public int imgVelX = 0;
	private int scaledImageWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int scaledImageHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	private BufferedImage airplaneImg;
	private BufferedImage healthImg;
	private BufferedImage healthIcon;
	private BufferedImage cloudQuestionBox;
	private BufferedImage thunderCloud;
			
	double birdX, birdY, planeX, planeY, cloudQuestionX, cloudQuestionY, fishX, fishY, thunderCloudX, thunderCloudY;
	
	private int imgWidth = 150;
	private String birdImagePath = "src/images/osprey_frames.png";
	private int birdFrameCount = 22;
	private BufferedImage[] bird_imagesBufferedImage;
	private BufferedImage[] fishFrames;
	private int picNum = 0;
	private short picNumFish = 0;
	
	private int health;
	private int healthCount;
	
	private Bird b;
	private GameObject plane;
	private GameObject thunderCloudObj;
	private GameObject cloudQuestionBoxObj;
	private GameObject food;
	
	public SideSwiperView() {
		super();
		this.loadImage();
	}
	
	//Draws the blocks which must be collided with to answer questions
	public void displayBlocks() {
		
	}
	
	//Draws the background for the SideSwiper minigame
	@Override
	public void drawBackground() {
		
		
	}
	
	private void loadImage() {
		ImageIcon grass_1 = new ImageIcon("src/images/grass3.png");
        //miniMap = new ImageIcon("src/images/minimap.png");
        
        ImageIcon[] migrationMap = new ImageIcon[9];
        migrationMap[0] = new ImageIcon("src/images/migrationMiniMap1.png");
        migrationMap[1] = new ImageIcon("src/images/migrationMiniMap2.png");
        migrationMap[2] = new ImageIcon("src/images/migrationMiniMap3.png");
        migrationMap[3] = new ImageIcon("src/images/migrationMiniMap4.png");
        migrationMap[4] = new ImageIcon("src/images/migrationMiniMap5.png");
        migrationMap[5] = new ImageIcon("src/images/migrationMiniMap6.png");
        migrationMap[6] = new ImageIcon("src/images/migrationMiniMap7.png");
        migrationMap[7] = new ImageIcon("src/images/migrationMiniMap8.png");
        migrationMap[8] = new ImageIcon("src/images/migrationMiniMap9.png");
        
        
		airplaneImg = super.createImage("src/images/airplane.png");
		healthImg = super.createImage("src/images/health.png");
		healthIcon = super.createImage("src/images/birdHealth.png");
		cloudQuestionBox = super.createImage("src/images/cloudQuestionMark.png");
		thunderCloud = super.createImage("src/images/thunderCloud.png");
		
		g1 = grass_1.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		map = migrationMap[0].getImage().getScaledInstance(scaledImageWidth/4, scaledImageHeight, Image.SCALE_DEFAULT);
		
		BufferedImage birdFrames = super.createImage(birdImagePath);
		BufferedImage fishAnimation = super.createImage("src/images/fishFrames.png");
		bird_imagesBufferedImage = new BufferedImage[birdFrameCount];
		fishFrames = new BufferedImage[4];
		
		for (int i = 0; i < birdFrameCount; i++)
			bird_imagesBufferedImage[i] = birdFrames.getSubimage(imgWidth * i, 0, imgWidth, 150);
		
		for (int i = 0; i < 4; i++) //100 = fish image pixel width
			fishFrames[i] = fishAnimation.getSubimage(100 * i, 0, 100, 65);
		
		setDoubleBuffered(true);

	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		Graphics2D g3 = (Graphics2D) g.create();
		
		picNumFish = (short) ((picNumFish + 1) % 4);
		picNum = (picNum + 1) % birdFrameCount;
		imgVelX-=1;
		
		if (imgVelX % scaledImageWidth == 0) {
			imgVelX = 0;
		}
		
		g2.drawImage(g1, (imgVelX % scaledImageWidth), 0, null); // draws image in the window
		g2.drawImage(g1, ((imgVelX % scaledImageWidth)+scaledImageWidth), 0, null); // draws image in the window, had to make second image the same as the first for continuity
	    
		g2.drawImage(bird_imagesBufferedImage[picNum], (int)birdX, (int)birdY, null);
		
		g2.drawImage(this.thunderCloud, (int)thunderCloudX, (int)thunderCloudY, null);
		g2.drawImage(this.cloudQuestionBox, (int)cloudQuestionX, (int)cloudQuestionY, null);

		g2.drawImage(airplaneImg, (int)planeX, (int)planeY, null);
		g2.drawImage(fishFrames[picNumFish], (int)fishX, (int)fishY, null);
		
		g3.drawImage(healthImg, scaledImageWidth - 390, 20, null);
		g3.drawRect((int)this.b.getBirdBox().getX(), (int)this.b.getBirdBox().getY(), (int)this.b.getBirdBox().getWidth(), (int)this.b.getBirdBox().getHeight());
		g3.drawRect((int)this.plane.GameObjectBox.getX(), (int)this.plane.GameObjectBox.getY(), (int)this.plane.GameObjectBox.getWidth(), (int)this.plane.GameObjectBox.getHeight());
		g3.drawRect((int)this.thunderCloudObj.GameObjectBox.getX(), (int)this.thunderCloudObj.GameObjectBox.getY(), (int)this.thunderCloudObj.GameObjectBox.getWidth(), (int)this.thunderCloudObj.GameObjectBox.getHeight());
		g3.drawRect((int)this.cloudQuestionBoxObj.GameObjectBox.getX(), (int)this.cloudQuestionBoxObj.GameObjectBox.getY(), (int)this.cloudQuestionBoxObj.GameObjectBox.getWidth(), (int)this.cloudQuestionBoxObj.GameObjectBox.getHeight());
		g3.drawRect((int)this.food.GameObjectBox.getX(), (int)this.food.GameObjectBox.getY(), (int)this.food.GameObjectBox.getWidth(), (int)this.food.GameObjectBox.getHeight());
		g3.drawImage(map, 0, 0, null);
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
		health = b.getHealth();
		healthCount = b.getHealthCount();
	}
	
	public ImageIcon[] getMigrationMap() {
		return migrationMap;
	}

	public void setMigrationMap(ImageIcon[] migrationMap) {
		this.migrationMap = migrationMap;
	}

}