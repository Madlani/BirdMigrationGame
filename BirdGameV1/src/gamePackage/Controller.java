package gamePackage;
import static org.junit.Assert.assertEquals;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.AbstractAction;

import org.junit.jupiter.api.Test;

public class Controller implements ActionListener, KeyListener {

	private Model gameModel;
	private View gameView;
	SideSwiperView ssv;
	
	
	private Action gameAction;
	private boolean keyPressed = false;
	private boolean controllerStart = false;
	private boolean keyReleased = false;
	private boolean actionPerformed = false;
	private boolean pauseButtonFlag = false;
	final int DRAW_DELAY = 100;
	//Dimension screenSize;
	
	public Controller() {
		
		gameView = new View(this);
		gameModel = new Model();
		//gameModel = new Model(gameView.getWidth(), gameView.getHeight(), gameView.getImgWidth(), gameView.getImgHeight());
		ssv = new SideSwiperView(this);
		//gameView.updateButton(this);
		gameView.addKeyListener(this);
	
		
		// Creates the frame and selects settings
		JFrame frame = new JFrame();
		frame.getContentPane().add(gameView);
		//frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set screen to full screen
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


		frame.add(ssv);
		//frame.add(gameView.pauseButton);
		frame.pack();
		
		frame.setVisible(true);
	

		//gameView.pauseButton.setVisible(true);
	}
	
	//starts our game, initializes the beginning View.
	
	@SuppressWarnings("serial")
	public void start() {
		
		gameAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (!pauseButtonFlag) {
					gameModel.updateLocationAndDirection();
				}
				gameView.update(gameModel.getOsprey().getX(), gameModel.getOsprey().getY(), gameModel.getDirection());
				System.out.println("Printing from Controller OSPREY LOCATION: " + gameModel.osprey.getX() + ", " + gameModel.osprey.getY());
				System.out.println("-----------------------------------");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Right arrow key 
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameModel.getOsprey().moveRight();
		}
		
		//Left arrow key 
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gameModel.getOsprey().moveLeft();
		}
		
		//Up arrow key 
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			gameModel.getOsprey().moveUp();
		}
		
		//Down arrow key 
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gameModel.getOsprey().moveDown();
		}
	}

	/** keyReleased()
	 * returns the bird to the default movement, which is forward
	 * sets the count to be the frame count
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		gameView.setMovement("_forward_");
		//gameView.setCount(gameView.getFRAME_COUNT());
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

}