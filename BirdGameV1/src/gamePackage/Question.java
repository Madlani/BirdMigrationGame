package gamePackage;

import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Question {
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
														     														  
	private String[][] northernHarrierAnswers = new String[][] { 
														// 0						1 						2
														//CHOICE_A				CHOICE_B				CORRECT_ANSWER
													{ "Yes", 				"No", 					"No" },
													{ "Owls", 				"Mice", 				"Mice" },
													{ "High Up", 			"Down Low", 			"Down Low" },
													{ "Owl", 				"Hawk", 				"Owl" },
													{ "Mountains", 			"Estuaries", 			"Estuaries" },
													{ "Osprey", 			"Northern Harrier", 	"Northern Harrier" }  			
																														};												     														  
	
	public Question() {
		setQuestions();
	}
	
	/**
	 * setQuestions()
	 * This method initializes the question arrays for the osprey and northern harrier.
	 */
	public void setQuestions() {
		
		// sets up the question array for the Osprey
		setQuestionArray(new String[NUMBER_OSPREY_QUESTIONS]);
		ospreyQuestions[0] = "Where do ospreys migrate to for breeding?";
		ospreyQuestions[1] = "What do ospreys like to eat?";
		ospreyQuestions[2] = "Where do ospreys like to build their nests?";
		ospreyQuestions[3] = "Which animal is a predator to the osprey?";
		ospreyQuestions[4] = "How do ospreys fly?";
		ospreyQuestions[5] = "What bird are you playing as?";
		
		// sets up the question array for the Northern Harrier
		setQuestionArray(new String[NUMBER_NH_QUESTIONS]);
		northernHarrierQuestions[0] = "Is the Northern Harrier a migratory bird?";
		northernHarrierQuestions[1] = "What do Northern Harriers like to eat?";
		northernHarrierQuestions[2] = "Where do Northern Harriers like to build their nests?";
		northernHarrierQuestions[3] = "Which animal is a predator to the Northern Harrier?";
		northernHarrierQuestions[4] = "What environment do Northern Harriers live in?";
		northernHarrierQuestions[5] = "What bird are you playing as?";
	}

	public String[] getQuestionArray() {
		return ospreyQuestions;
	}

	public void setQuestionArray(String[] questionArray) {
		this.ospreyQuestions = questionArray;
	}
	
	/**
	 * displayQuestion()
	 * Generates a random number to select a random question.
	 * Displays the question with the appropriate answer choices for that question.
	 * Checks the answer against the correct one.
	 */
	public void displayQuestion() {
		// Generates a random number based on the number of possible questions
		int randQuestionNum = (int)(Math.random()*((NUMBER_OSPREY_QUESTIONS - 1) + 1));
		
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
        JButton B = new JButton(choiceB);
        
        optionPane.add(A);
        optionPane.add(B);
        
        dialog.setVisible(true);
        dialog.dispose();
	    
        // TODO: Fix the way the response is collected so we can check against the correct answer
	    String response = optionPane.getValue().toString();
	    
	    // ----------------------------------------------------------------------------------------------------
	    // NEED TO FIX THIS SECTION
	    
	    // Checking the correctness of the answer the user clicked
	    if(response.contentEquals(ospreyAnswers[randQuestionNum][CORRECT_ANSWER])) {
	    	A.setFocusable(true);
	    	A.doClick();
	    	System.out.println("A clicked");
	    	isCorrect = true;
	    }
	    else {
	    	B.setFocusable(true);
	    	B.doClick();
	    	System.out.println("B clicked");
	    	isCorrect = false;
	    }
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



