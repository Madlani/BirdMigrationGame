package gamePackage;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class OspreyWinView extends View implements Serializable {
	private Image backgroundImage;

	private final int NO_SPEED = 0;
	private int scaledImageWidth = Model.scaledImageWidth;
	private int scaledImageHeight = Model.scaledImageHeight;

	
	public OspreyWinView() {
		super();
		this.loadImage();
	}

	/**
	 * loadImage()
	 * Loads the image for the end view
	 */
	private void loadImage() {
		ImageIcon winImage = new ImageIcon("src/images/ospreyEnd.png");
		backgroundImage = winImage.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
	}

	/**
	 * paintComponent()
	 * Paints the EndView component into the window. This view does not move, so the x and y 
	 * velocity are filled with our constant NO_SPEED which is 0.
	 */
	@Override
	public void paintComponent(Graphics g) {
		g = g.create();
		g.drawImage(backgroundImage, NO_SPEED, NO_SPEED, null);
	}
	
	/**
	 * update()
	 * This method is not used in this particular view, but is required to be implemented since we extend the abstract View class.
	 */
	@Override
	public void update(ArrayList<GameObject> list) {

	}

}
