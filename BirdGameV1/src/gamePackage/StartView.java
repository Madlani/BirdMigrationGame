package gamePackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class StartView extends View {
	private Image g1;
	
	public int imgVelX = 0;
	private int scaledImageWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int scaledImageHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	private boolean tutorialClicked;
	private boolean startClicked;
	

	public StartView() {
		super();
		this.loadImage();
	}
	
	//Displays the bird image
	public void createBirdObject() {
		
	}
	
	//Draws the background that the game will display at the start
	@Override
	public void drawBackground() {
		
	}
	
	//Returns a boolean that detects if the tutorial button was clicked 
	public boolean getTutorialClicked() {
		return this.tutorialClicked;
	}
	
	//Returns a boolean that detects if the start game button was clicked
	public boolean getStartClicked() {
		return this.startClicked;
	}

	@Override
	public void update(ArrayList<GameObject> list) {
		// TODO Auto-generated method stub
		
	}
	
	private void loadImage() {
		ImageIcon startScreen = new ImageIcon("src/images/startView.png");
		g1 = startScreen.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);

	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(g1, (imgVelX % scaledImageWidth), 0, null); // draws image in the window

	}

}




