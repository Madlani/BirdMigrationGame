package gamePackage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;



@SuppressWarnings("serial")
public class WhackAMoleView extends View {
	private Image background;
	private Image left;
	private Image food;
	private Image right;
	private Image up;
	private Image down;
	private int upDownKeyState = 0;
	private int leftRightKeyState = 0;

	private int scaledImageWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int scaledImageHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	public WhackAMoleView() {
		super();
		this.loadImage();
	}
	

	public void loadImage() {
		
		//background
		ImageIcon bg = new ImageIcon("src/images/WhackAMoleBackground.png");
		background = bg.getImage();
			
		//correct bird image
		ImageIcon l = new ImageIcon("src/images/WhackAMoleArrowLeft.png");
		left = l.getImage();
		
		ImageIcon r = new ImageIcon("src/images/WhackAMoleArrowRight.png");
		right = r.getImage();
		
		ImageIcon u = new ImageIcon("src/images/WhackAMoleArrowUp.png");
		up = u.getImage();
		
		ImageIcon d = new ImageIcon("src/images/WhackAMoleArrowDown.png");
		down = d.getImage();
		
		//food
		ImageIcon f = new ImageIcon("src/images/WhackAMoleFood.png");
		food = f.getImage();

	}
	
	@Override
	//paints image
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		//up
		g.drawImage(food, (scaledImageWidth/2) - 150, 0, null);
		//down
		g.drawImage(food, (scaledImageWidth/2) - 150, scaledImageHeight - 224, null);
		//right
		g.drawImage(food, scaledImageWidth - 350, (scaledImageHeight/2) - 112, null);
		//left
		g.drawImage(food, 0, (scaledImageHeight/2) - 112, null);
		
		
		// Draws correct bird image based on key presses
		switch (upDownKeyState) {
		case 1:
			g.drawImage(up, (scaledImageWidth / 2) - 150, (scaledImageHeight / 2) - 150, null);
			break;

		case -1:
			g.drawImage(down, (scaledImageWidth / 2) - 150, (scaledImageHeight / 2) - 150, null);
			break;
		}

		switch (leftRightKeyState) {
		case 1:
			g.drawImage(right, (scaledImageWidth / 2) - 150, (scaledImageHeight / 2) - 150, null);
			break;
		case -1:
			g.drawImage(left, (scaledImageWidth / 2) - 150, (scaledImageHeight / 2) - 150, null);
			break;
		}

	}
	
	
	//Draws the objects that will be used in the WhackAMole game, including sticks, health, etc.
	public void displayObjects() {
		
	}
	
	public int getUpDownKeyState() {
		return upDownKeyState;
	}


	public void setUpDownKeyState(int upDownKeyState) {
		this.upDownKeyState = upDownKeyState;
	}


	public int getLeftRightKeyState() {
		return leftRightKeyState;
	}


	public void setLeftRightKeyState(int leftRightKeyState) {
		this.leftRightKeyState = leftRightKeyState;
	}



	@Override
	public void update(ArrayList<GameObject> list) {
		// TODO Auto-generated method stub
		
	}
	
}

//-----------------------------------------------------------------------------------------------------