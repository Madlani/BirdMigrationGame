package gamePackage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

//1 - up
////2 - down
////3 - left
////4 - right


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
	Timer myTimer = new Timer();
	private long timerDelay = 1;
	
	
	//MUST FIX
	WhackAMoleModel whackModel = new WhackAMoleModel();

	//scaled image size
	private int scaledImageWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int scaledImageHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	
	//Transparent colors
	Color OPAQUE_GREEN = new Color(.75f, 1f, 0f, .75f);	//75% opaque
	Color OPAQUE_RED = new Color(.75f, 0f, 0f, .75f);	//75% opaque
	
	
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
		
		//initial components
			//background
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
			//up
		g.drawImage(food, (scaledImageWidth/2) - 175, 0, null);
			//down
		g.drawImage(food, (scaledImageWidth/2) - 175, scaledImageHeight - 224, null);
			//right
		g.drawImage(food, scaledImageWidth - 350, (scaledImageHeight/2) - 112, null);
			//left
		g.drawImage(food, 0, (scaledImageHeight/2) - 112, null);
		
		
		
		
		//Draws the bird image looking in the correct direction based on key presses
		switch (upDownKeyState) {
			case 1:
				g.drawImage(up, (scaledImageWidth/2) - 175, (scaledImageHeight/2) - 150, null);
				
				//Overlays a transparent green rectangle over the food image when Up is pressed
				g.setColor(OPAQUE_GREEN);
				g.fillRect((scaledImageWidth/2) - 175, 0, 350, 224);
				break;
	
			case -1:
				g.drawImage(down, (scaledImageWidth/2) - 175, (scaledImageHeight/2) - 150, null);
				
				//Overlays a transparent green rectangle over the food image when Down is pressed
				g.setColor(OPAQUE_GREEN);
				g.fillRect((scaledImageWidth/2) - 175, scaledImageHeight - 224, 350, 224);
				break;
		}

		switch (leftRightKeyState) {
			case 1:
				g.drawImage(right, (scaledImageWidth / 2) - 150, (scaledImageHeight / 2) - 150, null);
				
				//Overlays a transparent green rectangle over the food image when Right is pressed
				g.setColor(OPAQUE_GREEN);
				g.fillRect(scaledImageWidth - 350, (scaledImageHeight/2) - 112, 350, 224);
				break;
			case -1:
				g.drawImage(left, (scaledImageWidth / 2) - 150, (scaledImageHeight / 2) - 150, null);
				
				//Overlays a transparent green rectangle over the food image when Left is pressed
				g.setColor(OPAQUE_GREEN);
				g.fillRect( 0, (scaledImageHeight/2) - 112, 350, 224);
				break;
			}
		
		
		
		//viewGamePattern
		for (int i = 0; i < whackModel.getGamePattern().size(); i++) {
			//up
			if (whackModel.getGamePattern().get(i) == 1) {
				myTimer.schedule(new TimerTask() {
					@Override
					public void run() {
		            	g.setColor(OPAQUE_RED);
						g.fillRect((scaledImageWidth/2) - 175, 0, 350, 224);
					}
				}, timerDelay);
			}
			//down
			if (whackModel.getGamePattern().get(i) == 2) {
				myTimer.schedule(new TimerTask() {
		            @Override
		            public void run() {
		            	g.setColor(OPAQUE_RED);
						g.fillRect((scaledImageWidth/2) - 175, scaledImageHeight - 224, 350, 224);
		            }
		        }, 0, timerDelay);	
			}
			//right
			if (whackModel.getGamePattern().get(i) == 3) {
				myTimer.schedule(new TimerTask() {
		            @Override
		            public void run() {
		            	g.setColor(OPAQUE_RED);
						g.fillRect(scaledImageWidth - 350, (scaledImageHeight/2) - 112, 350, 224);
		            }
		        }, 0, timerDelay);	
			}
			//left
			if (whackModel.getGamePattern().get(i) == 4) {
				myTimer.schedule(new TimerTask() {
		            @Override
		            public void run() {
		            	g.setColor(OPAQUE_RED);
						g.fillRect( 0, (scaledImageHeight/2) - 112, 350, 224);
		            }
		        }, 0, timerDelay);	
			}
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