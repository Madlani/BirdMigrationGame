package gamePackage;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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
	private StartView startViewOsprey;
	private StartView startViewNorthernHarrier;
	private WinView winView;
	private EndView endView;
	private SideSwiperTutorialView sideTutView;
	private SideSwiperTutorialModel sideTutModel;
	private MigrationTutorialView migrationTutView;
	private MigrationTutorialModel migrationTutModel;
	
	private GameState state;
	private BirdType birdType;
	private JFrame frame;
	private JPanel masterPanel;
	private CardLayout cardLayout;
	
	private boolean ssvPaused = false;
	private boolean mmvPaused = false;
	private boolean sideSwiperGameOver = false;
	private boolean migrationGameOver = false;
	private boolean whackWillWin = false;
	private boolean whackWinner = false;
	
	private final int FPS = 15;
	private int count = 1;
	private ArrayList<Integer> whackUserSequence = new ArrayList<Integer>();
	
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
		startViewOsprey = new StartView(BirdType.OSPREY);
		startViewNorthernHarrier = new StartView(BirdType.NORTHERNHARRIER);
		
		//Code to display Win screen
		winView = new WinView();
				
		//Code to display End screen
		endView = new EndView();
		
		//Code to display SideSwiperTutorialView screen
		sideTutView = new SideSwiperTutorialView();
		
		//Code to display SideSwiperTutorialModel screen
		sideTutModel = new SideSwiperTutorialModel();
		
		//Code to display MigrationTutorialView screen
		migrationTutView = new MigrationTutorialView();
		
		//Code to display MigrationTutorialModel screen
		migrationTutModel = new MigrationTutorialModel();
		
		this.cardLayout = new CardLayout();
	
		//-----------------------------------------------------------------------------
		this.birdType = BirdType.OSPREY;
		masterPanel = new JPanel(cardLayout);
		frame.add(masterPanel);
		masterPanel.add(startViewOsprey, "startOsprey");
		masterPanel.add(sideTutView, "sideSwiperTutorial");
		masterPanel.add(sideSwipeView, "sideSwiper");	
		masterPanel.add(whackView, "whackAMole");
		masterPanel.add(winView, "win");
		masterPanel.add(startViewNorthernHarrier, "startNorthernHarrier");
		masterPanel.add(migrationTutView, "migrationTutorial");
		masterPanel.add(migrationView, "migration");
		masterPanel.add(endView, "end");
		
		this.state = GameState.START;

	
		//-----------------------------------------------------------------------------

		
		//Goes from startOsprey to side swiper tutorial
		addKeyBinding(startViewOsprey, KeyEvent.VK_SPACE, "next panel from start", (e) -> {
			this.state = GameState.SIDESWIPERTUTORIAL;
			this.cardLayout.show(this.masterPanel, "sideSwiperTutorial");
			System.out.println("should start tuturial now");
		
		}, false);
		
		//Goes from side swiper tutorial to side swiper game
		addKeyBinding(sideTutView, KeyEvent.VK_SPACE, "next panel from sideswiperTutorial", (e) -> {
			this.state = GameState.SIDESWIPER;
			this.cardLayout.show(this.masterPanel, "sideSwiper");
	
		}, false);
		
		//Goes from side swiper game to whack a mole game
		addKeyBinding(sideSwipeView, KeyEvent.VK_SPACE, "next panel from ssv", (e) -> {
			this.state = GameState.WHACKAMOLE;
			this.cardLayout.show(this.masterPanel, "whackAMole");
			whackModel.randomizeSequence();
			whackView.updateSequence(whackModel.getSequence());
			whackView.setIsWackView(true);
			whackView.initTimers();
		}, false);
		
		//Goes from whack a mole game to win screen if player wins
		addKeyBinding(whackView, KeyEvent.VK_SPACE, "next panel from wmv", (e) -> { 
			if (count % 2 != 0) {
				this.state = GameState.WIN;
				this.cardLayout.show(this.masterPanel, "win");
				count++;
			}
			else {
				this.state = GameState.END;
				this.cardLayout.show(this.masterPanel, "end");
				count++;
			}
		}, false);
		
		//Goes from win screen to migration tutorial
		addKeyBinding(winView, KeyEvent.VK_SPACE, "next panel from wmv", (e) -> { 
			
			this.state = GameState.START;
			this.birdType = BirdType.NORTHERNHARRIER;
			this.cardLayout.show(this.masterPanel, "startNorthernHarrier");
			whackView.resetTimers();
			whackModel.setKeyState(0);
			
		}, false);
		
		addKeyBinding(startViewNorthernHarrier, KeyEvent.VK_SPACE, "next panel from start", (e) -> {

			this.state = GameState.MIGRATIONTUTORIAL;
			this.cardLayout.show(this.masterPanel, "migrationTutorial");
			

		}, false);

		addKeyBinding(migrationTutView, KeyEvent.VK_SPACE, "next panel from winView", (e) -> {

			this.state = GameState.MIGRATION;
			this.cardLayout.show(this.masterPanel, "migration");
			System.out.println("should start tuturial now");
		}, false);

		

		addKeyBinding(migrationView, KeyEvent.VK_SPACE, "next panel from mmv", (e) -> {
			
			this.state = GameState.WHACKAMOLE;
			this.cardLayout.show(this.masterPanel, "whackAMole");
			whackModel.randomizeSequence();
			whackView.updateSequence(whackModel.getSequence());
			whackView.setIsWackView(true);
			whackView.initTimers();
			
		}, false);

		addKeyBinding(endView, KeyEvent.VK_SPACE, "next panel from end", (e) -> {
			this.state = GameState.START;
			this.cardLayout.show(this.masterPanel, "startOsprey");
			
			whackView.resetTimers();
			whackModel.setKeyState(0);
		}, false);
		
		//-----------------------------------------------------------------------------
		
		setBindingsToSideSwiperTutorial();
		setBindingsToSideSwiper();
		setBindingsToMigration();
		setBindingsToWhackAMole();
		
		frame.setVisible(true);
	}

	/**
	 * starts our game, initializes the beginning View.
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
	
	public void updateSideSwiperModel() {
		System.out.println(sideSwiperModel.getPicNumMap() % 9);
		if (sideSwiperModel.getPicNumMap() > 1)
			sideSwiperModel.setIsFirstFrame(false);
		
		sideSwipeView.setPicNumMap(sideSwiperModel.getPicNumMap());
		ssvPaused = sideSwiperModel.updateLocationAndDirectionForOsprey();
		ArrayList<GameObject> list2 = sideSwiperModel.getUpdatableGameObjectsForOsprey();
		
		if (sideSwiperModel.getOsprey().getHealthCount() <= 0) {
			sideSwiperGameOver = true;
			this.state = GameState.END;
			sideSwiperModel.getOsprey().setFlyState(FlyState.STILL);
			for (int i = 1; i < list2.size(); i++) {
				sideSwiperModel.resetGameObjectLocation(list2.get(i));
			}
			
			gameOver();
		}
		
		if (!sideSwiperModel.getIsFirstFrame() && sideSwiperModel.getPicNumMap() % 9 == 0) {
			this.state = GameState.WHACKAMOLE;
			this.cardLayout.show(this.masterPanel, "whackAMole");
			whackModel.randomizeSequence();
			whackView.updateSequence(whackModel.getSequence());
			whackView.setIsWackView(true);
			whackView.initTimers();
			sideSwiperModel.getOsprey().setFlyState(FlyState.STILL);
			for (int i = 1; i < list2.size(); i++) {
				sideSwiperModel.resetGameObjectLocation(list2.get(i));
			}
			sideSwiperModel.setIsFirstFrame(true);
			
		}
		
		if(sideSwiperModel.getIsHit() == true) {
			sideSwipeView.setTimeForRectangle(true);
			System.out.println("It is time to draw the red rectangle");
		}
		
		sideSwipeView.update(list2);
	}
	
	public void updateSideSwiperTutorialModel() {
		ssvPaused = sideTutModel.updateLocationAndDirectionForOsprey();
		ArrayList<GameObject> list2 = sideTutModel.getUpdatableGameObjectsForOsprey();

		if (sideTutModel.getOsprey().getHealthCount() <= 0) {
			//sideSwiperGameOver = true;
			//this.state = GameState.END;
			sideTutModel.getOsprey().setFlyState(FlyState.STILL);
			for (int i = 1; i < list2.size(); i++) {
				sideTutModel.resetGameObjectLocation(list2.get(i));
			}

			gameOver();
		}

		sideTutModel.getOsprey().setFlyState(FlyState.STILL);
		for (int i = 1; i < list2.size(); i++) {
			sideTutModel.resetGameObjectLocation(list2.get(i));
		}
		sideTutModel.setIsFirstFrame(true);

		if (sideTutModel.getIsHit() == true) {
			sideTutView.setTimeForRectangle(true);
			System.out.println("It is time to draw the red rectangle");
		}

		sideTutView.update(list2);
	}
	
	public void updateMigrationModel() {
		mmvPaused = migrationModel.updateLocationAndDirectionForNorthernHarrier();
		ArrayList<GameObject> list3 = migrationModel.getUpdatableGameObjectsForNorthernHarrier();
		
		if (migrationModel.getNorthernHarrier().getHealthCount() <= 0) {
			migrationGameOver = true;
			this.state = GameState.END;
			migrationModel.getNorthernHarrier().setFlyState(FlyState.STILL);
			for (int i = 1; i < list3.size(); i++) {
				migrationModel.resetGameObjectLocation(list3.get(i));
			}
			gameOver();
		}
		migrationView.update(list3);
	}
	
	public void gameOver() {
		if (sideSwiperGameOver) {
			sideSwiperModel.getOsprey().setHealthCount(3);
			this.cardLayout.show(this.masterPanel, "end");
			sideSwiperGameOver = false;
		}
		if (migrationGameOver) {
			migrationModel.getNorthernHarrier().setHealthCount(3);
			this.cardLayout.show(this.masterPanel, "end");
			migrationGameOver = false;
		}

	}
	
	public void winner() {
		if (whackUserSequence.size() == whackView.getEXPECTED_PATTERN_SIZE()) {
			System.out.println("user pattern reached 4");
			for (int i = 0; i < whackView.getEXPECTED_PATTERN_SIZE(); i++) {
				if (whackModel.getSequence().get(i) == whackUserSequence.get(i)) {
					whackWillWin = true;
					System.out.println("correct index");
				}
				else {
					whackWillWin = false;
					System.out.println("False index!");
				}
			}
			whackWinner = whackWillWin;
			if (whackWinner) {
				System.out.println("Winner!!!");
				
				this.cardLayout.show(this.masterPanel, "win");
				this.state = GameState.WIN;
			}
		}
		else {
			gameOver();
		}
	}

	public boolean repeat() {
		if (sideSwiperModel.getPauseGameFlag() == false) {
			updateMode();
			drawView();
			return true;
		} else {
			Question q = new Question(this.birdType);
			//setBindingsToQuestions(q);
			q.displayQuestion();
			while(sideSwiperModel.getPauseGameFlag() == true) {
				if(q.isCorrect()) {
					sideSwiperModel.getOsprey().increaseHealthCount(4);
					q.setCorrect(false);
				}
				sideSwiperModel.changePauseGameFlag();
				start();
			}
			return false;
		}
	}
	
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
					winner();
					break;
				case START:
					break;
				case WIN:
					whackUserSequence.clear();
					break;
				case END:
					whackUserSequence.clear();
					break;
				case SIDESWIPERTUTORIAL:
					if (!ssvPaused)
						updateSideSwiperTutorialModel();
					break;
				}
				return null;
			}
		};
		
		updateModelWorker.execute();
	}
	
	public void updateWhackKeyState()
	{
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
			SwingUtilities.invokeLater(() ->  this.winView.repaint());
			break;
		case START:
			SwingUtilities.invokeLater(() ->  this.startViewOsprey.repaint());
			SwingUtilities.invokeLater(() ->  this.startViewNorthernHarrier.repaint());
			break;
		case WIN:
			SwingUtilities.invokeLater(() ->  this.winView.repaint());
			break;
		case END:
			SwingUtilities.invokeLater(() ->  this.endView.repaint());
			break;
		case SIDESWIPERTUTORIAL:
			SwingUtilities.invokeLater(() -> this.sideTutView.repaint());
		}
	}
	
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
	
	//@SuppressWarnings("unchecked")
	public void setBindingsToWhackAMole() {
		addKeyBinding(whackView, KeyEvent.VK_RIGHT, "go right", (evt) -> {
			whackModel.setKeyState(3);
			whackUserSequence.add(4);
			
			Iterator i = whackUserSequence.iterator();
			while (i.hasNext()) {
				System.out.print("User Sequence: ");
				System.out.println(i.next());
			}
			
			System.out.println("User Sequence size: ");
			System.out.println(whackUserSequence.size());

		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_LEFT, "go left", (evt) -> {
			whackModel.setKeyState(4);
			whackUserSequence.add(3);
			
			Iterator i = whackUserSequence.iterator();
			while (i.hasNext()) {
				System.out.print("User Sequence: ");
				System.out.println(i.next());
			}
			
			System.out.println("User Sequence size: ");
			System.out.println(whackUserSequence.size());
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_UP, "go up", (evt) -> {
			whackModel.setKeyState(1);
			whackUserSequence.add(1);
			
			Iterator i = whackUserSequence.iterator();
			while (i.hasNext()) {
				System.out.print("User Sequence: ");
				System.out.println(i.next());
			}
			
			System.out.println("User Sequence size: ");
			System.out.println(whackUserSequence.size());
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_DOWN, "go down", (evt) -> {
			whackModel.setKeyState(2);
			whackUserSequence.add(2);

			Iterator i = whackUserSequence.iterator();
			while (i.hasNext()) {
				System.out.print("User Sequence: ");
				System.out.println(i.next());
			}
			
			System.out.println("User Sequence size: ");
			System.out.println(whackUserSequence.size());
		}, false);
	}
	
	public void setBindingsToMigration() {
		addKeyBinding(migrationView, KeyEvent.VK_RIGHT, "go right", (evt) -> {
			System.out.println("right pressed");
			migrationModel.getNorthernHarrier().setFlyState(FlyState.RIGHT);
		}, false);
		
		addKeyBinding(migrationView, KeyEvent.VK_RIGHT, "go right release", (evt) -> {
			System.out.println("right released");
			migrationModel.getNorthernHarrier().setFlyState(FlyState.STILL);
		}, true);
		
		addKeyBinding(migrationView, KeyEvent.VK_LEFT, "go left", (evt) -> {
			migrationModel.getNorthernHarrier().setFlyState(FlyState.LEFT);
			
		}, false);
		
		addKeyBinding(migrationView, KeyEvent.VK_LEFT, "go left release", (evt) -> {
			migrationModel.getNorthernHarrier().setFlyState(FlyState.STILL);
			
		}, true);
	}
	
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
	
	public void setBindingsToSideSwiperTutorial() {
		addKeyBinding(sideTutView, KeyEvent.VK_UP, "go up", (evt) -> {
			sideTutModel.getOsprey().setFlyState(FlyState.UP);
		}, false);
		
		addKeyBinding(sideTutView, KeyEvent.VK_UP, "go up release", (evt) -> {
			sideTutModel.getOsprey().setFlyState(FlyState.STILL);
		}, true);
		
		addKeyBinding(sideTutView, KeyEvent.VK_DOWN, "go down", (evt) -> {
			sideTutModel.getOsprey().setFlyState(FlyState.DOWN);
		}, false);
		
		addKeyBinding(sideTutView, KeyEvent.VK_DOWN, "go down release", (evt) -> {
			sideTutModel.getOsprey().setFlyState(FlyState.STILL);
		}, true);
	}
	
}