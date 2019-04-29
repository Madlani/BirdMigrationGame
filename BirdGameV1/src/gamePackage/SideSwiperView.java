package gamePackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.junit.Test;

@SuppressWarnings("serial")
public class SideSwiperView extends View {
	
	//private Image birdImg = Toolkit.getDefaultToolkit().createImage("src/images/smaller osprey.gif");
	private Image g1;
	public int imgVelX = -2;
	private int scaledImageWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int scaledImageHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private Image obstacleImg = Toolkit.getDefaultToolkit().createImage("src/images/airplane.gif");
	private Image blockImg = Toolkit.getDefaultToolkit().createImage("src/images/block.png");
	double birdX, birdY, planeX, planeY, blockX, blockY;
	
	private int imgWidth = 150;
	private String birdImagePath = "src/images/osprey_frames.png";
	private int birdFrameCount = 22;
	private BufferedImage[] bird_imagesBufferedImage;
	private int picNum = 0;
	
	public SideSwiperView(Controller controller) {
		super(controller);
		
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
		g1 = grass_1.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		BufferedImage birdFrames = super.createImage(birdImagePath);
		bird_imagesBufferedImage = new BufferedImage[birdFrameCount];
		
		for (int i = 0; i < birdFrameCount; i++) {
			bird_imagesBufferedImage[i] = birdFrames.getSubimage(imgWidth * i, 0, imgWidth, 150);
		}
		setDoubleBuffered(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		
		picNum = (picNum + 1) % birdFrameCount;
		imgVelX-=5;
		birdX = (int)controller.getGameModel().getOsprey().getX();
		birdY = (int)controller.getGameModel().getOsprey().getY();
		planeX = (int)controller.getGameModel().getAirplane().getX();
		planeY = (int)controller.getGameModel().getAirplane().getY();
		blockX = (int)controller.getGameModel().getBlock().getX();
		blockY = (int)controller.getGameModel().getBlock().getY();
		
//		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
//        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
//        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//		
		
		g2.drawImage(g1, (imgVelX % scaledImageWidth), 0, null); // draws image in the window
		g2.drawImage(g1, ((imgVelX % scaledImageWidth)+scaledImageWidth), 0, null); // draws image in the window, had to make second image the same as the first for continuity
		
//		g.drawImage(birdImg, (int)birdX, (int)birdY, null);
		g2.drawImage(bird_imagesBufferedImage[picNum], (int)birdX, (int)birdY, null);
		g2.drawImage(blockImg, (int)blockX, (int)blockY, null);

		g2.drawImage(obstacleImg, (int)planeX, (int)planeY, null);
		//System.out.println("Printing from SideSwiperView BIRDX, BIRDY: " + birdX + ", " + birdY);
		this.update(birdX, birdY, imgVelX);
	}
}