package gamePackage;

import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

public class Question {
	private BirdType birdType;
	private String[] ospreyQuestions;
	private final int NUMBER_OSPREY_QUESTIONS = 6;
	
	private String[] northernHarrierQuestions;
	private final int NUMBER_NH_QUESTIONS = 6;
	
	private final int CORRECT_ANSWER = 2; // the index where all the correct answers are stored
	
	private final int CHOICE_A = 0; // Represents the UP button on the JOptionPane
	private final int CHOICE_B = 1; // Represents the DOWN button on the JOptionPane
	
	private boolean isCorrect;
	
	private String[][] ospreyAnswers = new String[][] { //  0						1						2
														//CHOICE_A				CHOICE_B				CORRECT_ANSWER
													{"North America",		"South America",		"North America"},
												    {"Fish",				"Foxes",				"Fish"},
										    	    {"High Up",			    "Down Low",				"High Up"},
												    {"Fish",				"Fox",					"Fox"},
												    {"With a flock",		"By themselves",		"By themselves"},
												    {"Osprey",				"Northern Harrier",		"Osprey"}
														     														  };
														     														  
//	private String[][] northernHarrierAnswers = new String[][] { 
//														// 0						1 						2
//														//CHOICE_A				CHOICE_B				CORRECT_ANSWER
//													{ "Yes", 				"No", 					"No" },
//													{ "Owls", 				"Mice", 				"Mice" },
//													{ "High Up", 			"Down Low", 			"Down Low" },
//													{ "Owl", 				"Hawk", 				"Owl" },
//													{ "Mountains", 			"Estuaries", 			"Estuaries" },
//													{ "Osprey", 			"Northern Harrier", 	"Northern Harrier" }  			
//																														};												     														  
	
	public Question(BirdType birdType) {
		this.birdType = birdType;
		
		switch(birdType) {
		case OSPREY:
			setOspreyQuestions();	
//		case NORTHERNHARRIER:
//			setNorthernHarrierQuestions();
		}
	}
	
	/**
	 * setOspreyQuestions()
	 * This method initializes the question array for the osprey.
	 */
	public void setOspreyQuestions() {
		
		// sets up the question array for the Osprey
		setQuestionArray(new String[NUMBER_OSPREY_QUESTIONS]);
		ospreyQuestions[0] = "Where do ospreys migrate to for breeding?";
		ospreyQuestions[1] = "What do ospreys like to eat?";
		ospreyQuestions[2] = "Where do ospreys like to build their nests?";
		ospreyQuestions[3] = "Which animal is a predator to the osprey?";
		ospreyQuestions[4] = "How do ospreys fly?";
		ospreyQuestions[5] = "What bird are you playing as?";
	}
	
	/**
	 * setNorthernHarrierQuestions()
	 * This method initializes the question array for the northern harrier.
	 */
//	public void setNorthernHarrierQuestions() {
//		
//		// sets up the question array for the Northern Harrier
//		setQuestionArray(new String[NUMBER_NH_QUESTIONS]);
//		northernHarrierQuestions[0] = "Is the Northern Harrier a migratory bird?";
//		northernHarrierQuestions[1] = "What do Northern Harriers like to eat?";
//		northernHarrierQuestions[2] = "Where do Northern Harriers like to build their nests?";
//		northernHarrierQuestions[3] = "Which animal is a predator to the Northern Harrier?";
//		northernHarrierQuestions[4] = "What environment do Northern Harriers live in?";
//		northernHarrierQuestions[5] = "What bird are you playing as?";
//	}

	public String[] getQuestionArray() {
		return ospreyQuestions;
	}

	public void setQuestionArray(String[] questionArray) {
		switch(birdType) {
		case OSPREY:
			this.ospreyQuestions = questionArray;
		case NORTHERNHARRIER:
			this.northernHarrierQuestions = questionArray;
		}
		
	}
	
