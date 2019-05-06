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
	private Image stick;
	private Image pressedStick;
	
	
	private int keyState = 0;
	private boolean drawUp = false;
	private boolean drawDown = false;
	private boolean drawLeft = false;
	private boolean drawRight = false;
	
	//highlight stick
	private BufferedImage highlightStickBuffer;
	private Timer highlightStickTimer;
	private int highlightTimerDelay = 1000;
	private ActionListener highlightStickListener;
	
	
	//normal stick
	private BufferedImage normalStickBuffer;
	private Timer normalTimer;
	private int normalStickTimerDelay = 1100;
	private ActionListener normalStickListener;
	
	private boolean isView;



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
		//setDoubleBuffered(false);
		
	}
	

	public void initTimers() {
		if (isView) {
		
		//highlight stick
				highlightStickBuffer = new BufferedImage(scaledImageWidth, scaledImageHeight, BufferedImage.TYPE_INT_ARGB);
				highlightStickListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (whackModel.gamePattern.size() < 4) {
							highlightStickMethod(highlightStickBuffer.getGraphics());
							repaint();
						}
					}
				 };
				 highlightStickTimer = new Timer(highlightTimerDelay, highlightStickListener);
				 highlightStickTimer.start();
				
				
				//repaint normal stick
				normalStickBuffer = new BufferedImage(scaledImageWidth, scaledImageHeight, BufferedImage.TYPE_INT_ARGB);
				normalStickListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							normalStickMethod(normalStickBuffer.getGraphics());
							repaint();
					}
				 };
				normalTimer = new Timer(normalStickTimerDelay, normalStickListener);
				normalTimer.start();
				
		}
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
		
		//stick
		ImageIcon s = new ImageIcon("src/images/stick.png");
		stick = s.getImage();
		
		//pressedStick
		ImageIcon p = new ImageIcon("src/images/pressedStick.png");
		pressedStick = p.getImage();

	}
	
