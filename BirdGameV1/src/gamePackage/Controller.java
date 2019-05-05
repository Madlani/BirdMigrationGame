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
	
	SideSwiperModel sideSwiperModel;
	SideSwiperView sideSwipeView;
	
	WhackAMoleView whackView;
	WhackAMoleModel whackModel;
	
	private MigrationModel migrationModel;
	MigrationView migrationView;
	
	StartView startView;
	
	EndView endView;
	
	JFrame frame;
	JPanel masterPanel;
	JPanel secondaryPanel;

	private final int FPS = 15;
	public Controller() {
		
		sideSwiperModel = new SideSwiperModel();
		migrationModel = new MigrationModel();
		whackModel = new WhackAMoleModel();
		
		// Creates the frame and selects settings
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // sets screen to full screen
		
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
		
		CardLayout cardLayout = new CardLayout();
		
		
	
		//-----------------------------------------------------------------------------
		masterPanel = new JPanel(cardLayout);
		frame.add(masterPanel);
		
		masterPanel.add(startView);
		masterPanel.add(sideSwipeView);
		masterPanel.add(whackView);
		masterPanel.add(migrationView);
		masterPanel.add(endView);
		
		addKeyBinding(masterPanel, KeyEvent.VK_SPACE, "next panel from masterPanel", (evt) -> cardLayout.next(masterPanel), false);
		addKeyBinding(masterPanel, KeyEvent.VK_1, "next panel from masterPanel", (evt) -> cardLayout.next(masterPanel), false);
		addKeyBinding(sideSwipeView, KeyEvent.VK_SPACE, "next panel from ssv", e -> cardLayout.next(masterPanel), false);
		addKeyBinding(whackView, KeyEvent.VK_SPACE, "next panel from wmv", e -> cardLayout.next(masterPanel), false);
		addKeyBinding(migrationView, KeyEvent.VK_SPACE, "next panel from mmv", e -> cardLayout.next(masterPanel), false);
		addKeyBinding(startView, KeyEvent.VK_SPACE, "next panel from start", e -> cardLayout.next(masterPanel), false);
		addKeyBinding(endView, KeyEvent.VK_SPACE, "next panel from end", e -> cardLayout.next(masterPanel), false);
		//-----------------------------------------------------------------------------

//		secondaryPanel = new JPanel(cardLayout);
//		frame.add(secondaryPanel);
//		
//		secondaryPanel.add(startView);
//		secondaryPanel.add(sideSwipeView);
//		secondaryPanel.add(whackView);
//		secondaryPanel.add(endView);
//		
//		addKeyBinding(secondaryPanel, KeyEvent.VK_1, "next panel from secondaryPanel", (evt) -> cardLayout.next(secondaryPanel), false);
//		addKeyBinding(sideSwipeView, KeyEvent.VK_1, "next panel from ssv", e -> cardLayout.next(masterPanel), false);
//		addKeyBinding(whackView, KeyEvent.VK_1, "next panel from wmv", e -> cardLayout.next(masterPanel), false);
//		addKeyBinding(migrationView, KeyEvent.VK_1, "next panel from mmv", e -> cardLayout.next(masterPanel), false);
//		addKeyBinding(startView, KeyEvent.VK_1, "next panel from start", e -> cardLayout.next(masterPanel), false);
//		addKeyBinding(endView, KeyEvent.VK_1, "next panel from end", e -> cardLayout.next(masterPanel), false);


		
		setBindingsToSideSwiper();
		setBindingsToMigration();
		setBindingsToWhackAMole();
		
		frame.pack();
		frame.setVisible(true);
	}
	
	//starts our game, initializes the beginning View.

	public void start() {
		while (repeat()) {
			try {
				Thread.sleep(FPS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateModel() {
		sideSwiperModel.updateLocationAndDirection();
		migrationModel.updateLocationAndDirection();
		
		ArrayList<GameObject> list2 = sideSwiperModel.getUpdatableGameObjects();
		ArrayList<GameObject> list3 = migrationModel.getUpdatableGameObjects();

		sideSwipeView.update(list2);
		migrationView.update(list3);
	}

	public boolean repeat()	{
		SwingUtilities.invokeLater(() ->  this.sideSwipeView.repaint());
		SwingUtilities.invokeLater(() ->  this.migrationView.repaint());
		SwingUtilities.invokeLater(() ->  this.startView.repaint());
		SwingUtilities.invokeLater(() ->  this.endView.repaint());


		// update the model
		Thread t = new Thread((new Runnable() {
			@Override
			public void run() {
				updateModel();
			}
		}));
		t.start();
		return true;
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
	
	public void setBindingsToWhackAMole() {
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
	}
	
	public void setBindingsToMigration() {
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
	}
	
	public void setBindingsToSideSwiper() {
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
	}
}