package gamePackage;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.AbstractAction;

public class Controller {
	
	private SideSwiperModel sideSwiperModel;
	private SideSwiperView sideSwipeView;
	private WhackAMoleView whackView;
	private WhackAMoleModel whackModel;
	private MigrationModel migrationModel;
	private MigrationView migrationView;
	private StartView startView;
	private EndView endView;
	
	private GameState state;
	private JFrame frame;
	private JPanel masterPanel;
	private CardLayout cardLayout;
	
	private boolean ssvPaused = false;
	private boolean mmvPaused = false;
	private boolean sideSwiperGameOver = false;
	private boolean migrationGameOver = false;
	
	private final int FPS = 15;
	
	public Controller() {
		
		sideSwiperModel = new SideSwiperModel();
		migrationModel = new MigrationModel();
		whackModel = new WhackAMoleModel();
		
		// Creates the frame and selects settings
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH); // sets screen to full screen
		
		// Code to run SideSwiper Game
		sideSwipeView = new SideSwiperView();
		
		// Code to run Whack a Mole Game
		whackView = new WhackAMoleView();
		
		// Code to run Migration Game
		migrationView = new MigrationView();
		
		//Code to display Start screen
		startView = new StartView();
		
		//Code to display End screen
		endView = new EndView();
		
		this.cardLayout = new CardLayout();
	
		//-----------------------------------------------------------------------------
		masterPanel = new JPanel(cardLayout);
		frame.add(masterPanel);
		masterPanel.add(startView, "start");
		masterPanel.add(sideSwipeView, "sideSwiper");	
		masterPanel.add(migrationView, "migration");
		masterPanel.add(whackView, "whackAMole");
		masterPanel.add(endView, "end");
		
		this.state = GameState.START;
		
		addKeyBinding(sideSwipeView, KeyEvent.VK_SPACE, "next panel from ssv", (e) -> { 
			
			this.state = GameState.MIGRATION;
			this.cardLayout.show(this.masterPanel, "migration");
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_SPACE, "next panel from wmv", (e) -> { 
			
			this.state = GameState.END;
			this.cardLayout.show(this.masterPanel, "end");
			
		}, false);
		
		addKeyBinding(migrationView, KeyEvent.VK_SPACE, "next panel from mmv", (e) -> {
			
			this.state = GameState.WHACKAMOLE;
			this.cardLayout.show(this.masterPanel, "whackAMole");
			whackModel.randomizeSequence();
			whackView.updateSequence(whackModel.getSequence());
			whackView.setIsWackView(true);
			whackView.initTimers();
		}, false);
		
		
		addKeyBinding(startView, KeyEvent.VK_SPACE, "next panel from start", (e) -> {
			
			this.state = GameState.SIDESWIPER;
			this.cardLayout.show(this.masterPanel, "sideSwiper");
			
		}, false);
		
		
		addKeyBinding(endView, KeyEvent.VK_SPACE, "next panel from end", (e) -> {
			this.state = GameState.START;
			this.cardLayout.show(this.masterPanel, "start");
			
		}, false);
		
		
		//-----------------------------------------------------------------------------
		
		setBindingsToSideSwiper();
		setBindingsToMigration();
		setBindingsToWhackAMole();
		
		frame.setVisible(true);
	}

