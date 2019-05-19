package gamePackage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Question {
	private BirdType birdType;
	
	private String[] ospreyQuestions;
	private final int NUMBER_OSPREY_QUESTIONS = 6;
	
	private String[] northernHarrierQuestions;
	private final int NUMBER_NH_QUESTIONS = 6;
	
	private final int CHOICE_A = 0; 		// Represents the UP button on the JOptionPane
	private final int CHOICE_B = 1; 		// Represents the DOWN button on the JOptionPane
	private final int CORRECT_ANSWER = 2; 	// the index where all the correct answers are stored
	private final int LEFT_BUTTON = 0;
	private final int RIGHT_BUTTON = 0;
	
	private boolean isCorrect;
	
	private String[][] ospreyAnswers;													     														  
	private String[][] northernHarrierAnswers;											     														  
	
	public Question(BirdType birdType) {
		this.birdType = birdType;
		
		switch(birdType) {
		case OSPREY:
			setOspreyQuestions();
			break;	
		case NORTHERNHARRIER:
			setNorthernHarrierQuestions();
			break;
		}
	}
	
	/**
	 * setOspreyQuestions()
	 * This method initializes the question array for the osprey.
	 * It also initializes the answer choices for each question.
	 */
	public void setOspreyQuestions() {
		
		// sets up the question array for the Osprey
		ospreyQuestions = new String[NUMBER_OSPREY_QUESTIONS];
		ospreyQuestions[0] = "Where do ospreys migrate to for breeding?";
		ospreyQuestions[1] = "What do ospreys like to eat?";
		ospreyQuestions[2] = "Where do ospreys like to build their nests?";
		ospreyQuestions[3] = "Which animal is a predator to the osprey?";
		ospreyQuestions[4] = "How do ospreys fly?";
		ospreyQuestions[5] = "What bird are you playing as?";
		
		ospreyAnswers = new String[][] { //  0						1						2
										//CHOICE_A				CHOICE_B				CORRECT_ANSWER
										{"North America",		"South America",		"North America"},
									    {"Fish",				"Foxes",				"Fish"},
									    {"High Up",			    "Down Low",				"High Up"},
									    {"Fish",				"Fox",					"Fox"},
									    {"With a flock",		"By themselves",		"By themselves"},
									    {"Osprey",				"Northern Harrier",		"Osprey"}
			     														  								};
	}
	
	/**
	 * setNorthernHarrierQuestions()
	 * This method initializes the question array for the northern harrier.
	 * This method also initializes the answer choices for each question.
	 */
	public void setNorthernHarrierQuestions() {
		
		// sets up the question array for the Northern Harrier
		northernHarrierQuestions = new String[NUMBER_NH_QUESTIONS];
		northernHarrierQuestions[0] = "Is the Northern Harrier a migratory bird?";
		northernHarrierQuestions[1] = "What do Northern Harriers like to eat?";
		northernHarrierQuestions[2] = "Where do Northern Harriers like to build their nests?";
		northernHarrierQuestions[3] = "Which animal is a predator to the Northern Harrier?";
		northernHarrierQuestions[4] = "What environment do Northern Harriers live in?";
		northernHarrierQuestions[5] = "What bird are you playing as?";
		
		northernHarrierAnswers = new String[][] { 
											// 0						1 						2
											//CHOICE_A				CHOICE_B				CORRECT_ANSWER
											{ "Yes", 				"No", 					"No" },
											{ "Owls", 				"Mice", 				"Mice" },
											{ "High Up", 			"Down Low", 			"Down Low" },
											{ "Owl", 				"Hawk", 				"Owl" },
											{ "Mountains", 			"Estuaries", 			"Estuaries" },
											{ "Osprey", 			"Northern Harrier", 	"Northern Harrier" }  			
																												};	
	}
	
	/**
	 * displayQuestion()
	 * Calls an overloaded method based on the bird type so that the overloaded method can display the proper questions.
	 */
	public void displayQuestion() {
		
		switch(birdType) {
		case OSPREY:
			displayQuestion(ospreyQuestions, ospreyAnswers, NUMBER_OSPREY_QUESTIONS);
			break;
		case NORTHERNHARRIER:
			displayQuestion(northernHarrierQuestions, northernHarrierAnswers, NUMBER_NH_QUESTIONS);
			break;
		}
	}
	
	/**
	 * displayQuestion()
	 * Generates a random number to select a random question.
	 * Displays the question with the appropriate answer choices for that question.
	 * Checks the answer against the correct one.
	 */
	public void displayQuestion(String[] birdQuestions, String[][] birdAnswers, int numQuestions) {
		// Generates a random number based on the number of possible questions
		int randQuestionNum = (int) (Math.random() * ((numQuestions - 1) + 1));

		// Creates an array with the two Strings representing the answer choices for this particular question
		String[] options = { birdAnswers[randQuestionNum][CHOICE_A], birdAnswers[randQuestionNum][CHOICE_B] };

		JPanel panel = new JPanel();
		String questionText = birdQuestions[randQuestionNum];

		// Creates text on multiple lines that displays the question and instructions on how to choose an answer
		JLabel label = new JLabel();
		label.setText("<html><body><font size=\"+2\">" + questionText + "</font><p>"
				+ "Press TAB to switch between the answer choices"
				+ "<p>Press SPACE to select your answer</body></html>");

		panel.add(label);

		String choiceA = birdAnswers[randQuestionNum][CHOICE_A]; // text on the left button
		String choiceB = birdAnswers[randQuestionNum][CHOICE_B]; // text on the right button

		// Collects the numerical representation of the button the user clicks
		int result = JOptionPane.showOptionDialog(null, panel, "Question", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, null);

		// Checks the validity of the answer
		if (result == LEFT_BUTTON && choiceA.equals(birdAnswers[randQuestionNum][CORRECT_ANSWER])) {
			JOptionPane.showMessageDialog(null, "Correct!");
			isCorrect = true;
		} else if (result == RIGHT_BUTTON && choiceB.equals(birdAnswers[randQuestionNum][CORRECT_ANSWER])) {
			JOptionPane.showMessageDialog(null, "Correct!");
			isCorrect = true;
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect");
			isCorrect = false;
		}
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}



