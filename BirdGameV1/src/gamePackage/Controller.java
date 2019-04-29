package gamePackage;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.AbstractAction;

public class Controller implements ActionListener, KeyListener {

	private Model gameModel;
	private View gameView;
	SideSwiperView ssv;
	WhackAMoleView wmv;
	
	private Action gameAction;
	private boolean keyPressed = false;
	private boolean controllerStart = false;
	private boolean keyReleased = false;
	private boolean actionPerformed = false;
	private boolean pauseButtonFlag = false;
	final int DRAW_DELAY = 15;
	
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	
	public Controller() {
		
		gameView = new View(this);
		gameView.addKeyListener(this);
		gameModel = new Model();
		
		// Creates the frame and selects settings
		JFrame frame = new JFrame();
		frame.getContentPane().add(gameView);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // sets screen to full screen
		
		// Code to run SideSwiper Game
		ssv = new SideSwiperView(this);
		//frame.add(ssv);
		
		// Code to run Whack a Mole Game
		wmv = new WhackAMoleView(this);
		frame.add(wmv);

		frame.pack();
		frame.setVisible(true);
	}
	
	//starts our game, initializes the beginning View.
	
	@SuppressWarnings("serial")
	public void start() {
		gameAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (!pauseButtonFlag) {
					gameModel.updateLocationAndDirection();
				}
			}
		};
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(DRAW_DELAY, gameAction);
				t.start();
			}
		});
	}

	
	// necessary methods to be implemented from super class
	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Right arrow key 
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameModel.getOsprey().moveRight();

			//For repainting purposes with WhackAMole mini-game
			upPressed = false;
			downPressed = false;
			leftPressed = false;
			rightPressed = true;
			wmv.repaint();
		}
		
		//Left arrow key 
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gameModel.getOsprey().moveLeft();
			
			//For repainting purposes with WhackAMole mini-game
			upPressed = false;
			downPressed = false;
			leftPressed = true;
			rightPressed = false;
			wmv.repaint();
		}
		
		//Up arrow key 
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			gameModel.getOsprey().moveUp();
			
			//For repainting purposes with WhackAMole mini-game
			upPressed = true;
			downPressed = false;
			leftPressed = false;
			rightPressed = false;
			wmv.repaint();
		}
		
		//Down arrow key 
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gameModel.getOsprey().moveDown();
			
			//For repainting purposes with WhackAMole mini-game
			upPressed = false;
			downPressed = true;
			leftPressed = false;
			rightPressed = false;
			wmv.repaint();
		}
	}

	/** keyReleased()
	 * returns the bird to the default movement, which is forward
	 * sets the count to be the frame count
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		gameView.setMovement("_forward_");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Paused".contentEquals(e.getActionCommand())) {
			gameView.pauseButton.setFocusable(false);
			pauseButtonFlag = !pauseButtonFlag;
		}
		
	}
	
	public Model getGameModel() {
		return gameModel;
	}

	public void setGameModel(Model gameModel) {
		this.gameModel = gameModel;
	}

	public View getGameView() {
		return gameView;
	}

	public void setGameView(View gameView) {
		this.gameView = gameView;
	}

	public Action getGameAction() {
		return gameAction;
	}

	public void setGameAction(Action gameAction) {
		this.gameAction = gameAction;
	}

	public boolean getKeyPressed() {
		return keyPressed;
	}
	public boolean getControllerStart() {
		return controllerStart;
	}
	public boolean getKeyReleased() {
		return keyReleased;
	}
	public boolean getActionPerformed() {
		return actionPerformed;
	}
	public boolean getUpPressed() {
		return upPressed;
	}
	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}
	public boolean getDownPressed() {
		return downPressed;
	}
	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}
	public boolean getLeftPressed() {
		return leftPressed;
	}
	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}
	public boolean getRightPressed() {
		return rightPressed;
	}
	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

}