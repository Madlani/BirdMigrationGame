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
	private boolean wam = true;
	
	private SideSwiperModel sideSwiperModel;
	private SideSwiperView sideSwipeView;

	private WhackAMoleView whackView;
	private WhackAMoleModel whackModel;
	
	private MigrationModel migrationModel;
	private MigrationView migrationView;
	
	private StartView startViewOsprey;
	private StartView startViewNorthernHarrier;
	
	private OspreyWinView ospreyWinView;
	private LoseView loseView;
	private WinView winView;
	
	private GameState state;
	

	private BirdType birdType;
	private JFrame frame;
	private JPanel masterPanel;
	private CardLayout cardLayout;
	
	private boolean ssvPaused = false;
	private boolean mmvPaused = false;
	private boolean sideSwiperGameOver = false;
	private boolean migrationGameOver = false;
	private boolean whackWillWin;
	private boolean whackWillNotWin;
	
	private int whackSwitch = 1;
	
	private boolean whackWinner = false;//change to int to handle if win from osprey or northern harrier
	
	private final int FPS = 15;
	private int count = 1;
	private ArrayList<Integer> whackUserSequence = new ArrayList<Integer>();
	
	public Controller() {
		init();
	}
	
	public void init() {
		this.birdType = BirdType.OSPREY;
		this.state = GameState.START;
		
		instantiateModels();
		instantiateViews();
		createJFrame();
		addPanelsToCardLayout();
		initializeKeyBindings();
		
		frame.setVisible(true);
	}
	
	private void instantiateModels() {
		sideSwiperModel = new SideSwiperModel();
		migrationModel = new MigrationModel();
		whackModel = new WhackAMoleModel();
	}
	
	private void instantiateViews() {
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
		ospreyWinView = new OspreyWinView();
				
		//Code to display End screen
		loseView = new LoseView();
		winView = new WinView();
	}
	
	private void createJFrame() {
		// Creates the frame and selects settings
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH); // sets screen to full screen
	}
	
	private void addPanelsToCardLayout() {
		this.cardLayout = new CardLayout();
		masterPanel = new JPanel(cardLayout);
		frame.add(masterPanel);
		
		masterPanel.add(startViewOsprey, "startOsprey");
		masterPanel.add(sideSwipeView, "sideSwiper");	
		masterPanel.add(whackView, "whackAMole");
		masterPanel.add(ospreyWinView, "ospreywin");
		masterPanel.add(startViewNorthernHarrier, "startNorthernHarrier");
		masterPanel.add(migrationView, "migration");
		masterPanel.add(loseView, "lose");
		masterPanel.add(winView, "win");
	}
	
	private void initializeKeyBindings() {
		
		//Goes from startOsprey to side swiper tutorial
		addKeyBinding(startViewOsprey, KeyEvent.VK_T, "next panel from start", (e) -> {
			this.state = GameState.SIDESWIPER;
			this.cardLayout.show(this.masterPanel, "sideSwiper");
			System.out.println("should start tuturial now");
		
		}, false);
		
		
		//Goes from side swiper game to whack a mole game
		addKeyBinding(sideSwipeView, KeyEvent.VK_0, "next panel from ssv", (e) -> {
			this.state = GameState.WHACKAMOLE;
			this.cardLayout.show(this.masterPanel, "whackAMole");
			whackModel.randomizeSequence();
			whackView.updateSequence(whackModel.getSequence());
			whackView.setIsWackView(true);
			whackView.initTimers();
		}, false);
		
		//Goes from whack a mole game to win screen if player wins
		addKeyBinding(whackView, KeyEvent.VK_0, "next panel from wmv", (e) -> { 
			if (count % 3 == 1) {
				this.state = GameState.OSPREYWIN;
				this.cardLayout.show(this.masterPanel, "ospreywin");
				count++;
			}
			else if (count % 3 == 2) {
				this.state = GameState.WIN;
				this.cardLayout.show(this.masterPanel, "win");
				count++;
			}
			else {
				this.state = GameState.LOSE;
				this.cardLayout.show(this.masterPanel, "lose");
				count++; //Just a test
			}
//			whackModel.randomizeSequence();
//			whackView.updateSequence(whackModel.getSequence());
//			whackView.setIsWackView(true);
//			whackView.initTimers();
		}, false);
		
		//Goes from win screen to migration tutorial
		addKeyBinding(ospreyWinView, KeyEvent.VK_SPACE, "next panel from wmv", (e) -> { 
			
			this.state = GameState.START;
			this.birdType = BirdType.NORTHERNHARRIER;
			this.cardLayout.show(this.masterPanel, "startNorthernHarrier");
			setBindingsToWhackAMoleNULL();
			whackView.resetTimers();
			whackModel.setKeyState(0);
			
			whackView.resetIndex();
			
		}, false);
		
		addKeyBinding(startViewNorthernHarrier, KeyEvent.VK_T, "next panel from start", (e) -> {

			this.state = GameState.MIGRATION;
			this.cardLayout.show(this.masterPanel, "migration");
			

		}, false);

		
		addKeyBinding(migrationView, KeyEvent.VK_0, "next panel from mmv", (e) -> {
			
			this.state = GameState.WHACKAMOLE;
			this.cardLayout.show(this.masterPanel, "whackAMole");
			whackModel.randomizeSequence();
			whackView.updateSequence(whackModel.getSequence());
			whackView.setIsWackView(true);
			whackView.initTimers();
			
		}, false);

		addKeyBinding(loseView, KeyEvent.VK_SPACE, "next panel from end", (e) -> {
			this.state = GameState.START;
			this.cardLayout.show(this.masterPanel, "startOsprey");
			setBindingsToWhackAMoleNULL();
			whackView.resetTimers();
			whackModel.setKeyState(0);
			whackView.resetIndex();
			
		}, false);
		
		addKeyBinding(winView, KeyEvent.VK_SPACE, "next panel from end", (e) -> {
			this.state = GameState.START;
			this.cardLayout.show(this.masterPanel, "startOsprey");
			this.birdType = BirdType.OSPREY;
			setBindingsToWhackAMoleNULL();
			whackView.resetTimers();
			whackModel.setKeyState(0);
		}, false);
		
//		setBindingsToSideSwiperTutorial();
		setBindingsToSideSwiper();
		setBindingsToMigration();
		//setBindingsToWhackAMole();
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
		//System.out.println(sideSwiperModel.getPicNumMap() % 9);
		if (sideSwiperModel.getPicNumMap() > 1)
			sideSwiperModel.setIsFirstFrame(false);
		
		sideSwipeView.setState(sideSwiperModel.getState());
		sideSwipeView.setPicNumMap(sideSwiperModel.getPicNumMap());
		ssvPaused = sideSwiperModel.updateLocationAndDirectionForOsprey();
		ArrayList<GameObject> list2 = sideSwiperModel.getUpdatableGameObjectsForOsprey();
		
		if (sideSwiperModel.getOsprey().getHealthCount() <= 0) {
			sideSwiperGameOver = true;
			this.state = GameState.LOSE;
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
	
	public void updateMigrationModel() {
		if (migrationModel.getPicNumMap() > 1) {
			migrationModel.setFirstFrame(false);
		}
		
		migrationView.setState(migrationModel.getState());
		migrationView.setPicNumMap(migrationModel.getPicNumMap());
		mmvPaused = migrationModel.updateLocationAndDirectionForNorthernHarrier();
		ArrayList<GameObject> list3 = migrationModel.getUpdatableGameObjectsForNorthernHarrier();
		
		if (migrationModel.getNorthernHarrier().getHealthCount() <= 0) {
			migrationGameOver = true;
			this.state = GameState.LOSE;
			migrationModel.getNorthernHarrier().setFlyState(FlyState.STILL);
			for (int i = 1; i < list3.size(); i++) {
				migrationModel.resetGameObjectLocation(list3.get(i));
			}
			gameOver();
		}
		
		if (!migrationModel.getFirstFrame() && migrationModel.getPicNumMap() % 13 == 0) {
			this.state = GameState.WHACKAMOLE;
			this.cardLayout.show(this.masterPanel, "whackAMole");
			whackModel.randomizeSequence();
			whackView.updateSequence(whackModel.getSequence());
			whackView.setIsWackView(true);
			whackView.initTimers();
			migrationModel.getNorthernHarrier().setFlyState(FlyState.STILL);
			for (int i = 1; i < list3.size(); i++) {
				migrationModel.resetGameObjectLocation(list3.get(i));
			}
			migrationModel.setFirstFrame(true);
			
		}
		
		migrationView.update(list3);
	}
	
	public void gameOver() {
		
		if (sideSwiperGameOver) {
			sideSwiperModel.init();
			migrationModel.init();
			sideSwiperModel.getOsprey().setHealthCount(3);
			this.cardLayout.show(this.masterPanel, "lose");
			sideSwiperGameOver = false;
		}
		if (migrationGameOver) {
			sideSwiperModel.init();
			migrationModel.init();
			migrationModel.getNorthernHarrier().setHealthCount(3);
			this.cardLayout.show(this.masterPanel, "lose");
			migrationGameOver = false;
		}

	}
	
	public void winner() {
		whackWillNotWin = false;
		whackWillWin = false;
		if (whackUserSequence.size() == whackView.getEXPECTED_PATTERN_SIZE()) {
			System.out.println("user pattern reached 4");
			this.wam = true;
			for (int i = 0; i < whackView.getEXPECTED_PATTERN_SIZE(); i++) {
				if (whackModel.getSequence().get(i) == whackUserSequence.get(i)) {
					whackWillWin = true;
					System.out.println("correct index");
				}
				else {
					whackWillNotWin = true;
					System.out.println("False index!");
				}
			}
			whackWinner = whackWillWin && !whackWillNotWin;
			if (whackWinner) {
				System.out.println("Winner!!!");
				if (this.whackSwitch % 2 != 0) {
					this.state = GameState.OSPREYWIN;
					this.cardLayout.show(this.masterPanel, "ospreywin");
					this.whackSwitch++;
				}
				else {
					this.state = GameState.WIN;
					this.cardLayout.show(this.masterPanel, "win");
					this.whackSwitch++;
					sideSwiperModel.init();
					migrationModel.init();
				}
				
//				this.cardLayout.show(this.masterPanel, "ospreywin");
//				this.state = GameState.OSPREYWIN;
			}
			if (!whackWinner && this.birdType == BirdType.OSPREY) {
				System.out.println("Loser!!!");
				
				
				//als;dfl;aksjf;lkajsd;lfkjas;dlkfja;lsdkjf;alksdf;lkajsd;flkjasd;lkja;slkdf;laksjdf;lakjsdf;lkjasd;lfkja;sdokja;lksd
				this.whackSwitch = 1;
				this.cardLayout.show(this.masterPanel, "lose");
				this.state = GameState.LOSE;
				sideSwiperModel.init();
				migrationModel.init();
			} else if (!whackWinner && this.birdType == BirdType.NORTHERNHARRIER) {
				System.out.println("Loser!!!");
				
				
				//als;dfl;aksjf;lkajsd;lfkjas;dlkfja;lsdkjf;alksdf;lkajsd;flkjasd;lkja;slkdf;laksjdf;lakjsdf;lkjasd;lfkja;sdokja;lksd
				this.whackSwitch = 1;
				this.cardLayout.show(this.masterPanel, "lose");
				this.state = GameState.LOSE;
				sideSwiperModel.init();
				migrationModel.init();
			}
		}
//		else {
//			gameOver();
//		}
	}

    /**
     * repeat()
     * Calls an overloaded version of repeat() which is conditional on the bird type.
     * @return a boolean to represent if the game should repeat
     */
    public boolean repeat() {
        
        boolean shouldRepeat = false;;
        if (this.state == GameState.WHACKAMOLE && whackView.getDrawed() && this.wam) {
			System.out.println("ASLKD;FL;ASKJD;FLKJAS;LDKJFAL;SKJDF;LAKSJD;FLKJAS;DLKJFA;LSKDF;ALSKJDF;LKJASD;LKFJA;SLDKJF;ASDJ");
			setBindingsToWhackAMole();
			this.wam = false;
		}
        
        switch(birdType) {
            case OSPREY:
                shouldRepeat = repeat(birdType, this.sideSwiperModel, this.sideSwiperModel.getOsprey());
                break;
            case NORTHERNHARRIER:
                shouldRepeat = repeat(birdType, this.migrationModel, this.migrationModel.getNorthernHarrier());
                break;
        }
        
        return shouldRepeat;
    }
    
    /**
     * repeat()
     * Updates the current mode and view if the game is not paused.
     * Displays a question when the game is paused. If the question is answered correctly, health is rewarded.
     * If the question is answered incorrectly, health is depleted.
     * @param birdType, the current birdType of the game
     * @param currentModel, the current model the game is playing with
     * @param bird, the current bird the game is using
     * @return true if the game should repeat; false otherwise
     */
    public boolean repeat(BirdType birdType, Model currentModel, Bird bird) {
        if (currentModel.getPauseGameFlag() == false) {
            updateMode();
            drawView();
            return true;
        } else {
            Question q = new Question(birdType);
            q.displayQuestion();
            while(currentModel.getPauseGameFlag() == true) {
                if(q.isCorrect()) {
                    bird.increaseHealthCount(4);
                    q.setCorrect(false);
                } else {
                    bird.decreaseHealthCount();
                    q.setCorrect(false);
                }
                currentModel.changePauseGameFlag();
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
					updateWhackKeyState();
					winner();
					break;
				case START:
					
					break;
				case OSPREYWIN:
					whackUserSequence.clear();
					break;
				case LOSE:
					whackUserSequence.clear();
					//gameOver();
//					whackView.resetIndex();
					break;
				case WIN:
					whackUserSequence.clear();
					//gameOver();
					
				}
				return null;
			}
		};
		
		updateModelWorker.execute();
	}
	
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
			SwingUtilities.invokeLater(() ->  this.ospreyWinView.repaint());
			break;
		case START:
			SwingUtilities.invokeLater(() ->  this.startViewOsprey.repaint());
			SwingUtilities.invokeLater(() ->  this.startViewNorthernHarrier.repaint());
			break;
		case OSPREYWIN:
			SwingUtilities.invokeLater(() ->  this.ospreyWinView.repaint());
			break;
		case LOSE:
			SwingUtilities.invokeLater(() ->  this.loseView.repaint());
			break;
//		case SIDESWIPERTUTORIAL:
//			SwingUtilities.invokeLater(() -> this.sideTutView.repaint());
		case WIN:
			SwingUtilities.invokeLater(() -> this.winView.repaint());
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
	
	public void setBindingsToWhackAMoleNULL() {
		addKeyBinding(whackView, KeyEvent.VK_RIGHT, "go right", (evt) -> {
//			whackModel.setKeyState(3);
//			whackUserSequence.add(4);
//			
//			Iterator i = whackUserSequence.iterator();
//			while (i.hasNext()) {
//				System.out.print("User Sequence: ");
//				System.out.println(i.next());
//			}
//			
//			System.out.println("User Sequence size: ");
//			System.out.println(whackUserSequence.size());

		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_LEFT, "go left", (evt) -> {
//			whackModel.setKeyState(4);
//			whackUserSequence.add(3);
//			
//			Iterator i = whackUserSequence.iterator();
//			while (i.hasNext()) {
//				System.out.print("User Sequence: ");
//				System.out.println(i.next());
//			}
//			
//			System.out.println("User Sequence size: ");
//			System.out.println(whackUserSequence.size());
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_UP, "go up", (evt) -> {
//			whackModel.setKeyState(1);
//			whackUserSequence.add(1);
//			
//			Iterator i = whackUserSequence.iterator();
//			while (i.hasNext()) {
//				System.out.print("User Sequence: ");
//				System.out.println(i.next());
//			}
//			
//			System.out.println("User Sequence size: ");
//			System.out.println(whackUserSequence.size());
		}, false);
		
		addKeyBinding(whackView, KeyEvent.VK_DOWN, "go down", (evt) -> {
//			whackModel.setKeyState(2);
//			whackUserSequence.add(2);
//
//			Iterator i = whackUserSequence.iterator();
//			while (i.hasNext()) {
//				System.out.print("User Sequence: ");
//				System.out.println(i.next());
//			}
//			
//			System.out.println("User Sequence size: ");
//			System.out.println(whackUserSequence.size());
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
	
	public SideSwiperModel getSideSwiperModel() {
		return sideSwiperModel;
	}

	public SideSwiperView getSideSwipeView() {
		return sideSwipeView;
	}

	public MigrationModel getMigrationModel() {
		return migrationModel;
	}

	public MigrationView getMigrationView() {
		return migrationView;
	}
	
	public GameState getState() {
		return state;
	}
}
