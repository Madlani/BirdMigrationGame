package gamePackage;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Question {
	private String[] questionArray;
	private Answer[][] answers;
	private final int NUMBER_OF_QUESTIONS = 2;
	
	private Answer[] question1Answers;
	private Answer[] question2Answers;
	
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
		
		question1Answers = new Answer[] {new Answer(true, "answer 1", KeyEvent.VK_RIGHT),
										   new Answer(false, "answer 2", KeyEvent.VK_LEFT)};
		
		question2Answers = new Answer[] {new Answer(false, "answer 1", KeyEvent.VK_RIGHT),
				 						   new Answer(true, "answer 2", KeyEvent.VK_LEFT)};
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
	
	public static void main(String[] args) {
		Question q = new Question();
	}
}



