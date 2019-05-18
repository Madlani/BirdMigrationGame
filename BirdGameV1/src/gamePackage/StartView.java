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
	private int scaledImageWidth = Model.scaledImageWidth;
	private int scaledImageHeight = Model.scaledImageHeight;

	private boolean tutorialClicked;
	private boolean startClicked;
	private BirdType type;
	
	ImageIcon startScreenNorthernHarrier = new ImageIcon("src/images/northernHarrierStartScreen.png");
	ImageIcon startScreenOsprey = new ImageIcon("src/images/ospreyStartScreen.png");
	
	
	public StartView(BirdType t) {
		super();
		this.type = t;
		this.loadImage();
	}
	
	/**
	 * getTutorialClicked()
	 * @return tutorialClicked, a boolean that detects if the tutorial button was clicked
	 */
	public boolean getTutorialClicked() {
		return this.tutorialClicked;
	}
	
	/**
	 * getStartClicked()
	 * @return startClicked, a boolean that detects if the start game button was clicked
	 */
	public boolean getStartClicked() {
		return this.startClicked;
	}

	/**
	 * update()
	 * This method is not used in this particular view, but is required to be implemented since we extend the abstract View class.
	 */
	@Override
	public void update(ArrayList<GameObject> list) {
		
	}
	
	/**
	 * loadImage()
	 * Loads in the images for the objects used for the StartView panel 
	 */
	private void loadImage() {
	
		if (type == BirdType.OSPREY) {
			g1 = startScreenOsprey.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		} else if (type == BirdType.NORTHERNHARRIER){
			g1 = startScreenNorthernHarrier.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
		}	
	}
	
	/**
	 * paintComponent()
	 * This method overrides the View's paintComponent(). It draws all of our images on the screen and sets them
	 * to be the appropriate starting locations that we have defined using constants.
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(g1, (imgVelX % scaledImageWidth), 0, null); // draws image in the window
	}
}