//	public void resetImages(Graphics g) {
//		g.drawImage(stick, (scaledImageWidth/2) - 196, 0, null);
//		g.drawImage(stick, (scaledImageWidth/2) - 196, scaledImageHeight - 360, null);
//		g.drawImage(stick, 0, (scaledImageHeight/2) - 180, null);
//		g.drawImage(stick, scaledImageWidth - 393, (scaledImageHeight/2) - 180, null);
//	}
	
	
	public void highlightStickMethod(Graphics g) {
		
		int randomNum = (int)(Math.random()*(4) + 1);
		
		while (whackModel.gamePattern.contains(randomNum)) {
			randomNum = (int)(Math.random()*(4) + 1);
		}
				
		switch (randomNum) {
			case 1:
				//up
				whackModel.gamePattern.add(randomNum);
				System.out.println(whackModel.gamePattern);
				System.out.println("highlight up");
				g.drawImage(pressedStick, (scaledImageWidth/2) - 196, 0, null);
				//repaint();
				drawUp = true;
				drawDown = false;
				drawLeft = false;
				drawRight = false;
				break;
			case 2:
				//down
				whackModel.gamePattern.add(randomNum);
				System.out.println(whackModel.gamePattern);
				System.out.println("highlight down");
				g.drawImage(pressedStick, (scaledImageWidth/2) - 196, scaledImageHeight - 360, null);
				//repaint();
				drawUp = false;
				drawDown = true;
				drawLeft = false;
				drawRight = false;
				break;
			case 3:
				//left
				whackModel.gamePattern.add(randomNum);
				System.out.println(whackModel.gamePattern);
				System.out.println("highlight left");
				g.drawImage(pressedStick, 0, (scaledImageHeight/2) - 180, null);
				//repaint();
				drawUp = false;
				drawDown = false;
				drawLeft = true;
				drawRight = false;
				break;
			case 4:
				//right
				whackModel.gamePattern.add(randomNum);
				System.out.println(whackModel.gamePattern);
				System.out.println("highlight right");
				g.drawImage(pressedStick, scaledImageWidth - 393, (scaledImageHeight/2) - 180, null);
				//repaint();
				drawUp = false;
				drawDown = false;
				drawLeft = false;
				drawRight = true;
				break;
		}
		
//		if (drawUp)
//			g.drawImage(stick, (scaledImageWidth/2) - 196, 0, null);
//		else if (drawDown)
//			g.drawImage(stick, (scaledImageWidth/2) - 196, scaledImageHeight - 360, null);
//		else if (drawLeft)
//			g.drawImage(stick, 0, (scaledImageHeight/2) - 180, null);
//		else if (drawRight)
//			g.drawImage(stick, scaledImageWidth - 393, (scaledImageHeight/2) - 180, null);
		
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
	
	public void normalStickMethod(Graphics g) {
		//up
		if (drawUp) {
			g.drawImage(stick, (scaledImageWidth/2) - 196, 0, null);
			System.out.println("drew original up");
		}
		//down
		else if (drawDown) {
			g.drawImage(stick, (scaledImageWidth/2) - 196, scaledImageHeight - 360, null);
			System.out.println("drew original down");
		}
		//left
		else if (drawLeft) {
			g.drawImage(stick, 0, (scaledImageHeight/2) - 180, null);
			System.out.println("drew original left");
		}
		//right
		else if (drawRight) {
			g.drawImage(stick, scaledImageWidth - 393, (scaledImageHeight/2) - 180, null);
			System.out.println("drew original right");
		}
		
	}
	
	
	
	
	@Override
	//paints image
	public void paintComponent(Graphics g) {
		if (isView) {
		
			super.paintComponent(g);
			g.setColor(OPAQUE_GREEN);
			
			//initial components
				//background
			g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
				//up
			g.drawImage(stick, (scaledImageWidth/2) - 196, 0, null);
				//down
			g.drawImage(stick, (scaledImageWidth/2) - 196, scaledImageHeight - 360, null);
				//left
			g.drawImage(stick, 0, (scaledImageHeight/2) - 180, null);
				//right
			g.drawImage(stick, scaledImageWidth - 393, (scaledImageHeight/2) - 180, null);
			
			
			
			
			//Draws the bird image looking in the correct direction based on key presses
			switch (keyState) {
				case 1:
					g.drawImage(up, (scaledImageWidth/2) - 175, (scaledImageHeight/2) - 150, null);
					//Overlays a transparent green rectangle over the food image when Up is pressed
					g.fillRect((scaledImageWidth/2) - 175, 0, 350, 224);
					break;
		
				case 2:
					g.drawImage(down, (scaledImageWidth/2) - 175, (scaledImageHeight/2) - 150, null);
					//Overlays a transparent green rectangle over the food image when Down is pressed
					g.fillRect((scaledImageWidth/2) - 175, scaledImageHeight - 224, 350, 224);
					break;
				case 3:
					g.drawImage(right, (scaledImageWidth / 2) - 150, (scaledImageHeight / 2) - 150, null);
					//Overlays a transparent green rectangle over the food image when Right is pressed
					g.fillRect(scaledImageWidth - 350, (scaledImageHeight/2) - 112, 350, 224);
					break;
				case 4:
					g.drawImage(left, (scaledImageWidth / 2) - 150, (scaledImageHeight / 2) - 150, null);
					//Overlays a transparent green rectangle over the food image when Left is pressed
					g.fillRect( 0, (scaledImageHeight/2) - 112, 350, 224);
					break;
				}
			
			//view game pattern
			g.drawImage(highlightStickBuffer, 0, 0, this);
			g.drawImage(normalStickBuffer, 0, 0, this);
		
		}

	}
	
	
	//Draws the objects that will be used in the WhackAMole game, including sticks, health, etc.
	public void displayObjects() {
		
	}
	
	public int getKeyState() {
		return this.keyState;
	}


	public void setKeyState(int keyState) {
		this.keyState = keyState;
	}


	

	@Override
	public void update(ArrayList<GameObject> list) {
		// TODO Auto-generated method stub
		
	}
	
//	public boolean getIsView() {
//		return isView;
//	}


	public void setIsView(boolean isView) {
		this.isView = isView;
	}
	
}

//-----------------------------------------------------------------------------------------------------