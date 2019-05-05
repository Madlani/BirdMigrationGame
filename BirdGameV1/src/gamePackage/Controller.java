package gamePackage;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.AbstractAction;

public class Controller implements ActionListener, KeyListener {
	
	private Model gameModel;
	private View gameView;
	
	protected JPanel mainPanel;
	protected CardLayout cardLayout;
	
	SideSwiperView ssv;
	WhackAMoleView wmv;
	MigrationView mmv;
	
	protected JButton leftArrowKey;
	protected JButton rightArrowKey;
	protected JButton upArrowKey;
	protected JButton downArrowKey;

	
	boolean move = false;
	
	private Action gameAction;
	private boolean keyPressed = false;
	private boolean controllerStart = false;
	private boolean keyReleased = false;
	private boolean actionPerformed = false;
	private boolean pauseButtonFlag = false;
	
	public Controller() {
		
		//----------------------------------------------------------------
/*
//		gameView = new SideSwiperView(this);
//		gameView.addKeyListener(this);
		gameModel = new Model();
		
		// Creates the frame and selects settings
		JFrame frame = new JFrame();
		//frame.getContentPane().add(gameView);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // sets screen to full screen
		
		// Code to run SideSwiper Game
		ssv = new SideSwiperView();
		ssv.addKeyListener(this);
		//frame.add(ssv);
		
		// Code to run Whack a Mole Game
		wmv = new WhackAMoleView();
		wmv.addKeyListener(this);
		frame.add(wmv);
		
		// Code to run Migration Game
		mmv = new MigrationView();
		mmv.addKeyListener(this);
		//frame.add(mmv);

		frame.pack();
		frame.setVisible(true);*/
		//----------------------------------------------------------------
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // sets screen to full screen
		
		gameModel = new Model();
		
		CardLayout cardLayout = new CardLayout();
		JPanel mainPanel = new JPanel(cardLayout);
		frame.add(mainPanel);
		
		ActionListener al1 = e -> cardLayout.next(mainPanel);
		ssv = new SideSwiperView(al1);
		mmv = new MigrationView(al1);
		
        mainPanel.add(ssv);
        mainPanel.add(mmv);
        
        mainPanel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "space");
        mainPanel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "right");
        
        @SuppressWarnings("serial")
        Action spaceBarPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("space bar pressed");
                cardLayout.next(mainPanel);
            }
        };
        
        
        @SuppressWarnings("serial")
        Action rightArrowKeyPressed = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("right arrow pressed");
				gameModel.getOsprey().setLeftRightFlyState(1);
            }
        };
        
        mainPanel.getActionMap().put("space", spaceBarPressed);
        mainPanel.getActionMap().put("right", rightArrowKeyPressed);
        
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
		ArrayList<GameObject> list = gameModel.getUpdatableGameObjects();
		
		ssv.update(list);
		mmv.update(list);
	}

	public boolean repeat()	{
		SwingUtilities.invokeLater(() ->  this.ssv.repaint());
		SwingUtilities.invokeLater(() ->  this.mmv.repaint());

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
	
	// necessary methods to be implemented from super class
	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		//Right arrow key 
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			gameModel.getOsprey().setLeftRightFlyState(1);
			wmv.setLeftRightKeyState(1);
			wmv.setUpDownKeyState(0);
	
			wmv.repaint();
		}
		
		//Left arrow key 
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gameModel.getOsprey().setLeftRightFlyState(-1);
			wmv.setLeftRightKeyState(-1);
			wmv.setUpDownKeyState(0);
			
			wmv.repaint();
		}
		
		//Up arrow key 
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			gameModel.getOsprey().setFlyState(1);
			wmv.setUpDownKeyState(1);
			wmv.setLeftRightKeyState(0);
		

			wmv.repaint();
		}
		
		//Down arrow key 
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gameModel.getOsprey().setFlyState(-1);
			wmv.setUpDownKeyState(-1);
			wmv.setLeftRightKeyState(0);

			wmv.repaint();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
		}
		
	
	}
	
	public void setUpGameButtons() {
		
	}

	/** keyReleased()
	 * returns the bird to the default movement, which is forward
	 * sets the count to be the frame count
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			gameModel.getOsprey().setFlyState(0);
			wmv.setUpDownKeyState(0);
		}
		
		//Down arrow key 
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gameModel.getOsprey().setFlyState(0);
			wmv.setUpDownKeyState(0);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameModel.getOsprey().setLeftRightFlyState(0);
			wmv.setLeftRightKeyState(0);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gameModel.getOsprey().setLeftRightFlyState(0);
			wmv.setLeftRightKeyState(0);
		}
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