	/**
	 * start()
	 * This method calls repeat(), which initializes the game views. We refresh the frame rate every 15 milliseconds.
	 */
	public void start() {
		while (repeat()) {
			try {
				Thread.sleep(FPS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * updateSideSwiperModel()
	 * This method continues to update the side swiper game and checks the logic to determine if an end state is reached.
	 * An end state is reached when the bird runs out of health; when this happens, the end screen is shown.
	 */
	public void updateSideSwiperModel() {
		ssvPaused = sideSwiperModel.updateLocationAndDirection();
		ArrayList<GameObject> list2 = sideSwiperModel.getUpdatableGameObjects();
		
		if (sideSwiperModel.getOsprey().getHealthCount() <= 0) {
			sideSwiperGameOver = true;
			this.state = GameState.END;
			sideSwiperModel.getOsprey().setFlyState(FlyState.STILL);
			for (int i = 1; i < list2.size(); i++) {
				sideSwiperModel.resetGameObjectLocation(list2.get(i));
			}
			gameOver();
		}
		
		sideSwipeView.update(list2);
	}
	
	/**
	 * This method continues to update the migration game and checks the logic to determine if an end state is reached.
	 * An end state is reached when the bird runs out of health; when this happens, the end screen is shown.
	 */
	public void updateMigrationModel() {
		mmvPaused = migrationModel.updateLocationAndDirection();
		ArrayList<GameObject> list3 = migrationModel.getUpdatableGameObjects();
		
		if (migrationModel.getOsprey().getHealthCount() <= 0) {
			migrationGameOver = true;
			this.state = GameState.END;
			migrationModel.getOsprey().setFlyState(FlyState.STILL);
			for (int i = 1; i < list3.size(); i++) {
				migrationModel.resetGameObjectLocation(list3.get(i));
			}
			gameOver();
		}
		migrationView.update(list3);
	}
	
	/**
	 * gameOver()
	 * This method resets the game components (including the health) so that if the user wants to play again, they can
	 * do that by hitting the space bar.
	 */
	public void gameOver() {
		if (sideSwiperGameOver) {
			sideSwiperModel.getOsprey().setHealthCount(3);
			this.cardLayout.show(this.masterPanel, "end");
			sideSwiperGameOver = false;
		}
		if (migrationGameOver) {
			migrationModel.getOsprey().setHealthCount(3);
			this.cardLayout.show(this.masterPanel, "end");
			migrationGameOver = false;
		}

	}

	/**
	 * repeat()
	 * The pause game flag in this method pauses the game state when the user collides with a question box. 
	 * The pause game flag is set to true to indicate a game stoppage. After the user answers the question, 
	 * the flag is reset to false and normal game play resumes.
	 * @return true, if the user is answering a question, false if playing the normal game.
	 */
	public boolean repeat() {
		if (sideSwiperModel.getPauseGameFlag() == false) {
			updateMode();
			drawView();
			return true;
		} else {
			Question q = new Question();
			//setBindingsToQuestions(q);
			q.displayQuestion();
			while(sideSwiperModel.getPauseGameFlag() == true) {
				sideSwiperModel.changePauseGameFlag();
				start();
			}
			return false;
		}
	}
	
	/**
	 * updateMode()
	 * Changes the state of the game based on the last state the game was in.
	 */
	public void updateMode() {
		SwingWorker<Void, Void> updateModelWorker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				switch (state) {
				case SIDESWIPER:
					if (!ssvPaused)
						updateSideSwiperModel();
					break;
				case MIGRATION:
					if (!mmvPaused)
						updateMigrationModel();
					break;
				case WHACKAMOLE:
//					whackModel.randomizeSequence();
//					whackView.updateSequence(whackModel.getSequence());
					updateWhackKeyState();
					break;
				case START:
					break;
				case END:
					
					break;
				}
				return null;
			}
		};
		
		updateModelWorker.execute();
	}
	
	/**
	 * updateWhackKeyState()
	 * Sets the key state in the view to match the key state in the model.
	 * Repaints the view to show the state change.
	 */
	public void updateWhackKeyState() {
		whackView.setKeyState(whackModel.getKeyState());
	}
	
	public void drawView() {
		switch (state) {
		case SIDESWIPER:
			SwingUtilities.invokeLater(() ->  this.sideSwipeView.repaint());
			
			break;
		case MIGRATION:
			SwingUtilities.invokeLater(() ->  this.migrationView.repaint());
			
			break;
		case WHACKAMOLE:
			break;
		case START:
			SwingUtilities.invokeLater(() ->  this.startView.repaint());
			break;
		case END:
			SwingUtilities.invokeLater(() ->  this.endView.repaint());
			break;
		}
	}
	
	/**
	 * addKeyBinding()
	 * Binds a key to an on-screen action, such as a button click.
	 * @param comp, the JComponent that key bindings will occur in.
	 * @param keyCode, the numerical representation of the key the user presses to trigger an action.
	 * @param id, a text version of the id to use as a key in the HashMap.
	 * @param actionListener, the type of action listener needed for the event.
	 * @param isReleased, a boolean to represent if the key needs to be released for the action to occur.
	 */
	@SuppressWarnings("serial")
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
	
	/**
	 * setBindingsToWhackAMole()
	 * Creates all the key bindings needed to use in the whack a mole game.
	 */
	public void setBindingsToWhackAMole() {
		addKeyBinding(whackView, KeyEvent.VK_RIGHT, "go right", (evt) -> {
			whackModel.setKeyState(3);
			System.out.println("right pressed whack");
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_LEFT, "go left", (evt) -> {
			whackModel.setKeyState(4);
			
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_UP, "go up", (evt) -> {
			whackModel.setKeyState(1);
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_DOWN, "go down", (evt) -> {
			whackModel.setKeyState(2);
			
		}, false);
	}
	
	/**
	 * setBindingsToMigration()
	 * Creates all the key bindings needed to use in the migration game.
	 */
	public void setBindingsToMigration() {
		addKeyBinding(migrationView, KeyEvent.VK_RIGHT, "go right", (evt) -> {
			System.out.println("right pressed");
			migrationModel.getOsprey().setFlyState(FlyState.RIGHT);
		}, false);
		
		addKeyBinding(migrationView, KeyEvent.VK_RIGHT, "go right release", (evt) -> {
			System.out.println("right released");
			migrationModel.getOsprey().setFlyState(FlyState.STILL);
		}, true);
		
		addKeyBinding(migrationView, KeyEvent.VK_LEFT, "go left", (evt) -> {
			migrationModel.getOsprey().setFlyState(FlyState.LEFT);
			
		}, false);
		
		addKeyBinding(migrationView, KeyEvent.VK_LEFT, "go left release", (evt) -> {
			migrationModel.getOsprey().setFlyState(FlyState.STILL);
			
		}, true);
	}
	
	/**
	 * setBingingsToSideSwiper()
	 * Creates all the key bindings needed to use in the side swiper game.
	 */
	public void setBindingsToSideSwiper() {
		addKeyBinding(sideSwipeView, KeyEvent.VK_UP, "go up", (evt) -> {
			sideSwiperModel.getOsprey().setFlyState(FlyState.UP);
		}, false);
		
		addKeyBinding(sideSwipeView, KeyEvent.VK_UP, "go up release", (evt) -> {
			sideSwiperModel.getOsprey().setFlyState(FlyState.STILL);
		}, true);
		
		addKeyBinding(sideSwipeView, KeyEvent.VK_DOWN, "go down", (evt) -> {
			sideSwiperModel.getOsprey().setFlyState(FlyState.DOWN);
		}, false);
		
		addKeyBinding(sideSwipeView, KeyEvent.VK_DOWN, "go down release", (evt) -> {
			sideSwiperModel.getOsprey().setFlyState(FlyState.STILL);
		}, true);
	}
	
}