	/**
	 * displayQuestion()
	 * Generates a random number to select a random question.
	 * Displays the question with the appropriate answer choices for that question.
	 * Checks the answer against the correct one.
	 */
	public void displayQuestion() {
		// Generates a random number based on the number of possible questions
		System.out.println("Bird Type:" + this.birdType);
		int randQuestionNum = (int)(Math.random()*((NUMBER_OSPREY_QUESTIONS - 1) + 1));
		System.out.println("Random Question Number: " + randQuestionNum);
		
		/*
		// Sets up the option pane to display the questions and answer choices
		JOptionPane optionPane = new JOptionPane(ospreyQuestions[randQuestionNum], JOptionPane.PLAIN_MESSAGE);
        JDialog dialog = optionPane.createDialog("Option Dialog");
        
        // Allows the user to use the UP and DOWN keys to navigate the button options
        Set<AWTKeyStroke> focusTraversalKeys = new HashSet<AWTKeyStroke>(optionPane.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_UP, KeyEvent.VK_UNDEFINED));
        focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_DOWN, KeyEvent.VK_UNDEFINED));
        dialog.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, focusTraversalKeys);
        
        optionPane.removeAll(); // used to remove the default "OK" button on JOptionPanes
        
        // Adds the text for the question to the JOptionPane
        optionPane.add(new JLabel(ospreyQuestions[randQuestionNum]));
        
        // Pulls the two answer options for this particular question and assigns to a string to be used later
        String choiceA = ospreyAnswers[randQuestionNum][CHOICE_A];
        String choiceB = ospreyAnswers[randQuestionNum][CHOICE_B];
        
        // Fills the text of the buttons to be the two answers options
        JButton A = new JButton(choiceA);
        //UIManager.put("A", A.toString());
        JButton B = new JButton(choiceB);
        //UIManager.put("B", B.toString());       
        optionPane.add(A);
        optionPane.add(B);
        //dialog.getDefaultCloseOperation();
        
        dialog.setVisible(true);
        dialog.dispose();
	    
        // TODO: Fix the way the response is collected so we can check against the correct answer
	    String response = dialog.getWindowListeners().toString();
	    System.out.println("Window Listeners toString: " + response);
	    */
		
		String[] options = {ospreyAnswers[randQuestionNum][CHOICE_A], ospreyAnswers[randQuestionNum][CHOICE_B]};
		System.out.println("OptionA: " + ospreyAnswers[randQuestionNum][CHOICE_A] + ", OptionB: " + ospreyAnswers[randQuestionNum][CHOICE_B]);
		
		JPanel panel = new JPanel();
		//panel.add(new JLabel(ospreyQuestions[randQuestionNum]));
		
		String questionText = ospreyQuestions[randQuestionNum];
		
		JLabel longLabel = new JLabel();
		longLabel.setText("<html><body><font size=\"+2\">" + questionText + "</font><p>" +
		                  "Press TAB to switch between the answer choices" +
		                  "<p>Press SPACE to select your answer</body></html>");
		
//		JLabel instructions = new JLabel();
//		instructions.setText("\nPress TAB to switch buttons and press SPACE to select your answer.");
		panel.add(longLabel);
		
		//panel.add(new JLabel("Press TAB to switch buttons and press SPACE to select your answer."));
		
		System.out.println("Question: " + ospreyQuestions[randQuestionNum]);
		
		String choiceA = ospreyAnswers[randQuestionNum][CHOICE_A];
        String choiceB = ospreyAnswers[randQuestionNum][CHOICE_B];
		
//		JButton A = new JButton(choiceA);
//		JButton B = new JButton(choiceB);
		
		Set<AWTKeyStroke> focusTraversalKeys = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_UP, KeyEvent.VK_UNDEFINED));
        focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_DOWN, KeyEvent.VK_UNDEFINED));
        panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, focusTraversalKeys);
        
		int result = JOptionPane.showOptionDialog(null, panel, "Question",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);
		
		if(result == 0 && choiceA.equals(ospreyAnswers[randQuestionNum][CORRECT_ANSWER]))
			JOptionPane.showMessageDialog(null, "Correct!");
		else if(result == 1 && choiceB.equals(ospreyAnswers[randQuestionNum][CORRECT_ANSWER]))
			JOptionPane.showMessageDialog(null, "Correct!");
		else
			JOptionPane.showMessageDialog(null, "Incorrect");
	    
	    // ----------------------------------------------------------------------------------------------------
	    // NEED TO FIX THIS SECTION
	    
	    // Checking the correctness of the answer the user clicked
//	    if(response.contentEquals(ospreyAnswers[randQuestionNum][CORRECT_ANSWER])) {
//	    	A.setFocusable(true);
//	    	A.doClick();
//	    	System.out.println("A clicked");
//	    	isCorrect = true;
//	    }
//	    else {
//	    	B.setFocusable(true);
//	    	B.doClick();
//	    	System.out.println("B clicked");
//	    	isCorrect = false;
//	    }
	    // ----------------------------------------------------------------------------------------------------
	}

	protected static JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane = null;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent)parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}



