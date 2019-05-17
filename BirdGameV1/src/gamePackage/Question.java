package gamePackage;

import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Question {
	private String[] questionArray;
	private Answer[][] answers;
	private final int NUMBER_OF_QUESTIONS = 3;
	private boolean isCorrect;
	
	private Answer[] question1Answers;
	private Answer[] question2Answers;
	
	private int[] correspondingAnswer;
	
	public Question() {
		setQuestions();
		setAnswers();
	}
	
	/**
	 * setQuestions()
	 * This method initializes the questionArray to the correct size and sets all possible answer 
	 * choices for each question.
	 */
	public void setQuestions() {
		setQuestionArray(new String[NUMBER_OF_QUESTIONS]);
		questionArray[0] = "Does an osprey migrate?";
		questionArray[1] = "Does the northern harrier migrate?";
		questionArray[2] = "Question #3";
		
		question1Answers = new Answer[] {new Answer(true, "Yes", KeyEvent.VK_RIGHT),
										   new Answer(false, "No", KeyEvent.VK_LEFT)};
		
		question2Answers = new Answer[] {new Answer(false, "answer 1", KeyEvent.VK_RIGHT),
				 						   new Answer(true, "answer 2", KeyEvent.VK_LEFT)};
		
		correspondingAnswer = new int[NUMBER_OF_QUESTIONS];
		correspondingAnswer[0] = 1;
		correspondingAnswer[1] = 0;
		correspondingAnswer[2] = 1;
	}
	
	public void setAnswers() {
		answers = new Answer[][] {question1Answers, question2Answers};
	}
	
	public String getCorrectAnswer(Answer[] questionAnswers) {
		for(Answer a : questionAnswers) {
			if(a.getCorrectness() == true) {
				return a.getAnswer();
			}
		}
		
		return "No correct answer";
	}

	public String[] getQuestionArray() {
		return questionArray;
	}

	public void setQuestionArray(String[] questionArray) {
		this.questionArray = questionArray;
	}

	public Answer[][] getAnswers() {
		return answers;
	}

	public void setAnswers(Answer[][] answers) {
		this.answers = answers;
	}
	
	public int displayQuestion() {
		int randQuestionNum = (int)(Math.random()*((NUMBER_OF_QUESTIONS - 1) + 1));
//		System.out.println("RandNumber: " + randQuestionNum);
//		JButton rightBtn = new JButton("RIGHT");
//		JButton leftBtn = new JButton("LEFT");
//		
//		rightBtn.setMnemonic('R');
//		rightBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane pane = getOptionPane((JComponent)e.getSource());
//                pane.setValue(JOptionPane.YES_OPTION);
//            }
//        });
//        leftBtn.setMnemonic(KeyEvent.VK_LEFT);
//        leftBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane pane = getOptionPane((JComponent)e.getSource());
//                pane.setValue(JOptionPane.NO_OPTION);
//            }
//        });
		//int option = JOptionPane.showConfirmDialog(null, questionArray[randQuestionNum], "Question Cloud", JOptionPane.YES_NO_OPTION);
		
//		JButton[] buttonsArray = new JButton[] {rightBtn, leftBtn};
//		int option = JOptionPane.showOptionDialog(
//                null, 
//                questionArray[randQuestionNum], 
//                "Question Cloud", 
//                JOptionPane.YES_NO_OPTION, 
//                JOptionPane.WARNING_MESSAGE, 
//                null, buttonsArray, null);
		
		JOptionPane optionPane = new JOptionPane(questionArray[randQuestionNum], JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
	    JDialog dialog = optionPane.createDialog("Confirm Dialog");
	    Set<AWTKeyStroke> focusTraversalKeys = new HashSet<AWTKeyStroke>(dialog.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
	    focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED));
	    focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_LEFT, KeyEvent.VK_UNDEFINED));
	    dialog.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, focusTraversalKeys);
	    dialog.setVisible(true);
	    dialog.dispose();
	    int option = (Integer) optionPane.getValue();
	    
	    if (option != correspondingAnswer[randQuestionNum]) {
	    	JOptionPane.showMessageDialog(null, "Correct!");
	    	isCorrect = true;
	    	
	    } else {
	    	JOptionPane.showMessageDialog(null, "Not Correct!");
	    }
		
		return option;
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
	
	//public static void main(String[] args) {
		
		// The following is a proof of concept: we are able to track the user's keyboard input and then 
		// look up the answer based on what the user clicked. We need to link the user's keyboard clicks
		// to an integer (using KeyEvent or key bindings) and then access that point in the array. The 
		// first array index in the 2D array is the question that was asked. The value of the first index
		// is from 0 to the final int declared above: NUMBER_OF_QUESTIONS.
		
//		Question q = new Question();
//		Scanner s = new Scanner(System.in);
//		int userInput = s.nextInt();
//		System.out.println(q.answers[0][userInput]);
		
//	}
}



