import static org.junit.Assert.assertEquals;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.Action;
import javax.swing.AbstractAction;

import org.junit.jupiter.api.Test;

public class Controller implements ActionListener, KeyListener {

	private Model gameModel;
	private View gameView;
	private Action gameAction;
	private boolean keyPressed = false;
	private boolean controllerStart = false;
	private boolean keyReleased = false;
	private boolean actionPerformed = false;
	private boolean pauseButtonFlag = false;
	final int DRAW_DELAY = 30;
	
	public Controller() {
		gameView = new View();
		gameModel = new Model(gameView.getWidth(), gameView.getHeight(), gameView.getImgWidth(), gameView.getImgHeight());
		//gameView.updateButton(this);
		//gameView.addKeyListener(this);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Right arrow key 
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameView.setMovement("_right_");
		}
		
		//Left arrow key 
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gameView.setMovement("_left_");
		}
		
		//Up arrow key 
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			gameView.setMovement("_up_");
		}
		
		//Down arrow key 
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gameView.setMovement("_down_");
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

//-----------------------------------------------------------------------------------------------------
//JUnit Tests


class ControllerTest {
	Controller testController = new Controller();
	
	@Test
	public void testStart() {
		assertEquals(true, testController.getControllerStart());
	}
	
	@Test
	public void testKeyTyped() {
		assertEquals(true, testController.getKeyPressed());
	}
	
	@Test
	public void testKeyReleased() {
		assertEquals(true, testController.getKeyReleased());
	}
	
	@Test
	public void testActionPerformed() {
		assertEquals(true, testController.getActionPerformed());
	}
	
}

