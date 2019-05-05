package gamePackage;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.AbstractAction;

public class Controller {

	private Model gameModel;
	private View gameView;
	
	SideSwiperModel sideSwiperModel;
	SideSwiperView sideSwipeView;
	
	WhackAMoleView whackView;
	WhackAMoleModel whackModel;
	
	private MigrationModel migrationModel;
	MigrationView migrationView;

	
	boolean move = false;
	
	private Action gameAction;
	private boolean keyPressed = false;
	private boolean controllerStart = false;
	private boolean keyReleased = false;
	private boolean actionPerformed = false;
	private boolean pauseButtonFlag = false;	
	
	JFrame frame;
	JPanel masterPanel;
	private boolean paused;
	public Controller() {
		
//		gameView = new SideSwiperView(this);
//		gameView.addKeyListener(this);
		gameModel = new Model();
		sideSwiperModel = new SideSwiperModel();
		migrationModel = new MigrationModel();

		
		// Creates the frame and selects settings
		frame = new JFrame();
		//frame.getContentPane().add(gameView);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // sets screen to full screen
		
		// Code to run SideSwiper Game
		sideSwipeView = new SideSwiperView();
		//sideSwipeView.addKeyListener(this);
		//frame.add(sideSwipeView);
		
		// Code to run Whack a Mole Game
		whackView = new WhackAMoleView();
		//whackModel = new WhackAMoleModel();
		//whackView.addKeyListener(this);
		//frame.add(whackView);
		
		// Code to run Migration Game
		migrationView = new MigrationView();
		//migrationView.addKeyListener(this);
		//frame.add(migrationView);
		
		CardLayout cardLayout = new CardLayout();
		masterPanel = new JPanel(cardLayout);
		frame.add(masterPanel);
		masterPanel.add(sideSwipeView);
		masterPanel.add(whackView);
		masterPanel.add(migrationView);
		
		addKeyBinding(masterPanel, KeyEvent.VK_SPACE, "next panel from masterPanel", (evt) -> cardLayout.next(masterPanel), false);
		addKeyBinding(sideSwipeView, KeyEvent.VK_SPACE, "next panel from ssv", e -> cardLayout.next(masterPanel), false);
		addKeyBinding(whackView, KeyEvent.VK_SPACE, "next panel from wmv", e -> cardLayout.next(masterPanel), false);
		addKeyBinding(migrationView, KeyEvent.VK_SPACE, "next panel from mmv", e -> cardLayout.next(masterPanel), false);
		
		/*
		addKeyBinding(sideSwipeView, KeyEvent.VK_RIGHT, "go right", (evt) -> {
			gameModel.getOsprey().setLeftRightFlyState(1);
		}, false);
		
		addKeyBinding(sideSwipeView, KeyEvent.VK_RIGHT, "go right release", (evt) -> {
			gameModel.getOsprey().setLeftRightFlyState(0);
		}, true);
		
		addKeyBinding(sideSwipeView, KeyEvent.VK_LEFT, "go left", (evt) -> {
			gameModel.getOsprey().setLeftRightFlyState(-1);
			
		}, false);
		
		addKeyBinding(sideSwipeView, KeyEvent.VK_LEFT, "go left release", (evt) -> {
			gameModel.getOsprey().setLeftRightFlyState(0);
			
		}, true);
		
		*/
		
		addKeyBinding(sideSwipeView, KeyEvent.VK_UP, "go up", (evt) -> {
			sideSwiperModel.getOsprey().setFlyState(1);
		}, false);
		
		addKeyBinding(sideSwipeView, KeyEvent.VK_UP, "go up release", (evt) -> {
			sideSwiperModel.getOsprey().setFlyState(0);
		}, true);
		
		addKeyBinding(sideSwipeView, KeyEvent.VK_DOWN, "go down", (evt) -> {
			sideSwiperModel.getOsprey().setFlyState(-1);
		}, false);
		
		addKeyBinding(sideSwipeView, KeyEvent.VK_DOWN, "go down release", (evt) -> {
			sideSwiperModel.getOsprey().setFlyState(0);
		}, true);
		//-------------------------------------------------------------------------------------
		addKeyBinding(migrationView, KeyEvent.VK_RIGHT, "go right", (evt) -> {
			System.out.println("right pressed");
			migrationModel.getOsprey().setLeftRightFlyState(1);
		}, false);
		
		addKeyBinding(migrationView, KeyEvent.VK_RIGHT, "go right release", (evt) -> {
			System.out.println("right released");
			migrationModel.getOsprey().setLeftRightFlyState(0);
		}, true);
		
		addKeyBinding(migrationView, KeyEvent.VK_LEFT, "go left", (evt) -> {
			migrationModel.getOsprey().setLeftRightFlyState(-1);
			
		}, false);
		
		addKeyBinding(migrationView, KeyEvent.VK_LEFT, "go left release", (evt) -> {
			migrationModel.getOsprey().setLeftRightFlyState(0);
			
		}, true);
		//-------------------------------------------------------------------------------------
		addKeyBinding(whackView, KeyEvent.VK_RIGHT, "go right", (evt) -> {
			whackView.setLeftRightKeyState(1);
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_RIGHT, "go right release", (evt) -> {
			whackView.setLeftRightKeyState(0);
		}, true);
		
		addKeyBinding(whackView, KeyEvent.VK_LEFT, "go left", (evt) -> {
			whackView.setLeftRightKeyState(-1);
			
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_LEFT, "go left release", (evt) -> {
			whackView.setLeftRightKeyState(0);
			
		}, true);
		
		addKeyBinding(whackView, KeyEvent.VK_UP, "go up", (evt) -> {
			whackView.setUpDownKeyState(1);
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_UP, "go up release", (evt) -> {
			whackView.setUpDownKeyState(0);
		}, true);
		
		addKeyBinding(whackView, KeyEvent.VK_DOWN, "go down", (evt) -> {
			whackView.setUpDownKeyState(-1);
			
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_DOWN, "go down release", (evt) -> {
			whackView.setUpDownKeyState(0);
			
		}, true);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	//starts our game, initializes the beginning View.

	public void start() {
		while (repeat()) {
			try {
				Thread.sleep(15);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateModel() {
		gameModel.updateLocationAndDirection();
		sideSwiperModel.updateLocationAndDirection();
		migrationModel.updateLocationAndDirection();
		ArrayList<GameObject> list = gameModel.getUpdatableGameObjects();
		ArrayList<GameObject> list2 = sideSwiperModel.getUpdatableGameObjects();
		ArrayList<GameObject> list3 = migrationModel.getUpdatableGameObjects();

		sideSwipeView.update(list2);
		migrationView.update(list3);
		
		
	}

	public boolean repeat()	{
		SwingUtilities.invokeLater(() ->  this.sideSwipeView.repaint());
		SwingUtilities.invokeLater(() ->  this.migrationView.repaint());

		// update the model
		Thread t = new Thread((new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				updateModel();
			}
		}));
		t.start();
		return true;
		
	}
	
	
	public static void addKeyBinding(JComponent comp, int keyCode, String id, ActionListener actionListener, boolean isReleased) {
		InputMap inputMap = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = comp.getActionMap();
		
		

		inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, isReleased), id);
		actionMap.put(id, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actionListener.actionPerformed(e);

			}
		});
	}
		
	
	// necessary methods to be implemented from super class
//	@Override
//	public void keyTyped(KeyEvent e) {
//	
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		//Right arrow key 
//		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//			gameModel.getOsprey().setLeftRightFlyState(1);
//			whackView.setLeftRightKeyState(1);
//			whackView.setUpDownKeyState(0);
//	
//			whackView.repaint();
//		}
//		
//		//Left arrow key 
//		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//			gameModel.getOsprey().setLeftRightFlyState(-1);
//			whackView.setLeftRightKeyState(-1);
//			whackView.setUpDownKeyState(0);
//			
//			whackView.repaint();
//		}
//		
//		//Up arrow key 
//		if (e.getKeyCode() == KeyEvent.VK_UP) {
//			gameModel.getOsprey().setFlyState(1);
//			whackView.setUpDownKeyState(1);
//			whackView.setLeftRightKeyState(0);
//		
//
//			whackView.repaint();
//		}
//		
//		//Down arrow key 
//		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//			gameModel.getOsprey().setFlyState(-1);
//			whackView.setUpDownKeyState(-1);
//			whackView.setLeftRightKeyState(0);
//
//			whackView.repaint();
//		}
//	
//	}
//
//	/** keyReleased()
//	 * returns the bird to the default movement, which is forward
//	 * sets the count to be the frame count
//	 */
//	@Override
//	public void keyReleased(KeyEvent e) {
//		if (e.getKeyCode() == KeyEvent.VK_UP) {
//			gameModel.getOsprey().setFlyState(0);
//		}
//		
//		//Down arrow key 
//		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//			gameModel.getOsprey().setFlyState(0);
//		}
//		
//		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//			gameModel.getOsprey().setLeftRightFlyState(0);
//		}
//		
//		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//			gameModel.getOsprey().setLeftRightFlyState(0);
//		}
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if ("Paused".contentEquals(e.getActionCommand())) {
//			gameView.pauseButton.setFocusable(false);
//			pauseButtonFlag = !pauseButtonFlag;
//		}
//		
//	}
	
	
	
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