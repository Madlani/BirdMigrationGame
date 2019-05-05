package gamePackage;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;
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
	private boolean drawUp = false;
	private boolean drawDown = false;
	private boolean drawLeft = false;
	private boolean drawRight = false;
	
	private BufferedImage buffer;
	Timer myTimer;
	private int timerDelay = 1000;
	ActionListener listener;
	
	
	
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
		setDoubleBuffered(false);
		buffer = new BufferedImage(scaledImageWidth, scaledImageHeight, BufferedImage.TYPE_INT_ARGB);
		
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (whackModel.gamePattern.size() < 4) {
					redRectangles(buffer.getGraphics());
					repaint();
					System.out.println(whackModel.gamePattern);
				}
			}
		 };
		myTimer = new Timer(timerDelay, listener);
		myTimer.start();
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
	
	public void redRectangles(Graphics g) {
		int randomNum = (int)(Math.random()*(4) + 1);
		g.setColor(OPAQUE_RED);
				
		switch (randomNum) {
			case 1:
				//up
				whackModel.gamePattern.add(randomNum);
				System.out.println("debug up");
				g.fillRect((scaledImageWidth/2) - 175, 0, 350, 224);
				break;
			case 2:
				//down
				whackModel.gamePattern.add(randomNum);
				System.out.println("debug down");
				g.fillRect((scaledImageWidth/2) - 175, scaledImageHeight - 224, 350, 224);
				break;
			case 3:
				//left
				whackModel.gamePattern.add(randomNum);
				System.out.println("debug left");
				g.fillRect( 0, (scaledImageHeight/2) - 112, 350, 224);
				break;
			case 4:
				//right
				whackModel.gamePattern.add(randomNum);
				System.out.println("debug right");
				g.fillRect(scaledImageWidth - 350, (scaledImageHeight/2) - 112, 350, 224);
				break;
		}
		
		//initializes boolean values for drawing game pattern
//		for (Integer num : whackModel.gamePattern) {
//			//up
//			if (num == 1) {
//				System.out.println("up true");
//				drawUp = true;
//				drawDown = false;
//				drawLeft = false;
//				drawRight = false;
//				repaint();
//				
//	
//			}
//			//down
//			else if (num == 2) {
//				System.out.println("down true");
//				drawUp = false;
//				drawDown = true;
//				drawLeft = false;
//				drawRight = false;
//				repaint();
//
//			}
//			//left
//			else if (num == 3) {
//				System.out.println("left true");
//				drawUp = false;
//				drawDown = false;
//				drawLeft = true;
//				drawRight = false;
//				repaint();
//
//			}
//			//right
//			else if (num == 4) {
//				System.out.println("right true");
//				drawUp = false;
//				drawDown = false;
//				drawLeft = false;
//				drawRight = true;
//				repaint();
//
//			}			
//		}	
		
		//draw game pattern based on boolean values
//		g.setColor(OPAQUE_RED);
//		//up
//		if (drawUp && !drawDown && !drawLeft && !drawRight) {
//			System.out.println("debug up");
//			g.fillRect((scaledImageWidth/2) - 175, 0, 350, 224);
//		}
//		//down
//		else if (!drawUp && drawDown && !drawLeft && !drawRight) {
//			System.out.println("debug down");
//			g.fillRect((scaledImageWidth/2) - 175, scaledImageHeight - 224, 350, 224);
//		}
//		//left
//		else if (!drawUp && !drawDown && drawLeft && !drawRight) {
//			System.out.println("debug left");
//			g.fillRect( 0, (scaledImageHeight/2) - 112, 350, 224);
//		}
//		//right
//		else if (!drawUp && !drawDown && !drawLeft && drawRight) {
//			System.out.println("debug right");
//			g.fillRect(scaledImageWidth - 350, (scaledImageHeight/2) - 112, 350, 224);
//		}
//		

		
		
		
//		int i = 0;
//		
//		if (i < whackModel.getGamePattern().size()) {
//			switch (whackModel.getGamePattern().get(i)) {
//				case 1:
//					System.out.println("up true");
//					drawUp = true;
//					drawDown = false;
//					drawLeft = false;
//					drawRight = false;
//					repaint();
//					i++;
//					break;
//				case 2:
//					System.out.println("down true");
//					drawUp = false;
//					drawDown = true;
//					drawLeft = false;
//					drawRight = false;
//					repaint();
//					i++;
//					break;
//				case 3:
//					System.out.println("left true");
//					drawUp = false;
//					drawDown = false;
//					drawLeft = true;
//					drawRight = false;
//					repaint();
//					i++;
//					break;
//				case 4:
//					System.out.println("right true");
//					drawUp = false;
//					drawDown = false;
//					drawLeft = false;
//					drawRight = true;
//					repaint();
//					i++;
//					break;
//			}
//		}
	
	}
	
	
	
	
	@Override
	//paints image
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(OPAQUE_GREEN);
		
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
				g.fillRect((scaledImageWidth/2) - 175, 0, 350, 224);
				break;
	
			case -1:
				g.drawImage(down, (scaledImageWidth/2) - 175, (scaledImageHeight/2) - 150, null);
				//Overlays a transparent green rectangle over the food image when Down is pressed
				g.fillRect((scaledImageWidth/2) - 175, scaledImageHeight - 224, 350, 224);
				break;
		}

		switch (leftRightKeyState) {
			case 1:
				g.drawImage(right, (scaledImageWidth / 2) - 150, (scaledImageHeight / 2) - 150, null);
				//Overlays a transparent green rectangle over the food image when Right is pressed
				g.fillRect(scaledImageWidth - 350, (scaledImageHeight/2) - 112, 350, 224);
				break;
			case -1:
				g.drawImage(left, (scaledImageWidth / 2) - 150, (scaledImageHeight / 2) - 150, null);
				//Overlays a transparent green rectangle over the food image when Left is pressed
				g.fillRect( 0, (scaledImageHeight/2) - 112, 350, 224);
				break;
			}
		
		//view game pattern
		g.drawImage(buffer, 0, 0, this);
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