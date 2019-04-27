package gamePackage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;



@SuppressWarnings("serial")
public class WhackAMoleView extends View {
	private Image Background;
	private Image Left;
//	private Image Right;
//	private Image Up;
//	private Image Down;
	
	private int scaledImageWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int scaledImageHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	public WhackAMoleView(Controller controller) {
		super(controller);
		this.loadImage();
	}
	

	public void loadImage() {
		ImageIcon bg = new ImageIcon("src/bird_images/WhackAMoleBackground.png");
		Background = bg.getImage();
		
		ImageIcon left = new ImageIcon("src/bird_images/WhackAMoleArrowLeft.png");
		Left = left.getImage();
//
//		ImageIcon right = new ImageIcon("src/bird_images/WhackAMoleRight.png");
//		Right = right.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
//	
//		ImageIcon up = new ImageIcon("src/bird_images/WhackAMoleUp.png");
//		Up = up.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
//	
//		ImageIcon down = new ImageIcon("src/bird_images/WhackAMoleDown.png");
//		Down = down.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);
	
	}
	
	@Override
	//paints image
	public void paintComponent(Graphics g) {
		g.drawImage(Background, 0, 0, getWidth(), getHeight(), this);
		//g.drawImage(Left, (scaledImageWidth/2) - 150, (scaledImageHeight/2) - 150, null);
		
	}
	
	
	//Draws the objects that will be used in the WhackAMole game, including sticks, health, etc.
	public void displayObjects() {
		
	}
	
}

//-----------------------------------------------------------------------------------------------------


