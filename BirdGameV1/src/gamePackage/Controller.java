package gamePackage;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class Controller {

	private Model gameModel;
	private View gameView;
	SideSwiperView ssv;
	WhackAMoleView wmv;
	MigrationView mmv;

	
	boolean move = false;
	
	private Action gameAction;
	private boolean keyPressed = false;
	private boolean controllerStart = false;
	private boolean keyReleased = false;
	private boolean actionPerformed = false;
	private boolean pauseButtonFlag = false;
	JFrame frame;
	private boolean paused;
	
	public Controller() {
		
//		gameView = new SideSwiperView(this);
//		gameView.addKeyListener(this);
		gameModel = new Model();
		
		// Creates the frame and selects settings
		frame = new JFrame();
		//frame.getContentPane().add(gameView);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // sets screen to full screen
		
		// Code to run SideSwiper Game
		ssv = new SideSwiperView();
		//ssv.addKeyListener(this);
		//frame.add(ssv);
		
		// Code to run Whack a Mole Game
		wmv = new WhackAMoleView();
		//wmv.addKeyListener(this);
		//frame.add(wmv);
		
		// Code to run Migration Game
		mmv = new MigrationView();
		//mmv.addKeyListener(this);
		//frame.add(mmv);
		
		frame.pack();
		frame.setVisible(true);

		CardLayout cardLayout = new CardLayout();
		JPanel masterPanel = new JPanel(cardLayout);
		frame.add(masterPanel);
		masterPanel.add(ssv);
		masterPanel.add(wmv);
		masterPanel.add(mmv);
		
		addKeyBinding(masterPanel, KeyEvent.VK_SPACE, "next panel from masterPanel", (evt) -> cardLayout.next(masterPanel), false);
		addKeyBinding(ssv, KeyEvent.VK_SPACE, "next panel from ssv", e -> cardLayout.next(masterPanel), false);
		addKeyBinding(wmv, KeyEvent.VK_SPACE, "next panel from wmv", e -> cardLayout.next(masterPanel), false);
		addKeyBinding(mmv, KeyEvent.VK_SPACE, "next panel from mmv", e -> cardLayout.next(masterPanel), false);
		
		
		addKeyBinding(ssv, KeyEvent.VK_RIGHT, "go right", (evt) -> {
			gameModel.getOsprey().setLeftRightFlyState(1);
		}, false);
		
		addKeyBinding(ssv, KeyEvent.VK_RIGHT, "go right release", (evt) -> {
			gameModel.getOsprey().setLeftRightFlyState(0);
		}, true);
		
		addKeyBinding(ssv, KeyEvent.VK_LEFT, "go left", (evt) -> {
			gameModel.getOsprey().setLeftRightFlyState(-1);
			
		}, false);
		
		addKeyBinding(ssv, KeyEvent.VK_LEFT, "go left release", (evt) -> {
			gameModel.getOsprey().setLeftRightFlyState(0);
			
		}, true);
		
		addKeyBinding(ssv, KeyEvent.VK_UP, "go up", (evt) -> {
			gameModel.getOsprey().setFlyState(1);
		}, false);
		
		addKeyBinding(ssv, KeyEvent.VK_UP, "go up release", (evt) -> {
			gameModel.getOsprey().setFlyState(0);
		}, true);
		
		addKeyBinding(ssv, KeyEvent.VK_DOWN, "go down", (evt) -> {
			gameModel.getOsprey().setFlyState(-1);
		}, false);
		
		addKeyBinding(ssv, KeyEvent.VK_DOWN, "go down release", (evt) -> {
			gameModel.getOsprey().setFlyState(0);
		}, true);
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
		paused = gameModel.updateLocationAndDirection();
		ArrayList<GameObject> list = gameModel.getUpdatableGameObjects();
		ssv.update(list);
	}

	public boolean repeat()	{
		// update the model
		Thread t = new Thread((new Runnable() {
			@Override
			public void run() {
				if (!paused) {
					updateModel();
					
				} else {
				}
			}
		}));
		t.start();
		SwingUtilities.invokeLater(() -> ssv.repaint());
		
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