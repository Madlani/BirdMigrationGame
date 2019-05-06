package gamePackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")

//display the final state of the game
public class EndView extends View {
	
private Image g1;
	
	public int imgVelX = 0;
	private int scaledImageWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int scaledImageHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public EndView() {
		super();
		this.loadImage();
	}

	public void replayGame() {

	}
	
	@Override
	public void update(ArrayList<GameObject> list) {
		// TODO Auto-generated method stub
		
	}
	
	private void loadImage() {
		ImageIcon startScreen = new ImageIcon("src/images/endView.png");
		g1 = startScreen.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_DEFAULT);

	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(g1, (imgVelX % scaledImageWidth), 0, null); // draws image in the window

	}

}



